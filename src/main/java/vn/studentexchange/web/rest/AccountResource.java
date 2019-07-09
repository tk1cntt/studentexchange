package vn.studentexchange.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.jhipster.web.util.ResponseUtil;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;
import vn.studentexchange.domain.User;
import vn.studentexchange.domain.enumeration.CurrencyType;
import vn.studentexchange.repository.UserRepository;
import vn.studentexchange.security.AuthoritiesConstants;
import vn.studentexchange.security.SecurityUtils;
import vn.studentexchange.security.jwt.JWTFilter;
import vn.studentexchange.security.jwt.TokenProvider;
import vn.studentexchange.service.CurrencyRateService;
import vn.studentexchange.service.MailService;
import vn.studentexchange.service.UserProfileService;
import vn.studentexchange.service.UserService;
import vn.studentexchange.service.dto.*;
import vn.studentexchange.web.rest.errors.*;
import vn.studentexchange.web.rest.util.Utils;
import vn.studentexchange.web.rest.vm.KeyAndPasswordVM;
import vn.studentexchange.web.rest.vm.ManagedUserVM;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * REST controller for managing the current user's account.
 */
@RestController
@RequestMapping("/api")
public class AccountResource {

    private final Logger log = LoggerFactory.getLogger(AccountResource.class);

    private final UserRepository userRepository;

    private final UserService userService;

    private final UserProfileService userProfileService;

    private final MailService mailService;

    private final TokenProvider tokenProvider;

    @Autowired
    private CurrencyRateService currencyRateService;

    public AccountResource(UserRepository userRepository, UserService userService, UserProfileService userProfileService, MailService mailService, TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
        this.userRepository = userRepository;
        this.userService = userService;
        this.userProfileService = userProfileService;
        this.mailService = mailService;
    }

    /**
     * POST  /register : register the user.
     *
     * @param managedUserVM the managed user View Model
     * @throws InvalidPasswordException 400 (Bad Request) if the password is incorrect
     * @throws EmailAlreadyUsedException 400 (Bad Request) if the email is already used
     * @throws LoginAlreadyUsedException 400 (Bad Request) if the login is already used
     */
    @PostMapping("/register")
    @Timed
    @ResponseStatus(HttpStatus.CREATED)
    public void registerAccount(@Valid @RequestBody ManagedUserVM managedUserVM) {
        if (!checkPasswordLength(managedUserVM.getPassword())) {
            throw new InvalidPasswordException();
        }
        User user = userService.registerUser(managedUserVM, managedUserVM.getPassword());
        mailService.sendActivationEmail(user);
    }

    /**
     * GET  /activate : activate the registered user.
     *
     * @param key the activation key
     * @throws RuntimeException 500 (Internal Server Error) if the user couldn't be activated
     */
    @GetMapping("/activate")
    @Timed
    public void activateAccount(@RequestParam(value = "key") String key) {
        Optional<User> user = userService.activateRegistration(key);
        if (!user.isPresent()) {
            throw new InternalServerErrorException("No user was found for this activation key");
        }
    }

    /**
     * GET  /authenticate : check if the user is authenticated, and return its login.
     *
     * @param request the HTTP request
     * @return the login if the user is authenticated
     */
    @GetMapping("/authenticate")
    @Timed
    public String isAuthenticated(HttpServletRequest request) {
        log.debug("REST request to check if the current user is authenticated");
        return request.getRemoteUser();
    }

    @GetMapping("/exchange-rate")
    @Timed
    public ResponseEntity<CurrencyRateDTO> getExchangeRate() {
        log.debug("REST request to check if the current user is authenticated");
        Optional<CurrencyRateDTO> currencyRate = currencyRateService.findByCurrency(CurrencyType.CNY);
        return ResponseUtil.wrapOrNotFound(currencyRate);
    }

    @GetMapping("/token-exchange")
    @Timed
    public ResponseEntity<String> tokenExchange(@RequestParam(value = "code") String code) {
        try {
            log.debug("REST request to check if fb code is authenticated");
            StringBuilder tokenExchangeURL = new StringBuilder("https://graph.accountkit.com/v1.1/access_token?");
            tokenExchangeURL.append("grant_type=authorization_code");
            tokenExchangeURL.append("&code=").append(code);
            tokenExchangeURL.append("&access_token=").append(URLEncoder.encode("AA|504517876723313|a81ddcb2220085e41f9ec7b38bb6fd16"));
            FBAccountDTO fbAccountDTO = getHttpGetResponse(tokenExchangeURL);
            StringBuilder meURL = new StringBuilder("https://graph.accountkit.com/v1.1/me?access_token=");
            meURL.append(fbAccountDTO.getAccessToken());
            meURL.append("&appsecret_proof=");
            meURL.append(Utils.encode("a81ddcb2220085e41f9ec7b38bb6fd16", fbAccountDTO.getAccessToken()));
            fbAccountDTO = getHttpGetResponse(meURL);
            // 2. Create account if ok
            if (fbAccountDTO.getCode() == 200) {
                String username = "0" + fbAccountDTO.getPhone().getNationalNumber();
                Optional<User> user = userService.getUserWithAuthoritiesByLogin(username);
                User currentUser = null;
                if (user.isPresent()) {
                    currentUser = user.get();
                } else {
                    UserDTO userDTO = new UserDTO();
                    userDTO.setLogin(username);
                    Set<String> authorities = new HashSet<>();
                    authorities.add("ROLE_USER");
                    userDTO.setAuthorities(authorities);
                    currentUser = userService.createUser(userDTO);
                    UserProfileDTO newUserProfile = new UserProfileDTO();
                    newUserProfile.setMobile(username);
                    userProfileService.save(newUserProfile, username);
                }
                // Create jwt from user
                List<GrantedAuthority> grantedAuthorities = currentUser.getAuthorities().stream()
                    .map(authority -> new SimpleGrantedAuthority(authority.getName()))
                    .collect(Collectors.toList());
                org.springframework.security.core.userdetails.User principal = new org.springframework.security.core.userdetails.User(username, "", grantedAuthorities);
                Authentication authentication =  new UsernamePasswordAuthenticationToken(principal, null, grantedAuthorities);
                String jwt = tokenProvider.createToken(authentication, true);
                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
                ObjectMapper mapper = new ObjectMapper();
                return new ResponseEntity<>(mapper.writeValueAsString(new UserJWTController.JWTToken(jwt)), httpHeaders, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("{code: 101, message: \"Token is invalid\"}", new HttpHeaders(), HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception ex) {
            log.info("FB Account Kit error: " + ex.getMessage());
        }
        return new ResponseEntity<>("{code: 100, message: \"Code is invalid\"}", new HttpHeaders(), HttpStatus.UNAUTHORIZED);
    }

    private FBAccountDTO getHttpGetResponse(StringBuilder tokenExchangeURL) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(tokenExchangeURL.toString());
        HttpResponse response = client.execute(request);
        String result = IOUtils.toString(response.getEntity().getContent(), "utf-8");
        FBAccountDTO fbAccountDTO = mapper.readValue(result, FBAccountDTO.class);
        fbAccountDTO.setCode(response.getStatusLine().getStatusCode());
        return fbAccountDTO;
    }

    /**
     * GET  /account : get the current user.
     *
     * @return the current user
     * @throws RuntimeException 500 (Internal Server Error) if the user couldn't be returned
     */
    @GetMapping("/account")
    @Timed
    public UserDTO getAccount() {
        return userService.getUserWithAuthorities()
            .map(UserDTO::new)
            .orElseThrow(() -> new InternalServerErrorException("User could not be found"));
    }

    /**
     * POST  /account : update the current user information.
     *
     * @param userDTO the current user information
     * @throws EmailAlreadyUsedException 400 (Bad Request) if the email is already used
     * @throws RuntimeException 500 (Internal Server Error) if the user login wasn't found
     */
    @PostMapping("/account")
    @Timed
    public void saveAccount(@Valid @RequestBody UserDTO userDTO) {
        final String userLogin = SecurityUtils.getCurrentUserLogin().orElseThrow(() -> new InternalServerErrorException("Current user login not found"));
        Optional<User> existingUser = userRepository.findOneByEmailIgnoreCase(userDTO.getEmail());
        if (existingUser.isPresent() && (!existingUser.get().getLogin().equalsIgnoreCase(userLogin))) {
            throw new EmailAlreadyUsedException();
        }
        Optional<User> user = userRepository.findOneByLogin(userLogin);
        if (!user.isPresent()) {
            throw new InternalServerErrorException("User could not be found");
        }
        userService.updateUser(userDTO.getFirstName(), userDTO.getLastName(), userDTO.getEmail(),
            userDTO.getLangKey(), userDTO.getImageUrl());
    }

    /**
     * POST  /account/change-password : changes the current user's password
     *
     * @param passwordChangeDto current and new password
     * @throws InvalidPasswordException 400 (Bad Request) if the new password is incorrect
     */
    @PostMapping(path = "/account/change-password")
    @Timed
    public void changePassword(@RequestBody PasswordChangeDTO passwordChangeDto) {
        if (!checkPasswordLength(passwordChangeDto.getNewPassword())) {
            throw new InvalidPasswordException();
        }
        userService.changePassword(passwordChangeDto.getCurrentPassword(), passwordChangeDto.getNewPassword());
    }

    @PostMapping(path = "/account/temporary-password")
    @Timed
    public ResponseEntity<String> temporaryPassword() {
        if (SecurityUtils.isCurrentUserInRole("ROLE_USER")) {
            return ResponseEntity.ok().body(Utils.encodeId(userService.temporaryPassword()));
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    /**
     * POST   /account/reset-password/init : Send an email to reset the password of the user
     *
     * @param mail the mail of the user
     * @throws EmailNotFoundException 400 (Bad Request) if the email address is not registered
     */
    @PostMapping(path = "/account/reset-password/init")
    @Timed
    public void requestPasswordReset(@RequestBody String mail) {
       mailService.sendPasswordResetMail(
           userService.requestPasswordReset(mail)
               .orElseThrow(EmailNotFoundException::new)
       );
    }

    /**
     * POST   /account/reset-password/finish : Finish to reset the password of the user
     *
     * @param keyAndPassword the generated key and the new password
     * @throws InvalidPasswordException 400 (Bad Request) if the password is incorrect
     * @throws RuntimeException 500 (Internal Server Error) if the password could not be reset
     */
    @PostMapping(path = "/account/reset-password/finish")
    @Timed
    public void finishPasswordReset(@RequestBody KeyAndPasswordVM keyAndPassword) {
        if (!checkPasswordLength(keyAndPassword.getNewPassword())) {
            throw new InvalidPasswordException();
        }
        Optional<User> user =
            userService.completePasswordReset(keyAndPassword.getNewPassword(), keyAndPassword.getKey());

        if (!user.isPresent()) {
            throw new InternalServerErrorException("No user was found for this reset key");
        }
    }

    private static boolean checkPasswordLength(String password) {
        return !StringUtils.isEmpty(password) &&
            password.length() >= ManagedUserVM.PASSWORD_MIN_LENGTH &&
            password.length() <= ManagedUserVM.PASSWORD_MAX_LENGTH;
    }
}
