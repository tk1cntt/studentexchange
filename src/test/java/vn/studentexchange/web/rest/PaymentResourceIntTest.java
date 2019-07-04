package vn.studentexchange.web.rest;

import vn.studentexchange.StudentexchangeApp;

import vn.studentexchange.domain.Payment;
import vn.studentexchange.domain.User;
import vn.studentexchange.domain.User;
import vn.studentexchange.domain.User;
import vn.studentexchange.domain.User;
import vn.studentexchange.repository.PaymentRepository;
import vn.studentexchange.service.PaymentService;
import vn.studentexchange.service.dto.PaymentDTO;
import vn.studentexchange.service.mapper.PaymentMapper;
import vn.studentexchange.web.rest.errors.ExceptionTranslator;
import vn.studentexchange.service.dto.PaymentCriteria;
import vn.studentexchange.service.PaymentQueryService;

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

import vn.studentexchange.domain.enumeration.PaymentMethod;
import vn.studentexchange.domain.enumeration.PaymentType;
import vn.studentexchange.domain.enumeration.PaymentStatusType;
/**
 * Test class for the PaymentResource REST controller.
 *
 * @see PaymentResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = StudentexchangeApp.class)
public class PaymentResourceIntTest {

    private static final Float DEFAULT_AMOUNT = 1F;
    private static final Float UPDATED_AMOUNT = 2F;

    private static final Long DEFAULT_CODE = 1L;
    private static final Long UPDATED_CODE = 2L;

    private static final Float DEFAULT_NEW_BALANCE = 1F;
    private static final Float UPDATED_NEW_BALANCE = 2F;

    private static final String DEFAULT_NOTE = "AAAAAAAAAA";
    private static final String UPDATED_NOTE = "BBBBBBBBBB";

    private static final String DEFAULT_ORDER_CODE = "AAAAAAAAAA";
    private static final String UPDATED_ORDER_CODE = "BBBBBBBBBB";

    private static final PaymentMethod DEFAULT_METHOD = PaymentMethod.CASH;
    private static final PaymentMethod UPDATED_METHOD = PaymentMethod.BANK_TRANSFER;

    private static final PaymentType DEFAULT_TYPE = PaymentType.DEPOSIT;
    private static final PaymentType UPDATED_TYPE = PaymentType.ORDER_PAYMENT;

    private static final PaymentStatusType DEFAULT_STATUS = PaymentStatusType.PENDING;
    private static final PaymentStatusType UPDATED_STATUS = PaymentStatusType.PAID;

    private static final Instant DEFAULT_CREATE_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATE_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Float DEFAULT_WITHDRAWAL_FEE = 1F;
    private static final Float UPDATED_WITHDRAWAL_FEE = 2F;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private PaymentMapper paymentMapper;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private PaymentQueryService paymentQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restPaymentMockMvc;

    private Payment payment;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PaymentResource paymentResource = new PaymentResource(paymentService);
        this.restPaymentMockMvc = MockMvcBuilders.standaloneSetup(paymentResource)
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
    public static Payment createEntity(EntityManager em) {
        Payment payment = new Payment()
            .amount(DEFAULT_AMOUNT)
            .code(DEFAULT_CODE)
            .newBalance(DEFAULT_NEW_BALANCE)
            .note(DEFAULT_NOTE)
            .orderCode(DEFAULT_ORDER_CODE)
            .method(DEFAULT_METHOD)
            .type(DEFAULT_TYPE)
            .status(DEFAULT_STATUS)
            .createAt(DEFAULT_CREATE_AT)
            .withdrawalFee(DEFAULT_WITHDRAWAL_FEE);
        return payment;
    }

    @Before
    public void initTest() {
        payment = createEntity(em);
    }

    @Test
    @Transactional
    public void createPayment() throws Exception {
        int databaseSizeBeforeCreate = paymentRepository.findAll().size();

        // Create the Payment
        PaymentDTO paymentDTO = paymentMapper.toDto(payment);
        restPaymentMockMvc.perform(post("/api/payments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(paymentDTO)))
            .andExpect(status().isCreated());

        // Validate the Payment in the database
        List<Payment> paymentList = paymentRepository.findAll();
        assertThat(paymentList).hasSize(databaseSizeBeforeCreate + 1);
        Payment testPayment = paymentList.get(paymentList.size() - 1);
        assertThat(testPayment.getAmount()).isEqualTo(DEFAULT_AMOUNT);
        assertThat(testPayment.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testPayment.getNewBalance()).isEqualTo(DEFAULT_NEW_BALANCE);
        assertThat(testPayment.getNote()).isEqualTo(DEFAULT_NOTE);
        assertThat(testPayment.getOrderCode()).isEqualTo(DEFAULT_ORDER_CODE);
        assertThat(testPayment.getMethod()).isEqualTo(DEFAULT_METHOD);
        assertThat(testPayment.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testPayment.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testPayment.getCreateAt()).isEqualTo(DEFAULT_CREATE_AT);
        assertThat(testPayment.getWithdrawalFee()).isEqualTo(DEFAULT_WITHDRAWAL_FEE);
    }

    @Test
    @Transactional
    public void createPaymentWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = paymentRepository.findAll().size();

        // Create the Payment with an existing ID
        payment.setId(1L);
        PaymentDTO paymentDTO = paymentMapper.toDto(payment);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPaymentMockMvc.perform(post("/api/payments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(paymentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Payment in the database
        List<Payment> paymentList = paymentRepository.findAll();
        assertThat(paymentList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllPayments() throws Exception {
        // Initialize the database
        paymentRepository.saveAndFlush(payment);

        // Get all the paymentList
        restPaymentMockMvc.perform(get("/api/payments?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(payment.getId().intValue())))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE.intValue())))
            .andExpect(jsonPath("$.[*].newBalance").value(hasItem(DEFAULT_NEW_BALANCE.doubleValue())))
            .andExpect(jsonPath("$.[*].note").value(hasItem(DEFAULT_NOTE.toString())))
            .andExpect(jsonPath("$.[*].orderCode").value(hasItem(DEFAULT_ORDER_CODE.toString())))
            .andExpect(jsonPath("$.[*].method").value(hasItem(DEFAULT_METHOD.toString())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].createAt").value(hasItem(DEFAULT_CREATE_AT.toString())))
            .andExpect(jsonPath("$.[*].withdrawalFee").value(hasItem(DEFAULT_WITHDRAWAL_FEE.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getPayment() throws Exception {
        // Initialize the database
        paymentRepository.saveAndFlush(payment);

        // Get the payment
        restPaymentMockMvc.perform(get("/api/payments/{id}", payment.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(payment.getId().intValue()))
            .andExpect(jsonPath("$.amount").value(DEFAULT_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE.intValue()))
            .andExpect(jsonPath("$.newBalance").value(DEFAULT_NEW_BALANCE.doubleValue()))
            .andExpect(jsonPath("$.note").value(DEFAULT_NOTE.toString()))
            .andExpect(jsonPath("$.orderCode").value(DEFAULT_ORDER_CODE.toString()))
            .andExpect(jsonPath("$.method").value(DEFAULT_METHOD.toString()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.createAt").value(DEFAULT_CREATE_AT.toString()))
            .andExpect(jsonPath("$.withdrawalFee").value(DEFAULT_WITHDRAWAL_FEE.doubleValue()));
    }

    @Test
    @Transactional
    public void getAllPaymentsByAmountIsEqualToSomething() throws Exception {
        // Initialize the database
        paymentRepository.saveAndFlush(payment);

        // Get all the paymentList where amount equals to DEFAULT_AMOUNT
        defaultPaymentShouldBeFound("amount.equals=" + DEFAULT_AMOUNT);

        // Get all the paymentList where amount equals to UPDATED_AMOUNT
        defaultPaymentShouldNotBeFound("amount.equals=" + UPDATED_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllPaymentsByAmountIsInShouldWork() throws Exception {
        // Initialize the database
        paymentRepository.saveAndFlush(payment);

        // Get all the paymentList where amount in DEFAULT_AMOUNT or UPDATED_AMOUNT
        defaultPaymentShouldBeFound("amount.in=" + DEFAULT_AMOUNT + "," + UPDATED_AMOUNT);

        // Get all the paymentList where amount equals to UPDATED_AMOUNT
        defaultPaymentShouldNotBeFound("amount.in=" + UPDATED_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllPaymentsByAmountIsNullOrNotNull() throws Exception {
        // Initialize the database
        paymentRepository.saveAndFlush(payment);

        // Get all the paymentList where amount is not null
        defaultPaymentShouldBeFound("amount.specified=true");

        // Get all the paymentList where amount is null
        defaultPaymentShouldNotBeFound("amount.specified=false");
    }

    @Test
    @Transactional
    public void getAllPaymentsByCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        paymentRepository.saveAndFlush(payment);

        // Get all the paymentList where code equals to DEFAULT_CODE
        defaultPaymentShouldBeFound("code.equals=" + DEFAULT_CODE);

        // Get all the paymentList where code equals to UPDATED_CODE
        defaultPaymentShouldNotBeFound("code.equals=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllPaymentsByCodeIsInShouldWork() throws Exception {
        // Initialize the database
        paymentRepository.saveAndFlush(payment);

        // Get all the paymentList where code in DEFAULT_CODE or UPDATED_CODE
        defaultPaymentShouldBeFound("code.in=" + DEFAULT_CODE + "," + UPDATED_CODE);

        // Get all the paymentList where code equals to UPDATED_CODE
        defaultPaymentShouldNotBeFound("code.in=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllPaymentsByCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        paymentRepository.saveAndFlush(payment);

        // Get all the paymentList where code is not null
        defaultPaymentShouldBeFound("code.specified=true");

        // Get all the paymentList where code is null
        defaultPaymentShouldNotBeFound("code.specified=false");
    }

    @Test
    @Transactional
    public void getAllPaymentsByCodeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        paymentRepository.saveAndFlush(payment);

        // Get all the paymentList where code greater than or equals to DEFAULT_CODE
        defaultPaymentShouldBeFound("code.greaterOrEqualThan=" + DEFAULT_CODE);

        // Get all the paymentList where code greater than or equals to UPDATED_CODE
        defaultPaymentShouldNotBeFound("code.greaterOrEqualThan=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllPaymentsByCodeIsLessThanSomething() throws Exception {
        // Initialize the database
        paymentRepository.saveAndFlush(payment);

        // Get all the paymentList where code less than or equals to DEFAULT_CODE
        defaultPaymentShouldNotBeFound("code.lessThan=" + DEFAULT_CODE);

        // Get all the paymentList where code less than or equals to UPDATED_CODE
        defaultPaymentShouldBeFound("code.lessThan=" + UPDATED_CODE);
    }


    @Test
    @Transactional
    public void getAllPaymentsByNewBalanceIsEqualToSomething() throws Exception {
        // Initialize the database
        paymentRepository.saveAndFlush(payment);

        // Get all the paymentList where newBalance equals to DEFAULT_NEW_BALANCE
        defaultPaymentShouldBeFound("newBalance.equals=" + DEFAULT_NEW_BALANCE);

        // Get all the paymentList where newBalance equals to UPDATED_NEW_BALANCE
        defaultPaymentShouldNotBeFound("newBalance.equals=" + UPDATED_NEW_BALANCE);
    }

    @Test
    @Transactional
    public void getAllPaymentsByNewBalanceIsInShouldWork() throws Exception {
        // Initialize the database
        paymentRepository.saveAndFlush(payment);

        // Get all the paymentList where newBalance in DEFAULT_NEW_BALANCE or UPDATED_NEW_BALANCE
        defaultPaymentShouldBeFound("newBalance.in=" + DEFAULT_NEW_BALANCE + "," + UPDATED_NEW_BALANCE);

        // Get all the paymentList where newBalance equals to UPDATED_NEW_BALANCE
        defaultPaymentShouldNotBeFound("newBalance.in=" + UPDATED_NEW_BALANCE);
    }

    @Test
    @Transactional
    public void getAllPaymentsByNewBalanceIsNullOrNotNull() throws Exception {
        // Initialize the database
        paymentRepository.saveAndFlush(payment);

        // Get all the paymentList where newBalance is not null
        defaultPaymentShouldBeFound("newBalance.specified=true");

        // Get all the paymentList where newBalance is null
        defaultPaymentShouldNotBeFound("newBalance.specified=false");
    }

    @Test
    @Transactional
    public void getAllPaymentsByNoteIsEqualToSomething() throws Exception {
        // Initialize the database
        paymentRepository.saveAndFlush(payment);

        // Get all the paymentList where note equals to DEFAULT_NOTE
        defaultPaymentShouldBeFound("note.equals=" + DEFAULT_NOTE);

        // Get all the paymentList where note equals to UPDATED_NOTE
        defaultPaymentShouldNotBeFound("note.equals=" + UPDATED_NOTE);
    }

    @Test
    @Transactional
    public void getAllPaymentsByNoteIsInShouldWork() throws Exception {
        // Initialize the database
        paymentRepository.saveAndFlush(payment);

        // Get all the paymentList where note in DEFAULT_NOTE or UPDATED_NOTE
        defaultPaymentShouldBeFound("note.in=" + DEFAULT_NOTE + "," + UPDATED_NOTE);

        // Get all the paymentList where note equals to UPDATED_NOTE
        defaultPaymentShouldNotBeFound("note.in=" + UPDATED_NOTE);
    }

    @Test
    @Transactional
    public void getAllPaymentsByNoteIsNullOrNotNull() throws Exception {
        // Initialize the database
        paymentRepository.saveAndFlush(payment);

        // Get all the paymentList where note is not null
        defaultPaymentShouldBeFound("note.specified=true");

        // Get all the paymentList where note is null
        defaultPaymentShouldNotBeFound("note.specified=false");
    }

    @Test
    @Transactional
    public void getAllPaymentsByOrderCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        paymentRepository.saveAndFlush(payment);

        // Get all the paymentList where orderCode equals to DEFAULT_ORDER_CODE
        defaultPaymentShouldBeFound("orderCode.equals=" + DEFAULT_ORDER_CODE);

        // Get all the paymentList where orderCode equals to UPDATED_ORDER_CODE
        defaultPaymentShouldNotBeFound("orderCode.equals=" + UPDATED_ORDER_CODE);
    }

    @Test
    @Transactional
    public void getAllPaymentsByOrderCodeIsInShouldWork() throws Exception {
        // Initialize the database
        paymentRepository.saveAndFlush(payment);

        // Get all the paymentList where orderCode in DEFAULT_ORDER_CODE or UPDATED_ORDER_CODE
        defaultPaymentShouldBeFound("orderCode.in=" + DEFAULT_ORDER_CODE + "," + UPDATED_ORDER_CODE);

        // Get all the paymentList where orderCode equals to UPDATED_ORDER_CODE
        defaultPaymentShouldNotBeFound("orderCode.in=" + UPDATED_ORDER_CODE);
    }

    @Test
    @Transactional
    public void getAllPaymentsByOrderCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        paymentRepository.saveAndFlush(payment);

        // Get all the paymentList where orderCode is not null
        defaultPaymentShouldBeFound("orderCode.specified=true");

        // Get all the paymentList where orderCode is null
        defaultPaymentShouldNotBeFound("orderCode.specified=false");
    }

    @Test
    @Transactional
    public void getAllPaymentsByMethodIsEqualToSomething() throws Exception {
        // Initialize the database
        paymentRepository.saveAndFlush(payment);

        // Get all the paymentList where method equals to DEFAULT_METHOD
        defaultPaymentShouldBeFound("method.equals=" + DEFAULT_METHOD);

        // Get all the paymentList where method equals to UPDATED_METHOD
        defaultPaymentShouldNotBeFound("method.equals=" + UPDATED_METHOD);
    }

    @Test
    @Transactional
    public void getAllPaymentsByMethodIsInShouldWork() throws Exception {
        // Initialize the database
        paymentRepository.saveAndFlush(payment);

        // Get all the paymentList where method in DEFAULT_METHOD or UPDATED_METHOD
        defaultPaymentShouldBeFound("method.in=" + DEFAULT_METHOD + "," + UPDATED_METHOD);

        // Get all the paymentList where method equals to UPDATED_METHOD
        defaultPaymentShouldNotBeFound("method.in=" + UPDATED_METHOD);
    }

    @Test
    @Transactional
    public void getAllPaymentsByMethodIsNullOrNotNull() throws Exception {
        // Initialize the database
        paymentRepository.saveAndFlush(payment);

        // Get all the paymentList where method is not null
        defaultPaymentShouldBeFound("method.specified=true");

        // Get all the paymentList where method is null
        defaultPaymentShouldNotBeFound("method.specified=false");
    }

    @Test
    @Transactional
    public void getAllPaymentsByTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        paymentRepository.saveAndFlush(payment);

        // Get all the paymentList where type equals to DEFAULT_TYPE
        defaultPaymentShouldBeFound("type.equals=" + DEFAULT_TYPE);

        // Get all the paymentList where type equals to UPDATED_TYPE
        defaultPaymentShouldNotBeFound("type.equals=" + UPDATED_TYPE);
    }

    @Test
    @Transactional
    public void getAllPaymentsByTypeIsInShouldWork() throws Exception {
        // Initialize the database
        paymentRepository.saveAndFlush(payment);

        // Get all the paymentList where type in DEFAULT_TYPE or UPDATED_TYPE
        defaultPaymentShouldBeFound("type.in=" + DEFAULT_TYPE + "," + UPDATED_TYPE);

        // Get all the paymentList where type equals to UPDATED_TYPE
        defaultPaymentShouldNotBeFound("type.in=" + UPDATED_TYPE);
    }

    @Test
    @Transactional
    public void getAllPaymentsByTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        paymentRepository.saveAndFlush(payment);

        // Get all the paymentList where type is not null
        defaultPaymentShouldBeFound("type.specified=true");

        // Get all the paymentList where type is null
        defaultPaymentShouldNotBeFound("type.specified=false");
    }

    @Test
    @Transactional
    public void getAllPaymentsByStatusIsEqualToSomething() throws Exception {
        // Initialize the database
        paymentRepository.saveAndFlush(payment);

        // Get all the paymentList where status equals to DEFAULT_STATUS
        defaultPaymentShouldBeFound("status.equals=" + DEFAULT_STATUS);

        // Get all the paymentList where status equals to UPDATED_STATUS
        defaultPaymentShouldNotBeFound("status.equals=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllPaymentsByStatusIsInShouldWork() throws Exception {
        // Initialize the database
        paymentRepository.saveAndFlush(payment);

        // Get all the paymentList where status in DEFAULT_STATUS or UPDATED_STATUS
        defaultPaymentShouldBeFound("status.in=" + DEFAULT_STATUS + "," + UPDATED_STATUS);

        // Get all the paymentList where status equals to UPDATED_STATUS
        defaultPaymentShouldNotBeFound("status.in=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllPaymentsByStatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        paymentRepository.saveAndFlush(payment);

        // Get all the paymentList where status is not null
        defaultPaymentShouldBeFound("status.specified=true");

        // Get all the paymentList where status is null
        defaultPaymentShouldNotBeFound("status.specified=false");
    }

    @Test
    @Transactional
    public void getAllPaymentsByCreateAtIsEqualToSomething() throws Exception {
        // Initialize the database
        paymentRepository.saveAndFlush(payment);

        // Get all the paymentList where createAt equals to DEFAULT_CREATE_AT
        defaultPaymentShouldBeFound("createAt.equals=" + DEFAULT_CREATE_AT);

        // Get all the paymentList where createAt equals to UPDATED_CREATE_AT
        defaultPaymentShouldNotBeFound("createAt.equals=" + UPDATED_CREATE_AT);
    }

    @Test
    @Transactional
    public void getAllPaymentsByCreateAtIsInShouldWork() throws Exception {
        // Initialize the database
        paymentRepository.saveAndFlush(payment);

        // Get all the paymentList where createAt in DEFAULT_CREATE_AT or UPDATED_CREATE_AT
        defaultPaymentShouldBeFound("createAt.in=" + DEFAULT_CREATE_AT + "," + UPDATED_CREATE_AT);

        // Get all the paymentList where createAt equals to UPDATED_CREATE_AT
        defaultPaymentShouldNotBeFound("createAt.in=" + UPDATED_CREATE_AT);
    }

    @Test
    @Transactional
    public void getAllPaymentsByCreateAtIsNullOrNotNull() throws Exception {
        // Initialize the database
        paymentRepository.saveAndFlush(payment);

        // Get all the paymentList where createAt is not null
        defaultPaymentShouldBeFound("createAt.specified=true");

        // Get all the paymentList where createAt is null
        defaultPaymentShouldNotBeFound("createAt.specified=false");
    }

    @Test
    @Transactional
    public void getAllPaymentsByWithdrawalFeeIsEqualToSomething() throws Exception {
        // Initialize the database
        paymentRepository.saveAndFlush(payment);

        // Get all the paymentList where withdrawalFee equals to DEFAULT_WITHDRAWAL_FEE
        defaultPaymentShouldBeFound("withdrawalFee.equals=" + DEFAULT_WITHDRAWAL_FEE);

        // Get all the paymentList where withdrawalFee equals to UPDATED_WITHDRAWAL_FEE
        defaultPaymentShouldNotBeFound("withdrawalFee.equals=" + UPDATED_WITHDRAWAL_FEE);
    }

    @Test
    @Transactional
    public void getAllPaymentsByWithdrawalFeeIsInShouldWork() throws Exception {
        // Initialize the database
        paymentRepository.saveAndFlush(payment);

        // Get all the paymentList where withdrawalFee in DEFAULT_WITHDRAWAL_FEE or UPDATED_WITHDRAWAL_FEE
        defaultPaymentShouldBeFound("withdrawalFee.in=" + DEFAULT_WITHDRAWAL_FEE + "," + UPDATED_WITHDRAWAL_FEE);

        // Get all the paymentList where withdrawalFee equals to UPDATED_WITHDRAWAL_FEE
        defaultPaymentShouldNotBeFound("withdrawalFee.in=" + UPDATED_WITHDRAWAL_FEE);
    }

    @Test
    @Transactional
    public void getAllPaymentsByWithdrawalFeeIsNullOrNotNull() throws Exception {
        // Initialize the database
        paymentRepository.saveAndFlush(payment);

        // Get all the paymentList where withdrawalFee is not null
        defaultPaymentShouldBeFound("withdrawalFee.specified=true");

        // Get all the paymentList where withdrawalFee is null
        defaultPaymentShouldNotBeFound("withdrawalFee.specified=false");
    }

    @Test
    @Transactional
    public void getAllPaymentsByStaffApprovalIsEqualToSomething() throws Exception {
        // Initialize the database
        User staffApproval = UserResourceIntTest.createEntity(em);
        em.persist(staffApproval);
        em.flush();
        payment.setStaffApproval(staffApproval);
        paymentRepository.saveAndFlush(payment);
        Long staffApprovalId = staffApproval.getId();

        // Get all the paymentList where staffApproval equals to staffApprovalId
        defaultPaymentShouldBeFound("staffApprovalId.equals=" + staffApprovalId);

        // Get all the paymentList where staffApproval equals to staffApprovalId + 1
        defaultPaymentShouldNotBeFound("staffApprovalId.equals=" + (staffApprovalId + 1));
    }


    @Test
    @Transactional
    public void getAllPaymentsByStaffCancelIsEqualToSomething() throws Exception {
        // Initialize the database
        User staffCancel = UserResourceIntTest.createEntity(em);
        em.persist(staffCancel);
        em.flush();
        payment.setStaffCancel(staffCancel);
        paymentRepository.saveAndFlush(payment);
        Long staffCancelId = staffCancel.getId();

        // Get all the paymentList where staffCancel equals to staffCancelId
        defaultPaymentShouldBeFound("staffCancelId.equals=" + staffCancelId);

        // Get all the paymentList where staffCancel equals to staffCancelId + 1
        defaultPaymentShouldNotBeFound("staffCancelId.equals=" + (staffCancelId + 1));
    }


    @Test
    @Transactional
    public void getAllPaymentsByCustomerIsEqualToSomething() throws Exception {
        // Initialize the database
        User customer = UserResourceIntTest.createEntity(em);
        em.persist(customer);
        em.flush();
        payment.setCustomer(customer);
        paymentRepository.saveAndFlush(payment);
        Long customerId = customer.getId();

        // Get all the paymentList where customer equals to customerId
        defaultPaymentShouldBeFound("customerId.equals=" + customerId);

        // Get all the paymentList where customer equals to customerId + 1
        defaultPaymentShouldNotBeFound("customerId.equals=" + (customerId + 1));
    }


    @Test
    @Transactional
    public void getAllPaymentsByCreateByIsEqualToSomething() throws Exception {
        // Initialize the database
        User createBy = UserResourceIntTest.createEntity(em);
        em.persist(createBy);
        em.flush();
        payment.setCreateBy(createBy);
        paymentRepository.saveAndFlush(payment);
        Long createById = createBy.getId();

        // Get all the paymentList where createBy equals to createById
        defaultPaymentShouldBeFound("createById.equals=" + createById);

        // Get all the paymentList where createBy equals to createById + 1
        defaultPaymentShouldNotBeFound("createById.equals=" + (createById + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultPaymentShouldBeFound(String filter) throws Exception {
        restPaymentMockMvc.perform(get("/api/payments?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(payment.getId().intValue())))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE.intValue())))
            .andExpect(jsonPath("$.[*].newBalance").value(hasItem(DEFAULT_NEW_BALANCE.doubleValue())))
            .andExpect(jsonPath("$.[*].note").value(hasItem(DEFAULT_NOTE.toString())))
            .andExpect(jsonPath("$.[*].orderCode").value(hasItem(DEFAULT_ORDER_CODE.toString())))
            .andExpect(jsonPath("$.[*].method").value(hasItem(DEFAULT_METHOD.toString())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].createAt").value(hasItem(DEFAULT_CREATE_AT.toString())))
            .andExpect(jsonPath("$.[*].withdrawalFee").value(hasItem(DEFAULT_WITHDRAWAL_FEE.doubleValue())));

        // Check, that the count call also returns 1
        restPaymentMockMvc.perform(get("/api/payments/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultPaymentShouldNotBeFound(String filter) throws Exception {
        restPaymentMockMvc.perform(get("/api/payments?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restPaymentMockMvc.perform(get("/api/payments/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingPayment() throws Exception {
        // Get the payment
        restPaymentMockMvc.perform(get("/api/payments/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePayment() throws Exception {
        // Initialize the database
        paymentRepository.saveAndFlush(payment);

        int databaseSizeBeforeUpdate = paymentRepository.findAll().size();

        // Update the payment
        Payment updatedPayment = paymentRepository.findById(payment.getId()).get();
        // Disconnect from session so that the updates on updatedPayment are not directly saved in db
        em.detach(updatedPayment);
        updatedPayment
            .amount(UPDATED_AMOUNT)
            .code(UPDATED_CODE)
            .newBalance(UPDATED_NEW_BALANCE)
            .note(UPDATED_NOTE)
            .orderCode(UPDATED_ORDER_CODE)
            .method(UPDATED_METHOD)
            .type(UPDATED_TYPE)
            .status(UPDATED_STATUS)
            .createAt(UPDATED_CREATE_AT)
            .withdrawalFee(UPDATED_WITHDRAWAL_FEE);
        PaymentDTO paymentDTO = paymentMapper.toDto(updatedPayment);

        restPaymentMockMvc.perform(put("/api/payments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(paymentDTO)))
            .andExpect(status().isOk());

        // Validate the Payment in the database
        List<Payment> paymentList = paymentRepository.findAll();
        assertThat(paymentList).hasSize(databaseSizeBeforeUpdate);
        Payment testPayment = paymentList.get(paymentList.size() - 1);
        assertThat(testPayment.getAmount()).isEqualTo(UPDATED_AMOUNT);
        assertThat(testPayment.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testPayment.getNewBalance()).isEqualTo(UPDATED_NEW_BALANCE);
        assertThat(testPayment.getNote()).isEqualTo(UPDATED_NOTE);
        assertThat(testPayment.getOrderCode()).isEqualTo(UPDATED_ORDER_CODE);
        assertThat(testPayment.getMethod()).isEqualTo(UPDATED_METHOD);
        assertThat(testPayment.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testPayment.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testPayment.getCreateAt()).isEqualTo(UPDATED_CREATE_AT);
        assertThat(testPayment.getWithdrawalFee()).isEqualTo(UPDATED_WITHDRAWAL_FEE);
    }

    @Test
    @Transactional
    public void updateNonExistingPayment() throws Exception {
        int databaseSizeBeforeUpdate = paymentRepository.findAll().size();

        // Create the Payment
        PaymentDTO paymentDTO = paymentMapper.toDto(payment);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPaymentMockMvc.perform(put("/api/payments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(paymentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Payment in the database
        List<Payment> paymentList = paymentRepository.findAll();
        assertThat(paymentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePayment() throws Exception {
        // Initialize the database
        paymentRepository.saveAndFlush(payment);

        int databaseSizeBeforeDelete = paymentRepository.findAll().size();

        // Get the payment
        restPaymentMockMvc.perform(delete("/api/payments/{id}", payment.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Payment> paymentList = paymentRepository.findAll();
        assertThat(paymentList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Payment.class);
        Payment payment1 = new Payment();
        payment1.setId(1L);
        Payment payment2 = new Payment();
        payment2.setId(payment1.getId());
        assertThat(payment1).isEqualTo(payment2);
        payment2.setId(2L);
        assertThat(payment1).isNotEqualTo(payment2);
        payment1.setId(null);
        assertThat(payment1).isNotEqualTo(payment2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PaymentDTO.class);
        PaymentDTO paymentDTO1 = new PaymentDTO();
        paymentDTO1.setId(1L);
        PaymentDTO paymentDTO2 = new PaymentDTO();
        assertThat(paymentDTO1).isNotEqualTo(paymentDTO2);
        paymentDTO2.setId(paymentDTO1.getId());
        assertThat(paymentDTO1).isEqualTo(paymentDTO2);
        paymentDTO2.setId(2L);
        assertThat(paymentDTO1).isNotEqualTo(paymentDTO2);
        paymentDTO1.setId(null);
        assertThat(paymentDTO1).isNotEqualTo(paymentDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(paymentMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(paymentMapper.fromId(null)).isNull();
    }
}
