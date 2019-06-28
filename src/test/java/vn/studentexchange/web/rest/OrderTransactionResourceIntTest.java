package vn.studentexchange.web.rest;

import vn.studentexchange.StudentexchangeApp;

import vn.studentexchange.domain.OrderTransaction;
import vn.studentexchange.repository.OrderTransactionRepository;
import vn.studentexchange.service.OrderTransactionService;
import vn.studentexchange.service.dto.OrderTransactionDTO;
import vn.studentexchange.service.mapper.OrderTransactionMapper;
import vn.studentexchange.web.rest.errors.ExceptionTranslator;

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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;


import static vn.studentexchange.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import vn.studentexchange.domain.enumeration.OrderTransactionType;
/**
 * Test class for the OrderTransactionResource REST controller.
 *
 * @see OrderTransactionResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = StudentexchangeApp.class)
public class OrderTransactionResourceIntTest {

    private static final Float DEFAULT_AMOUNT = 1F;
    private static final Float UPDATED_AMOUNT = 2F;

    private static final String DEFAULT_NOTE = "AAAAAAAAAA";
    private static final String UPDATED_NOTE = "BBBBBBBBBB";

    private static final OrderTransactionType DEFAULT_STATUS = OrderTransactionType.DEPOSIT;
    private static final OrderTransactionType UPDATED_STATUS = OrderTransactionType.ORDER_PAYMENT;

    private static final LocalDate DEFAULT_CREATE_AT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATE_AT = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private OrderTransactionRepository orderTransactionRepository;

    @Autowired
    private OrderTransactionMapper orderTransactionMapper;

    @Autowired
    private OrderTransactionService orderTransactionService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restOrderTransactionMockMvc;

    private OrderTransaction orderTransaction;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final OrderTransactionResource orderTransactionResource = new OrderTransactionResource(orderTransactionService);
        this.restOrderTransactionMockMvc = MockMvcBuilders.standaloneSetup(orderTransactionResource)
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
    public static OrderTransaction createEntity(EntityManager em) {
        OrderTransaction orderTransaction = new OrderTransaction()
            .amount(DEFAULT_AMOUNT)
            .note(DEFAULT_NOTE)
            .status(DEFAULT_STATUS)
            .createAt(DEFAULT_CREATE_AT);
        return orderTransaction;
    }

    @Before
    public void initTest() {
        orderTransaction = createEntity(em);
    }

    @Test
    @Transactional
    public void createOrderTransaction() throws Exception {
        int databaseSizeBeforeCreate = orderTransactionRepository.findAll().size();

        // Create the OrderTransaction
        OrderTransactionDTO orderTransactionDTO = orderTransactionMapper.toDto(orderTransaction);
        restOrderTransactionMockMvc.perform(post("/api/order-transactions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(orderTransactionDTO)))
            .andExpect(status().isCreated());

        // Validate the OrderTransaction in the database
        List<OrderTransaction> orderTransactionList = orderTransactionRepository.findAll();
        assertThat(orderTransactionList).hasSize(databaseSizeBeforeCreate + 1);
        OrderTransaction testOrderTransaction = orderTransactionList.get(orderTransactionList.size() - 1);
        assertThat(testOrderTransaction.getAmount()).isEqualTo(DEFAULT_AMOUNT);
        assertThat(testOrderTransaction.getNote()).isEqualTo(DEFAULT_NOTE);
        assertThat(testOrderTransaction.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testOrderTransaction.getCreateAt()).isEqualTo(DEFAULT_CREATE_AT);
    }

    @Test
    @Transactional
    public void createOrderTransactionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = orderTransactionRepository.findAll().size();

        // Create the OrderTransaction with an existing ID
        orderTransaction.setId(1L);
        OrderTransactionDTO orderTransactionDTO = orderTransactionMapper.toDto(orderTransaction);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOrderTransactionMockMvc.perform(post("/api/order-transactions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(orderTransactionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the OrderTransaction in the database
        List<OrderTransaction> orderTransactionList = orderTransactionRepository.findAll();
        assertThat(orderTransactionList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllOrderTransactions() throws Exception {
        // Initialize the database
        orderTransactionRepository.saveAndFlush(orderTransaction);

        // Get all the orderTransactionList
        restOrderTransactionMockMvc.perform(get("/api/order-transactions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(orderTransaction.getId().intValue())))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].note").value(hasItem(DEFAULT_NOTE.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].createAt").value(hasItem(DEFAULT_CREATE_AT.toString())));
    }
    
    @Test
    @Transactional
    public void getOrderTransaction() throws Exception {
        // Initialize the database
        orderTransactionRepository.saveAndFlush(orderTransaction);

        // Get the orderTransaction
        restOrderTransactionMockMvc.perform(get("/api/order-transactions/{id}", orderTransaction.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(orderTransaction.getId().intValue()))
            .andExpect(jsonPath("$.amount").value(DEFAULT_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.note").value(DEFAULT_NOTE.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.createAt").value(DEFAULT_CREATE_AT.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingOrderTransaction() throws Exception {
        // Get the orderTransaction
        restOrderTransactionMockMvc.perform(get("/api/order-transactions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOrderTransaction() throws Exception {
        // Initialize the database
        orderTransactionRepository.saveAndFlush(orderTransaction);

        int databaseSizeBeforeUpdate = orderTransactionRepository.findAll().size();

        // Update the orderTransaction
        OrderTransaction updatedOrderTransaction = orderTransactionRepository.findById(orderTransaction.getId()).get();
        // Disconnect from session so that the updates on updatedOrderTransaction are not directly saved in db
        em.detach(updatedOrderTransaction);
        updatedOrderTransaction
            .amount(UPDATED_AMOUNT)
            .note(UPDATED_NOTE)
            .status(UPDATED_STATUS)
            .createAt(UPDATED_CREATE_AT);
        OrderTransactionDTO orderTransactionDTO = orderTransactionMapper.toDto(updatedOrderTransaction);

        restOrderTransactionMockMvc.perform(put("/api/order-transactions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(orderTransactionDTO)))
            .andExpect(status().isOk());

        // Validate the OrderTransaction in the database
        List<OrderTransaction> orderTransactionList = orderTransactionRepository.findAll();
        assertThat(orderTransactionList).hasSize(databaseSizeBeforeUpdate);
        OrderTransaction testOrderTransaction = orderTransactionList.get(orderTransactionList.size() - 1);
        assertThat(testOrderTransaction.getAmount()).isEqualTo(UPDATED_AMOUNT);
        assertThat(testOrderTransaction.getNote()).isEqualTo(UPDATED_NOTE);
        assertThat(testOrderTransaction.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testOrderTransaction.getCreateAt()).isEqualTo(UPDATED_CREATE_AT);
    }

    @Test
    @Transactional
    public void updateNonExistingOrderTransaction() throws Exception {
        int databaseSizeBeforeUpdate = orderTransactionRepository.findAll().size();

        // Create the OrderTransaction
        OrderTransactionDTO orderTransactionDTO = orderTransactionMapper.toDto(orderTransaction);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOrderTransactionMockMvc.perform(put("/api/order-transactions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(orderTransactionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the OrderTransaction in the database
        List<OrderTransaction> orderTransactionList = orderTransactionRepository.findAll();
        assertThat(orderTransactionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteOrderTransaction() throws Exception {
        // Initialize the database
        orderTransactionRepository.saveAndFlush(orderTransaction);

        int databaseSizeBeforeDelete = orderTransactionRepository.findAll().size();

        // Get the orderTransaction
        restOrderTransactionMockMvc.perform(delete("/api/order-transactions/{id}", orderTransaction.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<OrderTransaction> orderTransactionList = orderTransactionRepository.findAll();
        assertThat(orderTransactionList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OrderTransaction.class);
        OrderTransaction orderTransaction1 = new OrderTransaction();
        orderTransaction1.setId(1L);
        OrderTransaction orderTransaction2 = new OrderTransaction();
        orderTransaction2.setId(orderTransaction1.getId());
        assertThat(orderTransaction1).isEqualTo(orderTransaction2);
        orderTransaction2.setId(2L);
        assertThat(orderTransaction1).isNotEqualTo(orderTransaction2);
        orderTransaction1.setId(null);
        assertThat(orderTransaction1).isNotEqualTo(orderTransaction2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(OrderTransactionDTO.class);
        OrderTransactionDTO orderTransactionDTO1 = new OrderTransactionDTO();
        orderTransactionDTO1.setId(1L);
        OrderTransactionDTO orderTransactionDTO2 = new OrderTransactionDTO();
        assertThat(orderTransactionDTO1).isNotEqualTo(orderTransactionDTO2);
        orderTransactionDTO2.setId(orderTransactionDTO1.getId());
        assertThat(orderTransactionDTO1).isEqualTo(orderTransactionDTO2);
        orderTransactionDTO2.setId(2L);
        assertThat(orderTransactionDTO1).isNotEqualTo(orderTransactionDTO2);
        orderTransactionDTO1.setId(null);
        assertThat(orderTransactionDTO1).isNotEqualTo(orderTransactionDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(orderTransactionMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(orderTransactionMapper.fromId(null)).isNull();
    }
}
