package vn.studentexchange.web.rest;

import com.codahale.metrics.annotation.Timed;
import org.checkerframework.checker.units.qual.A;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.util.StringUtils;
import vn.studentexchange.domain.OrderComment;
import vn.studentexchange.domain.OrderTransaction;
import vn.studentexchange.domain.User;
import vn.studentexchange.domain.enumeration.*;
import vn.studentexchange.repository.UserRepository;
import vn.studentexchange.security.AuthoritiesConstants;
import vn.studentexchange.security.SecurityUtils;
import vn.studentexchange.service.*;
import vn.studentexchange.service.dto.*;
import vn.studentexchange.service.mapper.OrderCartMapper;
import vn.studentexchange.service.mapper.OrderItemMapper;
import vn.studentexchange.web.rest.errors.BadRequestAlertException;
import vn.studentexchange.web.rest.util.HeaderUtil;
import vn.studentexchange.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.studentexchange.web.rest.util.Utils;

import java.net.URI;
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

    private final OrderCartQueryService orderCartQueryService;

    private final UserShippingAddressService userShippingAddressService;

    private final ShoppingCartService shoppingCartService;

    @Autowired
    private OrderCartMapper orderCartMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private CurrencyRateService currencyRateService;

    @Autowired
    private OrderTransactionService orderTransactionService;

    @Autowired
    private UserBalanceService userBalanceService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private OrderCommentService orderCommentService;

    @Autowired
    private OrderHistoryService orderHistoryService;

    @Autowired
    private UserRepository userRepository;

    public OrderCartResource(OrderCartService orderCartService, OrderCartQueryService orderCartQueryService, UserShippingAddressService userShippingAddressService, ShoppingCartService shoppingCartService) {
        this.orderCartService = orderCartService;
        this.orderCartQueryService = orderCartQueryService;
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
    @Secured(AuthoritiesConstants.USER)
    public ResponseEntity<List<OrderCartDTO>> createOrderCart(@RequestBody OrderDTO requestOrder) throws URISyntaxException {
        log.debug("REST request to save OrderCart : {}", requestOrder);
        long userShippingAddressId = Utils.decodeId(requestOrder.getUserShippingAddressId());
        List<ShoppingCartDTO> shoppingCarts = new ArrayList<>();
        String username = SecurityUtils.getCurrentUserLogin().get();
        if (StringUtils.isEmpty(requestOrder.getShopid())) {
            shoppingCarts = shoppingCartService.findByOwner(username);
        } else {
            long shopId = Utils.decodeId(requestOrder.getShopid());
            Optional<ShoppingCartDTO> shoppingCartDTO = shoppingCartService.findOne(shopId);
            if (shoppingCartDTO.isPresent()) {
                ShoppingCartDTO item = shoppingCartDTO.get();
                if (item.getCreateByLogin().equals(SecurityUtils.getCurrentUserLogin().get())) {
                    shoppingCarts.add(shoppingCartDTO.get());
                } else {
                    throw new BadRequestAlertException("Wrong cart id", ENTITY_NAME, "wrongid");
                }
            } else {
                throw new BadRequestAlertException("Wrong cart id", ENTITY_NAME, "wrongid");
            }
        }
        float depositRatio = 0.7f; // Get from where?
        Optional<UserShippingAddressDTO> shippingAddressDTO = userShippingAddressService.findOne(userShippingAddressId);
        List<OrderCartDTO> orderCarts = new ArrayList<>();
        Optional<CurrencyRateDTO> rate =  currencyRateService.findByCurrency(CurrencyType.CNY);
        for (ShoppingCartDTO shoppingCartDTO : shoppingCarts) {
            shoppingCartDTO = Utils.calculate(shoppingCartDTO, rate.get());
            OrderCartDTO orderCartDTO = orderCartMapper.toOrderCartDto(shoppingCartDTO);
            long orderCode = Utils.generateNumber();
            float finalAmount = orderCartDTO.getTotalAmount() + orderCartDTO.getServiceFee() + orderCartDTO.getTallyFee();
            float depositMount = (float) Math.ceil(finalAmount * depositRatio);
            orderCartDTO = addOrderCart(depositRatio, shippingAddressDTO, rate, orderCartDTO, orderCode, depositMount);
            for (ShoppingCartItemDTO item : shoppingCartDTO.getItems()) {
                addOrderItem(orderCartDTO, item);
            }
            float currentBalance = paymentUserBalance(username, depositMount);
            addOrderTransaction(orderCartDTO,"Đặt cọc cho đơn hàng " + orderCode, orderCode, depositMount, OrderTransactionType.DEPOSIT);
            addHistory(orderCartDTO, "Đã đặt cọc", OrderStatus.DEPOSITED);
            addComment(orderCartDTO.getId(), "owner", "Đã đặt cọc đơn hàng này");
            addPayment(orderCartDTO, "Đặt cọc cho đơn hàng " + orderCode, orderCode, depositMount, currentBalance, PaymentType.ORDER_PAYMENT);
            // Delete item at shopping cart
            shoppingCartService.delete(shoppingCartDTO.getId());
            orderCarts.add(orderCartDTO);
        }
        return ResponseEntity.ok().body(orderCarts);
    }

    private void addHistory(OrderCartDTO orderCartDTO, String description, OrderStatus status) {
        OrderHistoryDTO historyDTO = orderCartMapper.toOrderHistoryDTO(orderCartDTO);
        historyDTO.setOrderCartId(orderCartDTO.getId());
        historyDTO.setDescription(description);
        historyDTO.setStatus(status);
        historyDTO.setCreateAt(Instant.now());
        orderHistoryService.save(historyDTO);
    }

    private void addComment(Long orderId, String sender, String message) {
        OrderCommentDTO comment = new OrderCommentDTO();
        comment.setOrderCartId(orderId);
        comment.setCreateAt(Instant.now());
        comment.setSender(sender);
        comment.setType(CommentType.SYSTEM_LOG);
        comment.setMessage(message);
        orderCommentService.save(comment);
    }

    private void addPayment(OrderCartDTO orderCartDTO, String note, long orderCode, float depositMount, float currentBalance, PaymentType paymentType) {
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setCode(Utils.generateNumber());
        paymentDTO.setAmount(depositMount);
        paymentDTO.setOrderCode(orderCode + "");
        paymentDTO.setNote(note);
        paymentDTO.setMethod(PaymentMethod.CASH);
        paymentDTO.setType(paymentType);
        paymentDTO.setNewBalance(currentBalance);
        paymentDTO.setCreateAt(Instant.now());
        paymentDTO.setCreateById(orderCartDTO.getCreateById());
        paymentDTO.setCreateByLogin(orderCartDTO.getCreateByLogin());
        paymentDTO.setCustomerId(orderCartDTO.getCreateById());
        paymentDTO.setCustomerLogin(orderCartDTO.getCreateByLogin());
        paymentDTO.setStatus(PaymentStatusType.PAID);
        paymentService.save(paymentDTO);
    }

    private OrderCartDTO addOrderCart(float depositRatio, Optional<UserShippingAddressDTO> shippingAddressDTO, Optional<CurrencyRateDTO> rate, OrderCartDTO orderCartDTO, long orderCode, float depositMount) {
        orderCartDTO.setId(null); // clear shopping id
        orderCartDTO.setCode(orderCode);
        orderCartDTO.setRate(rate.get().getRate());
        orderCartDTO.setDepositRatio(depositRatio);
        orderCartDTO.setDepositAmount(depositMount);
        orderCartDTO.setDepositTime(Instant.now());
        orderCartDTO.setStatus(OrderStatus.DEPOSITED);
        orderCartDTO.setReceiverAddress(getShippingAddress(shippingAddressDTO));
        orderCartDTO.setReceiverMobile(shippingAddressDTO.get().getMobile());
        orderCartDTO.setReceiverName(shippingAddressDTO.get().getName());
        orderCartDTO.setReceiverNote(shippingAddressDTO.get().getNote());
        orderCartDTO = orderCartService.save(orderCartDTO);
        return orderCartDTO;
    }

    private void addOrderItem(OrderCartDTO orderCartDTO, ShoppingCartItemDTO item) {
        OrderItemDTO orderItemDTO = orderItemMapper.toOrderItemDto(item);
        orderItemDTO.setCreateAt(Instant.now());
        orderItemDTO.setOrderCartId(orderCartDTO.getId());
        orderItemService.save(SecurityUtils.getCurrentUserLogin().get(), orderItemDTO);
    }

    private float paymentUserBalance(String username, float depositMount) {
        Optional<UserBalanceDTO> userBalanceDTO = userBalanceService.findByOwner(username);
        if (userBalanceDTO.isPresent()) {
            UserBalanceDTO balance = userBalanceDTO.get();
            if (depositMount > balance.getBalanceAvailable()) {
                throw new BadRequestAlertException("Out of money", ENTITY_NAME, "outofmoney");
            }
            balance.setCash(balance.getCash() - depositMount);
            balance.setBalanceAvailable(balance.getBalanceAvailable() - depositMount);
            balance.setUpdateAt(Instant.now());
            balance = userBalanceService.save(balance);
            return balance.getBalanceAvailable();
        } else {
            throw new BadRequestAlertException("Out of money", ENTITY_NAME, "outofmoney");
        }
    }

    private float refundUserBalance(String username, float depositMount) {
        Optional<UserBalanceDTO> userBalanceDTO = userBalanceService.findByOwner(username);
        if (userBalanceDTO.isPresent()) {
            UserBalanceDTO balance = userBalanceDTO.get();
            if (depositMount < 0) {
                throw new BadRequestAlertException("Out of money", ENTITY_NAME, "outofmoney");
            }
            balance.setCash(balance.getCash() + depositMount);
            balance.setBalanceAvailable(balance.getBalanceAvailable() + depositMount);
            balance.setUpdateAt(Instant.now());
            balance = userBalanceService.save(balance);
            return balance.getBalanceAvailable();
        } else {
            throw new BadRequestAlertException("Out of money", ENTITY_NAME, "outofmoney");
        }
    }

    private void addOrderTransaction(OrderCartDTO orderCartDTO, String note, long orderCode, float depositMount, OrderTransactionType type ) {
        OrderTransactionDTO orderTransactionDTO = new OrderTransactionDTO();
        orderTransactionDTO.setAmount(depositMount);
        orderTransactionDTO.setOrderCartId(orderCartDTO.getId());
        orderTransactionDTO.setStatus(type);
        orderTransactionDTO.setNote(note);
        orderTransactionDTO.setCreateAt(Instant.now());
        orderTransactionDTO.setCreateById(orderCartDTO.getCreateById());
        orderTransactionDTO.setCreateByLogin(orderCartDTO.getCreateByLogin());
        orderTransactionService.save(orderTransactionDTO);
    }

    private String getShippingAddress(Optional<UserShippingAddressDTO> shippingAddressDTO) {
        StringBuilder address = new StringBuilder();
        address.append(shippingAddressDTO.get().getAddress());
        address.append(" - ");
        address.append(shippingAddressDTO.get().getDistrictType()).append(" ").append(shippingAddressDTO.get().getDistrictName());
        address.append(" - ");
        address.append(shippingAddressDTO.get().getCityName());
        return address.toString();
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

    @PutMapping("/order-carts/buying")
    @Timed
    @Secured(AuthoritiesConstants.STAFF)
    public ResponseEntity<OrderCartDTO> updateOrderCartBuying(@RequestBody OrderCartDTO orderCartDTO) throws URISyntaxException {
        log.debug("REST request to update OrderCart : {}", orderCartDTO);
        if (orderCartDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Optional<User> user = userRepository.findOneByLogin(SecurityUtils.getCurrentUserLogin().get());
        Optional<OrderCartDTO> result = orderCartService.findOne(orderCartDTO.getId());
        result.ifPresent(order -> {
            order.setUpdateAt(Instant.now());
            order.setStatus(OrderStatus.ARE_BUYING);
            order.setBuyerId(user.get().getId());
            order.setBuyerLogin(user.get().getLogin());
            addHistory(orderCartDTO, "Đang mua hàng", OrderStatus.ARE_BUYING);
            addComment(order.getId(), "staff", "Đơn hàng đang được đặt mua");
            orderCartService.save(order);
        });
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, orderCartDTO.getId().toString()))
            .body(result.get());
    }

    @PutMapping("/order-carts/purchased")
    @Timed
    @Secured(AuthoritiesConstants.STAFF)
    public ResponseEntity<OrderCartDTO> updateOrderCartPurchased(@RequestBody OrderCartDTO orderCartDTO) throws URISyntaxException {
        log.debug("REST request to update OrderCart : {}", orderCartDTO);
        if (orderCartDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Optional<User> user = userRepository.findOneByLogin(SecurityUtils.getCurrentUserLogin().get());
        Optional<CurrencyRateDTO> rate =  currencyRateService.findByCurrency(CurrencyType.CNY);
        Optional<OrderCartDTO> result = orderCartService.findOne(orderCartDTO.getId());
        result.ifPresent(order -> {
            order.setShippingChinaCode(orderCartDTO.getShippingChinaCode());
            order.setDomesticShippingChinaFeeNDT(orderCartDTO.getDomesticShippingChinaFeeNDT());
            order.setDomesticShippingChinaFee(orderCartDTO.getDomesticShippingChinaFeeNDT() * rate.get().getRate());
            order.setUpdateAt(Instant.now());
            order.setStatus(OrderStatus.PURCHASED);
            order.setBuyerId(user.get().getId());
            order.setBuyerLogin(user.get().getLogin());
            addHistory(orderCartDTO, "Đã mua hàng", OrderStatus.PURCHASED);
            addComment(order.getId(), "staff", "Đơn hàng đã được đặt mua thành công trên " + order.getWebsite());
            orderCartService.save(order);
        });
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, orderCartDTO.getId().toString()))
            .body(result.get());
    }

    @PutMapping("/order-carts/cancel")
    @Timed
    @Secured(AuthoritiesConstants.STAFF)
    public ResponseEntity<OrderCartDTO> updateOrderCartCancel(@RequestBody OrderCartDTO orderCartDTO) throws URISyntaxException {
        log.debug("REST request to update OrderCart : {}", orderCartDTO);
        if (orderCartDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Optional<User> user = userRepository.findOneByLogin(SecurityUtils.getCurrentUserLogin().get());
        Optional<OrderCartDTO> result = orderCartService.findOne(orderCartDTO.getId());
        result.ifPresent(order -> {
            order.setUpdateAt(Instant.now());
            order.setStatus(OrderStatus.CANCELLED);
            order.setStatusName(orderCartDTO.getStatusName());
            order.setUpdateById(user.get().getId());
            order.setUpdateByLogin(user.get().getLogin());
            float currentBalance = refundUserBalance(order.getCreateByLogin(), order.getDepositAmount());
            addOrderTransaction(order,"Hoàn tiền cho đơn hàng " + order.getCode(), order.getCode(), order.getDepositAmount(), OrderTransactionType.REFUND);
            addHistory(order, "Đã huỷ", OrderStatus.CANCELLED);
            addComment(order.getId(), "staff", "Đã hoàn tiền đặt cọc cho đơn hàng này");
            addPayment(order, "Hoàn tiền cho đơn hàng " + order.getCode(), order.getCode(), order.getDepositAmount(), currentBalance, PaymentType.REFUND);
            orderCartService.save(order);
        });
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, orderCartDTO.getId().toString()))
            .body(result.get());
    }

    /**
     * GET  /order-carts : get all the orderCarts.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of orderCarts in body
     */
    @GetMapping("/order-carts")
    @Timed
    public ResponseEntity<List<OrderCartDTO>> getAllOrderCarts(OrderCartCriteria criteria, Pageable pageable) {
        log.debug("REST request to get OrderCarts by criteria: {}", criteria);
        Page<OrderCartDTO> page = orderCartQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/order-carts");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /order-carts/count : count all the orderCarts.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/order-carts/count")
    @Timed
    public ResponseEntity<Long> countOrderCarts(OrderCartCriteria criteria) {
        log.debug("REST request to count OrderCarts by criteria: {}", criteria);
        return ResponseEntity.ok().body(orderCartQueryService.countByCriteria(criteria));
    }

    @GetMapping("/order-carts/owner")
    @Timed
    @Secured(AuthoritiesConstants.USER)
    public ResponseEntity<List<OrderCartDTO>> getOwnerShoppingCarts(Pageable pageable) {
        log.debug("REST request to get owner ShoppingCarts");
        String username = SecurityUtils.getCurrentUserLogin().get();
        Page<OrderCartDTO> page = orderCartService.findByOwner(username, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/order-carts/owner");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/order-carts/owner/{id}")
    @Timed
    @Secured(AuthoritiesConstants.USER)
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
    @Secured(AuthoritiesConstants.STAFF)
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
    @Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity<Void> deleteOrderCart(@PathVariable Long id) {
        log.debug("REST request to delete OrderCart : {}", id);
        orderCartService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
