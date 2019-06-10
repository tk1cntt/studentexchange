package vn.studentexchange.web.rest;

import com.codahale.metrics.annotation.Timed;
import vn.studentexchange.service.DeliveryPackageService;
import vn.studentexchange.web.rest.errors.BadRequestAlertException;
import vn.studentexchange.web.rest.util.HeaderUtil;
import vn.studentexchange.service.dto.DeliveryPackageDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing DeliveryPackage.
 */
@RestController
@RequestMapping("/api")
public class DeliveryPackageResource {

    private final Logger log = LoggerFactory.getLogger(DeliveryPackageResource.class);

    private static final String ENTITY_NAME = "deliveryPackage";

    private final DeliveryPackageService deliveryPackageService;

    public DeliveryPackageResource(DeliveryPackageService deliveryPackageService) {
        this.deliveryPackageService = deliveryPackageService;
    }

    /**
     * POST  /delivery-packages : Create a new deliveryPackage.
     *
     * @param deliveryPackageDTO the deliveryPackageDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new deliveryPackageDTO, or with status 400 (Bad Request) if the deliveryPackage has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/delivery-packages")
    @Timed
    public ResponseEntity<DeliveryPackageDTO> createDeliveryPackage(@RequestBody DeliveryPackageDTO deliveryPackageDTO) throws URISyntaxException {
        log.debug("REST request to save DeliveryPackage : {}", deliveryPackageDTO);
        if (deliveryPackageDTO.getId() != null) {
            throw new BadRequestAlertException("A new deliveryPackage cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DeliveryPackageDTO result = deliveryPackageService.save(deliveryPackageDTO);
        return ResponseEntity.created(new URI("/api/delivery-packages/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /delivery-packages : Updates an existing deliveryPackage.
     *
     * @param deliveryPackageDTO the deliveryPackageDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated deliveryPackageDTO,
     * or with status 400 (Bad Request) if the deliveryPackageDTO is not valid,
     * or with status 500 (Internal Server Error) if the deliveryPackageDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/delivery-packages")
    @Timed
    public ResponseEntity<DeliveryPackageDTO> updateDeliveryPackage(@RequestBody DeliveryPackageDTO deliveryPackageDTO) throws URISyntaxException {
        log.debug("REST request to update DeliveryPackage : {}", deliveryPackageDTO);
        if (deliveryPackageDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DeliveryPackageDTO result = deliveryPackageService.save(deliveryPackageDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, deliveryPackageDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /delivery-packages : get all the deliveryPackages.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of deliveryPackages in body
     */
    @GetMapping("/delivery-packages")
    @Timed
    public List<DeliveryPackageDTO> getAllDeliveryPackages() {
        log.debug("REST request to get all DeliveryPackages");
        return deliveryPackageService.findAll();
    }

    /**
     * GET  /delivery-packages/:id : get the "id" deliveryPackage.
     *
     * @param id the id of the deliveryPackageDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the deliveryPackageDTO, or with status 404 (Not Found)
     */
    @GetMapping("/delivery-packages/{id}")
    @Timed
    public ResponseEntity<DeliveryPackageDTO> getDeliveryPackage(@PathVariable Long id) {
        log.debug("REST request to get DeliveryPackage : {}", id);
        Optional<DeliveryPackageDTO> deliveryPackageDTO = deliveryPackageService.findOne(id);
        return ResponseUtil.wrapOrNotFound(deliveryPackageDTO);
    }

    /**
     * DELETE  /delivery-packages/:id : delete the "id" deliveryPackage.
     *
     * @param id the id of the deliveryPackageDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/delivery-packages/{id}")
    @Timed
    public ResponseEntity<Void> deleteDeliveryPackage(@PathVariable Long id) {
        log.debug("REST request to delete DeliveryPackage : {}", id);
        deliveryPackageService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
