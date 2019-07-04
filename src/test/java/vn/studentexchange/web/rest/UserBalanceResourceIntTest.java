package vn.studentexchange.web.rest;

import vn.studentexchange.StudentexchangeApp;

import vn.studentexchange.domain.UserBalance;
import vn.studentexchange.repository.UserBalanceRepository;
import vn.studentexchange.repository.UserRepository;
import vn.studentexchange.service.PaymentService;
import vn.studentexchange.service.UserBalanceService;
import vn.studentexchange.service.dto.UserBalanceDTO;
import vn.studentexchange.service.mapper.UserBalanceMapper;
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
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;


import static vn.studentexchange.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the UserBalanceResource REST controller.
 *
 * @see UserBalanceResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = StudentexchangeApp.class)
public class UserBalanceResourceIntTest {

    private static final Float DEFAULT_BALANCE_AVAILABLE = 1F;
    private static final Float UPDATED_BALANCE_AVAILABLE = 2F;

    private static final Float DEFAULT_BALANCE_FREEZING = 1F;
    private static final Float UPDATED_BALANCE_FREEZING = 2F;

    private static final Float DEFAULT_CASH = 1F;
    private static final Float UPDATED_CASH = 2F;

    private static final Instant DEFAULT_CREATE_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATE_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_UPDATE_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATE_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private UserBalanceRepository userBalanceRepository;

    @Autowired
    private UserBalanceMapper userBalanceMapper;

    @Autowired
    private UserBalanceService userBalanceService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restUserBalanceMockMvc;

    private UserBalance userBalance;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final UserBalanceResource userBalanceResource = new UserBalanceResource(userBalanceService, userRepository, paymentService);
        this.restUserBalanceMockMvc = MockMvcBuilders.standaloneSetup(userBalanceResource)
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
    public static UserBalance createEntity(EntityManager em) {
        UserBalance userBalance = new UserBalance()
            .balanceAvailable(DEFAULT_BALANCE_AVAILABLE)
            .balanceFreezing(DEFAULT_BALANCE_FREEZING)
            .cash(DEFAULT_CASH)
            .createAt(DEFAULT_CREATE_AT)
            .updateAt(DEFAULT_UPDATE_AT);
        return userBalance;
    }

    @Before
    public void initTest() {
        userBalance = createEntity(em);
    }

    @Test
    @Transactional
    public void createUserBalance() throws Exception {
        int databaseSizeBeforeCreate = userBalanceRepository.findAll().size();

        // Create the UserBalance
        UserBalanceDTO userBalanceDTO = userBalanceMapper.toDto(userBalance);
        restUserBalanceMockMvc.perform(post("/api/user-balances")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userBalanceDTO)))
            .andExpect(status().isCreated());

        // Validate the UserBalance in the database
        List<UserBalance> userBalanceList = userBalanceRepository.findAll();
        assertThat(userBalanceList).hasSize(databaseSizeBeforeCreate + 1);
        UserBalance testUserBalance = userBalanceList.get(userBalanceList.size() - 1);
        assertThat(testUserBalance.getBalanceAvailable()).isEqualTo(DEFAULT_BALANCE_AVAILABLE);
        assertThat(testUserBalance.getBalanceFreezing()).isEqualTo(DEFAULT_BALANCE_FREEZING);
        assertThat(testUserBalance.getCash()).isEqualTo(DEFAULT_CASH);
        assertThat(testUserBalance.getCreateAt()).isEqualTo(DEFAULT_CREATE_AT);
        assertThat(testUserBalance.getUpdateAt()).isEqualTo(DEFAULT_UPDATE_AT);
    }

    @Test
    @Transactional
    public void createUserBalanceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = userBalanceRepository.findAll().size();

        // Create the UserBalance with an existing ID
        userBalance.setId(1L);
        UserBalanceDTO userBalanceDTO = userBalanceMapper.toDto(userBalance);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUserBalanceMockMvc.perform(post("/api/user-balances")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userBalanceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the UserBalance in the database
        List<UserBalance> userBalanceList = userBalanceRepository.findAll();
        assertThat(userBalanceList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllUserBalances() throws Exception {
        // Initialize the database
        userBalanceRepository.saveAndFlush(userBalance);

        // Get all the userBalanceList
        restUserBalanceMockMvc.perform(get("/api/user-balances?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(userBalance.getId().intValue())))
            .andExpect(jsonPath("$.[*].balanceAvailable").value(hasItem(DEFAULT_BALANCE_AVAILABLE.doubleValue())))
            .andExpect(jsonPath("$.[*].balanceFreezing").value(hasItem(DEFAULT_BALANCE_FREEZING.doubleValue())))
            .andExpect(jsonPath("$.[*].cash").value(hasItem(DEFAULT_CASH.doubleValue())))
            .andExpect(jsonPath("$.[*].createAt").value(hasItem(DEFAULT_CREATE_AT.toString())))
            .andExpect(jsonPath("$.[*].updateAt").value(hasItem(DEFAULT_UPDATE_AT.toString())));
    }
    
    @Test
    @Transactional
    public void getUserBalance() throws Exception {
        // Initialize the database
        userBalanceRepository.saveAndFlush(userBalance);

        // Get the userBalance
        restUserBalanceMockMvc.perform(get("/api/user-balances/{id}", userBalance.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(userBalance.getId().intValue()))
            .andExpect(jsonPath("$.balanceAvailable").value(DEFAULT_BALANCE_AVAILABLE.doubleValue()))
            .andExpect(jsonPath("$.balanceFreezing").value(DEFAULT_BALANCE_FREEZING.doubleValue()))
            .andExpect(jsonPath("$.cash").value(DEFAULT_CASH.doubleValue()))
            .andExpect(jsonPath("$.createAt").value(DEFAULT_CREATE_AT.toString()))
            .andExpect(jsonPath("$.updateAt").value(DEFAULT_UPDATE_AT.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingUserBalance() throws Exception {
        // Get the userBalance
        restUserBalanceMockMvc.perform(get("/api/user-balances/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUserBalance() throws Exception {
        // Initialize the database
        userBalanceRepository.saveAndFlush(userBalance);

        int databaseSizeBeforeUpdate = userBalanceRepository.findAll().size();

        // Update the userBalance
        UserBalance updatedUserBalance = userBalanceRepository.findById(userBalance.getId()).get();
        // Disconnect from session so that the updates on updatedUserBalance are not directly saved in db
        em.detach(updatedUserBalance);
        updatedUserBalance
            .balanceAvailable(UPDATED_BALANCE_AVAILABLE)
            .balanceFreezing(UPDATED_BALANCE_FREEZING)
            .cash(UPDATED_CASH)
            .createAt(UPDATED_CREATE_AT)
            .updateAt(UPDATED_UPDATE_AT);
        UserBalanceDTO userBalanceDTO = userBalanceMapper.toDto(updatedUserBalance);

        restUserBalanceMockMvc.perform(put("/api/user-balances")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userBalanceDTO)))
            .andExpect(status().isOk());

        // Validate the UserBalance in the database
        List<UserBalance> userBalanceList = userBalanceRepository.findAll();
        assertThat(userBalanceList).hasSize(databaseSizeBeforeUpdate);
        UserBalance testUserBalance = userBalanceList.get(userBalanceList.size() - 1);
        assertThat(testUserBalance.getBalanceAvailable()).isEqualTo(UPDATED_BALANCE_AVAILABLE);
        assertThat(testUserBalance.getBalanceFreezing()).isEqualTo(UPDATED_BALANCE_FREEZING);
        assertThat(testUserBalance.getCash()).isEqualTo(UPDATED_CASH);
        assertThat(testUserBalance.getCreateAt()).isEqualTo(UPDATED_CREATE_AT);
        assertThat(testUserBalance.getUpdateAt()).isEqualTo(UPDATED_UPDATE_AT);
    }

    @Test
    @Transactional
    public void updateNonExistingUserBalance() throws Exception {
        int databaseSizeBeforeUpdate = userBalanceRepository.findAll().size();

        // Create the UserBalance
        UserBalanceDTO userBalanceDTO = userBalanceMapper.toDto(userBalance);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUserBalanceMockMvc.perform(put("/api/user-balances")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userBalanceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the UserBalance in the database
        List<UserBalance> userBalanceList = userBalanceRepository.findAll();
        assertThat(userBalanceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteUserBalance() throws Exception {
        // Initialize the database
        userBalanceRepository.saveAndFlush(userBalance);

        int databaseSizeBeforeDelete = userBalanceRepository.findAll().size();

        // Get the userBalance
        restUserBalanceMockMvc.perform(delete("/api/user-balances/{id}", userBalance.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<UserBalance> userBalanceList = userBalanceRepository.findAll();
        assertThat(userBalanceList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserBalance.class);
        UserBalance userBalance1 = new UserBalance();
        userBalance1.setId(1L);
        UserBalance userBalance2 = new UserBalance();
        userBalance2.setId(userBalance1.getId());
        assertThat(userBalance1).isEqualTo(userBalance2);
        userBalance2.setId(2L);
        assertThat(userBalance1).isNotEqualTo(userBalance2);
        userBalance1.setId(null);
        assertThat(userBalance1).isNotEqualTo(userBalance2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserBalanceDTO.class);
        UserBalanceDTO userBalanceDTO1 = new UserBalanceDTO();
        userBalanceDTO1.setId(1L);
        UserBalanceDTO userBalanceDTO2 = new UserBalanceDTO();
        assertThat(userBalanceDTO1).isNotEqualTo(userBalanceDTO2);
        userBalanceDTO2.setId(userBalanceDTO1.getId());
        assertThat(userBalanceDTO1).isEqualTo(userBalanceDTO2);
        userBalanceDTO2.setId(2L);
        assertThat(userBalanceDTO1).isNotEqualTo(userBalanceDTO2);
        userBalanceDTO1.setId(null);
        assertThat(userBalanceDTO1).isNotEqualTo(userBalanceDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(userBalanceMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(userBalanceMapper.fromId(null)).isNull();
    }
}
