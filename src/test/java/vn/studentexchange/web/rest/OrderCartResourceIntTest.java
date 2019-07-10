package vn.studentexchange.web.rest;

import vn.studentexchange.StudentexchangeApp;

import vn.studentexchange.domain.OrderCart;
import vn.studentexchange.domain.OrderItem;
import vn.studentexchange.domain.OrderComment;
import vn.studentexchange.domain.OrderHistory;
import vn.studentexchange.domain.OrderPackage;
import vn.studentexchange.domain.OrderTransaction;
import vn.studentexchange.domain.User;
import vn.studentexchange.domain.User;
import vn.studentexchange.domain.User;
import vn.studentexchange.domain.User;
import vn.studentexchange.domain.User;
import vn.studentexchange.domain.User;
import vn.studentexchange.repository.OrderCartRepository;
import vn.studentexchange.service.OrderCartService;
import vn.studentexchange.service.ShoppingCartService;
import vn.studentexchange.service.UserShippingAddressService;
import vn.studentexchange.service.dto.OrderCartDTO;
import vn.studentexchange.service.mapper.OrderCartMapper;
import vn.studentexchange.web.rest.errors.ExceptionTranslator;
import vn.studentexchange.service.dto.OrderCartCriteria;
import vn.studentexchange.service.OrderCartQueryService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;


import static vn.studentexchange.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import vn.studentexchange.domain.enumeration.OrderStatus;
/**
 * Test class for the OrderCartResource REST controller.
 *
 * @see OrderCartResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = StudentexchangeApp.class)
public class OrderCartResourceIntTest {

    private static final Long DEFAULT_CODE = 1L;
    private static final Long UPDATED_CODE = 2L;

    private static final String DEFAULT_SHIPPING_CHINA_CODE = "AAAAAAAAAA";
    private static final String UPDATED_SHIPPING_CHINA_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_AVATAR = "AAAAAAAAAA";
    private static final String UPDATED_AVATAR = "BBBBBBBBBB";

    private static final String DEFAULT_ALIWANGWANG = "AAAAAAAAAA";
    private static final String UPDATED_ALIWANGWANG = "BBBBBBBBBB";

    private static final Float DEFAULT_AMOUNT_DISCOUNT = 1F;
    private static final Float UPDATED_AMOUNT_DISCOUNT = 2F;

    private static final Float DEFAULT_AMOUNT_PAID = 1F;
    private static final Float UPDATED_AMOUNT_PAID = 2F;

    private static final Float DEFAULT_DEPOSIT_AMOUNT = 1F;
    private static final Float UPDATED_DEPOSIT_AMOUNT = 2F;

    private static final Float DEFAULT_DEPOSIT_RATIO = 1F;
    private static final Float UPDATED_DEPOSIT_RATIO = 2F;

    private static final Instant DEFAULT_DEPOSIT_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DEPOSIT_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Float DEFAULT_DOMESTIC_SHIPPING_CHINA_FEE_NDT = 1F;
    private static final Float UPDATED_DOMESTIC_SHIPPING_CHINA_FEE_NDT = 2F;

    private static final Float DEFAULT_DOMESTIC_SHIPPING_CHINA_FEE = 1F;
    private static final Float UPDATED_DOMESTIC_SHIPPING_CHINA_FEE = 2F;

    private static final Float DEFAULT_DOMESTIC_SHIPPING_VIETNAM_FEE = 1F;
    private static final Float UPDATED_DOMESTIC_SHIPPING_VIETNAM_FEE = 2F;

    private static final Integer DEFAULT_QUANTITY_ORDER = 1;
    private static final Integer UPDATED_QUANTITY_ORDER = 2;

    private static final Integer DEFAULT_QUANTITY_PENDING = 1;
    private static final Integer UPDATED_QUANTITY_PENDING = 2;

    private static final Integer DEFAULT_QUANTITY_RECEIVED = 1;
    private static final Integer UPDATED_QUANTITY_RECEIVED = 2;

    private static final Float DEFAULT_RATE = 1F;
    private static final Float UPDATED_RATE = 2F;

    private static final String DEFAULT_RECEIVER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_RECEIVER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_RECEIVER_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_RECEIVER_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_RECEIVER_MOBILE = "AAAAAAAAAA";
    private static final String UPDATED_RECEIVER_MOBILE = "BBBBBBBBBB";

    private static final String DEFAULT_RECEIVER_NOTE = "AAAAAAAAAA";
    private static final String UPDATED_RECEIVER_NOTE = "BBBBBBBBBB";

    private static final Float DEFAULT_REFUND_AMOUNT_BY_ALIPAY = 1F;
    private static final Float UPDATED_REFUND_AMOUNT_BY_ALIPAY = 2F;

    private static final Float DEFAULT_REFUND_AMOUNT_BY_COMPLAINT = 1F;
    private static final Float UPDATED_REFUND_AMOUNT_BY_COMPLAINT = 2F;

    private static final Float DEFAULT_REFUND_AMOUNT_BY_ORDER = 1F;
    private static final Float UPDATED_REFUND_AMOUNT_BY_ORDER = 2F;

    private static final Float DEFAULT_REFUND_AMOUNT_PENDING = 1F;
    private static final Float UPDATED_REFUND_AMOUNT_PENDING = 2F;

    private static final Float DEFAULT_SHIPPING_CHINA_VIETNAM_FEE = 1F;
    private static final Float UPDATED_SHIPPING_CHINA_VIETNAM_FEE = 2F;

    private static final Float DEFAULT_SHIPPING_CHINA_VIETNAM_FEE_DISCOUNT = 1F;
    private static final Float UPDATED_SHIPPING_CHINA_VIETNAM_FEE_DISCOUNT = 2F;

    private static final Float DEFAULT_SERVICE_FEE = 1F;
    private static final Float UPDATED_SERVICE_FEE = 2F;

    private static final Float DEFAULT_SERVICE_FEE_DISCOUNT = 1F;
    private static final Float UPDATED_SERVICE_FEE_DISCOUNT = 2F;

    private static final Boolean DEFAULT_ITEM_CHECKING = false;
    private static final Boolean UPDATED_ITEM_CHECKING = true;

    private static final Boolean DEFAULT_ITEM_WOOD_CRATING = false;
    private static final Boolean UPDATED_ITEM_WOOD_CRATING = true;

    private static final String DEFAULT_SHOP_ID = "AAAAAAAAAA";
    private static final String UPDATED_SHOP_ID = "BBBBBBBBBB";

    private static final String DEFAULT_SHOP_LINK = "AAAAAAAAAA";
    private static final String UPDATED_SHOP_LINK = "BBBBBBBBBB";

    private static final String DEFAULT_SHOP_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SHOP_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SHOP_NOTE = "AAAAAAAAAA";
    private static final String UPDATED_SHOP_NOTE = "BBBBBBBBBB";

    private static final String DEFAULT_WEBSITE = "AAAAAAAAAA";
    private static final String UPDATED_WEBSITE = "BBBBBBBBBB";

    private static final OrderStatus DEFAULT_STATUS = OrderStatus.DEPOSITED;
    private static final OrderStatus UPDATED_STATUS = OrderStatus.ARE_BUYING;

    private static final String DEFAULT_STATUS_NAME = "AAAAAAAAAA";
    private static final String UPDATED_STATUS_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS_STYLE = "AAAAAAAAAA";
    private static final String UPDATED_STATUS_STYLE = "BBBBBBBBBB";

    private static final Float DEFAULT_TALLY_FEE = 1F;
    private static final Float UPDATED_TALLY_FEE = 2F;

    private static final Float DEFAULT_TOTAL_AMOUNT = 1F;
    private static final Float UPDATED_TOTAL_AMOUNT = 2F;

    private static final Float DEFAULT_TOTAL_AMOUNT_NDT = 1F;
    private static final Float UPDATED_TOTAL_AMOUNT_NDT = 2F;

    private static final Float DEFAULT_TOTAL_AMOUNT_CHINA_NDT = 1F;
    private static final Float UPDATED_TOTAL_AMOUNT_CHINA_NDT = 2F;

    private static final Float DEFAULT_TOTAL_PAID_BY_CUSTOMER = 1F;
    private static final Float UPDATED_TOTAL_PAID_BY_CUSTOMER = 2F;

    private static final Float DEFAULT_TOTAL_SERVICE_FEE = 1F;
    private static final Float UPDATED_TOTAL_SERVICE_FEE = 2F;

    private static final Integer DEFAULT_TOTAL_QUANTITY = 1;
    private static final Integer UPDATED_TOTAL_QUANTITY = 2;

    private static final Float DEFAULT_FINAL_AMOUNT = 1F;
    private static final Float UPDATED_FINAL_AMOUNT = 2F;

    private static final Instant DEFAULT_CREATE_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATE_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_UPDATE_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATE_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private OrderCartRepository orderCartRepository;

    @Autowired
    private OrderCartMapper orderCartMapper;

    @Autowired
    private OrderCartService orderCartService;

    @Autowired
    private OrderCartQueryService orderCartQueryService;

    @Autowired
    private UserShippingAddressService userShippingAddressService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restOrderCartMockMvc;

    private OrderCart orderCart;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final OrderCartResource orderCartResource = new OrderCartResource(orderCartService, orderCartQueryService);
        this.restOrderCartMockMvc = MockMvcBuilders.standaloneSetup(orderCartResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OrderCart createEntity(EntityManager em) {
        OrderCart orderCart = new OrderCart()
            .code(DEFAULT_CODE)
            .shippingChinaCode(DEFAULT_SHIPPING_CHINA_CODE)
            .avatar(DEFAULT_AVATAR)
            .aliwangwang(DEFAULT_ALIWANGWANG)
            .amountDiscount(DEFAULT_AMOUNT_DISCOUNT)
            .amountPaid(DEFAULT_AMOUNT_PAID)
            .depositAmount(DEFAULT_DEPOSIT_AMOUNT)
            .depositRatio(DEFAULT_DEPOSIT_RATIO)
            .depositTime(DEFAULT_DEPOSIT_TIME)
            .domesticShippingChinaFeeNDT(DEFAULT_DOMESTIC_SHIPPING_CHINA_FEE_NDT)
            .domesticShippingChinaFee(DEFAULT_DOMESTIC_SHIPPING_CHINA_FEE)
            .domesticShippingVietnamFee(DEFAULT_DOMESTIC_SHIPPING_VIETNAM_FEE)
            .quantityOrder(DEFAULT_QUANTITY_ORDER)
            .quantityPending(DEFAULT_QUANTITY_PENDING)
            .quantityReceived(DEFAULT_QUANTITY_RECEIVED)
            .rate(DEFAULT_RATE)
            .receiverName(DEFAULT_RECEIVER_NAME)
            .receiverAddress(DEFAULT_RECEIVER_ADDRESS)
            .receiverMobile(DEFAULT_RECEIVER_MOBILE)
            .receiverNote(DEFAULT_RECEIVER_NOTE)
            .refundAmountByAlipay(DEFAULT_REFUND_AMOUNT_BY_ALIPAY)
            .refundAmountByComplaint(DEFAULT_REFUND_AMOUNT_BY_COMPLAINT)
            .refundAmountByOrder(DEFAULT_REFUND_AMOUNT_BY_ORDER)
            .refundAmountPending(DEFAULT_REFUND_AMOUNT_PENDING)
            .shippingChinaVietnamFee(DEFAULT_SHIPPING_CHINA_VIETNAM_FEE)
            .shippingChinaVietnamFeeDiscount(DEFAULT_SHIPPING_CHINA_VIETNAM_FEE_DISCOUNT)
            .serviceFee(DEFAULT_SERVICE_FEE)
            .serviceFeeDiscount(DEFAULT_SERVICE_FEE_DISCOUNT)
            .itemChecking(DEFAULT_ITEM_CHECKING)
            .itemWoodCrating(DEFAULT_ITEM_WOOD_CRATING)
            .shopId(DEFAULT_SHOP_ID)
            .shopLink(DEFAULT_SHOP_LINK)
            .shopName(DEFAULT_SHOP_NAME)
            .shopNote(DEFAULT_SHOP_NOTE)
            .website(DEFAULT_WEBSITE)
            .status(DEFAULT_STATUS)
            .statusName(DEFAULT_STATUS_NAME)
            .statusStyle(DEFAULT_STATUS_STYLE)
            .tallyFee(DEFAULT_TALLY_FEE)
            .totalAmount(DEFAULT_TOTAL_AMOUNT)
            .totalAmountNDT(DEFAULT_TOTAL_AMOUNT_NDT)
            .totalAmountChinaNDT(DEFAULT_TOTAL_AMOUNT_CHINA_NDT)
            .totalPaidByCustomer(DEFAULT_TOTAL_PAID_BY_CUSTOMER)
            .totalServiceFee(DEFAULT_TOTAL_SERVICE_FEE)
            .totalQuantity(DEFAULT_TOTAL_QUANTITY)
            .finalAmount(DEFAULT_FINAL_AMOUNT)
            .createAt(DEFAULT_CREATE_AT)
            .updateAt(DEFAULT_UPDATE_AT);
        return orderCart;
    }

    @Before
    public void initTest() {
        orderCart = createEntity(em);
    }

    @Test
    @Transactional
    public void createOrderCart() throws Exception {
        int databaseSizeBeforeCreate = orderCartRepository.findAll().size();

        // Create the OrderCart
        OrderCartDTO orderCartDTO = orderCartMapper.toDto(orderCart);
        restOrderCartMockMvc.perform(post("/api/order-carts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(orderCartDTO)))
            .andExpect(status().isCreated());

        // Validate the OrderCart in the database
        List<OrderCart> orderCartList = orderCartRepository.findAll();
        assertThat(orderCartList).hasSize(databaseSizeBeforeCreate + 1);
        OrderCart testOrderCart = orderCartList.get(orderCartList.size() - 1);
        assertThat(testOrderCart.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testOrderCart.getShippingChinaCode()).isEqualTo(DEFAULT_SHIPPING_CHINA_CODE);
        assertThat(testOrderCart.getAvatar()).isEqualTo(DEFAULT_AVATAR);
        assertThat(testOrderCart.getAliwangwang()).isEqualTo(DEFAULT_ALIWANGWANG);
        assertThat(testOrderCart.getAmountDiscount()).isEqualTo(DEFAULT_AMOUNT_DISCOUNT);
        assertThat(testOrderCart.getAmountPaid()).isEqualTo(DEFAULT_AMOUNT_PAID);
        assertThat(testOrderCart.getDepositAmount()).isEqualTo(DEFAULT_DEPOSIT_AMOUNT);
        assertThat(testOrderCart.getDepositRatio()).isEqualTo(DEFAULT_DEPOSIT_RATIO);
        assertThat(testOrderCart.getDepositTime()).isEqualTo(DEFAULT_DEPOSIT_TIME);
        assertThat(testOrderCart.getDomesticShippingChinaFeeNDT()).isEqualTo(DEFAULT_DOMESTIC_SHIPPING_CHINA_FEE_NDT);
        assertThat(testOrderCart.getDomesticShippingChinaFee()).isEqualTo(DEFAULT_DOMESTIC_SHIPPING_CHINA_FEE);
        assertThat(testOrderCart.getDomesticShippingVietnamFee()).isEqualTo(DEFAULT_DOMESTIC_SHIPPING_VIETNAM_FEE);
        assertThat(testOrderCart.getQuantityOrder()).isEqualTo(DEFAULT_QUANTITY_ORDER);
        assertThat(testOrderCart.getQuantityPending()).isEqualTo(DEFAULT_QUANTITY_PENDING);
        assertThat(testOrderCart.getQuantityReceived()).isEqualTo(DEFAULT_QUANTITY_RECEIVED);
        assertThat(testOrderCart.getRate()).isEqualTo(DEFAULT_RATE);
        assertThat(testOrderCart.getReceiverName()).isEqualTo(DEFAULT_RECEIVER_NAME);
        assertThat(testOrderCart.getReceiverAddress()).isEqualTo(DEFAULT_RECEIVER_ADDRESS);
        assertThat(testOrderCart.getReceiverMobile()).isEqualTo(DEFAULT_RECEIVER_MOBILE);
        assertThat(testOrderCart.getReceiverNote()).isEqualTo(DEFAULT_RECEIVER_NOTE);
        assertThat(testOrderCart.getRefundAmountByAlipay()).isEqualTo(DEFAULT_REFUND_AMOUNT_BY_ALIPAY);
        assertThat(testOrderCart.getRefundAmountByComplaint()).isEqualTo(DEFAULT_REFUND_AMOUNT_BY_COMPLAINT);
        assertThat(testOrderCart.getRefundAmountByOrder()).isEqualTo(DEFAULT_REFUND_AMOUNT_BY_ORDER);
        assertThat(testOrderCart.getRefundAmountPending()).isEqualTo(DEFAULT_REFUND_AMOUNT_PENDING);
        assertThat(testOrderCart.getShippingChinaVietnamFee()).isEqualTo(DEFAULT_SHIPPING_CHINA_VIETNAM_FEE);
        assertThat(testOrderCart.getShippingChinaVietnamFeeDiscount()).isEqualTo(DEFAULT_SHIPPING_CHINA_VIETNAM_FEE_DISCOUNT);
        assertThat(testOrderCart.getServiceFee()).isEqualTo(DEFAULT_SERVICE_FEE);
        assertThat(testOrderCart.getServiceFeeDiscount()).isEqualTo(DEFAULT_SERVICE_FEE_DISCOUNT);
        assertThat(testOrderCart.isItemChecking()).isEqualTo(DEFAULT_ITEM_CHECKING);
        assertThat(testOrderCart.isItemWoodCrating()).isEqualTo(DEFAULT_ITEM_WOOD_CRATING);
        assertThat(testOrderCart.getShopId()).isEqualTo(DEFAULT_SHOP_ID);
        assertThat(testOrderCart.getShopLink()).isEqualTo(DEFAULT_SHOP_LINK);
        assertThat(testOrderCart.getShopName()).isEqualTo(DEFAULT_SHOP_NAME);
        assertThat(testOrderCart.getShopNote()).isEqualTo(DEFAULT_SHOP_NOTE);
        assertThat(testOrderCart.getWebsite()).isEqualTo(DEFAULT_WEBSITE);
        assertThat(testOrderCart.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testOrderCart.getStatusName()).isEqualTo(DEFAULT_STATUS_NAME);
        assertThat(testOrderCart.getStatusStyle()).isEqualTo(DEFAULT_STATUS_STYLE);
        assertThat(testOrderCart.getTallyFee()).isEqualTo(DEFAULT_TALLY_FEE);
        assertThat(testOrderCart.getTotalAmount()).isEqualTo(DEFAULT_TOTAL_AMOUNT);
        assertThat(testOrderCart.getTotalAmountNDT()).isEqualTo(DEFAULT_TOTAL_AMOUNT_NDT);
        assertThat(testOrderCart.getTotalAmountChinaNDT()).isEqualTo(DEFAULT_TOTAL_AMOUNT_CHINA_NDT);
        assertThat(testOrderCart.getTotalPaidByCustomer()).isEqualTo(DEFAULT_TOTAL_PAID_BY_CUSTOMER);
        assertThat(testOrderCart.getTotalServiceFee()).isEqualTo(DEFAULT_TOTAL_SERVICE_FEE);
        assertThat(testOrderCart.getTotalQuantity()).isEqualTo(DEFAULT_TOTAL_QUANTITY);
        assertThat(testOrderCart.getFinalAmount()).isEqualTo(DEFAULT_FINAL_AMOUNT);
        assertThat(testOrderCart.getCreateAt()).isEqualTo(DEFAULT_CREATE_AT);
        assertThat(testOrderCart.getUpdateAt()).isEqualTo(DEFAULT_UPDATE_AT);
    }

    @Test
    @Transactional
    public void createOrderCartWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = orderCartRepository.findAll().size();

        // Create the OrderCart with an existing ID
        orderCart.setId(1L);
        OrderCartDTO orderCartDTO = orderCartMapper.toDto(orderCart);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOrderCartMockMvc.perform(post("/api/order-carts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(orderCartDTO)))
            .andExpect(status().isBadRequest());

        // Validate the OrderCart in the database
        List<OrderCart> orderCartList = orderCartRepository.findAll();
        assertThat(orderCartList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllOrderCarts() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList
        restOrderCartMockMvc.perform(get("/api/order-carts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(orderCart.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE.intValue())))
            .andExpect(jsonPath("$.[*].shippingChinaCode").value(hasItem(DEFAULT_SHIPPING_CHINA_CODE.toString())))
            .andExpect(jsonPath("$.[*].avatar").value(hasItem(DEFAULT_AVATAR.toString())))
            .andExpect(jsonPath("$.[*].aliwangwang").value(hasItem(DEFAULT_ALIWANGWANG.toString())))
            .andExpect(jsonPath("$.[*].amountDiscount").value(hasItem(DEFAULT_AMOUNT_DISCOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].amountPaid").value(hasItem(DEFAULT_AMOUNT_PAID.doubleValue())))
            .andExpect(jsonPath("$.[*].depositAmount").value(hasItem(DEFAULT_DEPOSIT_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].depositRatio").value(hasItem(DEFAULT_DEPOSIT_RATIO.doubleValue())))
            .andExpect(jsonPath("$.[*].depositTime").value(hasItem(DEFAULT_DEPOSIT_TIME.toString())))
            .andExpect(jsonPath("$.[*].domesticShippingChinaFeeNDT").value(hasItem(DEFAULT_DOMESTIC_SHIPPING_CHINA_FEE_NDT.doubleValue())))
            .andExpect(jsonPath("$.[*].domesticShippingChinaFee").value(hasItem(DEFAULT_DOMESTIC_SHIPPING_CHINA_FEE.doubleValue())))
            .andExpect(jsonPath("$.[*].domesticShippingVietnamFee").value(hasItem(DEFAULT_DOMESTIC_SHIPPING_VIETNAM_FEE.doubleValue())))
            .andExpect(jsonPath("$.[*].quantityOrder").value(hasItem(DEFAULT_QUANTITY_ORDER)))
            .andExpect(jsonPath("$.[*].quantityPending").value(hasItem(DEFAULT_QUANTITY_PENDING)))
            .andExpect(jsonPath("$.[*].quantityReceived").value(hasItem(DEFAULT_QUANTITY_RECEIVED)))
            .andExpect(jsonPath("$.[*].rate").value(hasItem(DEFAULT_RATE.doubleValue())))
            .andExpect(jsonPath("$.[*].receiverName").value(hasItem(DEFAULT_RECEIVER_NAME.toString())))
            .andExpect(jsonPath("$.[*].receiverAddress").value(hasItem(DEFAULT_RECEIVER_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].receiverMobile").value(hasItem(DEFAULT_RECEIVER_MOBILE.toString())))
            .andExpect(jsonPath("$.[*].receiverNote").value(hasItem(DEFAULT_RECEIVER_NOTE.toString())))
            .andExpect(jsonPath("$.[*].refundAmountByAlipay").value(hasItem(DEFAULT_REFUND_AMOUNT_BY_ALIPAY.doubleValue())))
            .andExpect(jsonPath("$.[*].refundAmountByComplaint").value(hasItem(DEFAULT_REFUND_AMOUNT_BY_COMPLAINT.doubleValue())))
            .andExpect(jsonPath("$.[*].refundAmountByOrder").value(hasItem(DEFAULT_REFUND_AMOUNT_BY_ORDER.doubleValue())))
            .andExpect(jsonPath("$.[*].refundAmountPending").value(hasItem(DEFAULT_REFUND_AMOUNT_PENDING.doubleValue())))
            .andExpect(jsonPath("$.[*].shippingChinaVietnamFee").value(hasItem(DEFAULT_SHIPPING_CHINA_VIETNAM_FEE.doubleValue())))
            .andExpect(jsonPath("$.[*].shippingChinaVietnamFeeDiscount").value(hasItem(DEFAULT_SHIPPING_CHINA_VIETNAM_FEE_DISCOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].serviceFee").value(hasItem(DEFAULT_SERVICE_FEE.doubleValue())))
            .andExpect(jsonPath("$.[*].serviceFeeDiscount").value(hasItem(DEFAULT_SERVICE_FEE_DISCOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].itemChecking").value(hasItem(DEFAULT_ITEM_CHECKING.booleanValue())))
            .andExpect(jsonPath("$.[*].itemWoodCrating").value(hasItem(DEFAULT_ITEM_WOOD_CRATING.booleanValue())))
            .andExpect(jsonPath("$.[*].shopId").value(hasItem(DEFAULT_SHOP_ID.toString())))
            .andExpect(jsonPath("$.[*].shopLink").value(hasItem(DEFAULT_SHOP_LINK.toString())))
            .andExpect(jsonPath("$.[*].shopName").value(hasItem(DEFAULT_SHOP_NAME.toString())))
            .andExpect(jsonPath("$.[*].shopNote").value(hasItem(DEFAULT_SHOP_NOTE.toString())))
            .andExpect(jsonPath("$.[*].website").value(hasItem(DEFAULT_WEBSITE.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].statusName").value(hasItem(DEFAULT_STATUS_NAME.toString())))
            .andExpect(jsonPath("$.[*].statusStyle").value(hasItem(DEFAULT_STATUS_STYLE.toString())))
            .andExpect(jsonPath("$.[*].tallyFee").value(hasItem(DEFAULT_TALLY_FEE.doubleValue())))
            .andExpect(jsonPath("$.[*].totalAmount").value(hasItem(DEFAULT_TOTAL_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].totalAmountNDT").value(hasItem(DEFAULT_TOTAL_AMOUNT_NDT.doubleValue())))
            .andExpect(jsonPath("$.[*].totalAmountChinaNDT").value(hasItem(DEFAULT_TOTAL_AMOUNT_CHINA_NDT.doubleValue())))
            .andExpect(jsonPath("$.[*].totalPaidByCustomer").value(hasItem(DEFAULT_TOTAL_PAID_BY_CUSTOMER.doubleValue())))
            .andExpect(jsonPath("$.[*].totalServiceFee").value(hasItem(DEFAULT_TOTAL_SERVICE_FEE.doubleValue())))
            .andExpect(jsonPath("$.[*].totalQuantity").value(hasItem(DEFAULT_TOTAL_QUANTITY)))
            .andExpect(jsonPath("$.[*].finalAmount").value(hasItem(DEFAULT_FINAL_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].createAt").value(hasItem(DEFAULT_CREATE_AT.toString())))
            .andExpect(jsonPath("$.[*].updateAt").value(hasItem(DEFAULT_UPDATE_AT.toString())));
    }
    
    @Test
    @Transactional
    public void getOrderCart() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get the orderCart
        restOrderCartMockMvc.perform(get("/api/order-carts/{id}", orderCart.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(orderCart.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE.intValue()))
            .andExpect(jsonPath("$.shippingChinaCode").value(DEFAULT_SHIPPING_CHINA_CODE.toString()))
            .andExpect(jsonPath("$.avatar").value(DEFAULT_AVATAR.toString()))
            .andExpect(jsonPath("$.aliwangwang").value(DEFAULT_ALIWANGWANG.toString()))
            .andExpect(jsonPath("$.amountDiscount").value(DEFAULT_AMOUNT_DISCOUNT.doubleValue()))
            .andExpect(jsonPath("$.amountPaid").value(DEFAULT_AMOUNT_PAID.doubleValue()))
            .andExpect(jsonPath("$.depositAmount").value(DEFAULT_DEPOSIT_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.depositRatio").value(DEFAULT_DEPOSIT_RATIO.doubleValue()))
            .andExpect(jsonPath("$.depositTime").value(DEFAULT_DEPOSIT_TIME.toString()))
            .andExpect(jsonPath("$.domesticShippingChinaFeeNDT").value(DEFAULT_DOMESTIC_SHIPPING_CHINA_FEE_NDT.doubleValue()))
            .andExpect(jsonPath("$.domesticShippingChinaFee").value(DEFAULT_DOMESTIC_SHIPPING_CHINA_FEE.doubleValue()))
            .andExpect(jsonPath("$.domesticShippingVietnamFee").value(DEFAULT_DOMESTIC_SHIPPING_VIETNAM_FEE.doubleValue()))
            .andExpect(jsonPath("$.quantityOrder").value(DEFAULT_QUANTITY_ORDER))
            .andExpect(jsonPath("$.quantityPending").value(DEFAULT_QUANTITY_PENDING))
            .andExpect(jsonPath("$.quantityReceived").value(DEFAULT_QUANTITY_RECEIVED))
            .andExpect(jsonPath("$.rate").value(DEFAULT_RATE.doubleValue()))
            .andExpect(jsonPath("$.receiverName").value(DEFAULT_RECEIVER_NAME.toString()))
            .andExpect(jsonPath("$.receiverAddress").value(DEFAULT_RECEIVER_ADDRESS.toString()))
            .andExpect(jsonPath("$.receiverMobile").value(DEFAULT_RECEIVER_MOBILE.toString()))
            .andExpect(jsonPath("$.receiverNote").value(DEFAULT_RECEIVER_NOTE.toString()))
            .andExpect(jsonPath("$.refundAmountByAlipay").value(DEFAULT_REFUND_AMOUNT_BY_ALIPAY.doubleValue()))
            .andExpect(jsonPath("$.refundAmountByComplaint").value(DEFAULT_REFUND_AMOUNT_BY_COMPLAINT.doubleValue()))
            .andExpect(jsonPath("$.refundAmountByOrder").value(DEFAULT_REFUND_AMOUNT_BY_ORDER.doubleValue()))
            .andExpect(jsonPath("$.refundAmountPending").value(DEFAULT_REFUND_AMOUNT_PENDING.doubleValue()))
            .andExpect(jsonPath("$.shippingChinaVietnamFee").value(DEFAULT_SHIPPING_CHINA_VIETNAM_FEE.doubleValue()))
            .andExpect(jsonPath("$.shippingChinaVietnamFeeDiscount").value(DEFAULT_SHIPPING_CHINA_VIETNAM_FEE_DISCOUNT.doubleValue()))
            .andExpect(jsonPath("$.serviceFee").value(DEFAULT_SERVICE_FEE.doubleValue()))
            .andExpect(jsonPath("$.serviceFeeDiscount").value(DEFAULT_SERVICE_FEE_DISCOUNT.doubleValue()))
            .andExpect(jsonPath("$.itemChecking").value(DEFAULT_ITEM_CHECKING.booleanValue()))
            .andExpect(jsonPath("$.itemWoodCrating").value(DEFAULT_ITEM_WOOD_CRATING.booleanValue()))
            .andExpect(jsonPath("$.shopId").value(DEFAULT_SHOP_ID.toString()))
            .andExpect(jsonPath("$.shopLink").value(DEFAULT_SHOP_LINK.toString()))
            .andExpect(jsonPath("$.shopName").value(DEFAULT_SHOP_NAME.toString()))
            .andExpect(jsonPath("$.shopNote").value(DEFAULT_SHOP_NOTE.toString()))
            .andExpect(jsonPath("$.website").value(DEFAULT_WEBSITE.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.statusName").value(DEFAULT_STATUS_NAME.toString()))
            .andExpect(jsonPath("$.statusStyle").value(DEFAULT_STATUS_STYLE.toString()))
            .andExpect(jsonPath("$.tallyFee").value(DEFAULT_TALLY_FEE.doubleValue()))
            .andExpect(jsonPath("$.totalAmount").value(DEFAULT_TOTAL_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.totalAmountNDT").value(DEFAULT_TOTAL_AMOUNT_NDT.doubleValue()))
            .andExpect(jsonPath("$.totalAmountChinaNDT").value(DEFAULT_TOTAL_AMOUNT_CHINA_NDT.doubleValue()))
            .andExpect(jsonPath("$.totalPaidByCustomer").value(DEFAULT_TOTAL_PAID_BY_CUSTOMER.doubleValue()))
            .andExpect(jsonPath("$.totalServiceFee").value(DEFAULT_TOTAL_SERVICE_FEE.doubleValue()))
            .andExpect(jsonPath("$.totalQuantity").value(DEFAULT_TOTAL_QUANTITY))
            .andExpect(jsonPath("$.finalAmount").value(DEFAULT_FINAL_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.createAt").value(DEFAULT_CREATE_AT.toString()))
            .andExpect(jsonPath("$.updateAt").value(DEFAULT_UPDATE_AT.toString()));
    }

    @Test
    @Transactional
    public void getAllOrderCartsByCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where code equals to DEFAULT_CODE
        defaultOrderCartShouldBeFound("code.equals=" + DEFAULT_CODE);

        // Get all the orderCartList where code equals to UPDATED_CODE
        defaultOrderCartShouldNotBeFound("code.equals=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllOrderCartsByCodeIsInShouldWork() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where code in DEFAULT_CODE or UPDATED_CODE
        defaultOrderCartShouldBeFound("code.in=" + DEFAULT_CODE + "," + UPDATED_CODE);

        // Get all the orderCartList where code equals to UPDATED_CODE
        defaultOrderCartShouldNotBeFound("code.in=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllOrderCartsByCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where code is not null
        defaultOrderCartShouldBeFound("code.specified=true");

        // Get all the orderCartList where code is null
        defaultOrderCartShouldNotBeFound("code.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrderCartsByCodeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where code greater than or equals to DEFAULT_CODE
        defaultOrderCartShouldBeFound("code.greaterOrEqualThan=" + DEFAULT_CODE);

        // Get all the orderCartList where code greater than or equals to UPDATED_CODE
        defaultOrderCartShouldNotBeFound("code.greaterOrEqualThan=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllOrderCartsByCodeIsLessThanSomething() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where code less than or equals to DEFAULT_CODE
        defaultOrderCartShouldNotBeFound("code.lessThan=" + DEFAULT_CODE);

        // Get all the orderCartList where code less than or equals to UPDATED_CODE
        defaultOrderCartShouldBeFound("code.lessThan=" + UPDATED_CODE);
    }


    @Test
    @Transactional
    public void getAllOrderCartsByShippingChinaCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where shippingChinaCode equals to DEFAULT_SHIPPING_CHINA_CODE
        defaultOrderCartShouldBeFound("shippingChinaCode.equals=" + DEFAULT_SHIPPING_CHINA_CODE);

        // Get all the orderCartList where shippingChinaCode equals to UPDATED_SHIPPING_CHINA_CODE
        defaultOrderCartShouldNotBeFound("shippingChinaCode.equals=" + UPDATED_SHIPPING_CHINA_CODE);
    }

    @Test
    @Transactional
    public void getAllOrderCartsByShippingChinaCodeIsInShouldWork() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where shippingChinaCode in DEFAULT_SHIPPING_CHINA_CODE or UPDATED_SHIPPING_CHINA_CODE
        defaultOrderCartShouldBeFound("shippingChinaCode.in=" + DEFAULT_SHIPPING_CHINA_CODE + "," + UPDATED_SHIPPING_CHINA_CODE);

        // Get all the orderCartList where shippingChinaCode equals to UPDATED_SHIPPING_CHINA_CODE
        defaultOrderCartShouldNotBeFound("shippingChinaCode.in=" + UPDATED_SHIPPING_CHINA_CODE);
    }

    @Test
    @Transactional
    public void getAllOrderCartsByShippingChinaCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where shippingChinaCode is not null
        defaultOrderCartShouldBeFound("shippingChinaCode.specified=true");

        // Get all the orderCartList where shippingChinaCode is null
        defaultOrderCartShouldNotBeFound("shippingChinaCode.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrderCartsByAvatarIsEqualToSomething() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where avatar equals to DEFAULT_AVATAR
        defaultOrderCartShouldBeFound("avatar.equals=" + DEFAULT_AVATAR);

        // Get all the orderCartList where avatar equals to UPDATED_AVATAR
        defaultOrderCartShouldNotBeFound("avatar.equals=" + UPDATED_AVATAR);
    }

    @Test
    @Transactional
    public void getAllOrderCartsByAvatarIsInShouldWork() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where avatar in DEFAULT_AVATAR or UPDATED_AVATAR
        defaultOrderCartShouldBeFound("avatar.in=" + DEFAULT_AVATAR + "," + UPDATED_AVATAR);

        // Get all the orderCartList where avatar equals to UPDATED_AVATAR
        defaultOrderCartShouldNotBeFound("avatar.in=" + UPDATED_AVATAR);
    }

    @Test
    @Transactional
    public void getAllOrderCartsByAvatarIsNullOrNotNull() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where avatar is not null
        defaultOrderCartShouldBeFound("avatar.specified=true");

        // Get all the orderCartList where avatar is null
        defaultOrderCartShouldNotBeFound("avatar.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrderCartsByAliwangwangIsEqualToSomething() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where aliwangwang equals to DEFAULT_ALIWANGWANG
        defaultOrderCartShouldBeFound("aliwangwang.equals=" + DEFAULT_ALIWANGWANG);

        // Get all the orderCartList where aliwangwang equals to UPDATED_ALIWANGWANG
        defaultOrderCartShouldNotBeFound("aliwangwang.equals=" + UPDATED_ALIWANGWANG);
    }

    @Test
    @Transactional
    public void getAllOrderCartsByAliwangwangIsInShouldWork() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where aliwangwang in DEFAULT_ALIWANGWANG or UPDATED_ALIWANGWANG
        defaultOrderCartShouldBeFound("aliwangwang.in=" + DEFAULT_ALIWANGWANG + "," + UPDATED_ALIWANGWANG);

        // Get all the orderCartList where aliwangwang equals to UPDATED_ALIWANGWANG
        defaultOrderCartShouldNotBeFound("aliwangwang.in=" + UPDATED_ALIWANGWANG);
    }

    @Test
    @Transactional
    public void getAllOrderCartsByAliwangwangIsNullOrNotNull() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where aliwangwang is not null
        defaultOrderCartShouldBeFound("aliwangwang.specified=true");

        // Get all the orderCartList where aliwangwang is null
        defaultOrderCartShouldNotBeFound("aliwangwang.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrderCartsByAmountDiscountIsEqualToSomething() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where amountDiscount equals to DEFAULT_AMOUNT_DISCOUNT
        defaultOrderCartShouldBeFound("amountDiscount.equals=" + DEFAULT_AMOUNT_DISCOUNT);

        // Get all the orderCartList where amountDiscount equals to UPDATED_AMOUNT_DISCOUNT
        defaultOrderCartShouldNotBeFound("amountDiscount.equals=" + UPDATED_AMOUNT_DISCOUNT);
    }

    @Test
    @Transactional
    public void getAllOrderCartsByAmountDiscountIsInShouldWork() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where amountDiscount in DEFAULT_AMOUNT_DISCOUNT or UPDATED_AMOUNT_DISCOUNT
        defaultOrderCartShouldBeFound("amountDiscount.in=" + DEFAULT_AMOUNT_DISCOUNT + "," + UPDATED_AMOUNT_DISCOUNT);

        // Get all the orderCartList where amountDiscount equals to UPDATED_AMOUNT_DISCOUNT
        defaultOrderCartShouldNotBeFound("amountDiscount.in=" + UPDATED_AMOUNT_DISCOUNT);
    }

    @Test
    @Transactional
    public void getAllOrderCartsByAmountDiscountIsNullOrNotNull() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where amountDiscount is not null
        defaultOrderCartShouldBeFound("amountDiscount.specified=true");

        // Get all the orderCartList where amountDiscount is null
        defaultOrderCartShouldNotBeFound("amountDiscount.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrderCartsByAmountPaidIsEqualToSomething() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where amountPaid equals to DEFAULT_AMOUNT_PAID
        defaultOrderCartShouldBeFound("amountPaid.equals=" + DEFAULT_AMOUNT_PAID);

        // Get all the orderCartList where amountPaid equals to UPDATED_AMOUNT_PAID
        defaultOrderCartShouldNotBeFound("amountPaid.equals=" + UPDATED_AMOUNT_PAID);
    }

    @Test
    @Transactional
    public void getAllOrderCartsByAmountPaidIsInShouldWork() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where amountPaid in DEFAULT_AMOUNT_PAID or UPDATED_AMOUNT_PAID
        defaultOrderCartShouldBeFound("amountPaid.in=" + DEFAULT_AMOUNT_PAID + "," + UPDATED_AMOUNT_PAID);

        // Get all the orderCartList where amountPaid equals to UPDATED_AMOUNT_PAID
        defaultOrderCartShouldNotBeFound("amountPaid.in=" + UPDATED_AMOUNT_PAID);
    }

    @Test
    @Transactional
    public void getAllOrderCartsByAmountPaidIsNullOrNotNull() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where amountPaid is not null
        defaultOrderCartShouldBeFound("amountPaid.specified=true");

        // Get all the orderCartList where amountPaid is null
        defaultOrderCartShouldNotBeFound("amountPaid.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrderCartsByDepositAmountIsEqualToSomething() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where depositAmount equals to DEFAULT_DEPOSIT_AMOUNT
        defaultOrderCartShouldBeFound("depositAmount.equals=" + DEFAULT_DEPOSIT_AMOUNT);

        // Get all the orderCartList where depositAmount equals to UPDATED_DEPOSIT_AMOUNT
        defaultOrderCartShouldNotBeFound("depositAmount.equals=" + UPDATED_DEPOSIT_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllOrderCartsByDepositAmountIsInShouldWork() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where depositAmount in DEFAULT_DEPOSIT_AMOUNT or UPDATED_DEPOSIT_AMOUNT
        defaultOrderCartShouldBeFound("depositAmount.in=" + DEFAULT_DEPOSIT_AMOUNT + "," + UPDATED_DEPOSIT_AMOUNT);

        // Get all the orderCartList where depositAmount equals to UPDATED_DEPOSIT_AMOUNT
        defaultOrderCartShouldNotBeFound("depositAmount.in=" + UPDATED_DEPOSIT_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllOrderCartsByDepositAmountIsNullOrNotNull() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where depositAmount is not null
        defaultOrderCartShouldBeFound("depositAmount.specified=true");

        // Get all the orderCartList where depositAmount is null
        defaultOrderCartShouldNotBeFound("depositAmount.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrderCartsByDepositRatioIsEqualToSomething() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where depositRatio equals to DEFAULT_DEPOSIT_RATIO
        defaultOrderCartShouldBeFound("depositRatio.equals=" + DEFAULT_DEPOSIT_RATIO);

        // Get all the orderCartList where depositRatio equals to UPDATED_DEPOSIT_RATIO
        defaultOrderCartShouldNotBeFound("depositRatio.equals=" + UPDATED_DEPOSIT_RATIO);
    }

    @Test
    @Transactional
    public void getAllOrderCartsByDepositRatioIsInShouldWork() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where depositRatio in DEFAULT_DEPOSIT_RATIO or UPDATED_DEPOSIT_RATIO
        defaultOrderCartShouldBeFound("depositRatio.in=" + DEFAULT_DEPOSIT_RATIO + "," + UPDATED_DEPOSIT_RATIO);

        // Get all the orderCartList where depositRatio equals to UPDATED_DEPOSIT_RATIO
        defaultOrderCartShouldNotBeFound("depositRatio.in=" + UPDATED_DEPOSIT_RATIO);
    }

    @Test
    @Transactional
    public void getAllOrderCartsByDepositRatioIsNullOrNotNull() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where depositRatio is not null
        defaultOrderCartShouldBeFound("depositRatio.specified=true");

        // Get all the orderCartList where depositRatio is null
        defaultOrderCartShouldNotBeFound("depositRatio.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrderCartsByDepositTimeIsEqualToSomething() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where depositTime equals to DEFAULT_DEPOSIT_TIME
        defaultOrderCartShouldBeFound("depositTime.equals=" + DEFAULT_DEPOSIT_TIME);

        // Get all the orderCartList where depositTime equals to UPDATED_DEPOSIT_TIME
        defaultOrderCartShouldNotBeFound("depositTime.equals=" + UPDATED_DEPOSIT_TIME);
    }

    @Test
    @Transactional
    public void getAllOrderCartsByDepositTimeIsInShouldWork() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where depositTime in DEFAULT_DEPOSIT_TIME or UPDATED_DEPOSIT_TIME
        defaultOrderCartShouldBeFound("depositTime.in=" + DEFAULT_DEPOSIT_TIME + "," + UPDATED_DEPOSIT_TIME);

        // Get all the orderCartList where depositTime equals to UPDATED_DEPOSIT_TIME
        defaultOrderCartShouldNotBeFound("depositTime.in=" + UPDATED_DEPOSIT_TIME);
    }

    @Test
    @Transactional
    public void getAllOrderCartsByDepositTimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where depositTime is not null
        defaultOrderCartShouldBeFound("depositTime.specified=true");

        // Get all the orderCartList where depositTime is null
        defaultOrderCartShouldNotBeFound("depositTime.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrderCartsByDomesticShippingChinaFeeNDTIsEqualToSomething() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where domesticShippingChinaFeeNDT equals to DEFAULT_DOMESTIC_SHIPPING_CHINA_FEE_NDT
        defaultOrderCartShouldBeFound("domesticShippingChinaFeeNDT.equals=" + DEFAULT_DOMESTIC_SHIPPING_CHINA_FEE_NDT);

        // Get all the orderCartList where domesticShippingChinaFeeNDT equals to UPDATED_DOMESTIC_SHIPPING_CHINA_FEE_NDT
        defaultOrderCartShouldNotBeFound("domesticShippingChinaFeeNDT.equals=" + UPDATED_DOMESTIC_SHIPPING_CHINA_FEE_NDT);
    }

    @Test
    @Transactional
    public void getAllOrderCartsByDomesticShippingChinaFeeNDTIsInShouldWork() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where domesticShippingChinaFeeNDT in DEFAULT_DOMESTIC_SHIPPING_CHINA_FEE_NDT or UPDATED_DOMESTIC_SHIPPING_CHINA_FEE_NDT
        defaultOrderCartShouldBeFound("domesticShippingChinaFeeNDT.in=" + DEFAULT_DOMESTIC_SHIPPING_CHINA_FEE_NDT + "," + UPDATED_DOMESTIC_SHIPPING_CHINA_FEE_NDT);

        // Get all the orderCartList where domesticShippingChinaFeeNDT equals to UPDATED_DOMESTIC_SHIPPING_CHINA_FEE_NDT
        defaultOrderCartShouldNotBeFound("domesticShippingChinaFeeNDT.in=" + UPDATED_DOMESTIC_SHIPPING_CHINA_FEE_NDT);
    }

    @Test
    @Transactional
    public void getAllOrderCartsByDomesticShippingChinaFeeNDTIsNullOrNotNull() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where domesticShippingChinaFeeNDT is not null
        defaultOrderCartShouldBeFound("domesticShippingChinaFeeNDT.specified=true");

        // Get all the orderCartList where domesticShippingChinaFeeNDT is null
        defaultOrderCartShouldNotBeFound("domesticShippingChinaFeeNDT.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrderCartsByDomesticShippingChinaFeeIsEqualToSomething() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where domesticShippingChinaFee equals to DEFAULT_DOMESTIC_SHIPPING_CHINA_FEE
        defaultOrderCartShouldBeFound("domesticShippingChinaFee.equals=" + DEFAULT_DOMESTIC_SHIPPING_CHINA_FEE);

        // Get all the orderCartList where domesticShippingChinaFee equals to UPDATED_DOMESTIC_SHIPPING_CHINA_FEE
        defaultOrderCartShouldNotBeFound("domesticShippingChinaFee.equals=" + UPDATED_DOMESTIC_SHIPPING_CHINA_FEE);
    }

    @Test
    @Transactional
    public void getAllOrderCartsByDomesticShippingChinaFeeIsInShouldWork() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where domesticShippingChinaFee in DEFAULT_DOMESTIC_SHIPPING_CHINA_FEE or UPDATED_DOMESTIC_SHIPPING_CHINA_FEE
        defaultOrderCartShouldBeFound("domesticShippingChinaFee.in=" + DEFAULT_DOMESTIC_SHIPPING_CHINA_FEE + "," + UPDATED_DOMESTIC_SHIPPING_CHINA_FEE);

        // Get all the orderCartList where domesticShippingChinaFee equals to UPDATED_DOMESTIC_SHIPPING_CHINA_FEE
        defaultOrderCartShouldNotBeFound("domesticShippingChinaFee.in=" + UPDATED_DOMESTIC_SHIPPING_CHINA_FEE);
    }

    @Test
    @Transactional
    public void getAllOrderCartsByDomesticShippingChinaFeeIsNullOrNotNull() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where domesticShippingChinaFee is not null
        defaultOrderCartShouldBeFound("domesticShippingChinaFee.specified=true");

        // Get all the orderCartList where domesticShippingChinaFee is null
        defaultOrderCartShouldNotBeFound("domesticShippingChinaFee.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrderCartsByDomesticShippingVietnamFeeIsEqualToSomething() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where domesticShippingVietnamFee equals to DEFAULT_DOMESTIC_SHIPPING_VIETNAM_FEE
        defaultOrderCartShouldBeFound("domesticShippingVietnamFee.equals=" + DEFAULT_DOMESTIC_SHIPPING_VIETNAM_FEE);

        // Get all the orderCartList where domesticShippingVietnamFee equals to UPDATED_DOMESTIC_SHIPPING_VIETNAM_FEE
        defaultOrderCartShouldNotBeFound("domesticShippingVietnamFee.equals=" + UPDATED_DOMESTIC_SHIPPING_VIETNAM_FEE);
    }

    @Test
    @Transactional
    public void getAllOrderCartsByDomesticShippingVietnamFeeIsInShouldWork() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where domesticShippingVietnamFee in DEFAULT_DOMESTIC_SHIPPING_VIETNAM_FEE or UPDATED_DOMESTIC_SHIPPING_VIETNAM_FEE
        defaultOrderCartShouldBeFound("domesticShippingVietnamFee.in=" + DEFAULT_DOMESTIC_SHIPPING_VIETNAM_FEE + "," + UPDATED_DOMESTIC_SHIPPING_VIETNAM_FEE);

        // Get all the orderCartList where domesticShippingVietnamFee equals to UPDATED_DOMESTIC_SHIPPING_VIETNAM_FEE
        defaultOrderCartShouldNotBeFound("domesticShippingVietnamFee.in=" + UPDATED_DOMESTIC_SHIPPING_VIETNAM_FEE);
    }

    @Test
    @Transactional
    public void getAllOrderCartsByDomesticShippingVietnamFeeIsNullOrNotNull() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where domesticShippingVietnamFee is not null
        defaultOrderCartShouldBeFound("domesticShippingVietnamFee.specified=true");

        // Get all the orderCartList where domesticShippingVietnamFee is null
        defaultOrderCartShouldNotBeFound("domesticShippingVietnamFee.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrderCartsByQuantityOrderIsEqualToSomething() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where quantityOrder equals to DEFAULT_QUANTITY_ORDER
        defaultOrderCartShouldBeFound("quantityOrder.equals=" + DEFAULT_QUANTITY_ORDER);

        // Get all the orderCartList where quantityOrder equals to UPDATED_QUANTITY_ORDER
        defaultOrderCartShouldNotBeFound("quantityOrder.equals=" + UPDATED_QUANTITY_ORDER);
    }

    @Test
    @Transactional
    public void getAllOrderCartsByQuantityOrderIsInShouldWork() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where quantityOrder in DEFAULT_QUANTITY_ORDER or UPDATED_QUANTITY_ORDER
        defaultOrderCartShouldBeFound("quantityOrder.in=" + DEFAULT_QUANTITY_ORDER + "," + UPDATED_QUANTITY_ORDER);

        // Get all the orderCartList where quantityOrder equals to UPDATED_QUANTITY_ORDER
        defaultOrderCartShouldNotBeFound("quantityOrder.in=" + UPDATED_QUANTITY_ORDER);
    }

    @Test
    @Transactional
    public void getAllOrderCartsByQuantityOrderIsNullOrNotNull() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where quantityOrder is not null
        defaultOrderCartShouldBeFound("quantityOrder.specified=true");

        // Get all the orderCartList where quantityOrder is null
        defaultOrderCartShouldNotBeFound("quantityOrder.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrderCartsByQuantityOrderIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where quantityOrder greater than or equals to DEFAULT_QUANTITY_ORDER
        defaultOrderCartShouldBeFound("quantityOrder.greaterOrEqualThan=" + DEFAULT_QUANTITY_ORDER);

        // Get all the orderCartList where quantityOrder greater than or equals to UPDATED_QUANTITY_ORDER
        defaultOrderCartShouldNotBeFound("quantityOrder.greaterOrEqualThan=" + UPDATED_QUANTITY_ORDER);
    }

    @Test
    @Transactional
    public void getAllOrderCartsByQuantityOrderIsLessThanSomething() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where quantityOrder less than or equals to DEFAULT_QUANTITY_ORDER
        defaultOrderCartShouldNotBeFound("quantityOrder.lessThan=" + DEFAULT_QUANTITY_ORDER);

        // Get all the orderCartList where quantityOrder less than or equals to UPDATED_QUANTITY_ORDER
        defaultOrderCartShouldBeFound("quantityOrder.lessThan=" + UPDATED_QUANTITY_ORDER);
    }


    @Test
    @Transactional
    public void getAllOrderCartsByQuantityPendingIsEqualToSomething() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where quantityPending equals to DEFAULT_QUANTITY_PENDING
        defaultOrderCartShouldBeFound("quantityPending.equals=" + DEFAULT_QUANTITY_PENDING);

        // Get all the orderCartList where quantityPending equals to UPDATED_QUANTITY_PENDING
        defaultOrderCartShouldNotBeFound("quantityPending.equals=" + UPDATED_QUANTITY_PENDING);
    }

    @Test
    @Transactional
    public void getAllOrderCartsByQuantityPendingIsInShouldWork() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where quantityPending in DEFAULT_QUANTITY_PENDING or UPDATED_QUANTITY_PENDING
        defaultOrderCartShouldBeFound("quantityPending.in=" + DEFAULT_QUANTITY_PENDING + "," + UPDATED_QUANTITY_PENDING);

        // Get all the orderCartList where quantityPending equals to UPDATED_QUANTITY_PENDING
        defaultOrderCartShouldNotBeFound("quantityPending.in=" + UPDATED_QUANTITY_PENDING);
    }

    @Test
    @Transactional
    public void getAllOrderCartsByQuantityPendingIsNullOrNotNull() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where quantityPending is not null
        defaultOrderCartShouldBeFound("quantityPending.specified=true");

        // Get all the orderCartList where quantityPending is null
        defaultOrderCartShouldNotBeFound("quantityPending.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrderCartsByQuantityPendingIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where quantityPending greater than or equals to DEFAULT_QUANTITY_PENDING
        defaultOrderCartShouldBeFound("quantityPending.greaterOrEqualThan=" + DEFAULT_QUANTITY_PENDING);

        // Get all the orderCartList where quantityPending greater than or equals to UPDATED_QUANTITY_PENDING
        defaultOrderCartShouldNotBeFound("quantityPending.greaterOrEqualThan=" + UPDATED_QUANTITY_PENDING);
    }

    @Test
    @Transactional
    public void getAllOrderCartsByQuantityPendingIsLessThanSomething() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where quantityPending less than or equals to DEFAULT_QUANTITY_PENDING
        defaultOrderCartShouldNotBeFound("quantityPending.lessThan=" + DEFAULT_QUANTITY_PENDING);

        // Get all the orderCartList where quantityPending less than or equals to UPDATED_QUANTITY_PENDING
        defaultOrderCartShouldBeFound("quantityPending.lessThan=" + UPDATED_QUANTITY_PENDING);
    }


    @Test
    @Transactional
    public void getAllOrderCartsByQuantityReceivedIsEqualToSomething() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where quantityReceived equals to DEFAULT_QUANTITY_RECEIVED
        defaultOrderCartShouldBeFound("quantityReceived.equals=" + DEFAULT_QUANTITY_RECEIVED);

        // Get all the orderCartList where quantityReceived equals to UPDATED_QUANTITY_RECEIVED
        defaultOrderCartShouldNotBeFound("quantityReceived.equals=" + UPDATED_QUANTITY_RECEIVED);
    }

    @Test
    @Transactional
    public void getAllOrderCartsByQuantityReceivedIsInShouldWork() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where quantityReceived in DEFAULT_QUANTITY_RECEIVED or UPDATED_QUANTITY_RECEIVED
        defaultOrderCartShouldBeFound("quantityReceived.in=" + DEFAULT_QUANTITY_RECEIVED + "," + UPDATED_QUANTITY_RECEIVED);

        // Get all the orderCartList where quantityReceived equals to UPDATED_QUANTITY_RECEIVED
        defaultOrderCartShouldNotBeFound("quantityReceived.in=" + UPDATED_QUANTITY_RECEIVED);
    }

    @Test
    @Transactional
    public void getAllOrderCartsByQuantityReceivedIsNullOrNotNull() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where quantityReceived is not null
        defaultOrderCartShouldBeFound("quantityReceived.specified=true");

        // Get all the orderCartList where quantityReceived is null
        defaultOrderCartShouldNotBeFound("quantityReceived.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrderCartsByQuantityReceivedIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where quantityReceived greater than or equals to DEFAULT_QUANTITY_RECEIVED
        defaultOrderCartShouldBeFound("quantityReceived.greaterOrEqualThan=" + DEFAULT_QUANTITY_RECEIVED);

        // Get all the orderCartList where quantityReceived greater than or equals to UPDATED_QUANTITY_RECEIVED
        defaultOrderCartShouldNotBeFound("quantityReceived.greaterOrEqualThan=" + UPDATED_QUANTITY_RECEIVED);
    }

    @Test
    @Transactional
    public void getAllOrderCartsByQuantityReceivedIsLessThanSomething() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where quantityReceived less than or equals to DEFAULT_QUANTITY_RECEIVED
        defaultOrderCartShouldNotBeFound("quantityReceived.lessThan=" + DEFAULT_QUANTITY_RECEIVED);

        // Get all the orderCartList where quantityReceived less than or equals to UPDATED_QUANTITY_RECEIVED
        defaultOrderCartShouldBeFound("quantityReceived.lessThan=" + UPDATED_QUANTITY_RECEIVED);
    }


    @Test
    @Transactional
    public void getAllOrderCartsByRateIsEqualToSomething() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where rate equals to DEFAULT_RATE
        defaultOrderCartShouldBeFound("rate.equals=" + DEFAULT_RATE);

        // Get all the orderCartList where rate equals to UPDATED_RATE
        defaultOrderCartShouldNotBeFound("rate.equals=" + UPDATED_RATE);
    }

    @Test
    @Transactional
    public void getAllOrderCartsByRateIsInShouldWork() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where rate in DEFAULT_RATE or UPDATED_RATE
        defaultOrderCartShouldBeFound("rate.in=" + DEFAULT_RATE + "," + UPDATED_RATE);

        // Get all the orderCartList where rate equals to UPDATED_RATE
        defaultOrderCartShouldNotBeFound("rate.in=" + UPDATED_RATE);
    }

    @Test
    @Transactional
    public void getAllOrderCartsByRateIsNullOrNotNull() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where rate is not null
        defaultOrderCartShouldBeFound("rate.specified=true");

        // Get all the orderCartList where rate is null
        defaultOrderCartShouldNotBeFound("rate.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrderCartsByReceiverNameIsEqualToSomething() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where receiverName equals to DEFAULT_RECEIVER_NAME
        defaultOrderCartShouldBeFound("receiverName.equals=" + DEFAULT_RECEIVER_NAME);

        // Get all the orderCartList where receiverName equals to UPDATED_RECEIVER_NAME
        defaultOrderCartShouldNotBeFound("receiverName.equals=" + UPDATED_RECEIVER_NAME);
    }

    @Test
    @Transactional
    public void getAllOrderCartsByReceiverNameIsInShouldWork() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where receiverName in DEFAULT_RECEIVER_NAME or UPDATED_RECEIVER_NAME
        defaultOrderCartShouldBeFound("receiverName.in=" + DEFAULT_RECEIVER_NAME + "," + UPDATED_RECEIVER_NAME);

        // Get all the orderCartList where receiverName equals to UPDATED_RECEIVER_NAME
        defaultOrderCartShouldNotBeFound("receiverName.in=" + UPDATED_RECEIVER_NAME);
    }

    @Test
    @Transactional
    public void getAllOrderCartsByReceiverNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where receiverName is not null
        defaultOrderCartShouldBeFound("receiverName.specified=true");

        // Get all the orderCartList where receiverName is null
        defaultOrderCartShouldNotBeFound("receiverName.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrderCartsByReceiverAddressIsEqualToSomething() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where receiverAddress equals to DEFAULT_RECEIVER_ADDRESS
        defaultOrderCartShouldBeFound("receiverAddress.equals=" + DEFAULT_RECEIVER_ADDRESS);

        // Get all the orderCartList where receiverAddress equals to UPDATED_RECEIVER_ADDRESS
        defaultOrderCartShouldNotBeFound("receiverAddress.equals=" + UPDATED_RECEIVER_ADDRESS);
    }

    @Test
    @Transactional
    public void getAllOrderCartsByReceiverAddressIsInShouldWork() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where receiverAddress in DEFAULT_RECEIVER_ADDRESS or UPDATED_RECEIVER_ADDRESS
        defaultOrderCartShouldBeFound("receiverAddress.in=" + DEFAULT_RECEIVER_ADDRESS + "," + UPDATED_RECEIVER_ADDRESS);

        // Get all the orderCartList where receiverAddress equals to UPDATED_RECEIVER_ADDRESS
        defaultOrderCartShouldNotBeFound("receiverAddress.in=" + UPDATED_RECEIVER_ADDRESS);
    }

    @Test
    @Transactional
    public void getAllOrderCartsByReceiverAddressIsNullOrNotNull() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where receiverAddress is not null
        defaultOrderCartShouldBeFound("receiverAddress.specified=true");

        // Get all the orderCartList where receiverAddress is null
        defaultOrderCartShouldNotBeFound("receiverAddress.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrderCartsByReceiverMobileIsEqualToSomething() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where receiverMobile equals to DEFAULT_RECEIVER_MOBILE
        defaultOrderCartShouldBeFound("receiverMobile.equals=" + DEFAULT_RECEIVER_MOBILE);

        // Get all the orderCartList where receiverMobile equals to UPDATED_RECEIVER_MOBILE
        defaultOrderCartShouldNotBeFound("receiverMobile.equals=" + UPDATED_RECEIVER_MOBILE);
    }

    @Test
    @Transactional
    public void getAllOrderCartsByReceiverMobileIsInShouldWork() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where receiverMobile in DEFAULT_RECEIVER_MOBILE or UPDATED_RECEIVER_MOBILE
        defaultOrderCartShouldBeFound("receiverMobile.in=" + DEFAULT_RECEIVER_MOBILE + "," + UPDATED_RECEIVER_MOBILE);

        // Get all the orderCartList where receiverMobile equals to UPDATED_RECEIVER_MOBILE
        defaultOrderCartShouldNotBeFound("receiverMobile.in=" + UPDATED_RECEIVER_MOBILE);
    }

    @Test
    @Transactional
    public void getAllOrderCartsByReceiverMobileIsNullOrNotNull() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where receiverMobile is not null
        defaultOrderCartShouldBeFound("receiverMobile.specified=true");

        // Get all the orderCartList where receiverMobile is null
        defaultOrderCartShouldNotBeFound("receiverMobile.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrderCartsByReceiverNoteIsEqualToSomething() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where receiverNote equals to DEFAULT_RECEIVER_NOTE
        defaultOrderCartShouldBeFound("receiverNote.equals=" + DEFAULT_RECEIVER_NOTE);

        // Get all the orderCartList where receiverNote equals to UPDATED_RECEIVER_NOTE
        defaultOrderCartShouldNotBeFound("receiverNote.equals=" + UPDATED_RECEIVER_NOTE);
    }

    @Test
    @Transactional
    public void getAllOrderCartsByReceiverNoteIsInShouldWork() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where receiverNote in DEFAULT_RECEIVER_NOTE or UPDATED_RECEIVER_NOTE
        defaultOrderCartShouldBeFound("receiverNote.in=" + DEFAULT_RECEIVER_NOTE + "," + UPDATED_RECEIVER_NOTE);

        // Get all the orderCartList where receiverNote equals to UPDATED_RECEIVER_NOTE
        defaultOrderCartShouldNotBeFound("receiverNote.in=" + UPDATED_RECEIVER_NOTE);
    }

    @Test
    @Transactional
    public void getAllOrderCartsByReceiverNoteIsNullOrNotNull() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where receiverNote is not null
        defaultOrderCartShouldBeFound("receiverNote.specified=true");

        // Get all the orderCartList where receiverNote is null
        defaultOrderCartShouldNotBeFound("receiverNote.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrderCartsByRefundAmountByAlipayIsEqualToSomething() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where refundAmountByAlipay equals to DEFAULT_REFUND_AMOUNT_BY_ALIPAY
        defaultOrderCartShouldBeFound("refundAmountByAlipay.equals=" + DEFAULT_REFUND_AMOUNT_BY_ALIPAY);

        // Get all the orderCartList where refundAmountByAlipay equals to UPDATED_REFUND_AMOUNT_BY_ALIPAY
        defaultOrderCartShouldNotBeFound("refundAmountByAlipay.equals=" + UPDATED_REFUND_AMOUNT_BY_ALIPAY);
    }

    @Test
    @Transactional
    public void getAllOrderCartsByRefundAmountByAlipayIsInShouldWork() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where refundAmountByAlipay in DEFAULT_REFUND_AMOUNT_BY_ALIPAY or UPDATED_REFUND_AMOUNT_BY_ALIPAY
        defaultOrderCartShouldBeFound("refundAmountByAlipay.in=" + DEFAULT_REFUND_AMOUNT_BY_ALIPAY + "," + UPDATED_REFUND_AMOUNT_BY_ALIPAY);

        // Get all the orderCartList where refundAmountByAlipay equals to UPDATED_REFUND_AMOUNT_BY_ALIPAY
        defaultOrderCartShouldNotBeFound("refundAmountByAlipay.in=" + UPDATED_REFUND_AMOUNT_BY_ALIPAY);
    }

    @Test
    @Transactional
    public void getAllOrderCartsByRefundAmountByAlipayIsNullOrNotNull() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where refundAmountByAlipay is not null
        defaultOrderCartShouldBeFound("refundAmountByAlipay.specified=true");

        // Get all the orderCartList where refundAmountByAlipay is null
        defaultOrderCartShouldNotBeFound("refundAmountByAlipay.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrderCartsByRefundAmountByComplaintIsEqualToSomething() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where refundAmountByComplaint equals to DEFAULT_REFUND_AMOUNT_BY_COMPLAINT
        defaultOrderCartShouldBeFound("refundAmountByComplaint.equals=" + DEFAULT_REFUND_AMOUNT_BY_COMPLAINT);

        // Get all the orderCartList where refundAmountByComplaint equals to UPDATED_REFUND_AMOUNT_BY_COMPLAINT
        defaultOrderCartShouldNotBeFound("refundAmountByComplaint.equals=" + UPDATED_REFUND_AMOUNT_BY_COMPLAINT);
    }

    @Test
    @Transactional
    public void getAllOrderCartsByRefundAmountByComplaintIsInShouldWork() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where refundAmountByComplaint in DEFAULT_REFUND_AMOUNT_BY_COMPLAINT or UPDATED_REFUND_AMOUNT_BY_COMPLAINT
        defaultOrderCartShouldBeFound("refundAmountByComplaint.in=" + DEFAULT_REFUND_AMOUNT_BY_COMPLAINT + "," + UPDATED_REFUND_AMOUNT_BY_COMPLAINT);

        // Get all the orderCartList where refundAmountByComplaint equals to UPDATED_REFUND_AMOUNT_BY_COMPLAINT
        defaultOrderCartShouldNotBeFound("refundAmountByComplaint.in=" + UPDATED_REFUND_AMOUNT_BY_COMPLAINT);
    }

    @Test
    @Transactional
    public void getAllOrderCartsByRefundAmountByComplaintIsNullOrNotNull() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where refundAmountByComplaint is not null
        defaultOrderCartShouldBeFound("refundAmountByComplaint.specified=true");

        // Get all the orderCartList where refundAmountByComplaint is null
        defaultOrderCartShouldNotBeFound("refundAmountByComplaint.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrderCartsByRefundAmountByOrderIsEqualToSomething() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where refundAmountByOrder equals to DEFAULT_REFUND_AMOUNT_BY_ORDER
        defaultOrderCartShouldBeFound("refundAmountByOrder.equals=" + DEFAULT_REFUND_AMOUNT_BY_ORDER);

        // Get all the orderCartList where refundAmountByOrder equals to UPDATED_REFUND_AMOUNT_BY_ORDER
        defaultOrderCartShouldNotBeFound("refundAmountByOrder.equals=" + UPDATED_REFUND_AMOUNT_BY_ORDER);
    }

    @Test
    @Transactional
    public void getAllOrderCartsByRefundAmountByOrderIsInShouldWork() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where refundAmountByOrder in DEFAULT_REFUND_AMOUNT_BY_ORDER or UPDATED_REFUND_AMOUNT_BY_ORDER
        defaultOrderCartShouldBeFound("refundAmountByOrder.in=" + DEFAULT_REFUND_AMOUNT_BY_ORDER + "," + UPDATED_REFUND_AMOUNT_BY_ORDER);

        // Get all the orderCartList where refundAmountByOrder equals to UPDATED_REFUND_AMOUNT_BY_ORDER
        defaultOrderCartShouldNotBeFound("refundAmountByOrder.in=" + UPDATED_REFUND_AMOUNT_BY_ORDER);
    }

    @Test
    @Transactional
    public void getAllOrderCartsByRefundAmountByOrderIsNullOrNotNull() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where refundAmountByOrder is not null
        defaultOrderCartShouldBeFound("refundAmountByOrder.specified=true");

        // Get all the orderCartList where refundAmountByOrder is null
        defaultOrderCartShouldNotBeFound("refundAmountByOrder.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrderCartsByRefundAmountPendingIsEqualToSomething() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where refundAmountPending equals to DEFAULT_REFUND_AMOUNT_PENDING
        defaultOrderCartShouldBeFound("refundAmountPending.equals=" + DEFAULT_REFUND_AMOUNT_PENDING);

        // Get all the orderCartList where refundAmountPending equals to UPDATED_REFUND_AMOUNT_PENDING
        defaultOrderCartShouldNotBeFound("refundAmountPending.equals=" + UPDATED_REFUND_AMOUNT_PENDING);
    }

    @Test
    @Transactional
    public void getAllOrderCartsByRefundAmountPendingIsInShouldWork() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where refundAmountPending in DEFAULT_REFUND_AMOUNT_PENDING or UPDATED_REFUND_AMOUNT_PENDING
        defaultOrderCartShouldBeFound("refundAmountPending.in=" + DEFAULT_REFUND_AMOUNT_PENDING + "," + UPDATED_REFUND_AMOUNT_PENDING);

        // Get all the orderCartList where refundAmountPending equals to UPDATED_REFUND_AMOUNT_PENDING
        defaultOrderCartShouldNotBeFound("refundAmountPending.in=" + UPDATED_REFUND_AMOUNT_PENDING);
    }

    @Test
    @Transactional
    public void getAllOrderCartsByRefundAmountPendingIsNullOrNotNull() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where refundAmountPending is not null
        defaultOrderCartShouldBeFound("refundAmountPending.specified=true");

        // Get all the orderCartList where refundAmountPending is null
        defaultOrderCartShouldNotBeFound("refundAmountPending.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrderCartsByShippingChinaVietnamFeeIsEqualToSomething() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where shippingChinaVietnamFee equals to DEFAULT_SHIPPING_CHINA_VIETNAM_FEE
        defaultOrderCartShouldBeFound("shippingChinaVietnamFee.equals=" + DEFAULT_SHIPPING_CHINA_VIETNAM_FEE);

        // Get all the orderCartList where shippingChinaVietnamFee equals to UPDATED_SHIPPING_CHINA_VIETNAM_FEE
        defaultOrderCartShouldNotBeFound("shippingChinaVietnamFee.equals=" + UPDATED_SHIPPING_CHINA_VIETNAM_FEE);
    }

    @Test
    @Transactional
    public void getAllOrderCartsByShippingChinaVietnamFeeIsInShouldWork() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where shippingChinaVietnamFee in DEFAULT_SHIPPING_CHINA_VIETNAM_FEE or UPDATED_SHIPPING_CHINA_VIETNAM_FEE
        defaultOrderCartShouldBeFound("shippingChinaVietnamFee.in=" + DEFAULT_SHIPPING_CHINA_VIETNAM_FEE + "," + UPDATED_SHIPPING_CHINA_VIETNAM_FEE);

        // Get all the orderCartList where shippingChinaVietnamFee equals to UPDATED_SHIPPING_CHINA_VIETNAM_FEE
        defaultOrderCartShouldNotBeFound("shippingChinaVietnamFee.in=" + UPDATED_SHIPPING_CHINA_VIETNAM_FEE);
    }

    @Test
    @Transactional
    public void getAllOrderCartsByShippingChinaVietnamFeeIsNullOrNotNull() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where shippingChinaVietnamFee is not null
        defaultOrderCartShouldBeFound("shippingChinaVietnamFee.specified=true");

        // Get all the orderCartList where shippingChinaVietnamFee is null
        defaultOrderCartShouldNotBeFound("shippingChinaVietnamFee.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrderCartsByShippingChinaVietnamFeeDiscountIsEqualToSomething() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where shippingChinaVietnamFeeDiscount equals to DEFAULT_SHIPPING_CHINA_VIETNAM_FEE_DISCOUNT
        defaultOrderCartShouldBeFound("shippingChinaVietnamFeeDiscount.equals=" + DEFAULT_SHIPPING_CHINA_VIETNAM_FEE_DISCOUNT);

        // Get all the orderCartList where shippingChinaVietnamFeeDiscount equals to UPDATED_SHIPPING_CHINA_VIETNAM_FEE_DISCOUNT
        defaultOrderCartShouldNotBeFound("shippingChinaVietnamFeeDiscount.equals=" + UPDATED_SHIPPING_CHINA_VIETNAM_FEE_DISCOUNT);
    }

    @Test
    @Transactional
    public void getAllOrderCartsByShippingChinaVietnamFeeDiscountIsInShouldWork() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where shippingChinaVietnamFeeDiscount in DEFAULT_SHIPPING_CHINA_VIETNAM_FEE_DISCOUNT or UPDATED_SHIPPING_CHINA_VIETNAM_FEE_DISCOUNT
        defaultOrderCartShouldBeFound("shippingChinaVietnamFeeDiscount.in=" + DEFAULT_SHIPPING_CHINA_VIETNAM_FEE_DISCOUNT + "," + UPDATED_SHIPPING_CHINA_VIETNAM_FEE_DISCOUNT);

        // Get all the orderCartList where shippingChinaVietnamFeeDiscount equals to UPDATED_SHIPPING_CHINA_VIETNAM_FEE_DISCOUNT
        defaultOrderCartShouldNotBeFound("shippingChinaVietnamFeeDiscount.in=" + UPDATED_SHIPPING_CHINA_VIETNAM_FEE_DISCOUNT);
    }

    @Test
    @Transactional
    public void getAllOrderCartsByShippingChinaVietnamFeeDiscountIsNullOrNotNull() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where shippingChinaVietnamFeeDiscount is not null
        defaultOrderCartShouldBeFound("shippingChinaVietnamFeeDiscount.specified=true");

        // Get all the orderCartList where shippingChinaVietnamFeeDiscount is null
        defaultOrderCartShouldNotBeFound("shippingChinaVietnamFeeDiscount.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrderCartsByServiceFeeIsEqualToSomething() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where serviceFee equals to DEFAULT_SERVICE_FEE
        defaultOrderCartShouldBeFound("serviceFee.equals=" + DEFAULT_SERVICE_FEE);

        // Get all the orderCartList where serviceFee equals to UPDATED_SERVICE_FEE
        defaultOrderCartShouldNotBeFound("serviceFee.equals=" + UPDATED_SERVICE_FEE);
    }

    @Test
    @Transactional
    public void getAllOrderCartsByServiceFeeIsInShouldWork() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where serviceFee in DEFAULT_SERVICE_FEE or UPDATED_SERVICE_FEE
        defaultOrderCartShouldBeFound("serviceFee.in=" + DEFAULT_SERVICE_FEE + "," + UPDATED_SERVICE_FEE);

        // Get all the orderCartList where serviceFee equals to UPDATED_SERVICE_FEE
        defaultOrderCartShouldNotBeFound("serviceFee.in=" + UPDATED_SERVICE_FEE);
    }

    @Test
    @Transactional
    public void getAllOrderCartsByServiceFeeIsNullOrNotNull() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where serviceFee is not null
        defaultOrderCartShouldBeFound("serviceFee.specified=true");

        // Get all the orderCartList where serviceFee is null
        defaultOrderCartShouldNotBeFound("serviceFee.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrderCartsByServiceFeeDiscountIsEqualToSomething() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where serviceFeeDiscount equals to DEFAULT_SERVICE_FEE_DISCOUNT
        defaultOrderCartShouldBeFound("serviceFeeDiscount.equals=" + DEFAULT_SERVICE_FEE_DISCOUNT);

        // Get all the orderCartList where serviceFeeDiscount equals to UPDATED_SERVICE_FEE_DISCOUNT
        defaultOrderCartShouldNotBeFound("serviceFeeDiscount.equals=" + UPDATED_SERVICE_FEE_DISCOUNT);
    }

    @Test
    @Transactional
    public void getAllOrderCartsByServiceFeeDiscountIsInShouldWork() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where serviceFeeDiscount in DEFAULT_SERVICE_FEE_DISCOUNT or UPDATED_SERVICE_FEE_DISCOUNT
        defaultOrderCartShouldBeFound("serviceFeeDiscount.in=" + DEFAULT_SERVICE_FEE_DISCOUNT + "," + UPDATED_SERVICE_FEE_DISCOUNT);

        // Get all the orderCartList where serviceFeeDiscount equals to UPDATED_SERVICE_FEE_DISCOUNT
        defaultOrderCartShouldNotBeFound("serviceFeeDiscount.in=" + UPDATED_SERVICE_FEE_DISCOUNT);
    }

    @Test
    @Transactional
    public void getAllOrderCartsByServiceFeeDiscountIsNullOrNotNull() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where serviceFeeDiscount is not null
        defaultOrderCartShouldBeFound("serviceFeeDiscount.specified=true");

        // Get all the orderCartList where serviceFeeDiscount is null
        defaultOrderCartShouldNotBeFound("serviceFeeDiscount.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrderCartsByItemCheckingIsEqualToSomething() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where itemChecking equals to DEFAULT_ITEM_CHECKING
        defaultOrderCartShouldBeFound("itemChecking.equals=" + DEFAULT_ITEM_CHECKING);

        // Get all the orderCartList where itemChecking equals to UPDATED_ITEM_CHECKING
        defaultOrderCartShouldNotBeFound("itemChecking.equals=" + UPDATED_ITEM_CHECKING);
    }

    @Test
    @Transactional
    public void getAllOrderCartsByItemCheckingIsInShouldWork() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where itemChecking in DEFAULT_ITEM_CHECKING or UPDATED_ITEM_CHECKING
        defaultOrderCartShouldBeFound("itemChecking.in=" + DEFAULT_ITEM_CHECKING + "," + UPDATED_ITEM_CHECKING);

        // Get all the orderCartList where itemChecking equals to UPDATED_ITEM_CHECKING
        defaultOrderCartShouldNotBeFound("itemChecking.in=" + UPDATED_ITEM_CHECKING);
    }

    @Test
    @Transactional
    public void getAllOrderCartsByItemCheckingIsNullOrNotNull() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where itemChecking is not null
        defaultOrderCartShouldBeFound("itemChecking.specified=true");

        // Get all the orderCartList where itemChecking is null
        defaultOrderCartShouldNotBeFound("itemChecking.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrderCartsByItemWoodCratingIsEqualToSomething() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where itemWoodCrating equals to DEFAULT_ITEM_WOOD_CRATING
        defaultOrderCartShouldBeFound("itemWoodCrating.equals=" + DEFAULT_ITEM_WOOD_CRATING);

        // Get all the orderCartList where itemWoodCrating equals to UPDATED_ITEM_WOOD_CRATING
        defaultOrderCartShouldNotBeFound("itemWoodCrating.equals=" + UPDATED_ITEM_WOOD_CRATING);
    }

    @Test
    @Transactional
    public void getAllOrderCartsByItemWoodCratingIsInShouldWork() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where itemWoodCrating in DEFAULT_ITEM_WOOD_CRATING or UPDATED_ITEM_WOOD_CRATING
        defaultOrderCartShouldBeFound("itemWoodCrating.in=" + DEFAULT_ITEM_WOOD_CRATING + "," + UPDATED_ITEM_WOOD_CRATING);

        // Get all the orderCartList where itemWoodCrating equals to UPDATED_ITEM_WOOD_CRATING
        defaultOrderCartShouldNotBeFound("itemWoodCrating.in=" + UPDATED_ITEM_WOOD_CRATING);
    }

    @Test
    @Transactional
    public void getAllOrderCartsByItemWoodCratingIsNullOrNotNull() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where itemWoodCrating is not null
        defaultOrderCartShouldBeFound("itemWoodCrating.specified=true");

        // Get all the orderCartList where itemWoodCrating is null
        defaultOrderCartShouldNotBeFound("itemWoodCrating.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrderCartsByShopIdIsEqualToSomething() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where shopId equals to DEFAULT_SHOP_ID
        defaultOrderCartShouldBeFound("shopId.equals=" + DEFAULT_SHOP_ID);

        // Get all the orderCartList where shopId equals to UPDATED_SHOP_ID
        defaultOrderCartShouldNotBeFound("shopId.equals=" + UPDATED_SHOP_ID);
    }

    @Test
    @Transactional
    public void getAllOrderCartsByShopIdIsInShouldWork() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where shopId in DEFAULT_SHOP_ID or UPDATED_SHOP_ID
        defaultOrderCartShouldBeFound("shopId.in=" + DEFAULT_SHOP_ID + "," + UPDATED_SHOP_ID);

        // Get all the orderCartList where shopId equals to UPDATED_SHOP_ID
        defaultOrderCartShouldNotBeFound("shopId.in=" + UPDATED_SHOP_ID);
    }

    @Test
    @Transactional
    public void getAllOrderCartsByShopIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where shopId is not null
        defaultOrderCartShouldBeFound("shopId.specified=true");

        // Get all the orderCartList where shopId is null
        defaultOrderCartShouldNotBeFound("shopId.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrderCartsByShopLinkIsEqualToSomething() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where shopLink equals to DEFAULT_SHOP_LINK
        defaultOrderCartShouldBeFound("shopLink.equals=" + DEFAULT_SHOP_LINK);

        // Get all the orderCartList where shopLink equals to UPDATED_SHOP_LINK
        defaultOrderCartShouldNotBeFound("shopLink.equals=" + UPDATED_SHOP_LINK);
    }

    @Test
    @Transactional
    public void getAllOrderCartsByShopLinkIsInShouldWork() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where shopLink in DEFAULT_SHOP_LINK or UPDATED_SHOP_LINK
        defaultOrderCartShouldBeFound("shopLink.in=" + DEFAULT_SHOP_LINK + "," + UPDATED_SHOP_LINK);

        // Get all the orderCartList where shopLink equals to UPDATED_SHOP_LINK
        defaultOrderCartShouldNotBeFound("shopLink.in=" + UPDATED_SHOP_LINK);
    }

    @Test
    @Transactional
    public void getAllOrderCartsByShopLinkIsNullOrNotNull() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where shopLink is not null
        defaultOrderCartShouldBeFound("shopLink.specified=true");

        // Get all the orderCartList where shopLink is null
        defaultOrderCartShouldNotBeFound("shopLink.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrderCartsByShopNameIsEqualToSomething() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where shopName equals to DEFAULT_SHOP_NAME
        defaultOrderCartShouldBeFound("shopName.equals=" + DEFAULT_SHOP_NAME);

        // Get all the orderCartList where shopName equals to UPDATED_SHOP_NAME
        defaultOrderCartShouldNotBeFound("shopName.equals=" + UPDATED_SHOP_NAME);
    }

    @Test
    @Transactional
    public void getAllOrderCartsByShopNameIsInShouldWork() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where shopName in DEFAULT_SHOP_NAME or UPDATED_SHOP_NAME
        defaultOrderCartShouldBeFound("shopName.in=" + DEFAULT_SHOP_NAME + "," + UPDATED_SHOP_NAME);

        // Get all the orderCartList where shopName equals to UPDATED_SHOP_NAME
        defaultOrderCartShouldNotBeFound("shopName.in=" + UPDATED_SHOP_NAME);
    }

    @Test
    @Transactional
    public void getAllOrderCartsByShopNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where shopName is not null
        defaultOrderCartShouldBeFound("shopName.specified=true");

        // Get all the orderCartList where shopName is null
        defaultOrderCartShouldNotBeFound("shopName.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrderCartsByShopNoteIsEqualToSomething() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where shopNote equals to DEFAULT_SHOP_NOTE
        defaultOrderCartShouldBeFound("shopNote.equals=" + DEFAULT_SHOP_NOTE);

        // Get all the orderCartList where shopNote equals to UPDATED_SHOP_NOTE
        defaultOrderCartShouldNotBeFound("shopNote.equals=" + UPDATED_SHOP_NOTE);
    }

    @Test
    @Transactional
    public void getAllOrderCartsByShopNoteIsInShouldWork() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where shopNote in DEFAULT_SHOP_NOTE or UPDATED_SHOP_NOTE
        defaultOrderCartShouldBeFound("shopNote.in=" + DEFAULT_SHOP_NOTE + "," + UPDATED_SHOP_NOTE);

        // Get all the orderCartList where shopNote equals to UPDATED_SHOP_NOTE
        defaultOrderCartShouldNotBeFound("shopNote.in=" + UPDATED_SHOP_NOTE);
    }

    @Test
    @Transactional
    public void getAllOrderCartsByShopNoteIsNullOrNotNull() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where shopNote is not null
        defaultOrderCartShouldBeFound("shopNote.specified=true");

        // Get all the orderCartList where shopNote is null
        defaultOrderCartShouldNotBeFound("shopNote.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrderCartsByWebsiteIsEqualToSomething() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where website equals to DEFAULT_WEBSITE
        defaultOrderCartShouldBeFound("website.equals=" + DEFAULT_WEBSITE);

        // Get all the orderCartList where website equals to UPDATED_WEBSITE
        defaultOrderCartShouldNotBeFound("website.equals=" + UPDATED_WEBSITE);
    }

    @Test
    @Transactional
    public void getAllOrderCartsByWebsiteIsInShouldWork() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where website in DEFAULT_WEBSITE or UPDATED_WEBSITE
        defaultOrderCartShouldBeFound("website.in=" + DEFAULT_WEBSITE + "," + UPDATED_WEBSITE);

        // Get all the orderCartList where website equals to UPDATED_WEBSITE
        defaultOrderCartShouldNotBeFound("website.in=" + UPDATED_WEBSITE);
    }

    @Test
    @Transactional
    public void getAllOrderCartsByWebsiteIsNullOrNotNull() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where website is not null
        defaultOrderCartShouldBeFound("website.specified=true");

        // Get all the orderCartList where website is null
        defaultOrderCartShouldNotBeFound("website.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrderCartsByStatusIsEqualToSomething() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where status equals to DEFAULT_STATUS
        defaultOrderCartShouldBeFound("status.equals=" + DEFAULT_STATUS);

        // Get all the orderCartList where status equals to UPDATED_STATUS
        defaultOrderCartShouldNotBeFound("status.equals=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllOrderCartsByStatusIsInShouldWork() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where status in DEFAULT_STATUS or UPDATED_STATUS
        defaultOrderCartShouldBeFound("status.in=" + DEFAULT_STATUS + "," + UPDATED_STATUS);

        // Get all the orderCartList where status equals to UPDATED_STATUS
        defaultOrderCartShouldNotBeFound("status.in=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllOrderCartsByStatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where status is not null
        defaultOrderCartShouldBeFound("status.specified=true");

        // Get all the orderCartList where status is null
        defaultOrderCartShouldNotBeFound("status.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrderCartsByStatusNameIsEqualToSomething() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where statusName equals to DEFAULT_STATUS_NAME
        defaultOrderCartShouldBeFound("statusName.equals=" + DEFAULT_STATUS_NAME);

        // Get all the orderCartList where statusName equals to UPDATED_STATUS_NAME
        defaultOrderCartShouldNotBeFound("statusName.equals=" + UPDATED_STATUS_NAME);
    }

    @Test
    @Transactional
    public void getAllOrderCartsByStatusNameIsInShouldWork() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where statusName in DEFAULT_STATUS_NAME or UPDATED_STATUS_NAME
        defaultOrderCartShouldBeFound("statusName.in=" + DEFAULT_STATUS_NAME + "," + UPDATED_STATUS_NAME);

        // Get all the orderCartList where statusName equals to UPDATED_STATUS_NAME
        defaultOrderCartShouldNotBeFound("statusName.in=" + UPDATED_STATUS_NAME);
    }

    @Test
    @Transactional
    public void getAllOrderCartsByStatusNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where statusName is not null
        defaultOrderCartShouldBeFound("statusName.specified=true");

        // Get all the orderCartList where statusName is null
        defaultOrderCartShouldNotBeFound("statusName.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrderCartsByStatusStyleIsEqualToSomething() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where statusStyle equals to DEFAULT_STATUS_STYLE
        defaultOrderCartShouldBeFound("statusStyle.equals=" + DEFAULT_STATUS_STYLE);

        // Get all the orderCartList where statusStyle equals to UPDATED_STATUS_STYLE
        defaultOrderCartShouldNotBeFound("statusStyle.equals=" + UPDATED_STATUS_STYLE);
    }

    @Test
    @Transactional
    public void getAllOrderCartsByStatusStyleIsInShouldWork() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where statusStyle in DEFAULT_STATUS_STYLE or UPDATED_STATUS_STYLE
        defaultOrderCartShouldBeFound("statusStyle.in=" + DEFAULT_STATUS_STYLE + "," + UPDATED_STATUS_STYLE);

        // Get all the orderCartList where statusStyle equals to UPDATED_STATUS_STYLE
        defaultOrderCartShouldNotBeFound("statusStyle.in=" + UPDATED_STATUS_STYLE);
    }

    @Test
    @Transactional
    public void getAllOrderCartsByStatusStyleIsNullOrNotNull() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where statusStyle is not null
        defaultOrderCartShouldBeFound("statusStyle.specified=true");

        // Get all the orderCartList where statusStyle is null
        defaultOrderCartShouldNotBeFound("statusStyle.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrderCartsByTallyFeeIsEqualToSomething() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where tallyFee equals to DEFAULT_TALLY_FEE
        defaultOrderCartShouldBeFound("tallyFee.equals=" + DEFAULT_TALLY_FEE);

        // Get all the orderCartList where tallyFee equals to UPDATED_TALLY_FEE
        defaultOrderCartShouldNotBeFound("tallyFee.equals=" + UPDATED_TALLY_FEE);
    }

    @Test
    @Transactional
    public void getAllOrderCartsByTallyFeeIsInShouldWork() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where tallyFee in DEFAULT_TALLY_FEE or UPDATED_TALLY_FEE
        defaultOrderCartShouldBeFound("tallyFee.in=" + DEFAULT_TALLY_FEE + "," + UPDATED_TALLY_FEE);

        // Get all the orderCartList where tallyFee equals to UPDATED_TALLY_FEE
        defaultOrderCartShouldNotBeFound("tallyFee.in=" + UPDATED_TALLY_FEE);
    }

    @Test
    @Transactional
    public void getAllOrderCartsByTallyFeeIsNullOrNotNull() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where tallyFee is not null
        defaultOrderCartShouldBeFound("tallyFee.specified=true");

        // Get all the orderCartList where tallyFee is null
        defaultOrderCartShouldNotBeFound("tallyFee.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrderCartsByTotalAmountIsEqualToSomething() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where totalAmount equals to DEFAULT_TOTAL_AMOUNT
        defaultOrderCartShouldBeFound("totalAmount.equals=" + DEFAULT_TOTAL_AMOUNT);

        // Get all the orderCartList where totalAmount equals to UPDATED_TOTAL_AMOUNT
        defaultOrderCartShouldNotBeFound("totalAmount.equals=" + UPDATED_TOTAL_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllOrderCartsByTotalAmountIsInShouldWork() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where totalAmount in DEFAULT_TOTAL_AMOUNT or UPDATED_TOTAL_AMOUNT
        defaultOrderCartShouldBeFound("totalAmount.in=" + DEFAULT_TOTAL_AMOUNT + "," + UPDATED_TOTAL_AMOUNT);

        // Get all the orderCartList where totalAmount equals to UPDATED_TOTAL_AMOUNT
        defaultOrderCartShouldNotBeFound("totalAmount.in=" + UPDATED_TOTAL_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllOrderCartsByTotalAmountIsNullOrNotNull() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where totalAmount is not null
        defaultOrderCartShouldBeFound("totalAmount.specified=true");

        // Get all the orderCartList where totalAmount is null
        defaultOrderCartShouldNotBeFound("totalAmount.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrderCartsByTotalAmountNDTIsEqualToSomething() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where totalAmountNDT equals to DEFAULT_TOTAL_AMOUNT_NDT
        defaultOrderCartShouldBeFound("totalAmountNDT.equals=" + DEFAULT_TOTAL_AMOUNT_NDT);

        // Get all the orderCartList where totalAmountNDT equals to UPDATED_TOTAL_AMOUNT_NDT
        defaultOrderCartShouldNotBeFound("totalAmountNDT.equals=" + UPDATED_TOTAL_AMOUNT_NDT);
    }

    @Test
    @Transactional
    public void getAllOrderCartsByTotalAmountNDTIsInShouldWork() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where totalAmountNDT in DEFAULT_TOTAL_AMOUNT_NDT or UPDATED_TOTAL_AMOUNT_NDT
        defaultOrderCartShouldBeFound("totalAmountNDT.in=" + DEFAULT_TOTAL_AMOUNT_NDT + "," + UPDATED_TOTAL_AMOUNT_NDT);

        // Get all the orderCartList where totalAmountNDT equals to UPDATED_TOTAL_AMOUNT_NDT
        defaultOrderCartShouldNotBeFound("totalAmountNDT.in=" + UPDATED_TOTAL_AMOUNT_NDT);
    }

    @Test
    @Transactional
    public void getAllOrderCartsByTotalAmountNDTIsNullOrNotNull() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where totalAmountNDT is not null
        defaultOrderCartShouldBeFound("totalAmountNDT.specified=true");

        // Get all the orderCartList where totalAmountNDT is null
        defaultOrderCartShouldNotBeFound("totalAmountNDT.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrderCartsByTotalAmountChinaNDTIsEqualToSomething() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where totalAmountChinaNDT equals to DEFAULT_TOTAL_AMOUNT_CHINA_NDT
        defaultOrderCartShouldBeFound("totalAmountChinaNDT.equals=" + DEFAULT_TOTAL_AMOUNT_CHINA_NDT);

        // Get all the orderCartList where totalAmountChinaNDT equals to UPDATED_TOTAL_AMOUNT_CHINA_NDT
        defaultOrderCartShouldNotBeFound("totalAmountChinaNDT.equals=" + UPDATED_TOTAL_AMOUNT_CHINA_NDT);
    }

    @Test
    @Transactional
    public void getAllOrderCartsByTotalAmountChinaNDTIsInShouldWork() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where totalAmountChinaNDT in DEFAULT_TOTAL_AMOUNT_CHINA_NDT or UPDATED_TOTAL_AMOUNT_CHINA_NDT
        defaultOrderCartShouldBeFound("totalAmountChinaNDT.in=" + DEFAULT_TOTAL_AMOUNT_CHINA_NDT + "," + UPDATED_TOTAL_AMOUNT_CHINA_NDT);

        // Get all the orderCartList where totalAmountChinaNDT equals to UPDATED_TOTAL_AMOUNT_CHINA_NDT
        defaultOrderCartShouldNotBeFound("totalAmountChinaNDT.in=" + UPDATED_TOTAL_AMOUNT_CHINA_NDT);
    }

    @Test
    @Transactional
    public void getAllOrderCartsByTotalAmountChinaNDTIsNullOrNotNull() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where totalAmountChinaNDT is not null
        defaultOrderCartShouldBeFound("totalAmountChinaNDT.specified=true");

        // Get all the orderCartList where totalAmountChinaNDT is null
        defaultOrderCartShouldNotBeFound("totalAmountChinaNDT.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrderCartsByTotalPaidByCustomerIsEqualToSomething() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where totalPaidByCustomer equals to DEFAULT_TOTAL_PAID_BY_CUSTOMER
        defaultOrderCartShouldBeFound("totalPaidByCustomer.equals=" + DEFAULT_TOTAL_PAID_BY_CUSTOMER);

        // Get all the orderCartList where totalPaidByCustomer equals to UPDATED_TOTAL_PAID_BY_CUSTOMER
        defaultOrderCartShouldNotBeFound("totalPaidByCustomer.equals=" + UPDATED_TOTAL_PAID_BY_CUSTOMER);
    }

    @Test
    @Transactional
    public void getAllOrderCartsByTotalPaidByCustomerIsInShouldWork() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where totalPaidByCustomer in DEFAULT_TOTAL_PAID_BY_CUSTOMER or UPDATED_TOTAL_PAID_BY_CUSTOMER
        defaultOrderCartShouldBeFound("totalPaidByCustomer.in=" + DEFAULT_TOTAL_PAID_BY_CUSTOMER + "," + UPDATED_TOTAL_PAID_BY_CUSTOMER);

        // Get all the orderCartList where totalPaidByCustomer equals to UPDATED_TOTAL_PAID_BY_CUSTOMER
        defaultOrderCartShouldNotBeFound("totalPaidByCustomer.in=" + UPDATED_TOTAL_PAID_BY_CUSTOMER);
    }

    @Test
    @Transactional
    public void getAllOrderCartsByTotalPaidByCustomerIsNullOrNotNull() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where totalPaidByCustomer is not null
        defaultOrderCartShouldBeFound("totalPaidByCustomer.specified=true");

        // Get all the orderCartList where totalPaidByCustomer is null
        defaultOrderCartShouldNotBeFound("totalPaidByCustomer.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrderCartsByTotalServiceFeeIsEqualToSomething() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where totalServiceFee equals to DEFAULT_TOTAL_SERVICE_FEE
        defaultOrderCartShouldBeFound("totalServiceFee.equals=" + DEFAULT_TOTAL_SERVICE_FEE);

        // Get all the orderCartList where totalServiceFee equals to UPDATED_TOTAL_SERVICE_FEE
        defaultOrderCartShouldNotBeFound("totalServiceFee.equals=" + UPDATED_TOTAL_SERVICE_FEE);
    }

    @Test
    @Transactional
    public void getAllOrderCartsByTotalServiceFeeIsInShouldWork() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where totalServiceFee in DEFAULT_TOTAL_SERVICE_FEE or UPDATED_TOTAL_SERVICE_FEE
        defaultOrderCartShouldBeFound("totalServiceFee.in=" + DEFAULT_TOTAL_SERVICE_FEE + "," + UPDATED_TOTAL_SERVICE_FEE);

        // Get all the orderCartList where totalServiceFee equals to UPDATED_TOTAL_SERVICE_FEE
        defaultOrderCartShouldNotBeFound("totalServiceFee.in=" + UPDATED_TOTAL_SERVICE_FEE);
    }

    @Test
    @Transactional
    public void getAllOrderCartsByTotalServiceFeeIsNullOrNotNull() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where totalServiceFee is not null
        defaultOrderCartShouldBeFound("totalServiceFee.specified=true");

        // Get all the orderCartList where totalServiceFee is null
        defaultOrderCartShouldNotBeFound("totalServiceFee.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrderCartsByTotalQuantityIsEqualToSomething() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where totalQuantity equals to DEFAULT_TOTAL_QUANTITY
        defaultOrderCartShouldBeFound("totalQuantity.equals=" + DEFAULT_TOTAL_QUANTITY);

        // Get all the orderCartList where totalQuantity equals to UPDATED_TOTAL_QUANTITY
        defaultOrderCartShouldNotBeFound("totalQuantity.equals=" + UPDATED_TOTAL_QUANTITY);
    }

    @Test
    @Transactional
    public void getAllOrderCartsByTotalQuantityIsInShouldWork() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where totalQuantity in DEFAULT_TOTAL_QUANTITY or UPDATED_TOTAL_QUANTITY
        defaultOrderCartShouldBeFound("totalQuantity.in=" + DEFAULT_TOTAL_QUANTITY + "," + UPDATED_TOTAL_QUANTITY);

        // Get all the orderCartList where totalQuantity equals to UPDATED_TOTAL_QUANTITY
        defaultOrderCartShouldNotBeFound("totalQuantity.in=" + UPDATED_TOTAL_QUANTITY);
    }

    @Test
    @Transactional
    public void getAllOrderCartsByTotalQuantityIsNullOrNotNull() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where totalQuantity is not null
        defaultOrderCartShouldBeFound("totalQuantity.specified=true");

        // Get all the orderCartList where totalQuantity is null
        defaultOrderCartShouldNotBeFound("totalQuantity.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrderCartsByTotalQuantityIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where totalQuantity greater than or equals to DEFAULT_TOTAL_QUANTITY
        defaultOrderCartShouldBeFound("totalQuantity.greaterOrEqualThan=" + DEFAULT_TOTAL_QUANTITY);

        // Get all the orderCartList where totalQuantity greater than or equals to UPDATED_TOTAL_QUANTITY
        defaultOrderCartShouldNotBeFound("totalQuantity.greaterOrEqualThan=" + UPDATED_TOTAL_QUANTITY);
    }

    @Test
    @Transactional
    public void getAllOrderCartsByTotalQuantityIsLessThanSomething() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where totalQuantity less than or equals to DEFAULT_TOTAL_QUANTITY
        defaultOrderCartShouldNotBeFound("totalQuantity.lessThan=" + DEFAULT_TOTAL_QUANTITY);

        // Get all the orderCartList where totalQuantity less than or equals to UPDATED_TOTAL_QUANTITY
        defaultOrderCartShouldBeFound("totalQuantity.lessThan=" + UPDATED_TOTAL_QUANTITY);
    }


    @Test
    @Transactional
    public void getAllOrderCartsByFinalAmountIsEqualToSomething() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where finalAmount equals to DEFAULT_FINAL_AMOUNT
        defaultOrderCartShouldBeFound("finalAmount.equals=" + DEFAULT_FINAL_AMOUNT);

        // Get all the orderCartList where finalAmount equals to UPDATED_FINAL_AMOUNT
        defaultOrderCartShouldNotBeFound("finalAmount.equals=" + UPDATED_FINAL_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllOrderCartsByFinalAmountIsInShouldWork() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where finalAmount in DEFAULT_FINAL_AMOUNT or UPDATED_FINAL_AMOUNT
        defaultOrderCartShouldBeFound("finalAmount.in=" + DEFAULT_FINAL_AMOUNT + "," + UPDATED_FINAL_AMOUNT);

        // Get all the orderCartList where finalAmount equals to UPDATED_FINAL_AMOUNT
        defaultOrderCartShouldNotBeFound("finalAmount.in=" + UPDATED_FINAL_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllOrderCartsByFinalAmountIsNullOrNotNull() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where finalAmount is not null
        defaultOrderCartShouldBeFound("finalAmount.specified=true");

        // Get all the orderCartList where finalAmount is null
        defaultOrderCartShouldNotBeFound("finalAmount.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrderCartsByCreateAtIsEqualToSomething() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where createAt equals to DEFAULT_CREATE_AT
        defaultOrderCartShouldBeFound("createAt.equals=" + DEFAULT_CREATE_AT);

        // Get all the orderCartList where createAt equals to UPDATED_CREATE_AT
        defaultOrderCartShouldNotBeFound("createAt.equals=" + UPDATED_CREATE_AT);
    }

    @Test
    @Transactional
    public void getAllOrderCartsByCreateAtIsInShouldWork() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where createAt in DEFAULT_CREATE_AT or UPDATED_CREATE_AT
        defaultOrderCartShouldBeFound("createAt.in=" + DEFAULT_CREATE_AT + "," + UPDATED_CREATE_AT);

        // Get all the orderCartList where createAt equals to UPDATED_CREATE_AT
        defaultOrderCartShouldNotBeFound("createAt.in=" + UPDATED_CREATE_AT);
    }

    @Test
    @Transactional
    public void getAllOrderCartsByCreateAtIsNullOrNotNull() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where createAt is not null
        defaultOrderCartShouldBeFound("createAt.specified=true");

        // Get all the orderCartList where createAt is null
        defaultOrderCartShouldNotBeFound("createAt.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrderCartsByUpdateAtIsEqualToSomething() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where updateAt equals to DEFAULT_UPDATE_AT
        defaultOrderCartShouldBeFound("updateAt.equals=" + DEFAULT_UPDATE_AT);

        // Get all the orderCartList where updateAt equals to UPDATED_UPDATE_AT
        defaultOrderCartShouldNotBeFound("updateAt.equals=" + UPDATED_UPDATE_AT);
    }

    @Test
    @Transactional
    public void getAllOrderCartsByUpdateAtIsInShouldWork() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where updateAt in DEFAULT_UPDATE_AT or UPDATED_UPDATE_AT
        defaultOrderCartShouldBeFound("updateAt.in=" + DEFAULT_UPDATE_AT + "," + UPDATED_UPDATE_AT);

        // Get all the orderCartList where updateAt equals to UPDATED_UPDATE_AT
        defaultOrderCartShouldNotBeFound("updateAt.in=" + UPDATED_UPDATE_AT);
    }

    @Test
    @Transactional
    public void getAllOrderCartsByUpdateAtIsNullOrNotNull() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        // Get all the orderCartList where updateAt is not null
        defaultOrderCartShouldBeFound("updateAt.specified=true");

        // Get all the orderCartList where updateAt is null
        defaultOrderCartShouldNotBeFound("updateAt.specified=false");
    }

    @Test
    @Transactional
    public void getAllOrderCartsByItemsIsEqualToSomething() throws Exception {
        // Initialize the database
        OrderItem items = OrderItemResourceIntTest.createEntity(em);
        em.persist(items);
        em.flush();
        orderCart.addItems(items);
        orderCartRepository.saveAndFlush(orderCart);
        Long itemsId = items.getId();

        // Get all the orderCartList where items equals to itemsId
        defaultOrderCartShouldBeFound("itemsId.equals=" + itemsId);

        // Get all the orderCartList where items equals to itemsId + 1
        defaultOrderCartShouldNotBeFound("itemsId.equals=" + (itemsId + 1));
    }


    @Test
    @Transactional
    public void getAllOrderCartsByCommentsIsEqualToSomething() throws Exception {
        // Initialize the database
        OrderComment comments = OrderCommentResourceIntTest.createEntity(em);
        em.persist(comments);
        em.flush();
        orderCart.addComments(comments);
        orderCartRepository.saveAndFlush(orderCart);
        Long commentsId = comments.getId();

        // Get all the orderCartList where comments equals to commentsId
        defaultOrderCartShouldBeFound("commentsId.equals=" + commentsId);

        // Get all the orderCartList where comments equals to commentsId + 1
        defaultOrderCartShouldNotBeFound("commentsId.equals=" + (commentsId + 1));
    }


    @Test
    @Transactional
    public void getAllOrderCartsByHistoriesIsEqualToSomething() throws Exception {
        // Initialize the database
        OrderHistory histories = OrderHistoryResourceIntTest.createEntity(em);
        em.persist(histories);
        em.flush();
        orderCart.addHistories(histories);
        orderCartRepository.saveAndFlush(orderCart);
        Long historiesId = histories.getId();

        // Get all the orderCartList where histories equals to historiesId
        defaultOrderCartShouldBeFound("historiesId.equals=" + historiesId);

        // Get all the orderCartList where histories equals to historiesId + 1
        defaultOrderCartShouldNotBeFound("historiesId.equals=" + (historiesId + 1));
    }


    @Test
    @Transactional
    public void getAllOrderCartsByPackagesIsEqualToSomething() throws Exception {
        // Initialize the database
        OrderPackage packages = OrderPackageResourceIntTest.createEntity(em);
        em.persist(packages);
        em.flush();
        orderCart.addPackages(packages);
        orderCartRepository.saveAndFlush(orderCart);
        Long packagesId = packages.getId();

        // Get all the orderCartList where packages equals to packagesId
        defaultOrderCartShouldBeFound("packagesId.equals=" + packagesId);

        // Get all the orderCartList where packages equals to packagesId + 1
        defaultOrderCartShouldNotBeFound("packagesId.equals=" + (packagesId + 1));
    }


    @Test
    @Transactional
    public void getAllOrderCartsByTransactionsIsEqualToSomething() throws Exception {
        // Initialize the database
        OrderTransaction transactions = OrderTransactionResourceIntTest.createEntity(em);
        em.persist(transactions);
        em.flush();
        orderCart.addTransactions(transactions);
        orderCartRepository.saveAndFlush(orderCart);
        Long transactionsId = transactions.getId();

        // Get all the orderCartList where transactions equals to transactionsId
        defaultOrderCartShouldBeFound("transactionsId.equals=" + transactionsId);

        // Get all the orderCartList where transactions equals to transactionsId + 1
        defaultOrderCartShouldNotBeFound("transactionsId.equals=" + (transactionsId + 1));
    }


    @Test
    @Transactional
    public void getAllOrderCartsByBuyerIsEqualToSomething() throws Exception {
        // Initialize the database
        User buyer = UserResourceIntTest.createEntity(em);
        em.persist(buyer);
        em.flush();
        orderCart.setBuyer(buyer);
        orderCartRepository.saveAndFlush(orderCart);
        Long buyerId = buyer.getId();

        // Get all the orderCartList where buyer equals to buyerId
        defaultOrderCartShouldBeFound("buyerId.equals=" + buyerId);

        // Get all the orderCartList where buyer equals to buyerId + 1
        defaultOrderCartShouldNotBeFound("buyerId.equals=" + (buyerId + 1));
    }


    @Test
    @Transactional
    public void getAllOrderCartsByChinaStockerIsEqualToSomething() throws Exception {
        // Initialize the database
        User chinaStocker = UserResourceIntTest.createEntity(em);
        em.persist(chinaStocker);
        em.flush();
        orderCart.setChinaStocker(chinaStocker);
        orderCartRepository.saveAndFlush(orderCart);
        Long chinaStockerId = chinaStocker.getId();

        // Get all the orderCartList where chinaStocker equals to chinaStockerId
        defaultOrderCartShouldBeFound("chinaStockerId.equals=" + chinaStockerId);

        // Get all the orderCartList where chinaStocker equals to chinaStockerId + 1
        defaultOrderCartShouldNotBeFound("chinaStockerId.equals=" + (chinaStockerId + 1));
    }


    @Test
    @Transactional
    public void getAllOrderCartsByVietnamStockerIsEqualToSomething() throws Exception {
        // Initialize the database
        User vietnamStocker = UserResourceIntTest.createEntity(em);
        em.persist(vietnamStocker);
        em.flush();
        orderCart.setVietnamStocker(vietnamStocker);
        orderCartRepository.saveAndFlush(orderCart);
        Long vietnamStockerId = vietnamStocker.getId();

        // Get all the orderCartList where vietnamStocker equals to vietnamStockerId
        defaultOrderCartShouldBeFound("vietnamStockerId.equals=" + vietnamStockerId);

        // Get all the orderCartList where vietnamStocker equals to vietnamStockerId + 1
        defaultOrderCartShouldNotBeFound("vietnamStockerId.equals=" + (vietnamStockerId + 1));
    }


    @Test
    @Transactional
    public void getAllOrderCartsByExporterIsEqualToSomething() throws Exception {
        // Initialize the database
        User exporter = UserResourceIntTest.createEntity(em);
        em.persist(exporter);
        em.flush();
        orderCart.setExporter(exporter);
        orderCartRepository.saveAndFlush(orderCart);
        Long exporterId = exporter.getId();

        // Get all the orderCartList where exporter equals to exporterId
        defaultOrderCartShouldBeFound("exporterId.equals=" + exporterId);

        // Get all the orderCartList where exporter equals to exporterId + 1
        defaultOrderCartShouldNotBeFound("exporterId.equals=" + (exporterId + 1));
    }


    @Test
    @Transactional
    public void getAllOrderCartsByCreateByIsEqualToSomething() throws Exception {
        // Initialize the database
        User createBy = UserResourceIntTest.createEntity(em);
        em.persist(createBy);
        em.flush();
        orderCart.setCreateBy(createBy);
        orderCartRepository.saveAndFlush(orderCart);
        Long createById = createBy.getId();

        // Get all the orderCartList where createBy equals to createById
        defaultOrderCartShouldBeFound("createById.equals=" + createById);

        // Get all the orderCartList where createBy equals to createById + 1
        defaultOrderCartShouldNotBeFound("createById.equals=" + (createById + 1));
    }


    @Test
    @Transactional
    public void getAllOrderCartsByUpdateByIsEqualToSomething() throws Exception {
        // Initialize the database
        User updateBy = UserResourceIntTest.createEntity(em);
        em.persist(updateBy);
        em.flush();
        orderCart.setUpdateBy(updateBy);
        orderCartRepository.saveAndFlush(orderCart);
        Long updateById = updateBy.getId();

        // Get all the orderCartList where updateBy equals to updateById
        defaultOrderCartShouldBeFound("updateById.equals=" + updateById);

        // Get all the orderCartList where updateBy equals to updateById + 1
        defaultOrderCartShouldNotBeFound("updateById.equals=" + (updateById + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultOrderCartShouldBeFound(String filter) throws Exception {
        restOrderCartMockMvc.perform(get("/api/order-carts?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(orderCart.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE.intValue())))
            .andExpect(jsonPath("$.[*].shippingChinaCode").value(hasItem(DEFAULT_SHIPPING_CHINA_CODE.toString())))
            .andExpect(jsonPath("$.[*].avatar").value(hasItem(DEFAULT_AVATAR.toString())))
            .andExpect(jsonPath("$.[*].aliwangwang").value(hasItem(DEFAULT_ALIWANGWANG.toString())))
            .andExpect(jsonPath("$.[*].amountDiscount").value(hasItem(DEFAULT_AMOUNT_DISCOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].amountPaid").value(hasItem(DEFAULT_AMOUNT_PAID.doubleValue())))
            .andExpect(jsonPath("$.[*].depositAmount").value(hasItem(DEFAULT_DEPOSIT_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].depositRatio").value(hasItem(DEFAULT_DEPOSIT_RATIO.doubleValue())))
            .andExpect(jsonPath("$.[*].depositTime").value(hasItem(DEFAULT_DEPOSIT_TIME.toString())))
            .andExpect(jsonPath("$.[*].domesticShippingChinaFeeNDT").value(hasItem(DEFAULT_DOMESTIC_SHIPPING_CHINA_FEE_NDT.doubleValue())))
            .andExpect(jsonPath("$.[*].domesticShippingChinaFee").value(hasItem(DEFAULT_DOMESTIC_SHIPPING_CHINA_FEE.doubleValue())))
            .andExpect(jsonPath("$.[*].domesticShippingVietnamFee").value(hasItem(DEFAULT_DOMESTIC_SHIPPING_VIETNAM_FEE.doubleValue())))
            .andExpect(jsonPath("$.[*].quantityOrder").value(hasItem(DEFAULT_QUANTITY_ORDER)))
            .andExpect(jsonPath("$.[*].quantityPending").value(hasItem(DEFAULT_QUANTITY_PENDING)))
            .andExpect(jsonPath("$.[*].quantityReceived").value(hasItem(DEFAULT_QUANTITY_RECEIVED)))
            .andExpect(jsonPath("$.[*].rate").value(hasItem(DEFAULT_RATE.doubleValue())))
            .andExpect(jsonPath("$.[*].receiverName").value(hasItem(DEFAULT_RECEIVER_NAME.toString())))
            .andExpect(jsonPath("$.[*].receiverAddress").value(hasItem(DEFAULT_RECEIVER_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].receiverMobile").value(hasItem(DEFAULT_RECEIVER_MOBILE.toString())))
            .andExpect(jsonPath("$.[*].receiverNote").value(hasItem(DEFAULT_RECEIVER_NOTE.toString())))
            .andExpect(jsonPath("$.[*].refundAmountByAlipay").value(hasItem(DEFAULT_REFUND_AMOUNT_BY_ALIPAY.doubleValue())))
            .andExpect(jsonPath("$.[*].refundAmountByComplaint").value(hasItem(DEFAULT_REFUND_AMOUNT_BY_COMPLAINT.doubleValue())))
            .andExpect(jsonPath("$.[*].refundAmountByOrder").value(hasItem(DEFAULT_REFUND_AMOUNT_BY_ORDER.doubleValue())))
            .andExpect(jsonPath("$.[*].refundAmountPending").value(hasItem(DEFAULT_REFUND_AMOUNT_PENDING.doubleValue())))
            .andExpect(jsonPath("$.[*].shippingChinaVietnamFee").value(hasItem(DEFAULT_SHIPPING_CHINA_VIETNAM_FEE.doubleValue())))
            .andExpect(jsonPath("$.[*].shippingChinaVietnamFeeDiscount").value(hasItem(DEFAULT_SHIPPING_CHINA_VIETNAM_FEE_DISCOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].serviceFee").value(hasItem(DEFAULT_SERVICE_FEE.doubleValue())))
            .andExpect(jsonPath("$.[*].serviceFeeDiscount").value(hasItem(DEFAULT_SERVICE_FEE_DISCOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].itemChecking").value(hasItem(DEFAULT_ITEM_CHECKING.booleanValue())))
            .andExpect(jsonPath("$.[*].itemWoodCrating").value(hasItem(DEFAULT_ITEM_WOOD_CRATING.booleanValue())))
            .andExpect(jsonPath("$.[*].shopId").value(hasItem(DEFAULT_SHOP_ID.toString())))
            .andExpect(jsonPath("$.[*].shopLink").value(hasItem(DEFAULT_SHOP_LINK.toString())))
            .andExpect(jsonPath("$.[*].shopName").value(hasItem(DEFAULT_SHOP_NAME.toString())))
            .andExpect(jsonPath("$.[*].shopNote").value(hasItem(DEFAULT_SHOP_NOTE.toString())))
            .andExpect(jsonPath("$.[*].website").value(hasItem(DEFAULT_WEBSITE.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].statusName").value(hasItem(DEFAULT_STATUS_NAME.toString())))
            .andExpect(jsonPath("$.[*].statusStyle").value(hasItem(DEFAULT_STATUS_STYLE.toString())))
            .andExpect(jsonPath("$.[*].tallyFee").value(hasItem(DEFAULT_TALLY_FEE.doubleValue())))
            .andExpect(jsonPath("$.[*].totalAmount").value(hasItem(DEFAULT_TOTAL_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].totalAmountNDT").value(hasItem(DEFAULT_TOTAL_AMOUNT_NDT.doubleValue())))
            .andExpect(jsonPath("$.[*].totalAmountChinaNDT").value(hasItem(DEFAULT_TOTAL_AMOUNT_CHINA_NDT.doubleValue())))
            .andExpect(jsonPath("$.[*].totalPaidByCustomer").value(hasItem(DEFAULT_TOTAL_PAID_BY_CUSTOMER.doubleValue())))
            .andExpect(jsonPath("$.[*].totalServiceFee").value(hasItem(DEFAULT_TOTAL_SERVICE_FEE.doubleValue())))
            .andExpect(jsonPath("$.[*].totalQuantity").value(hasItem(DEFAULT_TOTAL_QUANTITY)))
            .andExpect(jsonPath("$.[*].finalAmount").value(hasItem(DEFAULT_FINAL_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].createAt").value(hasItem(DEFAULT_CREATE_AT.toString())))
            .andExpect(jsonPath("$.[*].updateAt").value(hasItem(DEFAULT_UPDATE_AT.toString())));

        // Check, that the count call also returns 1
        restOrderCartMockMvc.perform(get("/api/order-carts/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultOrderCartShouldNotBeFound(String filter) throws Exception {
        restOrderCartMockMvc.perform(get("/api/order-carts?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restOrderCartMockMvc.perform(get("/api/order-carts/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingOrderCart() throws Exception {
        // Get the orderCart
        restOrderCartMockMvc.perform(get("/api/order-carts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOrderCart() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        int databaseSizeBeforeUpdate = orderCartRepository.findAll().size();

        // Update the orderCart
        OrderCart updatedOrderCart = orderCartRepository.findById(orderCart.getId()).get();
        // Disconnect from session so that the updates on updatedOrderCart are not directly saved in db
        em.detach(updatedOrderCart);
        updatedOrderCart
            .code(UPDATED_CODE)
            .shippingChinaCode(UPDATED_SHIPPING_CHINA_CODE)
            .avatar(UPDATED_AVATAR)
            .aliwangwang(UPDATED_ALIWANGWANG)
            .amountDiscount(UPDATED_AMOUNT_DISCOUNT)
            .amountPaid(UPDATED_AMOUNT_PAID)
            .depositAmount(UPDATED_DEPOSIT_AMOUNT)
            .depositRatio(UPDATED_DEPOSIT_RATIO)
            .depositTime(UPDATED_DEPOSIT_TIME)
            .domesticShippingChinaFeeNDT(UPDATED_DOMESTIC_SHIPPING_CHINA_FEE_NDT)
            .domesticShippingChinaFee(UPDATED_DOMESTIC_SHIPPING_CHINA_FEE)
            .domesticShippingVietnamFee(UPDATED_DOMESTIC_SHIPPING_VIETNAM_FEE)
            .quantityOrder(UPDATED_QUANTITY_ORDER)
            .quantityPending(UPDATED_QUANTITY_PENDING)
            .quantityReceived(UPDATED_QUANTITY_RECEIVED)
            .rate(UPDATED_RATE)
            .receiverName(UPDATED_RECEIVER_NAME)
            .receiverAddress(UPDATED_RECEIVER_ADDRESS)
            .receiverMobile(UPDATED_RECEIVER_MOBILE)
            .receiverNote(UPDATED_RECEIVER_NOTE)
            .refundAmountByAlipay(UPDATED_REFUND_AMOUNT_BY_ALIPAY)
            .refundAmountByComplaint(UPDATED_REFUND_AMOUNT_BY_COMPLAINT)
            .refundAmountByOrder(UPDATED_REFUND_AMOUNT_BY_ORDER)
            .refundAmountPending(UPDATED_REFUND_AMOUNT_PENDING)
            .shippingChinaVietnamFee(UPDATED_SHIPPING_CHINA_VIETNAM_FEE)
            .shippingChinaVietnamFeeDiscount(UPDATED_SHIPPING_CHINA_VIETNAM_FEE_DISCOUNT)
            .serviceFee(UPDATED_SERVICE_FEE)
            .serviceFeeDiscount(UPDATED_SERVICE_FEE_DISCOUNT)
            .itemChecking(UPDATED_ITEM_CHECKING)
            .itemWoodCrating(UPDATED_ITEM_WOOD_CRATING)
            .shopId(UPDATED_SHOP_ID)
            .shopLink(UPDATED_SHOP_LINK)
            .shopName(UPDATED_SHOP_NAME)
            .shopNote(UPDATED_SHOP_NOTE)
            .website(UPDATED_WEBSITE)
            .status(UPDATED_STATUS)
            .statusName(UPDATED_STATUS_NAME)
            .statusStyle(UPDATED_STATUS_STYLE)
            .tallyFee(UPDATED_TALLY_FEE)
            .totalAmount(UPDATED_TOTAL_AMOUNT)
            .totalAmountNDT(UPDATED_TOTAL_AMOUNT_NDT)
            .totalAmountChinaNDT(UPDATED_TOTAL_AMOUNT_CHINA_NDT)
            .totalPaidByCustomer(UPDATED_TOTAL_PAID_BY_CUSTOMER)
            .totalServiceFee(UPDATED_TOTAL_SERVICE_FEE)
            .totalQuantity(UPDATED_TOTAL_QUANTITY)
            .finalAmount(UPDATED_FINAL_AMOUNT)
            .createAt(UPDATED_CREATE_AT)
            .updateAt(UPDATED_UPDATE_AT);
        OrderCartDTO orderCartDTO = orderCartMapper.toDto(updatedOrderCart);

        restOrderCartMockMvc.perform(put("/api/order-carts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(orderCartDTO)))
            .andExpect(status().isOk());

        // Validate the OrderCart in the database
        List<OrderCart> orderCartList = orderCartRepository.findAll();
        assertThat(orderCartList).hasSize(databaseSizeBeforeUpdate);
        OrderCart testOrderCart = orderCartList.get(orderCartList.size() - 1);
        assertThat(testOrderCart.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testOrderCart.getShippingChinaCode()).isEqualTo(UPDATED_SHIPPING_CHINA_CODE);
        assertThat(testOrderCart.getAvatar()).isEqualTo(UPDATED_AVATAR);
        assertThat(testOrderCart.getAliwangwang()).isEqualTo(UPDATED_ALIWANGWANG);
        assertThat(testOrderCart.getAmountDiscount()).isEqualTo(UPDATED_AMOUNT_DISCOUNT);
        assertThat(testOrderCart.getAmountPaid()).isEqualTo(UPDATED_AMOUNT_PAID);
        assertThat(testOrderCart.getDepositAmount()).isEqualTo(UPDATED_DEPOSIT_AMOUNT);
        assertThat(testOrderCart.getDepositRatio()).isEqualTo(UPDATED_DEPOSIT_RATIO);
        assertThat(testOrderCart.getDepositTime()).isEqualTo(UPDATED_DEPOSIT_TIME);
        assertThat(testOrderCart.getDomesticShippingChinaFeeNDT()).isEqualTo(UPDATED_DOMESTIC_SHIPPING_CHINA_FEE_NDT);
        assertThat(testOrderCart.getDomesticShippingChinaFee()).isEqualTo(UPDATED_DOMESTIC_SHIPPING_CHINA_FEE);
        assertThat(testOrderCart.getDomesticShippingVietnamFee()).isEqualTo(UPDATED_DOMESTIC_SHIPPING_VIETNAM_FEE);
        assertThat(testOrderCart.getQuantityOrder()).isEqualTo(UPDATED_QUANTITY_ORDER);
        assertThat(testOrderCart.getQuantityPending()).isEqualTo(UPDATED_QUANTITY_PENDING);
        assertThat(testOrderCart.getQuantityReceived()).isEqualTo(UPDATED_QUANTITY_RECEIVED);
        assertThat(testOrderCart.getRate()).isEqualTo(UPDATED_RATE);
        assertThat(testOrderCart.getReceiverName()).isEqualTo(UPDATED_RECEIVER_NAME);
        assertThat(testOrderCart.getReceiverAddress()).isEqualTo(UPDATED_RECEIVER_ADDRESS);
        assertThat(testOrderCart.getReceiverMobile()).isEqualTo(UPDATED_RECEIVER_MOBILE);
        assertThat(testOrderCart.getReceiverNote()).isEqualTo(UPDATED_RECEIVER_NOTE);
        assertThat(testOrderCart.getRefundAmountByAlipay()).isEqualTo(UPDATED_REFUND_AMOUNT_BY_ALIPAY);
        assertThat(testOrderCart.getRefundAmountByComplaint()).isEqualTo(UPDATED_REFUND_AMOUNT_BY_COMPLAINT);
        assertThat(testOrderCart.getRefundAmountByOrder()).isEqualTo(UPDATED_REFUND_AMOUNT_BY_ORDER);
        assertThat(testOrderCart.getRefundAmountPending()).isEqualTo(UPDATED_REFUND_AMOUNT_PENDING);
        assertThat(testOrderCart.getShippingChinaVietnamFee()).isEqualTo(UPDATED_SHIPPING_CHINA_VIETNAM_FEE);
        assertThat(testOrderCart.getShippingChinaVietnamFeeDiscount()).isEqualTo(UPDATED_SHIPPING_CHINA_VIETNAM_FEE_DISCOUNT);
        assertThat(testOrderCart.getServiceFee()).isEqualTo(UPDATED_SERVICE_FEE);
        assertThat(testOrderCart.getServiceFeeDiscount()).isEqualTo(UPDATED_SERVICE_FEE_DISCOUNT);
        assertThat(testOrderCart.isItemChecking()).isEqualTo(UPDATED_ITEM_CHECKING);
        assertThat(testOrderCart.isItemWoodCrating()).isEqualTo(UPDATED_ITEM_WOOD_CRATING);
        assertThat(testOrderCart.getShopId()).isEqualTo(UPDATED_SHOP_ID);
        assertThat(testOrderCart.getShopLink()).isEqualTo(UPDATED_SHOP_LINK);
        assertThat(testOrderCart.getShopName()).isEqualTo(UPDATED_SHOP_NAME);
        assertThat(testOrderCart.getShopNote()).isEqualTo(UPDATED_SHOP_NOTE);
        assertThat(testOrderCart.getWebsite()).isEqualTo(UPDATED_WEBSITE);
        assertThat(testOrderCart.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testOrderCart.getStatusName()).isEqualTo(UPDATED_STATUS_NAME);
        assertThat(testOrderCart.getStatusStyle()).isEqualTo(UPDATED_STATUS_STYLE);
        assertThat(testOrderCart.getTallyFee()).isEqualTo(UPDATED_TALLY_FEE);
        assertThat(testOrderCart.getTotalAmount()).isEqualTo(UPDATED_TOTAL_AMOUNT);
        assertThat(testOrderCart.getTotalAmountNDT()).isEqualTo(UPDATED_TOTAL_AMOUNT_NDT);
        assertThat(testOrderCart.getTotalAmountChinaNDT()).isEqualTo(UPDATED_TOTAL_AMOUNT_CHINA_NDT);
        assertThat(testOrderCart.getTotalPaidByCustomer()).isEqualTo(UPDATED_TOTAL_PAID_BY_CUSTOMER);
        assertThat(testOrderCart.getTotalServiceFee()).isEqualTo(UPDATED_TOTAL_SERVICE_FEE);
        assertThat(testOrderCart.getTotalQuantity()).isEqualTo(UPDATED_TOTAL_QUANTITY);
        assertThat(testOrderCart.getFinalAmount()).isEqualTo(UPDATED_FINAL_AMOUNT);
        assertThat(testOrderCart.getCreateAt()).isEqualTo(UPDATED_CREATE_AT);
        assertThat(testOrderCart.getUpdateAt()).isEqualTo(UPDATED_UPDATE_AT);
    }

    @Test
    @Transactional
    public void updateNonExistingOrderCart() throws Exception {
        int databaseSizeBeforeUpdate = orderCartRepository.findAll().size();

        // Create the OrderCart
        OrderCartDTO orderCartDTO = orderCartMapper.toDto(orderCart);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOrderCartMockMvc.perform(put("/api/order-carts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(orderCartDTO)))
            .andExpect(status().isBadRequest());

        // Validate the OrderCart in the database
        List<OrderCart> orderCartList = orderCartRepository.findAll();
        assertThat(orderCartList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteOrderCart() throws Exception {
        // Initialize the database
        orderCartRepository.saveAndFlush(orderCart);

        int databaseSizeBeforeDelete = orderCartRepository.findAll().size();

        // Get the orderCart
        restOrderCartMockMvc.perform(delete("/api/order-carts/{id}", orderCart.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<OrderCart> orderCartList = orderCartRepository.findAll();
        assertThat(orderCartList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OrderCart.class);
        OrderCart orderCart1 = new OrderCart();
        orderCart1.setId(1L);
        OrderCart orderCart2 = new OrderCart();
        orderCart2.setId(orderCart1.getId());
        assertThat(orderCart1).isEqualTo(orderCart2);
        orderCart2.setId(2L);
        assertThat(orderCart1).isNotEqualTo(orderCart2);
        orderCart1.setId(null);
        assertThat(orderCart1).isNotEqualTo(orderCart2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(OrderCartDTO.class);
        OrderCartDTO orderCartDTO1 = new OrderCartDTO();
        orderCartDTO1.setId(1L);
        OrderCartDTO orderCartDTO2 = new OrderCartDTO();
        assertThat(orderCartDTO1).isNotEqualTo(orderCartDTO2);
        orderCartDTO2.setId(orderCartDTO1.getId());
        assertThat(orderCartDTO1).isEqualTo(orderCartDTO2);
        orderCartDTO2.setId(2L);
        assertThat(orderCartDTO1).isNotEqualTo(orderCartDTO2);
        orderCartDTO1.setId(null);
        assertThat(orderCartDTO1).isNotEqualTo(orderCartDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(orderCartMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(orderCartMapper.fromId(null)).isNull();
    }
}
