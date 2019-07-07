package vn.studentexchange.web.rest;

import vn.studentexchange.StudentexchangeApp;

import vn.studentexchange.domain.OrderItem;
import vn.studentexchange.repository.OrderItemRepository;
import vn.studentexchange.service.OrderItemService;
import vn.studentexchange.service.dto.OrderItemDTO;
import vn.studentexchange.service.mapper.OrderItemMapper;
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
 * Test class for the OrderItemResource REST controller.
 *
 * @see OrderItemResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = StudentexchangeApp.class)
public class OrderItemResourceIntTest {

    private static final String DEFAULT_ITEM_ID = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_ID = "BBBBBBBBBB";

    private static final String DEFAULT_ITEM_IMAGE = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_IMAGE = "BBBBBBBBBB";

    private static final String DEFAULT_ITEM_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ITEM_LINK = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_LINK = "BBBBBBBBBB";

    private static final Float DEFAULT_ITEM_PRICE = 1F;
    private static final Float UPDATED_ITEM_PRICE = 2F;

    private static final Float DEFAULT_ITEM_PRICE_NDT = 1F;
    private static final Float UPDATED_ITEM_PRICE_NDT = 2F;

    private static final String DEFAULT_ITEM_NOTE = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_NOTE = "BBBBBBBBBB";

    private static final String DEFAULT_PROPERTIES_ID = "AAAAAAAAAA";
    private static final String UPDATED_PROPERTIES_ID = "BBBBBBBBBB";

    private static final String DEFAULT_PROPERTIES_IMAGE = "AAAAAAAAAA";
    private static final String UPDATED_PROPERTIES_IMAGE = "BBBBBBBBBB";

    private static final String DEFAULT_PROPERTIES_MD_5 = "AAAAAAAAAA";
    private static final String UPDATED_PROPERTIES_MD_5 = "BBBBBBBBBB";

    private static final String DEFAULT_PROPERTIES_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PROPERTIES_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PROPERTIES_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_PROPERTIES_TYPE = "BBBBBBBBBB";

    private static final Integer DEFAULT_QUANTITY = 1;
    private static final Integer UPDATED_QUANTITY = 2;

    private static final Integer DEFAULT_REQUIRE_MIN = 1;
    private static final Integer UPDATED_REQUIRE_MIN = 2;

    private static final Float DEFAULT_TOTAL_AMOUNT = 1F;
    private static final Float UPDATED_TOTAL_AMOUNT = 2F;

    private static final Float DEFAULT_TOTAL_AMOUNT_NDT = 1F;
    private static final Float UPDATED_TOTAL_AMOUNT_NDT = 2F;

    private static final Instant DEFAULT_CREATE_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATE_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_UPDATE_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATE_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restOrderItemMockMvc;

    private OrderItem orderItem;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final OrderItemResource orderItemResource = new OrderItemResource(orderItemService);
        this.restOrderItemMockMvc = MockMvcBuilders.standaloneSetup(orderItemResource)
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
    public static OrderItem createEntity(EntityManager em) {
        OrderItem orderItem = new OrderItem()
            .itemId(DEFAULT_ITEM_ID)
            .itemImage(DEFAULT_ITEM_IMAGE)
            .itemName(DEFAULT_ITEM_NAME)
            .itemLink(DEFAULT_ITEM_LINK)
            .itemPrice(DEFAULT_ITEM_PRICE)
            .itemPriceNDT(DEFAULT_ITEM_PRICE_NDT)
            .itemNote(DEFAULT_ITEM_NOTE)
            .propertiesId(DEFAULT_PROPERTIES_ID)
            .propertiesImage(DEFAULT_PROPERTIES_IMAGE)
            .propertiesMD5(DEFAULT_PROPERTIES_MD_5)
            .propertiesName(DEFAULT_PROPERTIES_NAME)
            .propertiesType(DEFAULT_PROPERTIES_TYPE)
            .quantity(DEFAULT_QUANTITY)
            .requireMin(DEFAULT_REQUIRE_MIN)
            .totalAmount(DEFAULT_TOTAL_AMOUNT)
            .totalAmountNDT(DEFAULT_TOTAL_AMOUNT_NDT)
            .createAt(DEFAULT_CREATE_AT)
            .updateAt(DEFAULT_UPDATE_AT);
        return orderItem;
    }

    @Before
    public void initTest() {
        orderItem = createEntity(em);
    }

    @Test
    @Transactional
    public void createOrderItem() throws Exception {
        int databaseSizeBeforeCreate = orderItemRepository.findAll().size();

        // Create the OrderItem
        OrderItemDTO orderItemDTO = orderItemMapper.toDto(orderItem);
        restOrderItemMockMvc.perform(post("/api/order-items")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(orderItemDTO)))
            .andExpect(status().isCreated());

        // Validate the OrderItem in the database
        List<OrderItem> orderItemList = orderItemRepository.findAll();
        assertThat(orderItemList).hasSize(databaseSizeBeforeCreate + 1);
        OrderItem testOrderItem = orderItemList.get(orderItemList.size() - 1);
        assertThat(testOrderItem.getItemId()).isEqualTo(DEFAULT_ITEM_ID);
        assertThat(testOrderItem.getItemImage()).isEqualTo(DEFAULT_ITEM_IMAGE);
        assertThat(testOrderItem.getItemName()).isEqualTo(DEFAULT_ITEM_NAME);
        assertThat(testOrderItem.getItemLink()).isEqualTo(DEFAULT_ITEM_LINK);
        assertThat(testOrderItem.getItemPrice()).isEqualTo(DEFAULT_ITEM_PRICE);
        assertThat(testOrderItem.getItemPriceNDT()).isEqualTo(DEFAULT_ITEM_PRICE_NDT);
        assertThat(testOrderItem.getItemNote()).isEqualTo(DEFAULT_ITEM_NOTE);
        assertThat(testOrderItem.getPropertiesId()).isEqualTo(DEFAULT_PROPERTIES_ID);
        assertThat(testOrderItem.getPropertiesImage()).isEqualTo(DEFAULT_PROPERTIES_IMAGE);
        assertThat(testOrderItem.getPropertiesMD5()).isEqualTo(DEFAULT_PROPERTIES_MD_5);
        assertThat(testOrderItem.getPropertiesName()).isEqualTo(DEFAULT_PROPERTIES_NAME);
        assertThat(testOrderItem.getPropertiesType()).isEqualTo(DEFAULT_PROPERTIES_TYPE);
        assertThat(testOrderItem.getQuantity()).isEqualTo(DEFAULT_QUANTITY);
        assertThat(testOrderItem.getRequireMin()).isEqualTo(DEFAULT_REQUIRE_MIN);
        assertThat(testOrderItem.getTotalAmount()).isEqualTo(DEFAULT_TOTAL_AMOUNT);
        assertThat(testOrderItem.getTotalAmountNDT()).isEqualTo(DEFAULT_TOTAL_AMOUNT_NDT);
        assertThat(testOrderItem.getCreateAt()).isEqualTo(DEFAULT_CREATE_AT);
        assertThat(testOrderItem.getUpdateAt()).isEqualTo(DEFAULT_UPDATE_AT);
    }

    @Test
    @Transactional
    public void createOrderItemWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = orderItemRepository.findAll().size();

        // Create the OrderItem with an existing ID
        orderItem.setId(1L);
        OrderItemDTO orderItemDTO = orderItemMapper.toDto(orderItem);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOrderItemMockMvc.perform(post("/api/order-items")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(orderItemDTO)))
            .andExpect(status().isBadRequest());

        // Validate the OrderItem in the database
        List<OrderItem> orderItemList = orderItemRepository.findAll();
        assertThat(orderItemList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllOrderItems() throws Exception {
        // Initialize the database
        orderItemRepository.saveAndFlush(orderItem);

        // Get all the orderItemList
        restOrderItemMockMvc.perform(get("/api/order-items?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(orderItem.getId().intValue())))
            .andExpect(jsonPath("$.[*].itemId").value(hasItem(DEFAULT_ITEM_ID.toString())))
            .andExpect(jsonPath("$.[*].itemImage").value(hasItem(DEFAULT_ITEM_IMAGE.toString())))
            .andExpect(jsonPath("$.[*].itemName").value(hasItem(DEFAULT_ITEM_NAME.toString())))
            .andExpect(jsonPath("$.[*].itemLink").value(hasItem(DEFAULT_ITEM_LINK.toString())))
            .andExpect(jsonPath("$.[*].itemPrice").value(hasItem(DEFAULT_ITEM_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].itemPriceNDT").value(hasItem(DEFAULT_ITEM_PRICE_NDT.doubleValue())))
            .andExpect(jsonPath("$.[*].itemNote").value(hasItem(DEFAULT_ITEM_NOTE.toString())))
            .andExpect(jsonPath("$.[*].propertiesId").value(hasItem(DEFAULT_PROPERTIES_ID.toString())))
            .andExpect(jsonPath("$.[*].propertiesImage").value(hasItem(DEFAULT_PROPERTIES_IMAGE.toString())))
            .andExpect(jsonPath("$.[*].propertiesMD5").value(hasItem(DEFAULT_PROPERTIES_MD_5.toString())))
            .andExpect(jsonPath("$.[*].propertiesName").value(hasItem(DEFAULT_PROPERTIES_NAME.toString())))
            .andExpect(jsonPath("$.[*].propertiesType").value(hasItem(DEFAULT_PROPERTIES_TYPE.toString())))
            .andExpect(jsonPath("$.[*].quantity").value(hasItem(DEFAULT_QUANTITY)))
            .andExpect(jsonPath("$.[*].requireMin").value(hasItem(DEFAULT_REQUIRE_MIN)))
            .andExpect(jsonPath("$.[*].totalAmount").value(hasItem(DEFAULT_TOTAL_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].totalAmountNDT").value(hasItem(DEFAULT_TOTAL_AMOUNT_NDT.doubleValue())))
            .andExpect(jsonPath("$.[*].createAt").value(hasItem(DEFAULT_CREATE_AT.toString())))
            .andExpect(jsonPath("$.[*].updateAt").value(hasItem(DEFAULT_UPDATE_AT.toString())));
    }
    
    @Test
    @Transactional
    public void getOrderItem() throws Exception {
        // Initialize the database
        orderItemRepository.saveAndFlush(orderItem);

        // Get the orderItem
        restOrderItemMockMvc.perform(get("/api/order-items/{id}", orderItem.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(orderItem.getId().intValue()))
            .andExpect(jsonPath("$.itemId").value(DEFAULT_ITEM_ID.toString()))
            .andExpect(jsonPath("$.itemImage").value(DEFAULT_ITEM_IMAGE.toString()))
            .andExpect(jsonPath("$.itemName").value(DEFAULT_ITEM_NAME.toString()))
            .andExpect(jsonPath("$.itemLink").value(DEFAULT_ITEM_LINK.toString()))
            .andExpect(jsonPath("$.itemPrice").value(DEFAULT_ITEM_PRICE.doubleValue()))
            .andExpect(jsonPath("$.itemPriceNDT").value(DEFAULT_ITEM_PRICE_NDT.doubleValue()))
            .andExpect(jsonPath("$.itemNote").value(DEFAULT_ITEM_NOTE.toString()))
            .andExpect(jsonPath("$.propertiesId").value(DEFAULT_PROPERTIES_ID.toString()))
            .andExpect(jsonPath("$.propertiesImage").value(DEFAULT_PROPERTIES_IMAGE.toString()))
            .andExpect(jsonPath("$.propertiesMD5").value(DEFAULT_PROPERTIES_MD_5.toString()))
            .andExpect(jsonPath("$.propertiesName").value(DEFAULT_PROPERTIES_NAME.toString()))
            .andExpect(jsonPath("$.propertiesType").value(DEFAULT_PROPERTIES_TYPE.toString()))
            .andExpect(jsonPath("$.quantity").value(DEFAULT_QUANTITY))
            .andExpect(jsonPath("$.requireMin").value(DEFAULT_REQUIRE_MIN))
            .andExpect(jsonPath("$.totalAmount").value(DEFAULT_TOTAL_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.totalAmountNDT").value(DEFAULT_TOTAL_AMOUNT_NDT.doubleValue()))
            .andExpect(jsonPath("$.createAt").value(DEFAULT_CREATE_AT.toString()))
            .andExpect(jsonPath("$.updateAt").value(DEFAULT_UPDATE_AT.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingOrderItem() throws Exception {
        // Get the orderItem
        restOrderItemMockMvc.perform(get("/api/order-items/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOrderItem() throws Exception {
        // Initialize the database
        orderItemRepository.saveAndFlush(orderItem);

        int databaseSizeBeforeUpdate = orderItemRepository.findAll().size();

        // Update the orderItem
        OrderItem updatedOrderItem = orderItemRepository.findById(orderItem.getId()).get();
        // Disconnect from session so that the updates on updatedOrderItem are not directly saved in db
        em.detach(updatedOrderItem);
        updatedOrderItem
            .itemId(UPDATED_ITEM_ID)
            .itemImage(UPDATED_ITEM_IMAGE)
            .itemName(UPDATED_ITEM_NAME)
            .itemLink(UPDATED_ITEM_LINK)
            .itemPrice(UPDATED_ITEM_PRICE)
            .itemPriceNDT(UPDATED_ITEM_PRICE_NDT)
            .itemNote(UPDATED_ITEM_NOTE)
            .propertiesId(UPDATED_PROPERTIES_ID)
            .propertiesImage(UPDATED_PROPERTIES_IMAGE)
            .propertiesMD5(UPDATED_PROPERTIES_MD_5)
            .propertiesName(UPDATED_PROPERTIES_NAME)
            .propertiesType(UPDATED_PROPERTIES_TYPE)
            .quantity(UPDATED_QUANTITY)
            .requireMin(UPDATED_REQUIRE_MIN)
            .totalAmount(UPDATED_TOTAL_AMOUNT)
            .totalAmountNDT(UPDATED_TOTAL_AMOUNT_NDT)
            .createAt(UPDATED_CREATE_AT)
            .updateAt(UPDATED_UPDATE_AT);
        OrderItemDTO orderItemDTO = orderItemMapper.toDto(updatedOrderItem);

        restOrderItemMockMvc.perform(put("/api/order-items")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(orderItemDTO)))
            .andExpect(status().isOk());

        // Validate the OrderItem in the database
        List<OrderItem> orderItemList = orderItemRepository.findAll();
        assertThat(orderItemList).hasSize(databaseSizeBeforeUpdate);
        OrderItem testOrderItem = orderItemList.get(orderItemList.size() - 1);
        assertThat(testOrderItem.getItemId()).isEqualTo(UPDATED_ITEM_ID);
        assertThat(testOrderItem.getItemImage()).isEqualTo(UPDATED_ITEM_IMAGE);
        assertThat(testOrderItem.getItemName()).isEqualTo(UPDATED_ITEM_NAME);
        assertThat(testOrderItem.getItemLink()).isEqualTo(UPDATED_ITEM_LINK);
        assertThat(testOrderItem.getItemPrice()).isEqualTo(UPDATED_ITEM_PRICE);
        assertThat(testOrderItem.getItemPriceNDT()).isEqualTo(UPDATED_ITEM_PRICE_NDT);
        assertThat(testOrderItem.getItemNote()).isEqualTo(UPDATED_ITEM_NOTE);
        assertThat(testOrderItem.getPropertiesId()).isEqualTo(UPDATED_PROPERTIES_ID);
        assertThat(testOrderItem.getPropertiesImage()).isEqualTo(UPDATED_PROPERTIES_IMAGE);
        assertThat(testOrderItem.getPropertiesMD5()).isEqualTo(UPDATED_PROPERTIES_MD_5);
        assertThat(testOrderItem.getPropertiesName()).isEqualTo(UPDATED_PROPERTIES_NAME);
        assertThat(testOrderItem.getPropertiesType()).isEqualTo(UPDATED_PROPERTIES_TYPE);
        assertThat(testOrderItem.getQuantity()).isEqualTo(UPDATED_QUANTITY);
        assertThat(testOrderItem.getRequireMin()).isEqualTo(UPDATED_REQUIRE_MIN);
        assertThat(testOrderItem.getTotalAmount()).isEqualTo(UPDATED_TOTAL_AMOUNT);
        assertThat(testOrderItem.getTotalAmountNDT()).isEqualTo(UPDATED_TOTAL_AMOUNT_NDT);
        assertThat(testOrderItem.getCreateAt()).isEqualTo(UPDATED_CREATE_AT);
        assertThat(testOrderItem.getUpdateAt()).isEqualTo(UPDATED_UPDATE_AT);
    }

    @Test
    @Transactional
    public void updateNonExistingOrderItem() throws Exception {
        int databaseSizeBeforeUpdate = orderItemRepository.findAll().size();

        // Create the OrderItem
        OrderItemDTO orderItemDTO = orderItemMapper.toDto(orderItem);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOrderItemMockMvc.perform(put("/api/order-items")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(orderItemDTO)))
            .andExpect(status().isBadRequest());

        // Validate the OrderItem in the database
        List<OrderItem> orderItemList = orderItemRepository.findAll();
        assertThat(orderItemList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteOrderItem() throws Exception {
        // Initialize the database
        orderItemRepository.saveAndFlush(orderItem);

        int databaseSizeBeforeDelete = orderItemRepository.findAll().size();

        // Get the orderItem
        restOrderItemMockMvc.perform(delete("/api/order-items/{id}", orderItem.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<OrderItem> orderItemList = orderItemRepository.findAll();
        assertThat(orderItemList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OrderItem.class);
        OrderItem orderItem1 = new OrderItem();
        orderItem1.setId(1L);
        OrderItem orderItem2 = new OrderItem();
        orderItem2.setId(orderItem1.getId());
        assertThat(orderItem1).isEqualTo(orderItem2);
        orderItem2.setId(2L);
        assertThat(orderItem1).isNotEqualTo(orderItem2);
        orderItem1.setId(null);
        assertThat(orderItem1).isNotEqualTo(orderItem2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(OrderItemDTO.class);
        OrderItemDTO orderItemDTO1 = new OrderItemDTO();
        orderItemDTO1.setId(1L);
        OrderItemDTO orderItemDTO2 = new OrderItemDTO();
        assertThat(orderItemDTO1).isNotEqualTo(orderItemDTO2);
        orderItemDTO2.setId(orderItemDTO1.getId());
        assertThat(orderItemDTO1).isEqualTo(orderItemDTO2);
        orderItemDTO2.setId(2L);
        assertThat(orderItemDTO1).isNotEqualTo(orderItemDTO2);
        orderItemDTO1.setId(null);
        assertThat(orderItemDTO1).isNotEqualTo(orderItemDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(orderItemMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(orderItemMapper.fromId(null)).isNull();
    }
}
