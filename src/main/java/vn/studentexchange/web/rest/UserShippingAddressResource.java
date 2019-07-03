package vn.studentexchange.web.rest;

import com.codahale.metrics.annotation.Timed;

import vn.studentexchange.security.SecurityUtils;
import vn.studentexchange.service.UserShippingAddressService;
import vn.studentexchange.web.rest.errors.BadRequestAlertException;
import vn.studentexchange.web.rest.util.HeaderUtil;
import vn.studentexchange.web.rest.util.PaginationUtil;
import vn.studentexchange.service.dto.UserShippingAddressDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing UserShippingAddress.
 */
@RestController
@RequestMapping("/api")
public class UserShippingAddressResource {

    private final Logger log = LoggerFactory.getLogger(UserShippingAddressResource.class);

    private static final String ENTITY_NAME = "userShippingAddress";

    private final UserShippingAddressService userShippingAddressService;

    public UserShippingAddressResource(UserShippingAddressService userShippingAddressService) {
        this.userShippingAddressService = userShippingAddressService;
    }

    /**
     * POST  /user-shipping-addresses : Create a new userShippingAddress.
     *
     * @param userShippingAddressDTO the userShippingAddressDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new userShippingAddressDTO, or with status 400 (Bad Request) if the userShippingAddress has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/user-shipping-addresses")
    @Timed
    public ResponseEntity<UserShippingAddressDTO> createUserShippingAddress(@RequestBody UserShippingAddressDTO userShippingAddressDTO) throws URISyntaxException {
        log.debug("REST request to save UserShippingAddress : {}", userShippingAddressDTO);
        if (userShippingAddressDTO.getId() != null) {
            throw new BadRequestAlertException("A new userShippingAddress cannot already have an ID", ENTITY_NAME, "idexists");
        }
        userShippingAddressDTO.setCreateByLogin(SecurityUtils.getCurrentUserLogin().get());
        userShippingAddressDTO.setCreateAt(Instant.now());
        UserShippingAddressDTO result = userShippingAddressService.save(userShippingAddressDTO);
        return ResponseEntity.created(new URI("/api/user-shipping-addresses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /user-shipping-addresses : Updates an existing userShippingAddress.
     *
     * @param userShippingAddressDTO the userShippingAddressDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated userShippingAddressDTO,
     * or with status 400 (Bad Request) if the userShippingAddressDTO is not valid,
     * or with status 500 (Internal Server Error) if the userShippingAddressDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/user-shipping-addresses")
    @Timed
    public ResponseEntity<UserShippingAddressDTO> updateUserShippingAddress(@RequestBody UserShippingAddressDTO userShippingAddressDTO) throws URISyntaxException {
        log.debug("REST request to update UserShippingAddress : {}", userShippingAddressDTO);
        if (userShippingAddressDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        userShippingAddressDTO.setUpdateByLogin(SecurityUtils.getCurrentUserLogin().get());
        userShippingAddressDTO.setUpdateAt(Instant.now());
        UserShippingAddressDTO result = userShippingAddressService.save(userShippingAddressDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, userShippingAddressDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /user-shipping-addresses : get all the userShippingAddresses.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of userShippingAddresses in body
     */
    @GetMapping("/user-shipping-addresses")
    @Timed
    public List<UserShippingAddressDTO> getAllUserShippingAddresses() {
        log.debug("REST request to get all UserShippingAddresses");
        return userShippingAddressService.findAll();
    }

    @GetMapping("/user-shipping-addresses/owner")
    @Timed
    public List<UserShippingAddressDTO> getOwnerUserShippingAddresses() {
        log.debug("REST request to get owner UserShippingAddresses");
        return userShippingAddressService.findByOwner();
    }

    /**
     * GET  /user-shipping-addresses/:id : get the "id" userShippingAddress.
     *
     * @param id the id of the userShippingAddressDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the userShippingAddressDTO, or with status 404 (Not Found)
     */
    @GetMapping("/user-shipping-addresses/{id}")
    @Timed
    public ResponseEntity<UserShippingAddressDTO> getUserShippingAddress(@PathVariable Long id) {
        log.debug("REST request to get UserShippingAddress : {}", id);
        Optional<UserShippingAddressDTO> userShippingAddressDTO = userShippingAddressService.findOne(id);
        return ResponseUtil.wrapOrNotFound(userShippingAddressDTO);
    }

    /**
     * DELETE  /user-shipping-addresses/:id : delete the "id" userShippingAddress.
     *
     * @param id the id of the userShippingAddressDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/user-shipping-addresses/{id}")
    @Timed
    public ResponseEntity<Void> deleteUserShippingAddress(@PathVariable Long id) {
        log.debug("REST request to delete UserShippingAddress : {}", id);
        userShippingAddressService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
