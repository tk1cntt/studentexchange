package vn.studentexchange.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.jhipster.web.util.ResponseUtil;
import liquibase.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.studentexchange.security.SecurityUtils;
import vn.studentexchange.service.CurrencyRateService;
import vn.studentexchange.service.OrderCartService;
import vn.studentexchange.service.ShoppingCartService;
import vn.studentexchange.service.UserShippingAddressService;
import vn.studentexchange.service.dto.OrderCartDTO;
import vn.studentexchange.service.dto.OrderDTO;
import vn.studentexchange.service.dto.ShoppingCartDTO;
import vn.studentexchange.service.dto.UserShippingAddressDTO;
import vn.studentexchange.service.mapper.OrderCartMapper;
import vn.studentexchange.web.rest.errors.BadRequestAlertException;
import vn.studentexchange.web.rest.util.HeaderUtil;
import vn.studentexchange.web.rest.util.PaginationUtil;
import vn.studentexchange.web.rest.util.Utils;

import java.net.URISyntaxException;
import java.time.Instant;
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

    @Autowired
    private OrderCartMapper orderCartMapper;

    @Autowired
    private CurrencyRateService currencyRateService;

    public OrderCartResource(OrderCartService orderCartService, UserShippingAddressService userShippingAddressService, ShoppingCartService shoppingCartService) {
        this.orderCartService = orderCartService;
        this.userShippingAddressService = userShippingAddressService;
        this.shoppingCartService = shoppingCartService;
    }

    /**
     * POST  /order-carts : Create a new orderCart.
     *
     * @param requestOrder the orderCartDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new orderCartDTO, or with status 400 (Bad Request) if the orderCart has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/order-carts")
    @Timed
    /*
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
    */
    //*
    public ResponseEntity<List<OrderCartDTO>> createOrderCart(@RequestBody OrderDTO requestOrder) throws URISyntaxException {
        log.debug("REST request to save OrderCart : {}", requestOrder);
        long userShippingAddressId = Utils.decodeId(requestOrder.getUserShippingAddressId());
        Optional<UserShippingAddressDTO> shippingAddressDTO = userShippingAddressService.findOne(userShippingAddressId);
        List<ShoppingCartDTO> shoppingCarts = new ArrayList<>();
        if (StringUtils.isEmpty(requestOrder.getShopid())) {
            String username = SecurityUtils.getCurrentUserLogin().get();
            shoppingCarts = shoppingCartService.findByOwner(username);
        } else {
            long shopId = Utils.decodeId(requestOrder.getShopid());
            Optional<ShoppingCartDTO> shoppingCartDTO = shoppingCartService.findOne(shopId);
            if (shoppingCartDTO.isPresent()) {
                shoppingCarts.add(shoppingCartDTO.get());
            }
        }
        StringBuilder address = new StringBuilder();
        address.append(shippingAddressDTO.get().getAddress());
        address.append(" - ");
        address.append(shippingAddressDTO.get().getDistrictType()).append(" ").append(shippingAddressDTO.get().getDistrictName());
        address.append(" - ");
        address.append(shippingAddressDTO.get().getCityName());
        ObjectMapper mapper = new ObjectMapper();
        List<OrderCartDTO> orderCarts = new ArrayList<>();
        for (ShoppingCartDTO shoppingCartDTO: shoppingCarts) {
                shoppingCartDTO = Utils.calculate(shoppingCartDTO, currencyRateService);
                log.debug(orderCartMapper.toOrderCart(shoppingCartDTO).toString());
                OrderCartDTO orderCartDTO = orderCartMapper.toOrderCart(shoppingCartDTO);
                orderCartDTO.setId(null); // clear shopping id
                orderCartDTO.setCode(Utils.generateNumber());
                float finalAmount = orderCartDTO.getTotalAmount() + orderCartDTO.getServiceFee() + orderCartDTO.getTallyFee();
                orderCartDTO.setDepositRatio(0.7f);
                orderCartDTO.setDepositAmount((float) Math.ceil(finalAmount * 0.7));
                orderCartDTO.setDepositTime(Instant.now());
                orderCartDTO.setReceiverAddress(address.toString());
                orderCartDTO.setReceiverMobile(shippingAddressDTO.get().getMobile());
                orderCartDTO.setReceiverName(shippingAddressDTO.get().getName());
                orderCartDTO.setReceiverNote(shippingAddressDTO.get().getNote());
                orderCartService.save(orderCartDTO);
                shoppingCartService.delete(shoppingCartDTO.getId());
                orderCarts.add(orderCartDTO);
        }
        return ResponseEntity.ok().body(orderCarts);
    }
    //*/

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

    @GetMapping("/order-carts/owner")
    @Timed
    public ResponseEntity<List<OrderCartDTO>> getOwnerShoppingCarts(Pageable pageable) {
        log.debug("REST request to get owner ShoppingCarts");
        String username = SecurityUtils.getCurrentUserLogin().get();
        Page<OrderCartDTO> page = orderCartService.findByOwner(username, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/order-carts/owner");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/order-carts/owner/{id}")
    @Timed
    public List<OrderCartDTO> getOwnerShoppingCartById(@PathVariable Long id) {
        log.debug("REST request to get owner ShoppingCarts");
        Optional<OrderCartDTO> dto = orderCartService.findOne(id);
        if (!dto.isPresent()) {
            return new ArrayList<>();
        }
        // OrderCartDTO currentCart = Utils.calculate(dto.get(), currencyRateService);
        List<OrderCartDTO> carts = new ArrayList<>();
        carts.add(dto.get());
        return carts;
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
