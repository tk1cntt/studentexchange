package vn.studentexchange.web.rest;

import com.codahale.metrics.annotation.Timed;
import liquibase.util.StringUtils;
import vn.studentexchange.domain.UserShippingAddress;
import vn.studentexchange.security.SecurityUtils;
import vn.studentexchange.service.OrderCartService;
import vn.studentexchange.service.ShoppingCartService;
import vn.studentexchange.service.UserShippingAddressService;
import vn.studentexchange.service.dto.OrderDTO;
import vn.studentexchange.service.dto.ShoppingCartDTO;
import vn.studentexchange.service.dto.UserShippingAddressDTO;
import vn.studentexchange.web.rest.errors.BadRequestAlertException;
import vn.studentexchange.web.rest.util.HeaderUtil;
import vn.studentexchange.service.dto.OrderCartDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.studentexchange.web.rest.util.Utils;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing OrderCart.
 */
@RestController
@RequestMapping("/api")
public class OrderCartResource {

    private final Logger log = LoggerFactory.getLogger(OrderCartResource.class);

    private static final String ENTITY_NAME = "orderCart";

    private final OrderCartService orderCartService;

    private final UserShippingAddressService userShippingAddressService;

    private final ShoppingCartService shoppingCartService;

    public OrderCartResource(OrderCartService orderCartService, UserShippingAddressService userShippingAddressService, ShoppingCartService shoppingCartService) {
        this.orderCartService = orderCartService;
        this.userShippingAddressService = userShippingAddressService;
        this.shoppingCartService = shoppingCartService;
    }

    /**
     * POST  /order-carts : Create a new orderCart.
     *
     * @param orderCartDTO the orderCartDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new orderCartDTO, or with status 400 (Bad Request) if the orderCart has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/order-carts")
    @Timed
    public ResponseEntity<OrderCartDTO> createOrderCart(@RequestBody OrderCartDTO orderCartDTO) throws URISyntaxException {
        log.debug("REST request to save OrderCart : {}", orderCartDTO);
        if (orderCartDTO.getId() != null) {
            throw new BadRequestAlertException("A new orderCart cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OrderCartDTO result = orderCartService.save(orderCartDTO);
        return ResponseEntity.created(new URI("/api/order-carts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }
    /*
    public ResponseEntity<OrderCartDTO> createOrderCart(@RequestBody OrderDTO orderCartDTO) throws URISyntaxException {
        log.debug("REST request to save OrderCart : {}", orderCartDTO);
        long userShippingAddressId = Utils.decodeId(orderCartDTO.getUserShippingAddressId());
        Optional<UserShippingAddressDTO> shippingAddressDTO = userShippingAddressService.findOne(userShippingAddressId);
        List<ShoppingCartDTO> shoppingCarts = new ArrayList<>();
        if (StringUtils.isEmpty(orderCartDTO.getShopid())) {
            String username = SecurityUtils.getCurrentUserLogin().get();
            shoppingCarts = shoppingCartService.findByOwner(username);
        } else {
            long shopId = Utils.decodeId(orderCartDTO.getShopid());
            String username = SecurityUtils.getCurrentUserLogin().get();
            Optional<ShoppingCartDTO> shoppingCartDTO = shoppingCartService.findOne(shopId);
            if (shoppingCartDTO.isPresent()) {
                shoppingCarts.add(shoppingCartDTO.get());
            }
        }
        return ResponseEntity.created(new URI("/api/order-carts/" + result.getId())

            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }
    */

    /**
     * PUT  /order-carts : Updates an existing orderCart.
     *
     * @param orderCartDTO the orderCartDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated orderCartDTO,
     * or with status 400 (Bad Request) if the orderCartDTO is not valid,
     * or with status 500 (Internal Server Error) if the orderCartDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/order-carts")
    @Timed
    public ResponseEntity<OrderCartDTO> updateOrderCart(@RequestBody OrderCartDTO orderCartDTO) throws URISyntaxException {
        log.debug("REST request to update OrderCart : {}", orderCartDTO);
        if (orderCartDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        OrderCartDTO result = orderCartService.save(orderCartDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, orderCartDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /order-carts : get all the orderCarts.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of orderCarts in body
     */
    @GetMapping("/order-carts")
    @Timed
    public List<OrderCartDTO> getAllOrderCarts() {
        log.debug("REST request to get all OrderCarts");
        return orderCartService.findAll();
    }

    /**
     * GET  /order-carts/:id : get the "id" orderCart.
     *
     * @param id the id of the orderCartDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the orderCartDTO, or with status 404 (Not Found)
     */
    @GetMapping("/order-carts/{id}")
    @Timed
    public ResponseEntity<OrderCartDTO> getOrderCart(@PathVariable Long id) {
        log.debug("REST request to get OrderCart : {}", id);
        Optional<OrderCartDTO> orderCartDTO = orderCartService.findOne(id);
        return ResponseUtil.wrapOrNotFound(orderCartDTO);
    }

    /**
     * DELETE  /order-carts/:id : delete the "id" orderCart.
     *
     * @param id the id of the orderCartDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/order-carts/{id}")
    @Timed
    public ResponseEntity<Void> deleteOrderCart(@PathVariable Long id) {
        log.debug("REST request to delete OrderCart : {}", id);
        orderCartService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
