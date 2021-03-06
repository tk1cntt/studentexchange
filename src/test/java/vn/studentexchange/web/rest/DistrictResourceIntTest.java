package vn.studentexchange.web.rest;

import vn.studentexchange.StudentexchangeApp;

import vn.studentexchange.domain.District;
import vn.studentexchange.domain.City;
import vn.studentexchange.domain.Ward;
import vn.studentexchange.repository.DistrictRepository;
import vn.studentexchange.service.DistrictService;
import vn.studentexchange.service.dto.DistrictDTO;
import vn.studentexchange.service.mapper.DistrictMapper;
import vn.studentexchange.web.rest.errors.ExceptionTranslator;
import vn.studentexchange.service.dto.DistrictCriteria;
import vn.studentexchange.service.DistrictQueryService;

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
 * Test class for the DistrictResource REST controller.
 *
 * @see DistrictResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = StudentexchangeApp.class)
public class DistrictResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final Float DEFAULT_LATITUDE = 1F;
    private static final Float UPDATED_LATITUDE = 2F;

    private static final Float DEFAULT_LONGITUDE = 1F;
    private static final Float UPDATED_LONGITUDE = 2F;

    private static final Boolean DEFAULT_ENABLED = false;
    private static final Boolean UPDATED_ENABLED = true;

    private static final Instant DEFAULT_CREATE_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATE_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_UPDATE_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATE_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private DistrictMapper districtMapper;

    @Autowired
    private DistrictService districtService;

    @Autowired
    private DistrictQueryService districtQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restDistrictMockMvc;

    private District district;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DistrictResource districtResource = new DistrictResource(districtService, districtQueryService);
        this.restDistrictMockMvc = MockMvcBuilders.standaloneSetup(districtResource)
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
    public static District createEntity(EntityManager em) {
        District district = new District()
            .name(DEFAULT_NAME)
            .type(DEFAULT_TYPE)
            .latitude(DEFAULT_LATITUDE)
            .longitude(DEFAULT_LONGITUDE)
            .enabled(DEFAULT_ENABLED)
            .createAt(DEFAULT_CREATE_AT)
            .updateAt(DEFAULT_UPDATE_AT);
        return district;
    }

    @Before
    public void initTest() {
        district = createEntity(em);
    }

    @Test
    @Transactional
    public void createDistrict() throws Exception {
        int databaseSizeBeforeCreate = districtRepository.findAll().size();

        // Create the District
        DistrictDTO districtDTO = districtMapper.toDto(district);
        restDistrictMockMvc.perform(post("/api/districts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(districtDTO)))
            .andExpect(status().isCreated());

        // Validate the District in the database
        List<District> districtList = districtRepository.findAll();
        assertThat(districtList).hasSize(databaseSizeBeforeCreate + 1);
        District testDistrict = districtList.get(districtList.size() - 1);
        assertThat(testDistrict.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testDistrict.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testDistrict.getLatitude()).isEqualTo(DEFAULT_LATITUDE);
        assertThat(testDistrict.getLongitude()).isEqualTo(DEFAULT_LONGITUDE);
        assertThat(testDistrict.isEnabled()).isEqualTo(DEFAULT_ENABLED);
        assertThat(testDistrict.getCreateAt()).isEqualTo(DEFAULT_CREATE_AT);
        assertThat(testDistrict.getUpdateAt()).isEqualTo(DEFAULT_UPDATE_AT);
    }

    @Test
    @Transactional
    public void createDistrictWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = districtRepository.findAll().size();

        // Create the District with an existing ID
        district.setId(1L);
        DistrictDTO districtDTO = districtMapper.toDto(district);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDistrictMockMvc.perform(post("/api/districts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(districtDTO)))
            .andExpect(status().isBadRequest());

        // Validate the District in the database
        List<District> districtList = districtRepository.findAll();
        assertThat(districtList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllDistricts() throws Exception {
        // Initialize the database
        districtRepository.saveAndFlush(district);

        // Get all the districtList
        restDistrictMockMvc.perform(get("/api/districts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(district.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].latitude").value(hasItem(DEFAULT_LATITUDE.doubleValue())))
            .andExpect(jsonPath("$.[*].longitude").value(hasItem(DEFAULT_LONGITUDE.doubleValue())))
            .andExpect(jsonPath("$.[*].enabled").value(hasItem(DEFAULT_ENABLED.booleanValue())))
            .andExpect(jsonPath("$.[*].createAt").value(hasItem(DEFAULT_CREATE_AT.toString())))
            .andExpect(jsonPath("$.[*].updateAt").value(hasItem(DEFAULT_UPDATE_AT.toString())));
    }
    
    @Test
    @Transactional
    public void getDistrict() throws Exception {
        // Initialize the database
        districtRepository.saveAndFlush(district);

        // Get the district
        restDistrictMockMvc.perform(get("/api/districts/{id}", district.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(district.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.latitude").value(DEFAULT_LATITUDE.doubleValue()))
            .andExpect(jsonPath("$.longitude").value(DEFAULT_LONGITUDE.doubleValue()))
            .andExpect(jsonPath("$.enabled").value(DEFAULT_ENABLED.booleanValue()))
            .andExpect(jsonPath("$.createAt").value(DEFAULT_CREATE_AT.toString()))
            .andExpect(jsonPath("$.updateAt").value(DEFAULT_UPDATE_AT.toString()));
    }

    @Test
    @Transactional
    public void getAllDistrictsByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        districtRepository.saveAndFlush(district);

        // Get all the districtList where name equals to DEFAULT_NAME
        defaultDistrictShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the districtList where name equals to UPDATED_NAME
        defaultDistrictShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllDistrictsByNameIsInShouldWork() throws Exception {
        // Initialize the database
        districtRepository.saveAndFlush(district);

        // Get all the districtList where name in DEFAULT_NAME or UPDATED_NAME
        defaultDistrictShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the districtList where name equals to UPDATED_NAME
        defaultDistrictShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllDistrictsByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        districtRepository.saveAndFlush(district);

        // Get all the districtList where name is not null
        defaultDistrictShouldBeFound("name.specified=true");

        // Get all the districtList where name is null
        defaultDistrictShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    public void getAllDistrictsByTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        districtRepository.saveAndFlush(district);

        // Get all the districtList where type equals to DEFAULT_TYPE
        defaultDistrictShouldBeFound("type.equals=" + DEFAULT_TYPE);

        // Get all the districtList where type equals to UPDATED_TYPE
        defaultDistrictShouldNotBeFound("type.equals=" + UPDATED_TYPE);
    }

    @Test
    @Transactional
    public void getAllDistrictsByTypeIsInShouldWork() throws Exception {
        // Initialize the database
        districtRepository.saveAndFlush(district);

        // Get all the districtList where type in DEFAULT_TYPE or UPDATED_TYPE
        defaultDistrictShouldBeFound("type.in=" + DEFAULT_TYPE + "," + UPDATED_TYPE);

        // Get all the districtList where type equals to UPDATED_TYPE
        defaultDistrictShouldNotBeFound("type.in=" + UPDATED_TYPE);
    }

    @Test
    @Transactional
    public void getAllDistrictsByTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        districtRepository.saveAndFlush(district);

        // Get all the districtList where type is not null
        defaultDistrictShouldBeFound("type.specified=true");

        // Get all the districtList where type is null
        defaultDistrictShouldNotBeFound("type.specified=false");
    }

    @Test
    @Transactional
    public void getAllDistrictsByLatitudeIsEqualToSomething() throws Exception {
        // Initialize the database
        districtRepository.saveAndFlush(district);

        // Get all the districtList where latitude equals to DEFAULT_LATITUDE
        defaultDistrictShouldBeFound("latitude.equals=" + DEFAULT_LATITUDE);

        // Get all the districtList where latitude equals to UPDATED_LATITUDE
        defaultDistrictShouldNotBeFound("latitude.equals=" + UPDATED_LATITUDE);
    }

    @Test
    @Transactional
    public void getAllDistrictsByLatitudeIsInShouldWork() throws Exception {
        // Initialize the database
        districtRepository.saveAndFlush(district);

        // Get all the districtList where latitude in DEFAULT_LATITUDE or UPDATED_LATITUDE
        defaultDistrictShouldBeFound("latitude.in=" + DEFAULT_LATITUDE + "," + UPDATED_LATITUDE);

        // Get all the districtList where latitude equals to UPDATED_LATITUDE
        defaultDistrictShouldNotBeFound("latitude.in=" + UPDATED_LATITUDE);
    }

    @Test
    @Transactional
    public void getAllDistrictsByLatitudeIsNullOrNotNull() throws Exception {
        // Initialize the database
        districtRepository.saveAndFlush(district);

        // Get all the districtList where latitude is not null
        defaultDistrictShouldBeFound("latitude.specified=true");

        // Get all the districtList where latitude is null
        defaultDistrictShouldNotBeFound("latitude.specified=false");
    }

    @Test
    @Transactional
    public void getAllDistrictsByLongitudeIsEqualToSomething() throws Exception {
        // Initialize the database
        districtRepository.saveAndFlush(district);

        // Get all the districtList where longitude equals to DEFAULT_LONGITUDE
        defaultDistrictShouldBeFound("longitude.equals=" + DEFAULT_LONGITUDE);

        // Get all the districtList where longitude equals to UPDATED_LONGITUDE
        defaultDistrictShouldNotBeFound("longitude.equals=" + UPDATED_LONGITUDE);
    }

    @Test
    @Transactional
    public void getAllDistrictsByLongitudeIsInShouldWork() throws Exception {
        // Initialize the database
        districtRepository.saveAndFlush(district);

        // Get all the districtList where longitude in DEFAULT_LONGITUDE or UPDATED_LONGITUDE
        defaultDistrictShouldBeFound("longitude.in=" + DEFAULT_LONGITUDE + "," + UPDATED_LONGITUDE);

        // Get all the districtList where longitude equals to UPDATED_LONGITUDE
        defaultDistrictShouldNotBeFound("longitude.in=" + UPDATED_LONGITUDE);
    }

    @Test
    @Transactional
    public void getAllDistrictsByLongitudeIsNullOrNotNull() throws Exception {
        // Initialize the database
        districtRepository.saveAndFlush(district);

        // Get all the districtList where longitude is not null
        defaultDistrictShouldBeFound("longitude.specified=true");

        // Get all the districtList where longitude is null
        defaultDistrictShouldNotBeFound("longitude.specified=false");
    }

    @Test
    @Transactional
    public void getAllDistrictsByEnabledIsEqualToSomething() throws Exception {
        // Initialize the database
        districtRepository.saveAndFlush(district);

        // Get all the districtList where enabled equals to DEFAULT_ENABLED
        defaultDistrictShouldBeFound("enabled.equals=" + DEFAULT_ENABLED);

        // Get all the districtList where enabled equals to UPDATED_ENABLED
        defaultDistrictShouldNotBeFound("enabled.equals=" + UPDATED_ENABLED);
    }

    @Test
    @Transactional
    public void getAllDistrictsByEnabledIsInShouldWork() throws Exception {
        // Initialize the database
        districtRepository.saveAndFlush(district);

        // Get all the districtList where enabled in DEFAULT_ENABLED or UPDATED_ENABLED
        defaultDistrictShouldBeFound("enabled.in=" + DEFAULT_ENABLED + "," + UPDATED_ENABLED);

        // Get all the districtList where enabled equals to UPDATED_ENABLED
        defaultDistrictShouldNotBeFound("enabled.in=" + UPDATED_ENABLED);
    }

    @Test
    @Transactional
    public void getAllDistrictsByEnabledIsNullOrNotNull() throws Exception {
        // Initialize the database
        districtRepository.saveAndFlush(district);

        // Get all the districtList where enabled is not null
        defaultDistrictShouldBeFound("enabled.specified=true");

        // Get all the districtList where enabled is null
        defaultDistrictShouldNotBeFound("enabled.specified=false");
    }

    @Test
    @Transactional
    public void getAllDistrictsByCreateAtIsEqualToSomething() throws Exception {
        // Initialize the database
        districtRepository.saveAndFlush(district);

        // Get all the districtList where createAt equals to DEFAULT_CREATE_AT
        defaultDistrictShouldBeFound("createAt.equals=" + DEFAULT_CREATE_AT);

        // Get all the districtList where createAt equals to UPDATED_CREATE_AT
        defaultDistrictShouldNotBeFound("createAt.equals=" + UPDATED_CREATE_AT);
    }

    @Test
    @Transactional
    public void getAllDistrictsByCreateAtIsInShouldWork() throws Exception {
        // Initialize the database
        districtRepository.saveAndFlush(district);

        // Get all the districtList where createAt in DEFAULT_CREATE_AT or UPDATED_CREATE_AT
        defaultDistrictShouldBeFound("createAt.in=" + DEFAULT_CREATE_AT + "," + UPDATED_CREATE_AT);

        // Get all the districtList where createAt equals to UPDATED_CREATE_AT
        defaultDistrictShouldNotBeFound("createAt.in=" + UPDATED_CREATE_AT);
    }

    @Test
    @Transactional
    public void getAllDistrictsByCreateAtIsNullOrNotNull() throws Exception {
        // Initialize the database
        districtRepository.saveAndFlush(district);

        // Get all the districtList where createAt is not null
        defaultDistrictShouldBeFound("createAt.specified=true");

        // Get all the districtList where createAt is null
        defaultDistrictShouldNotBeFound("createAt.specified=false");
    }

    @Test
    @Transactional
    public void getAllDistrictsByUpdateAtIsEqualToSomething() throws Exception {
        // Initialize the database
        districtRepository.saveAndFlush(district);

        // Get all the districtList where updateAt equals to DEFAULT_UPDATE_AT
        defaultDistrictShouldBeFound("updateAt.equals=" + DEFAULT_UPDATE_AT);

        // Get all the districtList where updateAt equals to UPDATED_UPDATE_AT
        defaultDistrictShouldNotBeFound("updateAt.equals=" + UPDATED_UPDATE_AT);
    }

    @Test
    @Transactional
    public void getAllDistrictsByUpdateAtIsInShouldWork() throws Exception {
        // Initialize the database
        districtRepository.saveAndFlush(district);

        // Get all the districtList where updateAt in DEFAULT_UPDATE_AT or UPDATED_UPDATE_AT
        defaultDistrictShouldBeFound("updateAt.in=" + DEFAULT_UPDATE_AT + "," + UPDATED_UPDATE_AT);

        // Get all the districtList where updateAt equals to UPDATED_UPDATE_AT
        defaultDistrictShouldNotBeFound("updateAt.in=" + UPDATED_UPDATE_AT);
    }

    @Test
    @Transactional
    public void getAllDistrictsByUpdateAtIsNullOrNotNull() throws Exception {
        // Initialize the database
        districtRepository.saveAndFlush(district);

        // Get all the districtList where updateAt is not null
        defaultDistrictShouldBeFound("updateAt.specified=true");

        // Get all the districtList where updateAt is null
        defaultDistrictShouldNotBeFound("updateAt.specified=false");
    }

    @Test
    @Transactional
    public void getAllDistrictsByCityIsEqualToSomething() throws Exception {
        // Initialize the database
        City city = CityResourceIntTest.createEntity(em);
        em.persist(city);
        em.flush();
        district.setCity(city);
        districtRepository.saveAndFlush(district);
        Long cityId = city.getId();

        // Get all the districtList where city equals to cityId
        defaultDistrictShouldBeFound("cityId.equals=" + cityId);

        // Get all the districtList where city equals to cityId + 1
        defaultDistrictShouldNotBeFound("cityId.equals=" + (cityId + 1));
    }


    @Test
    @Transactional
    public void getAllDistrictsByWardsIsEqualToSomething() throws Exception {
        // Initialize the database
        Ward wards = WardResourceIntTest.createEntity(em);
        em.persist(wards);
        em.flush();
        district.addWards(wards);
        districtRepository.saveAndFlush(district);
        Long wardsId = wards.getId();

        // Get all the districtList where wards equals to wardsId
        defaultDistrictShouldBeFound("wardsId.equals=" + wardsId);

        // Get all the districtList where wards equals to wardsId + 1
        defaultDistrictShouldNotBeFound("wardsId.equals=" + (wardsId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultDistrictShouldBeFound(String filter) throws Exception {
        restDistrictMockMvc.perform(get("/api/districts?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(district.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].latitude").value(hasItem(DEFAULT_LATITUDE.doubleValue())))
            .andExpect(jsonPath("$.[*].longitude").value(hasItem(DEFAULT_LONGITUDE.doubleValue())))
            .andExpect(jsonPath("$.[*].enabled").value(hasItem(DEFAULT_ENABLED.booleanValue())))
            .andExpect(jsonPath("$.[*].createAt").value(hasItem(DEFAULT_CREATE_AT.toString())))
            .andExpect(jsonPath("$.[*].updateAt").value(hasItem(DEFAULT_UPDATE_AT.toString())));

        // Check, that the count call also returns 1
        restDistrictMockMvc.perform(get("/api/districts/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultDistrictShouldNotBeFound(String filter) throws Exception {
        restDistrictMockMvc.perform(get("/api/districts?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restDistrictMockMvc.perform(get("/api/districts/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingDistrict() throws Exception {
        // Get the district
        restDistrictMockMvc.perform(get("/api/districts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDistrict() throws Exception {
        // Initialize the database
        districtRepository.saveAndFlush(district);

        int databaseSizeBeforeUpdate = districtRepository.findAll().size();

        // Update the district
        District updatedDistrict = districtRepository.findById(district.getId()).get();
        // Disconnect from session so that the updates on updatedDistrict are not directly saved in db
        em.detach(updatedDistrict);
        updatedDistrict
            .name(UPDATED_NAME)
            .type(UPDATED_TYPE)
            .latitude(UPDATED_LATITUDE)
            .longitude(UPDATED_LONGITUDE)
            .enabled(UPDATED_ENABLED)
            .createAt(UPDATED_CREATE_AT)
            .updateAt(UPDATED_UPDATE_AT);
        DistrictDTO districtDTO = districtMapper.toDto(updatedDistrict);

        restDistrictMockMvc.perform(put("/api/districts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(districtDTO)))
            .andExpect(status().isOk());

        // Validate the District in the database
        List<District> districtList = districtRepository.findAll();
        assertThat(districtList).hasSize(databaseSizeBeforeUpdate);
        District testDistrict = districtList.get(districtList.size() - 1);
        assertThat(testDistrict.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testDistrict.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testDistrict.getLatitude()).isEqualTo(UPDATED_LATITUDE);
        assertThat(testDistrict.getLongitude()).isEqualTo(UPDATED_LONGITUDE);
        assertThat(testDistrict.isEnabled()).isEqualTo(UPDATED_ENABLED);
        assertThat(testDistrict.getCreateAt()).isEqualTo(UPDATED_CREATE_AT);
        assertThat(testDistrict.getUpdateAt()).isEqualTo(UPDATED_UPDATE_AT);
    }

    @Test
    @Transactional
    public void updateNonExistingDistrict() throws Exception {
        int databaseSizeBeforeUpdate = districtRepository.findAll().size();

        // Create the District
        DistrictDTO districtDTO = districtMapper.toDto(district);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDistrictMockMvc.perform(put("/api/districts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(districtDTO)))
            .andExpect(status().isBadRequest());

        // Validate the District in the database
        List<District> districtList = districtRepository.findAll();
        assertThat(districtList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDistrict() throws Exception {
        // Initialize the database
        districtRepository.saveAndFlush(district);

        int databaseSizeBeforeDelete = districtRepository.findAll().size();

        // Get the district
        restDistrictMockMvc.perform(delete("/api/districts/{id}", district.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<District> districtList = districtRepository.findAll();
        assertThat(districtList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(District.class);
        District district1 = new District();
        district1.setId(1L);
        District district2 = new District();
        district2.setId(district1.getId());
        assertThat(district1).isEqualTo(district2);
        district2.setId(2L);
        assertThat(district1).isNotEqualTo(district2);
        district1.setId(null);
        assertThat(district1).isNotEqualTo(district2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DistrictDTO.class);
        DistrictDTO districtDTO1 = new DistrictDTO();
        districtDTO1.setId(1L);
        DistrictDTO districtDTO2 = new DistrictDTO();
        assertThat(districtDTO1).isNotEqualTo(districtDTO2);
        districtDTO2.setId(districtDTO1.getId());
        assertThat(districtDTO1).isEqualTo(districtDTO2);
        districtDTO2.setId(2L);
        assertThat(districtDTO1).isNotEqualTo(districtDTO2);
        districtDTO1.setId(null);
        assertThat(districtDTO1).isNotEqualTo(districtDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(districtMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(districtMapper.fromId(null)).isNull();
    }
}
