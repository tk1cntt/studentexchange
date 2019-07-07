package vn.studentexchange.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import vn.studentexchange.domain.User;
import vn.studentexchange.domain.enumeration.PaymentMethod;
import vn.studentexchange.domain.enumeration.PaymentStatusType;
import vn.studentexchange.domain.enumeration.PaymentType;
import vn.studentexchange.repository.UserRepository;
import vn.studentexchange.security.AuthoritiesConstants;
import vn.studentexchange.security.SecurityUtils;
import vn.studentexchange.service.PaymentService;
import vn.studentexchange.service.UserBalanceService;
import vn.studentexchange.service.dto.PaymentDTO;
import vn.studentexchange.service.dto.UserBalanceDTO;
import vn.studentexchange.web.rest.errors.BadRequestAlertException;
import vn.studentexchange.web.rest.util.HeaderUtil;
import vn.studentexchange.web.rest.util.Utils;

import java.net.URISyntaxException;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing UserBalance.
 */
@RestController
@RequestMapping("/api")
public class UserBalanceResource {

    private final Logger log = LoggerFactory.getLogger(UserBalanceResource.class);

    private static final String ENTITY_NAME = "userBalance";

    private final UserBalanceService userBalanceService;

    private final UserRepository userRepository;

    private final PaymentService paymentService;

    public UserBalanceResource(UserBalanceService userBalanceService, UserRepository userRepository, PaymentService paymentService) {
        this.userBalanceService = userBalanceService;
        this.userRepository = userRepository;
        this.paymentService = paymentService;
    }

    /**
     * POST  /user-balances : Create a new userBalance.
     *
     * @param userBalanceDTO the userBalanceDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new userBalanceDTO, or with status 400 (Bad Request) if the userBalance has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    /*
    @PostMapping("/user-balances")
    @Timed
    @Secured(AuthoritiesConstants.MANAGER)
    public ResponseEntity<UserBalanceDTO> createUserBalance(@RequestBody UserBalanceDTO userBalanceDTO) throws URISyntaxException {
        log.debug("REST request to save UserBalance : {}", userBalanceDTO);
        if (userBalanceDTO.getId() != null) {
            throw new BadRequestAlertException("A new userBalance cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UserBalanceDTO result = userBalanceService.save(userBalanceDTO);
        return ResponseEntity.created(new URI("/api/user-balances/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }
    //*/

    @PostMapping("/user-balances")
    @Timed
    @Secured(AuthoritiesConstants.MANAGER)
    public ResponseEntity<UserBalanceDTO> createUserBalance(@RequestBody UserBalanceDTO userBalanceDTO) throws URISyntaxException {
        log.debug("REST request to save UserBalance : {}", userBalanceDTO);
        Optional<User> paymentUser = userRepository.findOneByLogin(userBalanceDTO.getCreateByLogin());
        if (!paymentUser.isPresent()) {
            throw new BadRequestAlertException("User not found", ENTITY_NAME, "idexists");
        }
        Optional<UserBalanceDTO> dto = userBalanceService.findByOwner(userBalanceDTO.getCreateByLogin());
        UserBalanceDTO currentBalance;
        if (dto.isPresent()) {
            currentBalance = dto.get();
            currentBalance.setUpdateAt(Instant.now());
        } else {
            currentBalance = new UserBalanceDTO();
            currentBalance.setCreateAt(Instant.now());
            currentBalance.setCreateById(paymentUser.get().getId());
            currentBalance.setCreateByLogin(paymentUser.get().getLogin());
        }
        currentBalance.setBalanceAvailable(currentBalance.getBalanceAvailable() + userBalanceDTO.getCash());
        currentBalance.setCash(currentBalance.getCash() + userBalanceDTO.getCash());
        UserBalanceDTO result = userBalanceService.save(currentBalance);
        // Add to payment history
        Optional<User> manager = userRepository.findOneByLogin(SecurityUtils.getCurrentUserLogin().get());
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setCode(Utils.generateNumber());
        paymentDTO.setAmount(userBalanceDTO.getCash());
        paymentDTO.setNote("Nạp tiền vào tài khoản");
        paymentDTO.setMethod(PaymentMethod.BANK_TRANSFER);
        paymentDTO.setType(PaymentType.DEPOSIT);
        paymentDTO.setNewBalance(currentBalance.getCash());
        paymentDTO.setCreateAt(Instant.now());
        paymentDTO.setCreateById(manager.get().getId());
        paymentDTO.setCreateByLogin(manager.get().getLogin());
        paymentDTO.setCustomerId(paymentUser.get().getId());
        paymentDTO.setCustomerLogin(paymentUser.get().getLogin());
        paymentDTO.setStatus(PaymentStatusType.PAID);
        paymentService.save(paymentDTO);
        return ResponseEntity.ok().body(result);
    }

    /**
     * PUT  /user-balances : Updates an existing userBalance.
     *
     * @param userBalanceDTO the userBalanceDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated userBalanceDTO,
     * or with status 400 (Bad Request) if the userBalanceDTO is not valid,
     * or with status 500 (Internal Server Error) if the userBalanceDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/user-balances")
    @Timed
    @Secured(AuthoritiesConstants.MANAGER)
    public ResponseEntity<UserBalanceDTO> updateUserBalance(@RequestBody UserBalanceDTO userBalanceDTO) throws URISyntaxException {
        log.debug("REST request to update UserBalance : {}", userBalanceDTO);
        if (userBalanceDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        UserBalanceDTO result = userBalanceService.save(userBalanceDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, userBalanceDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /user-balances : get all the userBalances.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of userBalances in body
     */
    @GetMapping("/user-balances")
    @Timed
    @Secured(AuthoritiesConstants.MANAGER)
    public List<UserBalanceDTO> getAllUserBalances() {
        log.debug("REST request to get all UserBalances");
        return userBalanceService.findAll();
    }

    @GetMapping("/user-balances/owner")
    @Timed
    public ResponseEntity<UserBalanceDTO> getOwnerUserBalances() {
        log.debug("REST request to get all UserBalances");
        String username = SecurityUtils.getCurrentUserLogin().get();
        Optional<UserBalanceDTO> dto = userBalanceService.findByOwner(username);
        if (dto.isPresent()) {
            return ResponseUtil.wrapOrNotFound(dto);
        } else {
            UserBalanceDTO emptyBalance = new UserBalanceDTO();
            // emptyBalance.setBalanceAvailable(0f);
            // emptyBalance.setBalanceFreezing(0f);
            // emptyBalance.setCash(0f);
            return ResponseEntity.ok()
                .body(emptyBalance);
        }
    }

    /**
     * GET  /user-balances/:id : get the "id" userBalance.
     *
     * @param id the id of the userBalanceDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the userBalanceDTO, or with status 404 (Not Found)
     */
    @GetMapping("/user-balances/{id}")
    @Timed
    @Secured(AuthoritiesConstants.MANAGER)
    public ResponseEntity<UserBalanceDTO> getUserBalance(@PathVariable Long id) {
        log.debug("REST request to get UserBalance : {}", id);
        Optional<UserBalanceDTO> userBalanceDTO = userBalanceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(userBalanceDTO);
    }

    /**
     * DELETE  /user-balances/:id : delete the "id" userBalance.
     *
     * @param id the id of the userBalanceDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/user-balances/{id}")
    @Timed
    @Secured(AuthoritiesConstants.MANAGER)
    public ResponseEntity<Void> deleteUserBalance(@PathVariable Long id) {
        log.debug("REST request to delete UserBalance : {}", id);
        userBalanceService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
