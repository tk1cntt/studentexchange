package vn.studentexchange.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.studentexchange.security.SecurityUtils;
import vn.studentexchange.service.ShoppingCartItemService;
import vn.studentexchange.service.dto.ShoppingCartItemDTO;
import vn.studentexchange.web.rest.errors.BadRequestAlertException;
import vn.studentexchange.web.rest.util.HeaderUtil;
import vn.studentexchange.web.rest.util.PaginationUtil;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing ShoppingCartItem.
 */
@RestController
@RequestMapping("/api")
public class ShoppingCartItemResource {

    private final Logger log = LoggerFactory.getLogger(ShoppingCartItemResource.class);

    private static final String ENTITY_NAME = "shoppingCartItem";

    private final ShoppingCartItemService shoppingCartItemService;

    public ShoppingCartItemResource(ShoppingCartItemService shoppingCartItemService) {
        this.shoppingCartItemService = shoppingCartItemService;
    }

    /**
     * POST  /shopping-cart-items : Create a new shoppingCartItem.
     *
     * @param shoppingCartItemDTO the shoppingCartItemDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new shoppingCartItemDTO, or with status 400 (Bad Request) if the shoppingCartItem has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/shopping-cart-items")
    @Timed
    public ResponseEntity<ShoppingCartItemDTO> createShoppingCartItem(@RequestBody ShoppingCartItemDTO shoppingCartItemDTO) throws URISyntaxException {
        log.debug("REST request to save ShoppingCartItem : {}", shoppingCartItemDTO);
        if (shoppingCartItemDTO.getId() != null) {
            throw new BadRequestAlertException("A new shoppingCartItem cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ShoppingCartItemDTO result = shoppingCartItemService.save(shoppingCartItemDTO);
        return ResponseEntity.created(new URI("/api/shopping-cart-items/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /shopping-cart-items : Updates an existing shoppingCartItem.
     *
     * @param shoppingCartItemDTO the shoppingCartItemDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated shoppingCartItemDTO,
     * or with status 400 (Bad Request) if the shoppingCartItemDTO is not valid,
     * or with status 500 (Internal Server Error) if the shoppingCartItemDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/shopping-cart-items")
    @Timed
    public ResponseEntity<ShoppingCartItemDTO> updateShoppingCartItem(@RequestBody ShoppingCartItemDTO shoppingCartItemDTO) throws URISyntaxException {
        log.debug("REST request to update ShoppingCartItem : {}", shoppingCartItemDTO);
        if (shoppingCartItemDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ShoppingCartItemDTO result = shoppingCartItemService.save(shoppingCartItemDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, shoppingCartItemDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /shopping-cart-items : get all the shoppingCartItems.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of shoppingCartItems in body
     */
    @GetMapping("/shopping-cart-items")
    @Timed
    public List<ShoppingCartItemDTO> getAllShoppingCartItems() {
        log.debug("REST request to get all ShoppingCartItems");
        return shoppingCartItemService.findAll();
    }

    @GetMapping("/shopping-cart-items/owner")
    @Timed
    public ResponseEntity<List<ShoppingCartItemDTO>> getOwnerShoppingCartItems(Pageable pageable) {
        log.debug("REST request to get owner ShoppingCartItems");
        String username = SecurityUtils.getCurrentUserLogin().get();
        Page<ShoppingCartItemDTO> page = shoppingCartItemService.findByOwner(username, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/shopping-cart-items/owner");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /shopping-cart-items/:id : get the "id" shoppingCartItem.
     *
     * @param id the id of the shoppingCartItemDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the shoppingCartItemDTO, or with status 404 (Not Found)
     */
    @GetMapping("/shopping-cart-items/{id}")
    @Timed
    public ResponseEntity<ShoppingCartItemDTO> getShoppingCartItem(@PathVariable Long id) {
        log.debug("REST request to get ShoppingCartItem : {}", id);
        Optional<ShoppingCartItemDTO> shoppingCartItemDTO = shoppingCartItemService.findOne(id);
        return ResponseUtil.wrapOrNotFound(shoppingCartItemDTO);
    }

    /**
     * DELETE  /shopping-cart-items/:id : delete the "id" shoppingCartItem.
     *
     * @param id the id of the shoppingCartItemDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/shopping-cart-items/{id}")
    @Timed
    public ResponseEntity<Void> deleteShoppingCartItem(@PathVariable Long id) {
        log.debug("REST request to delete ShoppingCartItem : {}", id);
        shoppingCartItemService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
