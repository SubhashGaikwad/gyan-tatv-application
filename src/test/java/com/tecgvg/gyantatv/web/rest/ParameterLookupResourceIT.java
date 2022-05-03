package com.tecgvg.gyantatv.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.tecgvg.gyantatv.IntegrationTest;
import com.tecgvg.gyantatv.domain.ParameterLookup;
import com.tecgvg.gyantatv.domain.enumeration.ParameterType;
import com.tecgvg.gyantatv.repository.ParameterLookupRepository;
import com.tecgvg.gyantatv.service.criteria.ParameterLookupCriteria;
import com.tecgvg.gyantatv.service.dto.ParameterLookupDTO;
import com.tecgvg.gyantatv.service.mapper.ParameterLookupMapper;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link ParameterLookupResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ParameterLookupResourceIT {

    private static final String DEFAULT_PARAMETER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PARAMETER_NAME = "BBBBBBBBBB";

    private static final ParameterType DEFAULT_TYPE = ParameterType.REGISTRATION_CATEGORY;
    private static final ParameterType UPDATED_TYPE = ParameterType.OTHER;

    private static final String DEFAULT_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_VALUE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTIONS = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTIONS = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_ACTIVE = false;
    private static final Boolean UPDATED_IS_ACTIVE = true;

    private static final String DEFAULT_LAST_MODIFIED = "AAAAAAAAAA";
    private static final String UPDATED_LAST_MODIFIED = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_MODIFIED_BY = "AAAAAAAAAA";
    private static final String UPDATED_LAST_MODIFIED_BY = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/parameter-lookups";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ParameterLookupRepository parameterLookupRepository;

    @Autowired
    private ParameterLookupMapper parameterLookupMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restParameterLookupMockMvc;

    private ParameterLookup parameterLookup;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ParameterLookup createEntity(EntityManager em) {
        ParameterLookup parameterLookup = new ParameterLookup()
            .parameterName(DEFAULT_PARAMETER_NAME)
            .type(DEFAULT_TYPE)
            .value(DEFAULT_VALUE)
            .descriptions(DEFAULT_DESCRIPTIONS)
            .isActive(DEFAULT_IS_ACTIVE)
            .lastModified(DEFAULT_LAST_MODIFIED)
            .lastModifiedBy(DEFAULT_LAST_MODIFIED_BY);
        return parameterLookup;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ParameterLookup createUpdatedEntity(EntityManager em) {
        ParameterLookup parameterLookup = new ParameterLookup()
            .parameterName(UPDATED_PARAMETER_NAME)
            .type(UPDATED_TYPE)
            .value(UPDATED_VALUE)
            .descriptions(UPDATED_DESCRIPTIONS)
            .isActive(UPDATED_IS_ACTIVE)
            .lastModified(UPDATED_LAST_MODIFIED)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY);
        return parameterLookup;
    }

    @BeforeEach
    public void initTest() {
        parameterLookup = createEntity(em);
    }

    @Test
    @Transactional
    void createParameterLookup() throws Exception {
        int databaseSizeBeforeCreate = parameterLookupRepository.findAll().size();
        // Create the ParameterLookup
        ParameterLookupDTO parameterLookupDTO = parameterLookupMapper.toDto(parameterLookup);
        restParameterLookupMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(parameterLookupDTO))
            )
            .andExpect(status().isCreated());

        // Validate the ParameterLookup in the database
        List<ParameterLookup> parameterLookupList = parameterLookupRepository.findAll();
        assertThat(parameterLookupList).hasSize(databaseSizeBeforeCreate + 1);
        ParameterLookup testParameterLookup = parameterLookupList.get(parameterLookupList.size() - 1);
        assertThat(testParameterLookup.getParameterName()).isEqualTo(DEFAULT_PARAMETER_NAME);
        assertThat(testParameterLookup.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testParameterLookup.getValue()).isEqualTo(DEFAULT_VALUE);
        assertThat(testParameterLookup.getDescriptions()).isEqualTo(DEFAULT_DESCRIPTIONS);
        assertThat(testParameterLookup.getIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
        assertThat(testParameterLookup.getLastModified()).isEqualTo(DEFAULT_LAST_MODIFIED);
        assertThat(testParameterLookup.getLastModifiedBy()).isEqualTo(DEFAULT_LAST_MODIFIED_BY);
    }

    @Test
    @Transactional
    void createParameterLookupWithExistingId() throws Exception {
        // Create the ParameterLookup with an existing ID
        parameterLookup.setId(1L);
        ParameterLookupDTO parameterLookupDTO = parameterLookupMapper.toDto(parameterLookup);

        int databaseSizeBeforeCreate = parameterLookupRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restParameterLookupMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(parameterLookupDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ParameterLookup in the database
        List<ParameterLookup> parameterLookupList = parameterLookupRepository.findAll();
        assertThat(parameterLookupList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllParameterLookups() throws Exception {
        // Initialize the database
        parameterLookupRepository.saveAndFlush(parameterLookup);

        // Get all the parameterLookupList
        restParameterLookupMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(parameterLookup.getId().intValue())))
            .andExpect(jsonPath("$.[*].parameterName").value(hasItem(DEFAULT_PARAMETER_NAME)))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].value").value(hasItem(DEFAULT_VALUE)))
            .andExpect(jsonPath("$.[*].descriptions").value(hasItem(DEFAULT_DESCRIPTIONS)))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].lastModified").value(hasItem(DEFAULT_LAST_MODIFIED)))
            .andExpect(jsonPath("$.[*].lastModifiedBy").value(hasItem(DEFAULT_LAST_MODIFIED_BY)));
    }

    @Test
    @Transactional
    void getParameterLookup() throws Exception {
        // Initialize the database
        parameterLookupRepository.saveAndFlush(parameterLookup);

        // Get the parameterLookup
        restParameterLookupMockMvc
            .perform(get(ENTITY_API_URL_ID, parameterLookup.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(parameterLookup.getId().intValue()))
            .andExpect(jsonPath("$.parameterName").value(DEFAULT_PARAMETER_NAME))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.value").value(DEFAULT_VALUE))
            .andExpect(jsonPath("$.descriptions").value(DEFAULT_DESCRIPTIONS))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE.booleanValue()))
            .andExpect(jsonPath("$.lastModified").value(DEFAULT_LAST_MODIFIED))
            .andExpect(jsonPath("$.lastModifiedBy").value(DEFAULT_LAST_MODIFIED_BY));
    }

    @Test
    @Transactional
    void getParameterLookupsByIdFiltering() throws Exception {
        // Initialize the database
        parameterLookupRepository.saveAndFlush(parameterLookup);

        Long id = parameterLookup.getId();

        defaultParameterLookupShouldBeFound("id.equals=" + id);
        defaultParameterLookupShouldNotBeFound("id.notEquals=" + id);

        defaultParameterLookupShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultParameterLookupShouldNotBeFound("id.greaterThan=" + id);

        defaultParameterLookupShouldBeFound("id.lessThanOrEqual=" + id);
        defaultParameterLookupShouldNotBeFound("id.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllParameterLookupsByParameterNameIsEqualToSomething() throws Exception {
        // Initialize the database
        parameterLookupRepository.saveAndFlush(parameterLookup);

        // Get all the parameterLookupList where parameterName equals to DEFAULT_PARAMETER_NAME
        defaultParameterLookupShouldBeFound("parameterName.equals=" + DEFAULT_PARAMETER_NAME);

        // Get all the parameterLookupList where parameterName equals to UPDATED_PARAMETER_NAME
        defaultParameterLookupShouldNotBeFound("parameterName.equals=" + UPDATED_PARAMETER_NAME);
    }

    @Test
    @Transactional
    void getAllParameterLookupsByParameterNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        parameterLookupRepository.saveAndFlush(parameterLookup);

        // Get all the parameterLookupList where parameterName not equals to DEFAULT_PARAMETER_NAME
        defaultParameterLookupShouldNotBeFound("parameterName.notEquals=" + DEFAULT_PARAMETER_NAME);

        // Get all the parameterLookupList where parameterName not equals to UPDATED_PARAMETER_NAME
        defaultParameterLookupShouldBeFound("parameterName.notEquals=" + UPDATED_PARAMETER_NAME);
    }

    @Test
    @Transactional
    void getAllParameterLookupsByParameterNameIsInShouldWork() throws Exception {
        // Initialize the database
        parameterLookupRepository.saveAndFlush(parameterLookup);

        // Get all the parameterLookupList where parameterName in DEFAULT_PARAMETER_NAME or UPDATED_PARAMETER_NAME
        defaultParameterLookupShouldBeFound("parameterName.in=" + DEFAULT_PARAMETER_NAME + "," + UPDATED_PARAMETER_NAME);

        // Get all the parameterLookupList where parameterName equals to UPDATED_PARAMETER_NAME
        defaultParameterLookupShouldNotBeFound("parameterName.in=" + UPDATED_PARAMETER_NAME);
    }

    @Test
    @Transactional
    void getAllParameterLookupsByParameterNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        parameterLookupRepository.saveAndFlush(parameterLookup);

        // Get all the parameterLookupList where parameterName is not null
        defaultParameterLookupShouldBeFound("parameterName.specified=true");

        // Get all the parameterLookupList where parameterName is null
        defaultParameterLookupShouldNotBeFound("parameterName.specified=false");
    }

    @Test
    @Transactional
    void getAllParameterLookupsByParameterNameContainsSomething() throws Exception {
        // Initialize the database
        parameterLookupRepository.saveAndFlush(parameterLookup);

        // Get all the parameterLookupList where parameterName contains DEFAULT_PARAMETER_NAME
        defaultParameterLookupShouldBeFound("parameterName.contains=" + DEFAULT_PARAMETER_NAME);

        // Get all the parameterLookupList where parameterName contains UPDATED_PARAMETER_NAME
        defaultParameterLookupShouldNotBeFound("parameterName.contains=" + UPDATED_PARAMETER_NAME);
    }

    @Test
    @Transactional
    void getAllParameterLookupsByParameterNameNotContainsSomething() throws Exception {
        // Initialize the database
        parameterLookupRepository.saveAndFlush(parameterLookup);

        // Get all the parameterLookupList where parameterName does not contain DEFAULT_PARAMETER_NAME
        defaultParameterLookupShouldNotBeFound("parameterName.doesNotContain=" + DEFAULT_PARAMETER_NAME);

        // Get all the parameterLookupList where parameterName does not contain UPDATED_PARAMETER_NAME
        defaultParameterLookupShouldBeFound("parameterName.doesNotContain=" + UPDATED_PARAMETER_NAME);
    }

    @Test
    @Transactional
    void getAllParameterLookupsByTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        parameterLookupRepository.saveAndFlush(parameterLookup);

        // Get all the parameterLookupList where type equals to DEFAULT_TYPE
        defaultParameterLookupShouldBeFound("type.equals=" + DEFAULT_TYPE);

        // Get all the parameterLookupList where type equals to UPDATED_TYPE
        defaultParameterLookupShouldNotBeFound("type.equals=" + UPDATED_TYPE);
    }

    @Test
    @Transactional
    void getAllParameterLookupsByTypeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        parameterLookupRepository.saveAndFlush(parameterLookup);

        // Get all the parameterLookupList where type not equals to DEFAULT_TYPE
        defaultParameterLookupShouldNotBeFound("type.notEquals=" + DEFAULT_TYPE);

        // Get all the parameterLookupList where type not equals to UPDATED_TYPE
        defaultParameterLookupShouldBeFound("type.notEquals=" + UPDATED_TYPE);
    }

    @Test
    @Transactional
    void getAllParameterLookupsByTypeIsInShouldWork() throws Exception {
        // Initialize the database
        parameterLookupRepository.saveAndFlush(parameterLookup);

        // Get all the parameterLookupList where type in DEFAULT_TYPE or UPDATED_TYPE
        defaultParameterLookupShouldBeFound("type.in=" + DEFAULT_TYPE + "," + UPDATED_TYPE);

        // Get all the parameterLookupList where type equals to UPDATED_TYPE
        defaultParameterLookupShouldNotBeFound("type.in=" + UPDATED_TYPE);
    }

    @Test
    @Transactional
    void getAllParameterLookupsByTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        parameterLookupRepository.saveAndFlush(parameterLookup);

        // Get all the parameterLookupList where type is not null
        defaultParameterLookupShouldBeFound("type.specified=true");

        // Get all the parameterLookupList where type is null
        defaultParameterLookupShouldNotBeFound("type.specified=false");
    }

    @Test
    @Transactional
    void getAllParameterLookupsByValueIsEqualToSomething() throws Exception {
        // Initialize the database
        parameterLookupRepository.saveAndFlush(parameterLookup);

        // Get all the parameterLookupList where value equals to DEFAULT_VALUE
        defaultParameterLookupShouldBeFound("value.equals=" + DEFAULT_VALUE);

        // Get all the parameterLookupList where value equals to UPDATED_VALUE
        defaultParameterLookupShouldNotBeFound("value.equals=" + UPDATED_VALUE);
    }

    @Test
    @Transactional
    void getAllParameterLookupsByValueIsNotEqualToSomething() throws Exception {
        // Initialize the database
        parameterLookupRepository.saveAndFlush(parameterLookup);

        // Get all the parameterLookupList where value not equals to DEFAULT_VALUE
        defaultParameterLookupShouldNotBeFound("value.notEquals=" + DEFAULT_VALUE);

        // Get all the parameterLookupList where value not equals to UPDATED_VALUE
        defaultParameterLookupShouldBeFound("value.notEquals=" + UPDATED_VALUE);
    }

    @Test
    @Transactional
    void getAllParameterLookupsByValueIsInShouldWork() throws Exception {
        // Initialize the database
        parameterLookupRepository.saveAndFlush(parameterLookup);

        // Get all the parameterLookupList where value in DEFAULT_VALUE or UPDATED_VALUE
        defaultParameterLookupShouldBeFound("value.in=" + DEFAULT_VALUE + "," + UPDATED_VALUE);

        // Get all the parameterLookupList where value equals to UPDATED_VALUE
        defaultParameterLookupShouldNotBeFound("value.in=" + UPDATED_VALUE);
    }

    @Test
    @Transactional
    void getAllParameterLookupsByValueIsNullOrNotNull() throws Exception {
        // Initialize the database
        parameterLookupRepository.saveAndFlush(parameterLookup);

        // Get all the parameterLookupList where value is not null
        defaultParameterLookupShouldBeFound("value.specified=true");

        // Get all the parameterLookupList where value is null
        defaultParameterLookupShouldNotBeFound("value.specified=false");
    }

    @Test
    @Transactional
    void getAllParameterLookupsByValueContainsSomething() throws Exception {
        // Initialize the database
        parameterLookupRepository.saveAndFlush(parameterLookup);

        // Get all the parameterLookupList where value contains DEFAULT_VALUE
        defaultParameterLookupShouldBeFound("value.contains=" + DEFAULT_VALUE);

        // Get all the parameterLookupList where value contains UPDATED_VALUE
        defaultParameterLookupShouldNotBeFound("value.contains=" + UPDATED_VALUE);
    }

    @Test
    @Transactional
    void getAllParameterLookupsByValueNotContainsSomething() throws Exception {
        // Initialize the database
        parameterLookupRepository.saveAndFlush(parameterLookup);

        // Get all the parameterLookupList where value does not contain DEFAULT_VALUE
        defaultParameterLookupShouldNotBeFound("value.doesNotContain=" + DEFAULT_VALUE);

        // Get all the parameterLookupList where value does not contain UPDATED_VALUE
        defaultParameterLookupShouldBeFound("value.doesNotContain=" + UPDATED_VALUE);
    }

    @Test
    @Transactional
    void getAllParameterLookupsByDescriptionsIsEqualToSomething() throws Exception {
        // Initialize the database
        parameterLookupRepository.saveAndFlush(parameterLookup);

        // Get all the parameterLookupList where descriptions equals to DEFAULT_DESCRIPTIONS
        defaultParameterLookupShouldBeFound("descriptions.equals=" + DEFAULT_DESCRIPTIONS);

        // Get all the parameterLookupList where descriptions equals to UPDATED_DESCRIPTIONS
        defaultParameterLookupShouldNotBeFound("descriptions.equals=" + UPDATED_DESCRIPTIONS);
    }

    @Test
    @Transactional
    void getAllParameterLookupsByDescriptionsIsNotEqualToSomething() throws Exception {
        // Initialize the database
        parameterLookupRepository.saveAndFlush(parameterLookup);

        // Get all the parameterLookupList where descriptions not equals to DEFAULT_DESCRIPTIONS
        defaultParameterLookupShouldNotBeFound("descriptions.notEquals=" + DEFAULT_DESCRIPTIONS);

        // Get all the parameterLookupList where descriptions not equals to UPDATED_DESCRIPTIONS
        defaultParameterLookupShouldBeFound("descriptions.notEquals=" + UPDATED_DESCRIPTIONS);
    }

    @Test
    @Transactional
    void getAllParameterLookupsByDescriptionsIsInShouldWork() throws Exception {
        // Initialize the database
        parameterLookupRepository.saveAndFlush(parameterLookup);

        // Get all the parameterLookupList where descriptions in DEFAULT_DESCRIPTIONS or UPDATED_DESCRIPTIONS
        defaultParameterLookupShouldBeFound("descriptions.in=" + DEFAULT_DESCRIPTIONS + "," + UPDATED_DESCRIPTIONS);

        // Get all the parameterLookupList where descriptions equals to UPDATED_DESCRIPTIONS
        defaultParameterLookupShouldNotBeFound("descriptions.in=" + UPDATED_DESCRIPTIONS);
    }

    @Test
    @Transactional
    void getAllParameterLookupsByDescriptionsIsNullOrNotNull() throws Exception {
        // Initialize the database
        parameterLookupRepository.saveAndFlush(parameterLookup);

        // Get all the parameterLookupList where descriptions is not null
        defaultParameterLookupShouldBeFound("descriptions.specified=true");

        // Get all the parameterLookupList where descriptions is null
        defaultParameterLookupShouldNotBeFound("descriptions.specified=false");
    }

    @Test
    @Transactional
    void getAllParameterLookupsByDescriptionsContainsSomething() throws Exception {
        // Initialize the database
        parameterLookupRepository.saveAndFlush(parameterLookup);

        // Get all the parameterLookupList where descriptions contains DEFAULT_DESCRIPTIONS
        defaultParameterLookupShouldBeFound("descriptions.contains=" + DEFAULT_DESCRIPTIONS);

        // Get all the parameterLookupList where descriptions contains UPDATED_DESCRIPTIONS
        defaultParameterLookupShouldNotBeFound("descriptions.contains=" + UPDATED_DESCRIPTIONS);
    }

    @Test
    @Transactional
    void getAllParameterLookupsByDescriptionsNotContainsSomething() throws Exception {
        // Initialize the database
        parameterLookupRepository.saveAndFlush(parameterLookup);

        // Get all the parameterLookupList where descriptions does not contain DEFAULT_DESCRIPTIONS
        defaultParameterLookupShouldNotBeFound("descriptions.doesNotContain=" + DEFAULT_DESCRIPTIONS);

        // Get all the parameterLookupList where descriptions does not contain UPDATED_DESCRIPTIONS
        defaultParameterLookupShouldBeFound("descriptions.doesNotContain=" + UPDATED_DESCRIPTIONS);
    }

    @Test
    @Transactional
    void getAllParameterLookupsByIsActiveIsEqualToSomething() throws Exception {
        // Initialize the database
        parameterLookupRepository.saveAndFlush(parameterLookup);

        // Get all the parameterLookupList where isActive equals to DEFAULT_IS_ACTIVE
        defaultParameterLookupShouldBeFound("isActive.equals=" + DEFAULT_IS_ACTIVE);

        // Get all the parameterLookupList where isActive equals to UPDATED_IS_ACTIVE
        defaultParameterLookupShouldNotBeFound("isActive.equals=" + UPDATED_IS_ACTIVE);
    }

    @Test
    @Transactional
    void getAllParameterLookupsByIsActiveIsNotEqualToSomething() throws Exception {
        // Initialize the database
        parameterLookupRepository.saveAndFlush(parameterLookup);

        // Get all the parameterLookupList where isActive not equals to DEFAULT_IS_ACTIVE
        defaultParameterLookupShouldNotBeFound("isActive.notEquals=" + DEFAULT_IS_ACTIVE);

        // Get all the parameterLookupList where isActive not equals to UPDATED_IS_ACTIVE
        defaultParameterLookupShouldBeFound("isActive.notEquals=" + UPDATED_IS_ACTIVE);
    }

    @Test
    @Transactional
    void getAllParameterLookupsByIsActiveIsInShouldWork() throws Exception {
        // Initialize the database
        parameterLookupRepository.saveAndFlush(parameterLookup);

        // Get all the parameterLookupList where isActive in DEFAULT_IS_ACTIVE or UPDATED_IS_ACTIVE
        defaultParameterLookupShouldBeFound("isActive.in=" + DEFAULT_IS_ACTIVE + "," + UPDATED_IS_ACTIVE);

        // Get all the parameterLookupList where isActive equals to UPDATED_IS_ACTIVE
        defaultParameterLookupShouldNotBeFound("isActive.in=" + UPDATED_IS_ACTIVE);
    }

    @Test
    @Transactional
    void getAllParameterLookupsByIsActiveIsNullOrNotNull() throws Exception {
        // Initialize the database
        parameterLookupRepository.saveAndFlush(parameterLookup);

        // Get all the parameterLookupList where isActive is not null
        defaultParameterLookupShouldBeFound("isActive.specified=true");

        // Get all the parameterLookupList where isActive is null
        defaultParameterLookupShouldNotBeFound("isActive.specified=false");
    }

    @Test
    @Transactional
    void getAllParameterLookupsByLastModifiedIsEqualToSomething() throws Exception {
        // Initialize the database
        parameterLookupRepository.saveAndFlush(parameterLookup);

        // Get all the parameterLookupList where lastModified equals to DEFAULT_LAST_MODIFIED
        defaultParameterLookupShouldBeFound("lastModified.equals=" + DEFAULT_LAST_MODIFIED);

        // Get all the parameterLookupList where lastModified equals to UPDATED_LAST_MODIFIED
        defaultParameterLookupShouldNotBeFound("lastModified.equals=" + UPDATED_LAST_MODIFIED);
    }

    @Test
    @Transactional
    void getAllParameterLookupsByLastModifiedIsNotEqualToSomething() throws Exception {
        // Initialize the database
        parameterLookupRepository.saveAndFlush(parameterLookup);

        // Get all the parameterLookupList where lastModified not equals to DEFAULT_LAST_MODIFIED
        defaultParameterLookupShouldNotBeFound("lastModified.notEquals=" + DEFAULT_LAST_MODIFIED);

        // Get all the parameterLookupList where lastModified not equals to UPDATED_LAST_MODIFIED
        defaultParameterLookupShouldBeFound("lastModified.notEquals=" + UPDATED_LAST_MODIFIED);
    }

    @Test
    @Transactional
    void getAllParameterLookupsByLastModifiedIsInShouldWork() throws Exception {
        // Initialize the database
        parameterLookupRepository.saveAndFlush(parameterLookup);

        // Get all the parameterLookupList where lastModified in DEFAULT_LAST_MODIFIED or UPDATED_LAST_MODIFIED
        defaultParameterLookupShouldBeFound("lastModified.in=" + DEFAULT_LAST_MODIFIED + "," + UPDATED_LAST_MODIFIED);

        // Get all the parameterLookupList where lastModified equals to UPDATED_LAST_MODIFIED
        defaultParameterLookupShouldNotBeFound("lastModified.in=" + UPDATED_LAST_MODIFIED);
    }

    @Test
    @Transactional
    void getAllParameterLookupsByLastModifiedIsNullOrNotNull() throws Exception {
        // Initialize the database
        parameterLookupRepository.saveAndFlush(parameterLookup);

        // Get all the parameterLookupList where lastModified is not null
        defaultParameterLookupShouldBeFound("lastModified.specified=true");

        // Get all the parameterLookupList where lastModified is null
        defaultParameterLookupShouldNotBeFound("lastModified.specified=false");
    }

    @Test
    @Transactional
    void getAllParameterLookupsByLastModifiedContainsSomething() throws Exception {
        // Initialize the database
        parameterLookupRepository.saveAndFlush(parameterLookup);

        // Get all the parameterLookupList where lastModified contains DEFAULT_LAST_MODIFIED
        defaultParameterLookupShouldBeFound("lastModified.contains=" + DEFAULT_LAST_MODIFIED);

        // Get all the parameterLookupList where lastModified contains UPDATED_LAST_MODIFIED
        defaultParameterLookupShouldNotBeFound("lastModified.contains=" + UPDATED_LAST_MODIFIED);
    }

    @Test
    @Transactional
    void getAllParameterLookupsByLastModifiedNotContainsSomething() throws Exception {
        // Initialize the database
        parameterLookupRepository.saveAndFlush(parameterLookup);

        // Get all the parameterLookupList where lastModified does not contain DEFAULT_LAST_MODIFIED
        defaultParameterLookupShouldNotBeFound("lastModified.doesNotContain=" + DEFAULT_LAST_MODIFIED);

        // Get all the parameterLookupList where lastModified does not contain UPDATED_LAST_MODIFIED
        defaultParameterLookupShouldBeFound("lastModified.doesNotContain=" + UPDATED_LAST_MODIFIED);
    }

    @Test
    @Transactional
    void getAllParameterLookupsByLastModifiedByIsEqualToSomething() throws Exception {
        // Initialize the database
        parameterLookupRepository.saveAndFlush(parameterLookup);

        // Get all the parameterLookupList where lastModifiedBy equals to DEFAULT_LAST_MODIFIED_BY
        defaultParameterLookupShouldBeFound("lastModifiedBy.equals=" + DEFAULT_LAST_MODIFIED_BY);

        // Get all the parameterLookupList where lastModifiedBy equals to UPDATED_LAST_MODIFIED_BY
        defaultParameterLookupShouldNotBeFound("lastModifiedBy.equals=" + UPDATED_LAST_MODIFIED_BY);
    }

    @Test
    @Transactional
    void getAllParameterLookupsByLastModifiedByIsNotEqualToSomething() throws Exception {
        // Initialize the database
        parameterLookupRepository.saveAndFlush(parameterLookup);

        // Get all the parameterLookupList where lastModifiedBy not equals to DEFAULT_LAST_MODIFIED_BY
        defaultParameterLookupShouldNotBeFound("lastModifiedBy.notEquals=" + DEFAULT_LAST_MODIFIED_BY);

        // Get all the parameterLookupList where lastModifiedBy not equals to UPDATED_LAST_MODIFIED_BY
        defaultParameterLookupShouldBeFound("lastModifiedBy.notEquals=" + UPDATED_LAST_MODIFIED_BY);
    }

    @Test
    @Transactional
    void getAllParameterLookupsByLastModifiedByIsInShouldWork() throws Exception {
        // Initialize the database
        parameterLookupRepository.saveAndFlush(parameterLookup);

        // Get all the parameterLookupList where lastModifiedBy in DEFAULT_LAST_MODIFIED_BY or UPDATED_LAST_MODIFIED_BY
        defaultParameterLookupShouldBeFound("lastModifiedBy.in=" + DEFAULT_LAST_MODIFIED_BY + "," + UPDATED_LAST_MODIFIED_BY);

        // Get all the parameterLookupList where lastModifiedBy equals to UPDATED_LAST_MODIFIED_BY
        defaultParameterLookupShouldNotBeFound("lastModifiedBy.in=" + UPDATED_LAST_MODIFIED_BY);
    }

    @Test
    @Transactional
    void getAllParameterLookupsByLastModifiedByIsNullOrNotNull() throws Exception {
        // Initialize the database
        parameterLookupRepository.saveAndFlush(parameterLookup);

        // Get all the parameterLookupList where lastModifiedBy is not null
        defaultParameterLookupShouldBeFound("lastModifiedBy.specified=true");

        // Get all the parameterLookupList where lastModifiedBy is null
        defaultParameterLookupShouldNotBeFound("lastModifiedBy.specified=false");
    }

    @Test
    @Transactional
    void getAllParameterLookupsByLastModifiedByContainsSomething() throws Exception {
        // Initialize the database
        parameterLookupRepository.saveAndFlush(parameterLookup);

        // Get all the parameterLookupList where lastModifiedBy contains DEFAULT_LAST_MODIFIED_BY
        defaultParameterLookupShouldBeFound("lastModifiedBy.contains=" + DEFAULT_LAST_MODIFIED_BY);

        // Get all the parameterLookupList where lastModifiedBy contains UPDATED_LAST_MODIFIED_BY
        defaultParameterLookupShouldNotBeFound("lastModifiedBy.contains=" + UPDATED_LAST_MODIFIED_BY);
    }

    @Test
    @Transactional
    void getAllParameterLookupsByLastModifiedByNotContainsSomething() throws Exception {
        // Initialize the database
        parameterLookupRepository.saveAndFlush(parameterLookup);

        // Get all the parameterLookupList where lastModifiedBy does not contain DEFAULT_LAST_MODIFIED_BY
        defaultParameterLookupShouldNotBeFound("lastModifiedBy.doesNotContain=" + DEFAULT_LAST_MODIFIED_BY);

        // Get all the parameterLookupList where lastModifiedBy does not contain UPDATED_LAST_MODIFIED_BY
        defaultParameterLookupShouldBeFound("lastModifiedBy.doesNotContain=" + UPDATED_LAST_MODIFIED_BY);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultParameterLookupShouldBeFound(String filter) throws Exception {
        restParameterLookupMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(parameterLookup.getId().intValue())))
            .andExpect(jsonPath("$.[*].parameterName").value(hasItem(DEFAULT_PARAMETER_NAME)))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].value").value(hasItem(DEFAULT_VALUE)))
            .andExpect(jsonPath("$.[*].descriptions").value(hasItem(DEFAULT_DESCRIPTIONS)))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].lastModified").value(hasItem(DEFAULT_LAST_MODIFIED)))
            .andExpect(jsonPath("$.[*].lastModifiedBy").value(hasItem(DEFAULT_LAST_MODIFIED_BY)));

        // Check, that the count call also returns 1
        restParameterLookupMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultParameterLookupShouldNotBeFound(String filter) throws Exception {
        restParameterLookupMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restParameterLookupMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingParameterLookup() throws Exception {
        // Get the parameterLookup
        restParameterLookupMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewParameterLookup() throws Exception {
        // Initialize the database
        parameterLookupRepository.saveAndFlush(parameterLookup);

        int databaseSizeBeforeUpdate = parameterLookupRepository.findAll().size();

        // Update the parameterLookup
        ParameterLookup updatedParameterLookup = parameterLookupRepository.findById(parameterLookup.getId()).get();
        // Disconnect from session so that the updates on updatedParameterLookup are not directly saved in db
        em.detach(updatedParameterLookup);
        updatedParameterLookup
            .parameterName(UPDATED_PARAMETER_NAME)
            .type(UPDATED_TYPE)
            .value(UPDATED_VALUE)
            .descriptions(UPDATED_DESCRIPTIONS)
            .isActive(UPDATED_IS_ACTIVE)
            .lastModified(UPDATED_LAST_MODIFIED)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY);
        ParameterLookupDTO parameterLookupDTO = parameterLookupMapper.toDto(updatedParameterLookup);

        restParameterLookupMockMvc
            .perform(
                put(ENTITY_API_URL_ID, parameterLookupDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(parameterLookupDTO))
            )
            .andExpect(status().isOk());

        // Validate the ParameterLookup in the database
        List<ParameterLookup> parameterLookupList = parameterLookupRepository.findAll();
        assertThat(parameterLookupList).hasSize(databaseSizeBeforeUpdate);
        ParameterLookup testParameterLookup = parameterLookupList.get(parameterLookupList.size() - 1);
        assertThat(testParameterLookup.getParameterName()).isEqualTo(UPDATED_PARAMETER_NAME);
        assertThat(testParameterLookup.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testParameterLookup.getValue()).isEqualTo(UPDATED_VALUE);
        assertThat(testParameterLookup.getDescriptions()).isEqualTo(UPDATED_DESCRIPTIONS);
        assertThat(testParameterLookup.getIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testParameterLookup.getLastModified()).isEqualTo(UPDATED_LAST_MODIFIED);
        assertThat(testParameterLookup.getLastModifiedBy()).isEqualTo(UPDATED_LAST_MODIFIED_BY);
    }

    @Test
    @Transactional
    void putNonExistingParameterLookup() throws Exception {
        int databaseSizeBeforeUpdate = parameterLookupRepository.findAll().size();
        parameterLookup.setId(count.incrementAndGet());

        // Create the ParameterLookup
        ParameterLookupDTO parameterLookupDTO = parameterLookupMapper.toDto(parameterLookup);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restParameterLookupMockMvc
            .perform(
                put(ENTITY_API_URL_ID, parameterLookupDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(parameterLookupDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ParameterLookup in the database
        List<ParameterLookup> parameterLookupList = parameterLookupRepository.findAll();
        assertThat(parameterLookupList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchParameterLookup() throws Exception {
        int databaseSizeBeforeUpdate = parameterLookupRepository.findAll().size();
        parameterLookup.setId(count.incrementAndGet());

        // Create the ParameterLookup
        ParameterLookupDTO parameterLookupDTO = parameterLookupMapper.toDto(parameterLookup);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restParameterLookupMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(parameterLookupDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ParameterLookup in the database
        List<ParameterLookup> parameterLookupList = parameterLookupRepository.findAll();
        assertThat(parameterLookupList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamParameterLookup() throws Exception {
        int databaseSizeBeforeUpdate = parameterLookupRepository.findAll().size();
        parameterLookup.setId(count.incrementAndGet());

        // Create the ParameterLookup
        ParameterLookupDTO parameterLookupDTO = parameterLookupMapper.toDto(parameterLookup);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restParameterLookupMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(parameterLookupDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ParameterLookup in the database
        List<ParameterLookup> parameterLookupList = parameterLookupRepository.findAll();
        assertThat(parameterLookupList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateParameterLookupWithPatch() throws Exception {
        // Initialize the database
        parameterLookupRepository.saveAndFlush(parameterLookup);

        int databaseSizeBeforeUpdate = parameterLookupRepository.findAll().size();

        // Update the parameterLookup using partial update
        ParameterLookup partialUpdatedParameterLookup = new ParameterLookup();
        partialUpdatedParameterLookup.setId(parameterLookup.getId());

        partialUpdatedParameterLookup.descriptions(UPDATED_DESCRIPTIONS).isActive(UPDATED_IS_ACTIVE);

        restParameterLookupMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedParameterLookup.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedParameterLookup))
            )
            .andExpect(status().isOk());

        // Validate the ParameterLookup in the database
        List<ParameterLookup> parameterLookupList = parameterLookupRepository.findAll();
        assertThat(parameterLookupList).hasSize(databaseSizeBeforeUpdate);
        ParameterLookup testParameterLookup = parameterLookupList.get(parameterLookupList.size() - 1);
        assertThat(testParameterLookup.getParameterName()).isEqualTo(DEFAULT_PARAMETER_NAME);
        assertThat(testParameterLookup.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testParameterLookup.getValue()).isEqualTo(DEFAULT_VALUE);
        assertThat(testParameterLookup.getDescriptions()).isEqualTo(UPDATED_DESCRIPTIONS);
        assertThat(testParameterLookup.getIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testParameterLookup.getLastModified()).isEqualTo(DEFAULT_LAST_MODIFIED);
        assertThat(testParameterLookup.getLastModifiedBy()).isEqualTo(DEFAULT_LAST_MODIFIED_BY);
    }

    @Test
    @Transactional
    void fullUpdateParameterLookupWithPatch() throws Exception {
        // Initialize the database
        parameterLookupRepository.saveAndFlush(parameterLookup);

        int databaseSizeBeforeUpdate = parameterLookupRepository.findAll().size();

        // Update the parameterLookup using partial update
        ParameterLookup partialUpdatedParameterLookup = new ParameterLookup();
        partialUpdatedParameterLookup.setId(parameterLookup.getId());

        partialUpdatedParameterLookup
            .parameterName(UPDATED_PARAMETER_NAME)
            .type(UPDATED_TYPE)
            .value(UPDATED_VALUE)
            .descriptions(UPDATED_DESCRIPTIONS)
            .isActive(UPDATED_IS_ACTIVE)
            .lastModified(UPDATED_LAST_MODIFIED)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY);

        restParameterLookupMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedParameterLookup.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedParameterLookup))
            )
            .andExpect(status().isOk());

        // Validate the ParameterLookup in the database
        List<ParameterLookup> parameterLookupList = parameterLookupRepository.findAll();
        assertThat(parameterLookupList).hasSize(databaseSizeBeforeUpdate);
        ParameterLookup testParameterLookup = parameterLookupList.get(parameterLookupList.size() - 1);
        assertThat(testParameterLookup.getParameterName()).isEqualTo(UPDATED_PARAMETER_NAME);
        assertThat(testParameterLookup.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testParameterLookup.getValue()).isEqualTo(UPDATED_VALUE);
        assertThat(testParameterLookup.getDescriptions()).isEqualTo(UPDATED_DESCRIPTIONS);
        assertThat(testParameterLookup.getIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testParameterLookup.getLastModified()).isEqualTo(UPDATED_LAST_MODIFIED);
        assertThat(testParameterLookup.getLastModifiedBy()).isEqualTo(UPDATED_LAST_MODIFIED_BY);
    }

    @Test
    @Transactional
    void patchNonExistingParameterLookup() throws Exception {
        int databaseSizeBeforeUpdate = parameterLookupRepository.findAll().size();
        parameterLookup.setId(count.incrementAndGet());

        // Create the ParameterLookup
        ParameterLookupDTO parameterLookupDTO = parameterLookupMapper.toDto(parameterLookup);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restParameterLookupMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, parameterLookupDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(parameterLookupDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ParameterLookup in the database
        List<ParameterLookup> parameterLookupList = parameterLookupRepository.findAll();
        assertThat(parameterLookupList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchParameterLookup() throws Exception {
        int databaseSizeBeforeUpdate = parameterLookupRepository.findAll().size();
        parameterLookup.setId(count.incrementAndGet());

        // Create the ParameterLookup
        ParameterLookupDTO parameterLookupDTO = parameterLookupMapper.toDto(parameterLookup);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restParameterLookupMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(parameterLookupDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ParameterLookup in the database
        List<ParameterLookup> parameterLookupList = parameterLookupRepository.findAll();
        assertThat(parameterLookupList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamParameterLookup() throws Exception {
        int databaseSizeBeforeUpdate = parameterLookupRepository.findAll().size();
        parameterLookup.setId(count.incrementAndGet());

        // Create the ParameterLookup
        ParameterLookupDTO parameterLookupDTO = parameterLookupMapper.toDto(parameterLookup);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restParameterLookupMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(parameterLookupDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ParameterLookup in the database
        List<ParameterLookup> parameterLookupList = parameterLookupRepository.findAll();
        assertThat(parameterLookupList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteParameterLookup() throws Exception {
        // Initialize the database
        parameterLookupRepository.saveAndFlush(parameterLookup);

        int databaseSizeBeforeDelete = parameterLookupRepository.findAll().size();

        // Delete the parameterLookup
        restParameterLookupMockMvc
            .perform(delete(ENTITY_API_URL_ID, parameterLookup.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ParameterLookup> parameterLookupList = parameterLookupRepository.findAll();
        assertThat(parameterLookupList).hasSize(databaseSizeBeforeDelete - 1);
    }
}