package com.tecgvg.gyantatv.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.tecgvg.gyantatv.IntegrationTest;
import com.tecgvg.gyantatv.domain.ProductClientAgreement;
import com.tecgvg.gyantatv.domain.enumeration.PenaltyTerms;
import com.tecgvg.gyantatv.repository.ProductClientAgreementRepository;
import com.tecgvg.gyantatv.service.criteria.ProductClientAgreementCriteria;
import com.tecgvg.gyantatv.service.dto.ProductClientAgreementDTO;
import com.tecgvg.gyantatv.service.mapper.ProductClientAgreementMapper;
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
 * Integration tests for the {@link ProductClientAgreementResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ProductClientAgreementResourceIT {

    private static final Long DEFAULT_PRICE = 1L;
    private static final Long UPDATED_PRICE = 2L;
    private static final Long SMALLER_PRICE = 1L - 1L;

    private static final Double DEFAULT_CREDIT_PERIOD = 1D;
    private static final Double UPDATED_CREDIT_PERIOD = 2D;
    private static final Double SMALLER_CREDIT_PERIOD = 1D - 1D;

    private static final Long DEFAULT_PENALTY = 1L;
    private static final Long UPDATED_PENALTY = 2L;
    private static final Long SMALLER_PENALTY = 1L - 1L;

    private static final PenaltyTerms DEFAULT_PENALTY_TERMS = PenaltyTerms.DAILY;
    private static final PenaltyTerms UPDATED_PENALTY_TERMS = PenaltyTerms.MONTHLY;

    private static final String DEFAULT_NOTES = "AAAAAAAAAA";
    private static final String UPDATED_NOTES = "BBBBBBBBBB";

    private static final String DEFAULT_IMAGE = "AAAAAAAAAA";
    private static final String UPDATED_IMAGE = "BBBBBBBBBB";

    private static final Long DEFAULT_AGREEMENT_PERIOD = 1L;
    private static final Long UPDATED_AGREEMENT_PERIOD = 2L;
    private static final Long SMALLER_AGREEMENT_PERIOD = 1L - 1L;

    private static final String DEFAULT_TERM_AND_CONDITIONS = "AAAAAAAAAA";
    private static final String UPDATED_TERM_AND_CONDITIONS = "BBBBBBBBBB";

    private static final String DEFAULT_FREE_FIELD_1 = "AAAAAAAAAA";
    private static final String UPDATED_FREE_FIELD_1 = "BBBBBBBBBB";

    private static final String DEFAULT_FREE_FIELD_2 = "AAAAAAAAAA";
    private static final String UPDATED_FREE_FIELD_2 = "BBBBBBBBBB";

    private static final String DEFAULT_FREE_FIELD_3 = "AAAAAAAAAA";
    private static final String UPDATED_FREE_FIELD_3 = "BBBBBBBBBB";

    private static final String DEFAULT_FREE_FIELD_4 = "AAAAAAAAAA";
    private static final String UPDATED_FREE_FIELD_4 = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_MODIFIED = "AAAAAAAAAA";
    private static final String UPDATED_LAST_MODIFIED = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_MODIFIED_BY = "AAAAAAAAAA";
    private static final String UPDATED_LAST_MODIFIED_BY = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/product-client-agreements";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ProductClientAgreementRepository productClientAgreementRepository;

    @Autowired
    private ProductClientAgreementMapper productClientAgreementMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restProductClientAgreementMockMvc;

    private ProductClientAgreement productClientAgreement;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProductClientAgreement createEntity(EntityManager em) {
        ProductClientAgreement productClientAgreement = new ProductClientAgreement()
            .price(DEFAULT_PRICE)
            .creditPeriod(DEFAULT_CREDIT_PERIOD)
            .penalty(DEFAULT_PENALTY)
            .penaltyTerms(DEFAULT_PENALTY_TERMS)
            .notes(DEFAULT_NOTES)
            .image(DEFAULT_IMAGE)
            .agreementPeriod(DEFAULT_AGREEMENT_PERIOD)
            .termAndConditions(DEFAULT_TERM_AND_CONDITIONS)
            .freeField1(DEFAULT_FREE_FIELD_1)
            .freeField2(DEFAULT_FREE_FIELD_2)
            .freeField3(DEFAULT_FREE_FIELD_3)
            .freeField4(DEFAULT_FREE_FIELD_4)
            .lastModified(DEFAULT_LAST_MODIFIED)
            .lastModifiedBy(DEFAULT_LAST_MODIFIED_BY);
        return productClientAgreement;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProductClientAgreement createUpdatedEntity(EntityManager em) {
        ProductClientAgreement productClientAgreement = new ProductClientAgreement()
            .price(UPDATED_PRICE)
            .creditPeriod(UPDATED_CREDIT_PERIOD)
            .penalty(UPDATED_PENALTY)
            .penaltyTerms(UPDATED_PENALTY_TERMS)
            .notes(UPDATED_NOTES)
            .image(UPDATED_IMAGE)
            .agreementPeriod(UPDATED_AGREEMENT_PERIOD)
            .termAndConditions(UPDATED_TERM_AND_CONDITIONS)
            .freeField1(UPDATED_FREE_FIELD_1)
            .freeField2(UPDATED_FREE_FIELD_2)
            .freeField3(UPDATED_FREE_FIELD_3)
            .freeField4(UPDATED_FREE_FIELD_4)
            .lastModified(UPDATED_LAST_MODIFIED)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY);
        return productClientAgreement;
    }

    @BeforeEach
    public void initTest() {
        productClientAgreement = createEntity(em);
    }

    @Test
    @Transactional
    void createProductClientAgreement() throws Exception {
        int databaseSizeBeforeCreate = productClientAgreementRepository.findAll().size();
        // Create the ProductClientAgreement
        ProductClientAgreementDTO productClientAgreementDTO = productClientAgreementMapper.toDto(productClientAgreement);
        restProductClientAgreementMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(productClientAgreementDTO))
            )
            .andExpect(status().isCreated());

        // Validate the ProductClientAgreement in the database
        List<ProductClientAgreement> productClientAgreementList = productClientAgreementRepository.findAll();
        assertThat(productClientAgreementList).hasSize(databaseSizeBeforeCreate + 1);
        ProductClientAgreement testProductClientAgreement = productClientAgreementList.get(productClientAgreementList.size() - 1);
        assertThat(testProductClientAgreement.getPrice()).isEqualTo(DEFAULT_PRICE);
        assertThat(testProductClientAgreement.getCreditPeriod()).isEqualTo(DEFAULT_CREDIT_PERIOD);
        assertThat(testProductClientAgreement.getPenalty()).isEqualTo(DEFAULT_PENALTY);
        assertThat(testProductClientAgreement.getPenaltyTerms()).isEqualTo(DEFAULT_PENALTY_TERMS);
        assertThat(testProductClientAgreement.getNotes()).isEqualTo(DEFAULT_NOTES);
        assertThat(testProductClientAgreement.getImage()).isEqualTo(DEFAULT_IMAGE);
        assertThat(testProductClientAgreement.getAgreementPeriod()).isEqualTo(DEFAULT_AGREEMENT_PERIOD);
        assertThat(testProductClientAgreement.getTermAndConditions()).isEqualTo(DEFAULT_TERM_AND_CONDITIONS);
        assertThat(testProductClientAgreement.getFreeField1()).isEqualTo(DEFAULT_FREE_FIELD_1);
        assertThat(testProductClientAgreement.getFreeField2()).isEqualTo(DEFAULT_FREE_FIELD_2);
        assertThat(testProductClientAgreement.getFreeField3()).isEqualTo(DEFAULT_FREE_FIELD_3);
        assertThat(testProductClientAgreement.getFreeField4()).isEqualTo(DEFAULT_FREE_FIELD_4);
        assertThat(testProductClientAgreement.getLastModified()).isEqualTo(DEFAULT_LAST_MODIFIED);
        assertThat(testProductClientAgreement.getLastModifiedBy()).isEqualTo(DEFAULT_LAST_MODIFIED_BY);
    }

    @Test
    @Transactional
    void createProductClientAgreementWithExistingId() throws Exception {
        // Create the ProductClientAgreement with an existing ID
        productClientAgreement.setId(1L);
        ProductClientAgreementDTO productClientAgreementDTO = productClientAgreementMapper.toDto(productClientAgreement);

        int databaseSizeBeforeCreate = productClientAgreementRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restProductClientAgreementMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(productClientAgreementDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProductClientAgreement in the database
        List<ProductClientAgreement> productClientAgreementList = productClientAgreementRepository.findAll();
        assertThat(productClientAgreementList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllProductClientAgreements() throws Exception {
        // Initialize the database
        productClientAgreementRepository.saveAndFlush(productClientAgreement);

        // Get all the productClientAgreementList
        restProductClientAgreementMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(productClientAgreement.getId().intValue())))
            .andExpect(jsonPath("$.[*].price").value(hasItem(DEFAULT_PRICE.intValue())))
            .andExpect(jsonPath("$.[*].creditPeriod").value(hasItem(DEFAULT_CREDIT_PERIOD.doubleValue())))
            .andExpect(jsonPath("$.[*].penalty").value(hasItem(DEFAULT_PENALTY.intValue())))
            .andExpect(jsonPath("$.[*].penaltyTerms").value(hasItem(DEFAULT_PENALTY_TERMS.toString())))
            .andExpect(jsonPath("$.[*].notes").value(hasItem(DEFAULT_NOTES)))
            .andExpect(jsonPath("$.[*].image").value(hasItem(DEFAULT_IMAGE)))
            .andExpect(jsonPath("$.[*].agreementPeriod").value(hasItem(DEFAULT_AGREEMENT_PERIOD.intValue())))
            .andExpect(jsonPath("$.[*].termAndConditions").value(hasItem(DEFAULT_TERM_AND_CONDITIONS)))
            .andExpect(jsonPath("$.[*].freeField1").value(hasItem(DEFAULT_FREE_FIELD_1)))
            .andExpect(jsonPath("$.[*].freeField2").value(hasItem(DEFAULT_FREE_FIELD_2)))
            .andExpect(jsonPath("$.[*].freeField3").value(hasItem(DEFAULT_FREE_FIELD_3)))
            .andExpect(jsonPath("$.[*].freeField4").value(hasItem(DEFAULT_FREE_FIELD_4)))
            .andExpect(jsonPath("$.[*].lastModified").value(hasItem(DEFAULT_LAST_MODIFIED)))
            .andExpect(jsonPath("$.[*].lastModifiedBy").value(hasItem(DEFAULT_LAST_MODIFIED_BY)));
    }

    @Test
    @Transactional
    void getProductClientAgreement() throws Exception {
        // Initialize the database
        productClientAgreementRepository.saveAndFlush(productClientAgreement);

        // Get the productClientAgreement
        restProductClientAgreementMockMvc
            .perform(get(ENTITY_API_URL_ID, productClientAgreement.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(productClientAgreement.getId().intValue()))
            .andExpect(jsonPath("$.price").value(DEFAULT_PRICE.intValue()))
            .andExpect(jsonPath("$.creditPeriod").value(DEFAULT_CREDIT_PERIOD.doubleValue()))
            .andExpect(jsonPath("$.penalty").value(DEFAULT_PENALTY.intValue()))
            .andExpect(jsonPath("$.penaltyTerms").value(DEFAULT_PENALTY_TERMS.toString()))
            .andExpect(jsonPath("$.notes").value(DEFAULT_NOTES))
            .andExpect(jsonPath("$.image").value(DEFAULT_IMAGE))
            .andExpect(jsonPath("$.agreementPeriod").value(DEFAULT_AGREEMENT_PERIOD.intValue()))
            .andExpect(jsonPath("$.termAndConditions").value(DEFAULT_TERM_AND_CONDITIONS))
            .andExpect(jsonPath("$.freeField1").value(DEFAULT_FREE_FIELD_1))
            .andExpect(jsonPath("$.freeField2").value(DEFAULT_FREE_FIELD_2))
            .andExpect(jsonPath("$.freeField3").value(DEFAULT_FREE_FIELD_3))
            .andExpect(jsonPath("$.freeField4").value(DEFAULT_FREE_FIELD_4))
            .andExpect(jsonPath("$.lastModified").value(DEFAULT_LAST_MODIFIED))
            .andExpect(jsonPath("$.lastModifiedBy").value(DEFAULT_LAST_MODIFIED_BY));
    }

    @Test
    @Transactional
    void getProductClientAgreementsByIdFiltering() throws Exception {
        // Initialize the database
        productClientAgreementRepository.saveAndFlush(productClientAgreement);

        Long id = productClientAgreement.getId();

        defaultProductClientAgreementShouldBeFound("id.equals=" + id);
        defaultProductClientAgreementShouldNotBeFound("id.notEquals=" + id);

        defaultProductClientAgreementShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultProductClientAgreementShouldNotBeFound("id.greaterThan=" + id);

        defaultProductClientAgreementShouldBeFound("id.lessThanOrEqual=" + id);
        defaultProductClientAgreementShouldNotBeFound("id.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllProductClientAgreementsByPriceIsEqualToSomething() throws Exception {
        // Initialize the database
        productClientAgreementRepository.saveAndFlush(productClientAgreement);

        // Get all the productClientAgreementList where price equals to DEFAULT_PRICE
        defaultProductClientAgreementShouldBeFound("price.equals=" + DEFAULT_PRICE);

        // Get all the productClientAgreementList where price equals to UPDATED_PRICE
        defaultProductClientAgreementShouldNotBeFound("price.equals=" + UPDATED_PRICE);
    }

    @Test
    @Transactional
    void getAllProductClientAgreementsByPriceIsNotEqualToSomething() throws Exception {
        // Initialize the database
        productClientAgreementRepository.saveAndFlush(productClientAgreement);

        // Get all the productClientAgreementList where price not equals to DEFAULT_PRICE
        defaultProductClientAgreementShouldNotBeFound("price.notEquals=" + DEFAULT_PRICE);

        // Get all the productClientAgreementList where price not equals to UPDATED_PRICE
        defaultProductClientAgreementShouldBeFound("price.notEquals=" + UPDATED_PRICE);
    }

    @Test
    @Transactional
    void getAllProductClientAgreementsByPriceIsInShouldWork() throws Exception {
        // Initialize the database
        productClientAgreementRepository.saveAndFlush(productClientAgreement);

        // Get all the productClientAgreementList where price in DEFAULT_PRICE or UPDATED_PRICE
        defaultProductClientAgreementShouldBeFound("price.in=" + DEFAULT_PRICE + "," + UPDATED_PRICE);

        // Get all the productClientAgreementList where price equals to UPDATED_PRICE
        defaultProductClientAgreementShouldNotBeFound("price.in=" + UPDATED_PRICE);
    }

    @Test
    @Transactional
    void getAllProductClientAgreementsByPriceIsNullOrNotNull() throws Exception {
        // Initialize the database
        productClientAgreementRepository.saveAndFlush(productClientAgreement);

        // Get all the productClientAgreementList where price is not null
        defaultProductClientAgreementShouldBeFound("price.specified=true");

        // Get all the productClientAgreementList where price is null
        defaultProductClientAgreementShouldNotBeFound("price.specified=false");
    }

    @Test
    @Transactional
    void getAllProductClientAgreementsByPriceIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        productClientAgreementRepository.saveAndFlush(productClientAgreement);

        // Get all the productClientAgreementList where price is greater than or equal to DEFAULT_PRICE
        defaultProductClientAgreementShouldBeFound("price.greaterThanOrEqual=" + DEFAULT_PRICE);

        // Get all the productClientAgreementList where price is greater than or equal to UPDATED_PRICE
        defaultProductClientAgreementShouldNotBeFound("price.greaterThanOrEqual=" + UPDATED_PRICE);
    }

    @Test
    @Transactional
    void getAllProductClientAgreementsByPriceIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        productClientAgreementRepository.saveAndFlush(productClientAgreement);

        // Get all the productClientAgreementList where price is less than or equal to DEFAULT_PRICE
        defaultProductClientAgreementShouldBeFound("price.lessThanOrEqual=" + DEFAULT_PRICE);

        // Get all the productClientAgreementList where price is less than or equal to SMALLER_PRICE
        defaultProductClientAgreementShouldNotBeFound("price.lessThanOrEqual=" + SMALLER_PRICE);
    }

    @Test
    @Transactional
    void getAllProductClientAgreementsByPriceIsLessThanSomething() throws Exception {
        // Initialize the database
        productClientAgreementRepository.saveAndFlush(productClientAgreement);

        // Get all the productClientAgreementList where price is less than DEFAULT_PRICE
        defaultProductClientAgreementShouldNotBeFound("price.lessThan=" + DEFAULT_PRICE);

        // Get all the productClientAgreementList where price is less than UPDATED_PRICE
        defaultProductClientAgreementShouldBeFound("price.lessThan=" + UPDATED_PRICE);
    }

    @Test
    @Transactional
    void getAllProductClientAgreementsByPriceIsGreaterThanSomething() throws Exception {
        // Initialize the database
        productClientAgreementRepository.saveAndFlush(productClientAgreement);

        // Get all the productClientAgreementList where price is greater than DEFAULT_PRICE
        defaultProductClientAgreementShouldNotBeFound("price.greaterThan=" + DEFAULT_PRICE);

        // Get all the productClientAgreementList where price is greater than SMALLER_PRICE
        defaultProductClientAgreementShouldBeFound("price.greaterThan=" + SMALLER_PRICE);
    }

    @Test
    @Transactional
    void getAllProductClientAgreementsByCreditPeriodIsEqualToSomething() throws Exception {
        // Initialize the database
        productClientAgreementRepository.saveAndFlush(productClientAgreement);

        // Get all the productClientAgreementList where creditPeriod equals to DEFAULT_CREDIT_PERIOD
        defaultProductClientAgreementShouldBeFound("creditPeriod.equals=" + DEFAULT_CREDIT_PERIOD);

        // Get all the productClientAgreementList where creditPeriod equals to UPDATED_CREDIT_PERIOD
        defaultProductClientAgreementShouldNotBeFound("creditPeriod.equals=" + UPDATED_CREDIT_PERIOD);
    }

    @Test
    @Transactional
    void getAllProductClientAgreementsByCreditPeriodIsNotEqualToSomething() throws Exception {
        // Initialize the database
        productClientAgreementRepository.saveAndFlush(productClientAgreement);

        // Get all the productClientAgreementList where creditPeriod not equals to DEFAULT_CREDIT_PERIOD
        defaultProductClientAgreementShouldNotBeFound("creditPeriod.notEquals=" + DEFAULT_CREDIT_PERIOD);

        // Get all the productClientAgreementList where creditPeriod not equals to UPDATED_CREDIT_PERIOD
        defaultProductClientAgreementShouldBeFound("creditPeriod.notEquals=" + UPDATED_CREDIT_PERIOD);
    }

    @Test
    @Transactional
    void getAllProductClientAgreementsByCreditPeriodIsInShouldWork() throws Exception {
        // Initialize the database
        productClientAgreementRepository.saveAndFlush(productClientAgreement);

        // Get all the productClientAgreementList where creditPeriod in DEFAULT_CREDIT_PERIOD or UPDATED_CREDIT_PERIOD
        defaultProductClientAgreementShouldBeFound("creditPeriod.in=" + DEFAULT_CREDIT_PERIOD + "," + UPDATED_CREDIT_PERIOD);

        // Get all the productClientAgreementList where creditPeriod equals to UPDATED_CREDIT_PERIOD
        defaultProductClientAgreementShouldNotBeFound("creditPeriod.in=" + UPDATED_CREDIT_PERIOD);
    }

    @Test
    @Transactional
    void getAllProductClientAgreementsByCreditPeriodIsNullOrNotNull() throws Exception {
        // Initialize the database
        productClientAgreementRepository.saveAndFlush(productClientAgreement);

        // Get all the productClientAgreementList where creditPeriod is not null
        defaultProductClientAgreementShouldBeFound("creditPeriod.specified=true");

        // Get all the productClientAgreementList where creditPeriod is null
        defaultProductClientAgreementShouldNotBeFound("creditPeriod.specified=false");
    }

    @Test
    @Transactional
    void getAllProductClientAgreementsByCreditPeriodIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        productClientAgreementRepository.saveAndFlush(productClientAgreement);

        // Get all the productClientAgreementList where creditPeriod is greater than or equal to DEFAULT_CREDIT_PERIOD
        defaultProductClientAgreementShouldBeFound("creditPeriod.greaterThanOrEqual=" + DEFAULT_CREDIT_PERIOD);

        // Get all the productClientAgreementList where creditPeriod is greater than or equal to UPDATED_CREDIT_PERIOD
        defaultProductClientAgreementShouldNotBeFound("creditPeriod.greaterThanOrEqual=" + UPDATED_CREDIT_PERIOD);
    }

    @Test
    @Transactional
    void getAllProductClientAgreementsByCreditPeriodIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        productClientAgreementRepository.saveAndFlush(productClientAgreement);

        // Get all the productClientAgreementList where creditPeriod is less than or equal to DEFAULT_CREDIT_PERIOD
        defaultProductClientAgreementShouldBeFound("creditPeriod.lessThanOrEqual=" + DEFAULT_CREDIT_PERIOD);

        // Get all the productClientAgreementList where creditPeriod is less than or equal to SMALLER_CREDIT_PERIOD
        defaultProductClientAgreementShouldNotBeFound("creditPeriod.lessThanOrEqual=" + SMALLER_CREDIT_PERIOD);
    }

    @Test
    @Transactional
    void getAllProductClientAgreementsByCreditPeriodIsLessThanSomething() throws Exception {
        // Initialize the database
        productClientAgreementRepository.saveAndFlush(productClientAgreement);

        // Get all the productClientAgreementList where creditPeriod is less than DEFAULT_CREDIT_PERIOD
        defaultProductClientAgreementShouldNotBeFound("creditPeriod.lessThan=" + DEFAULT_CREDIT_PERIOD);

        // Get all the productClientAgreementList where creditPeriod is less than UPDATED_CREDIT_PERIOD
        defaultProductClientAgreementShouldBeFound("creditPeriod.lessThan=" + UPDATED_CREDIT_PERIOD);
    }

    @Test
    @Transactional
    void getAllProductClientAgreementsByCreditPeriodIsGreaterThanSomething() throws Exception {
        // Initialize the database
        productClientAgreementRepository.saveAndFlush(productClientAgreement);

        // Get all the productClientAgreementList where creditPeriod is greater than DEFAULT_CREDIT_PERIOD
        defaultProductClientAgreementShouldNotBeFound("creditPeriod.greaterThan=" + DEFAULT_CREDIT_PERIOD);

        // Get all the productClientAgreementList where creditPeriod is greater than SMALLER_CREDIT_PERIOD
        defaultProductClientAgreementShouldBeFound("creditPeriod.greaterThan=" + SMALLER_CREDIT_PERIOD);
    }

    @Test
    @Transactional
    void getAllProductClientAgreementsByPenaltyIsEqualToSomething() throws Exception {
        // Initialize the database
        productClientAgreementRepository.saveAndFlush(productClientAgreement);

        // Get all the productClientAgreementList where penalty equals to DEFAULT_PENALTY
        defaultProductClientAgreementShouldBeFound("penalty.equals=" + DEFAULT_PENALTY);

        // Get all the productClientAgreementList where penalty equals to UPDATED_PENALTY
        defaultProductClientAgreementShouldNotBeFound("penalty.equals=" + UPDATED_PENALTY);
    }

    @Test
    @Transactional
    void getAllProductClientAgreementsByPenaltyIsNotEqualToSomething() throws Exception {
        // Initialize the database
        productClientAgreementRepository.saveAndFlush(productClientAgreement);

        // Get all the productClientAgreementList where penalty not equals to DEFAULT_PENALTY
        defaultProductClientAgreementShouldNotBeFound("penalty.notEquals=" + DEFAULT_PENALTY);

        // Get all the productClientAgreementList where penalty not equals to UPDATED_PENALTY
        defaultProductClientAgreementShouldBeFound("penalty.notEquals=" + UPDATED_PENALTY);
    }

    @Test
    @Transactional
    void getAllProductClientAgreementsByPenaltyIsInShouldWork() throws Exception {
        // Initialize the database
        productClientAgreementRepository.saveAndFlush(productClientAgreement);

        // Get all the productClientAgreementList where penalty in DEFAULT_PENALTY or UPDATED_PENALTY
        defaultProductClientAgreementShouldBeFound("penalty.in=" + DEFAULT_PENALTY + "," + UPDATED_PENALTY);

        // Get all the productClientAgreementList where penalty equals to UPDATED_PENALTY
        defaultProductClientAgreementShouldNotBeFound("penalty.in=" + UPDATED_PENALTY);
    }

    @Test
    @Transactional
    void getAllProductClientAgreementsByPenaltyIsNullOrNotNull() throws Exception {
        // Initialize the database
        productClientAgreementRepository.saveAndFlush(productClientAgreement);

        // Get all the productClientAgreementList where penalty is not null
        defaultProductClientAgreementShouldBeFound("penalty.specified=true");

        // Get all the productClientAgreementList where penalty is null
        defaultProductClientAgreementShouldNotBeFound("penalty.specified=false");
    }

    @Test
    @Transactional
    void getAllProductClientAgreementsByPenaltyIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        productClientAgreementRepository.saveAndFlush(productClientAgreement);

        // Get all the productClientAgreementList where penalty is greater than or equal to DEFAULT_PENALTY
        defaultProductClientAgreementShouldBeFound("penalty.greaterThanOrEqual=" + DEFAULT_PENALTY);

        // Get all the productClientAgreementList where penalty is greater than or equal to UPDATED_PENALTY
        defaultProductClientAgreementShouldNotBeFound("penalty.greaterThanOrEqual=" + UPDATED_PENALTY);
    }

    @Test
    @Transactional
    void getAllProductClientAgreementsByPenaltyIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        productClientAgreementRepository.saveAndFlush(productClientAgreement);

        // Get all the productClientAgreementList where penalty is less than or equal to DEFAULT_PENALTY
        defaultProductClientAgreementShouldBeFound("penalty.lessThanOrEqual=" + DEFAULT_PENALTY);

        // Get all the productClientAgreementList where penalty is less than or equal to SMALLER_PENALTY
        defaultProductClientAgreementShouldNotBeFound("penalty.lessThanOrEqual=" + SMALLER_PENALTY);
    }

    @Test
    @Transactional
    void getAllProductClientAgreementsByPenaltyIsLessThanSomething() throws Exception {
        // Initialize the database
        productClientAgreementRepository.saveAndFlush(productClientAgreement);

        // Get all the productClientAgreementList where penalty is less than DEFAULT_PENALTY
        defaultProductClientAgreementShouldNotBeFound("penalty.lessThan=" + DEFAULT_PENALTY);

        // Get all the productClientAgreementList where penalty is less than UPDATED_PENALTY
        defaultProductClientAgreementShouldBeFound("penalty.lessThan=" + UPDATED_PENALTY);
    }

    @Test
    @Transactional
    void getAllProductClientAgreementsByPenaltyIsGreaterThanSomething() throws Exception {
        // Initialize the database
        productClientAgreementRepository.saveAndFlush(productClientAgreement);

        // Get all the productClientAgreementList where penalty is greater than DEFAULT_PENALTY
        defaultProductClientAgreementShouldNotBeFound("penalty.greaterThan=" + DEFAULT_PENALTY);

        // Get all the productClientAgreementList where penalty is greater than SMALLER_PENALTY
        defaultProductClientAgreementShouldBeFound("penalty.greaterThan=" + SMALLER_PENALTY);
    }

    @Test
    @Transactional
    void getAllProductClientAgreementsByPenaltyTermsIsEqualToSomething() throws Exception {
        // Initialize the database
        productClientAgreementRepository.saveAndFlush(productClientAgreement);

        // Get all the productClientAgreementList where penaltyTerms equals to DEFAULT_PENALTY_TERMS
        defaultProductClientAgreementShouldBeFound("penaltyTerms.equals=" + DEFAULT_PENALTY_TERMS);

        // Get all the productClientAgreementList where penaltyTerms equals to UPDATED_PENALTY_TERMS
        defaultProductClientAgreementShouldNotBeFound("penaltyTerms.equals=" + UPDATED_PENALTY_TERMS);
    }

    @Test
    @Transactional
    void getAllProductClientAgreementsByPenaltyTermsIsNotEqualToSomething() throws Exception {
        // Initialize the database
        productClientAgreementRepository.saveAndFlush(productClientAgreement);

        // Get all the productClientAgreementList where penaltyTerms not equals to DEFAULT_PENALTY_TERMS
        defaultProductClientAgreementShouldNotBeFound("penaltyTerms.notEquals=" + DEFAULT_PENALTY_TERMS);

        // Get all the productClientAgreementList where penaltyTerms not equals to UPDATED_PENALTY_TERMS
        defaultProductClientAgreementShouldBeFound("penaltyTerms.notEquals=" + UPDATED_PENALTY_TERMS);
    }

    @Test
    @Transactional
    void getAllProductClientAgreementsByPenaltyTermsIsInShouldWork() throws Exception {
        // Initialize the database
        productClientAgreementRepository.saveAndFlush(productClientAgreement);

        // Get all the productClientAgreementList where penaltyTerms in DEFAULT_PENALTY_TERMS or UPDATED_PENALTY_TERMS
        defaultProductClientAgreementShouldBeFound("penaltyTerms.in=" + DEFAULT_PENALTY_TERMS + "," + UPDATED_PENALTY_TERMS);

        // Get all the productClientAgreementList where penaltyTerms equals to UPDATED_PENALTY_TERMS
        defaultProductClientAgreementShouldNotBeFound("penaltyTerms.in=" + UPDATED_PENALTY_TERMS);
    }

    @Test
    @Transactional
    void getAllProductClientAgreementsByPenaltyTermsIsNullOrNotNull() throws Exception {
        // Initialize the database
        productClientAgreementRepository.saveAndFlush(productClientAgreement);

        // Get all the productClientAgreementList where penaltyTerms is not null
        defaultProductClientAgreementShouldBeFound("penaltyTerms.specified=true");

        // Get all the productClientAgreementList where penaltyTerms is null
        defaultProductClientAgreementShouldNotBeFound("penaltyTerms.specified=false");
    }

    @Test
    @Transactional
    void getAllProductClientAgreementsByNotesIsEqualToSomething() throws Exception {
        // Initialize the database
        productClientAgreementRepository.saveAndFlush(productClientAgreement);

        // Get all the productClientAgreementList where notes equals to DEFAULT_NOTES
        defaultProductClientAgreementShouldBeFound("notes.equals=" + DEFAULT_NOTES);

        // Get all the productClientAgreementList where notes equals to UPDATED_NOTES
        defaultProductClientAgreementShouldNotBeFound("notes.equals=" + UPDATED_NOTES);
    }

    @Test
    @Transactional
    void getAllProductClientAgreementsByNotesIsNotEqualToSomething() throws Exception {
        // Initialize the database
        productClientAgreementRepository.saveAndFlush(productClientAgreement);

        // Get all the productClientAgreementList where notes not equals to DEFAULT_NOTES
        defaultProductClientAgreementShouldNotBeFound("notes.notEquals=" + DEFAULT_NOTES);

        // Get all the productClientAgreementList where notes not equals to UPDATED_NOTES
        defaultProductClientAgreementShouldBeFound("notes.notEquals=" + UPDATED_NOTES);
    }

    @Test
    @Transactional
    void getAllProductClientAgreementsByNotesIsInShouldWork() throws Exception {
        // Initialize the database
        productClientAgreementRepository.saveAndFlush(productClientAgreement);

        // Get all the productClientAgreementList where notes in DEFAULT_NOTES or UPDATED_NOTES
        defaultProductClientAgreementShouldBeFound("notes.in=" + DEFAULT_NOTES + "," + UPDATED_NOTES);

        // Get all the productClientAgreementList where notes equals to UPDATED_NOTES
        defaultProductClientAgreementShouldNotBeFound("notes.in=" + UPDATED_NOTES);
    }

    @Test
    @Transactional
    void getAllProductClientAgreementsByNotesIsNullOrNotNull() throws Exception {
        // Initialize the database
        productClientAgreementRepository.saveAndFlush(productClientAgreement);

        // Get all the productClientAgreementList where notes is not null
        defaultProductClientAgreementShouldBeFound("notes.specified=true");

        // Get all the productClientAgreementList where notes is null
        defaultProductClientAgreementShouldNotBeFound("notes.specified=false");
    }

    @Test
    @Transactional
    void getAllProductClientAgreementsByNotesContainsSomething() throws Exception {
        // Initialize the database
        productClientAgreementRepository.saveAndFlush(productClientAgreement);

        // Get all the productClientAgreementList where notes contains DEFAULT_NOTES
        defaultProductClientAgreementShouldBeFound("notes.contains=" + DEFAULT_NOTES);

        // Get all the productClientAgreementList where notes contains UPDATED_NOTES
        defaultProductClientAgreementShouldNotBeFound("notes.contains=" + UPDATED_NOTES);
    }

    @Test
    @Transactional
    void getAllProductClientAgreementsByNotesNotContainsSomething() throws Exception {
        // Initialize the database
        productClientAgreementRepository.saveAndFlush(productClientAgreement);

        // Get all the productClientAgreementList where notes does not contain DEFAULT_NOTES
        defaultProductClientAgreementShouldNotBeFound("notes.doesNotContain=" + DEFAULT_NOTES);

        // Get all the productClientAgreementList where notes does not contain UPDATED_NOTES
        defaultProductClientAgreementShouldBeFound("notes.doesNotContain=" + UPDATED_NOTES);
    }

    @Test
    @Transactional
    void getAllProductClientAgreementsByImageIsEqualToSomething() throws Exception {
        // Initialize the database
        productClientAgreementRepository.saveAndFlush(productClientAgreement);

        // Get all the productClientAgreementList where image equals to DEFAULT_IMAGE
        defaultProductClientAgreementShouldBeFound("image.equals=" + DEFAULT_IMAGE);

        // Get all the productClientAgreementList where image equals to UPDATED_IMAGE
        defaultProductClientAgreementShouldNotBeFound("image.equals=" + UPDATED_IMAGE);
    }

    @Test
    @Transactional
    void getAllProductClientAgreementsByImageIsNotEqualToSomething() throws Exception {
        // Initialize the database
        productClientAgreementRepository.saveAndFlush(productClientAgreement);

        // Get all the productClientAgreementList where image not equals to DEFAULT_IMAGE
        defaultProductClientAgreementShouldNotBeFound("image.notEquals=" + DEFAULT_IMAGE);

        // Get all the productClientAgreementList where image not equals to UPDATED_IMAGE
        defaultProductClientAgreementShouldBeFound("image.notEquals=" + UPDATED_IMAGE);
    }

    @Test
    @Transactional
    void getAllProductClientAgreementsByImageIsInShouldWork() throws Exception {
        // Initialize the database
        productClientAgreementRepository.saveAndFlush(productClientAgreement);

        // Get all the productClientAgreementList where image in DEFAULT_IMAGE or UPDATED_IMAGE
        defaultProductClientAgreementShouldBeFound("image.in=" + DEFAULT_IMAGE + "," + UPDATED_IMAGE);

        // Get all the productClientAgreementList where image equals to UPDATED_IMAGE
        defaultProductClientAgreementShouldNotBeFound("image.in=" + UPDATED_IMAGE);
    }

    @Test
    @Transactional
    void getAllProductClientAgreementsByImageIsNullOrNotNull() throws Exception {
        // Initialize the database
        productClientAgreementRepository.saveAndFlush(productClientAgreement);

        // Get all the productClientAgreementList where image is not null
        defaultProductClientAgreementShouldBeFound("image.specified=true");

        // Get all the productClientAgreementList where image is null
        defaultProductClientAgreementShouldNotBeFound("image.specified=false");
    }

    @Test
    @Transactional
    void getAllProductClientAgreementsByImageContainsSomething() throws Exception {
        // Initialize the database
        productClientAgreementRepository.saveAndFlush(productClientAgreement);

        // Get all the productClientAgreementList where image contains DEFAULT_IMAGE
        defaultProductClientAgreementShouldBeFound("image.contains=" + DEFAULT_IMAGE);

        // Get all the productClientAgreementList where image contains UPDATED_IMAGE
        defaultProductClientAgreementShouldNotBeFound("image.contains=" + UPDATED_IMAGE);
    }

    @Test
    @Transactional
    void getAllProductClientAgreementsByImageNotContainsSomething() throws Exception {
        // Initialize the database
        productClientAgreementRepository.saveAndFlush(productClientAgreement);

        // Get all the productClientAgreementList where image does not contain DEFAULT_IMAGE
        defaultProductClientAgreementShouldNotBeFound("image.doesNotContain=" + DEFAULT_IMAGE);

        // Get all the productClientAgreementList where image does not contain UPDATED_IMAGE
        defaultProductClientAgreementShouldBeFound("image.doesNotContain=" + UPDATED_IMAGE);
    }

    @Test
    @Transactional
    void getAllProductClientAgreementsByAgreementPeriodIsEqualToSomething() throws Exception {
        // Initialize the database
        productClientAgreementRepository.saveAndFlush(productClientAgreement);

        // Get all the productClientAgreementList where agreementPeriod equals to DEFAULT_AGREEMENT_PERIOD
        defaultProductClientAgreementShouldBeFound("agreementPeriod.equals=" + DEFAULT_AGREEMENT_PERIOD);

        // Get all the productClientAgreementList where agreementPeriod equals to UPDATED_AGREEMENT_PERIOD
        defaultProductClientAgreementShouldNotBeFound("agreementPeriod.equals=" + UPDATED_AGREEMENT_PERIOD);
    }

    @Test
    @Transactional
    void getAllProductClientAgreementsByAgreementPeriodIsNotEqualToSomething() throws Exception {
        // Initialize the database
        productClientAgreementRepository.saveAndFlush(productClientAgreement);

        // Get all the productClientAgreementList where agreementPeriod not equals to DEFAULT_AGREEMENT_PERIOD
        defaultProductClientAgreementShouldNotBeFound("agreementPeriod.notEquals=" + DEFAULT_AGREEMENT_PERIOD);

        // Get all the productClientAgreementList where agreementPeriod not equals to UPDATED_AGREEMENT_PERIOD
        defaultProductClientAgreementShouldBeFound("agreementPeriod.notEquals=" + UPDATED_AGREEMENT_PERIOD);
    }

    @Test
    @Transactional
    void getAllProductClientAgreementsByAgreementPeriodIsInShouldWork() throws Exception {
        // Initialize the database
        productClientAgreementRepository.saveAndFlush(productClientAgreement);

        // Get all the productClientAgreementList where agreementPeriod in DEFAULT_AGREEMENT_PERIOD or UPDATED_AGREEMENT_PERIOD
        defaultProductClientAgreementShouldBeFound("agreementPeriod.in=" + DEFAULT_AGREEMENT_PERIOD + "," + UPDATED_AGREEMENT_PERIOD);

        // Get all the productClientAgreementList where agreementPeriod equals to UPDATED_AGREEMENT_PERIOD
        defaultProductClientAgreementShouldNotBeFound("agreementPeriod.in=" + UPDATED_AGREEMENT_PERIOD);
    }

    @Test
    @Transactional
    void getAllProductClientAgreementsByAgreementPeriodIsNullOrNotNull() throws Exception {
        // Initialize the database
        productClientAgreementRepository.saveAndFlush(productClientAgreement);

        // Get all the productClientAgreementList where agreementPeriod is not null
        defaultProductClientAgreementShouldBeFound("agreementPeriod.specified=true");

        // Get all the productClientAgreementList where agreementPeriod is null
        defaultProductClientAgreementShouldNotBeFound("agreementPeriod.specified=false");
    }

    @Test
    @Transactional
    void getAllProductClientAgreementsByAgreementPeriodIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        productClientAgreementRepository.saveAndFlush(productClientAgreement);

        // Get all the productClientAgreementList where agreementPeriod is greater than or equal to DEFAULT_AGREEMENT_PERIOD
        defaultProductClientAgreementShouldBeFound("agreementPeriod.greaterThanOrEqual=" + DEFAULT_AGREEMENT_PERIOD);

        // Get all the productClientAgreementList where agreementPeriod is greater than or equal to UPDATED_AGREEMENT_PERIOD
        defaultProductClientAgreementShouldNotBeFound("agreementPeriod.greaterThanOrEqual=" + UPDATED_AGREEMENT_PERIOD);
    }

    @Test
    @Transactional
    void getAllProductClientAgreementsByAgreementPeriodIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        productClientAgreementRepository.saveAndFlush(productClientAgreement);

        // Get all the productClientAgreementList where agreementPeriod is less than or equal to DEFAULT_AGREEMENT_PERIOD
        defaultProductClientAgreementShouldBeFound("agreementPeriod.lessThanOrEqual=" + DEFAULT_AGREEMENT_PERIOD);

        // Get all the productClientAgreementList where agreementPeriod is less than or equal to SMALLER_AGREEMENT_PERIOD
        defaultProductClientAgreementShouldNotBeFound("agreementPeriod.lessThanOrEqual=" + SMALLER_AGREEMENT_PERIOD);
    }

    @Test
    @Transactional
    void getAllProductClientAgreementsByAgreementPeriodIsLessThanSomething() throws Exception {
        // Initialize the database
        productClientAgreementRepository.saveAndFlush(productClientAgreement);

        // Get all the productClientAgreementList where agreementPeriod is less than DEFAULT_AGREEMENT_PERIOD
        defaultProductClientAgreementShouldNotBeFound("agreementPeriod.lessThan=" + DEFAULT_AGREEMENT_PERIOD);

        // Get all the productClientAgreementList where agreementPeriod is less than UPDATED_AGREEMENT_PERIOD
        defaultProductClientAgreementShouldBeFound("agreementPeriod.lessThan=" + UPDATED_AGREEMENT_PERIOD);
    }

    @Test
    @Transactional
    void getAllProductClientAgreementsByAgreementPeriodIsGreaterThanSomething() throws Exception {
        // Initialize the database
        productClientAgreementRepository.saveAndFlush(productClientAgreement);

        // Get all the productClientAgreementList where agreementPeriod is greater than DEFAULT_AGREEMENT_PERIOD
        defaultProductClientAgreementShouldNotBeFound("agreementPeriod.greaterThan=" + DEFAULT_AGREEMENT_PERIOD);

        // Get all the productClientAgreementList where agreementPeriod is greater than SMALLER_AGREEMENT_PERIOD
        defaultProductClientAgreementShouldBeFound("agreementPeriod.greaterThan=" + SMALLER_AGREEMENT_PERIOD);
    }

    @Test
    @Transactional
    void getAllProductClientAgreementsByTermAndConditionsIsEqualToSomething() throws Exception {
        // Initialize the database
        productClientAgreementRepository.saveAndFlush(productClientAgreement);

        // Get all the productClientAgreementList where termAndConditions equals to DEFAULT_TERM_AND_CONDITIONS
        defaultProductClientAgreementShouldBeFound("termAndConditions.equals=" + DEFAULT_TERM_AND_CONDITIONS);

        // Get all the productClientAgreementList where termAndConditions equals to UPDATED_TERM_AND_CONDITIONS
        defaultProductClientAgreementShouldNotBeFound("termAndConditions.equals=" + UPDATED_TERM_AND_CONDITIONS);
    }

    @Test
    @Transactional
    void getAllProductClientAgreementsByTermAndConditionsIsNotEqualToSomething() throws Exception {
        // Initialize the database
        productClientAgreementRepository.saveAndFlush(productClientAgreement);

        // Get all the productClientAgreementList where termAndConditions not equals to DEFAULT_TERM_AND_CONDITIONS
        defaultProductClientAgreementShouldNotBeFound("termAndConditions.notEquals=" + DEFAULT_TERM_AND_CONDITIONS);

        // Get all the productClientAgreementList where termAndConditions not equals to UPDATED_TERM_AND_CONDITIONS
        defaultProductClientAgreementShouldBeFound("termAndConditions.notEquals=" + UPDATED_TERM_AND_CONDITIONS);
    }

    @Test
    @Transactional
    void getAllProductClientAgreementsByTermAndConditionsIsInShouldWork() throws Exception {
        // Initialize the database
        productClientAgreementRepository.saveAndFlush(productClientAgreement);

        // Get all the productClientAgreementList where termAndConditions in DEFAULT_TERM_AND_CONDITIONS or UPDATED_TERM_AND_CONDITIONS
        defaultProductClientAgreementShouldBeFound(
            "termAndConditions.in=" + DEFAULT_TERM_AND_CONDITIONS + "," + UPDATED_TERM_AND_CONDITIONS
        );

        // Get all the productClientAgreementList where termAndConditions equals to UPDATED_TERM_AND_CONDITIONS
        defaultProductClientAgreementShouldNotBeFound("termAndConditions.in=" + UPDATED_TERM_AND_CONDITIONS);
    }

    @Test
    @Transactional
    void getAllProductClientAgreementsByTermAndConditionsIsNullOrNotNull() throws Exception {
        // Initialize the database
        productClientAgreementRepository.saveAndFlush(productClientAgreement);

        // Get all the productClientAgreementList where termAndConditions is not null
        defaultProductClientAgreementShouldBeFound("termAndConditions.specified=true");

        // Get all the productClientAgreementList where termAndConditions is null
        defaultProductClientAgreementShouldNotBeFound("termAndConditions.specified=false");
    }

    @Test
    @Transactional
    void getAllProductClientAgreementsByTermAndConditionsContainsSomething() throws Exception {
        // Initialize the database
        productClientAgreementRepository.saveAndFlush(productClientAgreement);

        // Get all the productClientAgreementList where termAndConditions contains DEFAULT_TERM_AND_CONDITIONS
        defaultProductClientAgreementShouldBeFound("termAndConditions.contains=" + DEFAULT_TERM_AND_CONDITIONS);

        // Get all the productClientAgreementList where termAndConditions contains UPDATED_TERM_AND_CONDITIONS
        defaultProductClientAgreementShouldNotBeFound("termAndConditions.contains=" + UPDATED_TERM_AND_CONDITIONS);
    }

    @Test
    @Transactional
    void getAllProductClientAgreementsByTermAndConditionsNotContainsSomething() throws Exception {
        // Initialize the database
        productClientAgreementRepository.saveAndFlush(productClientAgreement);

        // Get all the productClientAgreementList where termAndConditions does not contain DEFAULT_TERM_AND_CONDITIONS
        defaultProductClientAgreementShouldNotBeFound("termAndConditions.doesNotContain=" + DEFAULT_TERM_AND_CONDITIONS);

        // Get all the productClientAgreementList where termAndConditions does not contain UPDATED_TERM_AND_CONDITIONS
        defaultProductClientAgreementShouldBeFound("termAndConditions.doesNotContain=" + UPDATED_TERM_AND_CONDITIONS);
    }

    @Test
    @Transactional
    void getAllProductClientAgreementsByFreeField1IsEqualToSomething() throws Exception {
        // Initialize the database
        productClientAgreementRepository.saveAndFlush(productClientAgreement);

        // Get all the productClientAgreementList where freeField1 equals to DEFAULT_FREE_FIELD_1
        defaultProductClientAgreementShouldBeFound("freeField1.equals=" + DEFAULT_FREE_FIELD_1);

        // Get all the productClientAgreementList where freeField1 equals to UPDATED_FREE_FIELD_1
        defaultProductClientAgreementShouldNotBeFound("freeField1.equals=" + UPDATED_FREE_FIELD_1);
    }

    @Test
    @Transactional
    void getAllProductClientAgreementsByFreeField1IsNotEqualToSomething() throws Exception {
        // Initialize the database
        productClientAgreementRepository.saveAndFlush(productClientAgreement);

        // Get all the productClientAgreementList where freeField1 not equals to DEFAULT_FREE_FIELD_1
        defaultProductClientAgreementShouldNotBeFound("freeField1.notEquals=" + DEFAULT_FREE_FIELD_1);

        // Get all the productClientAgreementList where freeField1 not equals to UPDATED_FREE_FIELD_1
        defaultProductClientAgreementShouldBeFound("freeField1.notEquals=" + UPDATED_FREE_FIELD_1);
    }

    @Test
    @Transactional
    void getAllProductClientAgreementsByFreeField1IsInShouldWork() throws Exception {
        // Initialize the database
        productClientAgreementRepository.saveAndFlush(productClientAgreement);

        // Get all the productClientAgreementList where freeField1 in DEFAULT_FREE_FIELD_1 or UPDATED_FREE_FIELD_1
        defaultProductClientAgreementShouldBeFound("freeField1.in=" + DEFAULT_FREE_FIELD_1 + "," + UPDATED_FREE_FIELD_1);

        // Get all the productClientAgreementList where freeField1 equals to UPDATED_FREE_FIELD_1
        defaultProductClientAgreementShouldNotBeFound("freeField1.in=" + UPDATED_FREE_FIELD_1);
    }

    @Test
    @Transactional
    void getAllProductClientAgreementsByFreeField1IsNullOrNotNull() throws Exception {
        // Initialize the database
        productClientAgreementRepository.saveAndFlush(productClientAgreement);

        // Get all the productClientAgreementList where freeField1 is not null
        defaultProductClientAgreementShouldBeFound("freeField1.specified=true");

        // Get all the productClientAgreementList where freeField1 is null
        defaultProductClientAgreementShouldNotBeFound("freeField1.specified=false");
    }

    @Test
    @Transactional
    void getAllProductClientAgreementsByFreeField1ContainsSomething() throws Exception {
        // Initialize the database
        productClientAgreementRepository.saveAndFlush(productClientAgreement);

        // Get all the productClientAgreementList where freeField1 contains DEFAULT_FREE_FIELD_1
        defaultProductClientAgreementShouldBeFound("freeField1.contains=" + DEFAULT_FREE_FIELD_1);

        // Get all the productClientAgreementList where freeField1 contains UPDATED_FREE_FIELD_1
        defaultProductClientAgreementShouldNotBeFound("freeField1.contains=" + UPDATED_FREE_FIELD_1);
    }

    @Test
    @Transactional
    void getAllProductClientAgreementsByFreeField1NotContainsSomething() throws Exception {
        // Initialize the database
        productClientAgreementRepository.saveAndFlush(productClientAgreement);

        // Get all the productClientAgreementList where freeField1 does not contain DEFAULT_FREE_FIELD_1
        defaultProductClientAgreementShouldNotBeFound("freeField1.doesNotContain=" + DEFAULT_FREE_FIELD_1);

        // Get all the productClientAgreementList where freeField1 does not contain UPDATED_FREE_FIELD_1
        defaultProductClientAgreementShouldBeFound("freeField1.doesNotContain=" + UPDATED_FREE_FIELD_1);
    }

    @Test
    @Transactional
    void getAllProductClientAgreementsByFreeField2IsEqualToSomething() throws Exception {
        // Initialize the database
        productClientAgreementRepository.saveAndFlush(productClientAgreement);

        // Get all the productClientAgreementList where freeField2 equals to DEFAULT_FREE_FIELD_2
        defaultProductClientAgreementShouldBeFound("freeField2.equals=" + DEFAULT_FREE_FIELD_2);

        // Get all the productClientAgreementList where freeField2 equals to UPDATED_FREE_FIELD_2
        defaultProductClientAgreementShouldNotBeFound("freeField2.equals=" + UPDATED_FREE_FIELD_2);
    }

    @Test
    @Transactional
    void getAllProductClientAgreementsByFreeField2IsNotEqualToSomething() throws Exception {
        // Initialize the database
        productClientAgreementRepository.saveAndFlush(productClientAgreement);

        // Get all the productClientAgreementList where freeField2 not equals to DEFAULT_FREE_FIELD_2
        defaultProductClientAgreementShouldNotBeFound("freeField2.notEquals=" + DEFAULT_FREE_FIELD_2);

        // Get all the productClientAgreementList where freeField2 not equals to UPDATED_FREE_FIELD_2
        defaultProductClientAgreementShouldBeFound("freeField2.notEquals=" + UPDATED_FREE_FIELD_2);
    }

    @Test
    @Transactional
    void getAllProductClientAgreementsByFreeField2IsInShouldWork() throws Exception {
        // Initialize the database
        productClientAgreementRepository.saveAndFlush(productClientAgreement);

        // Get all the productClientAgreementList where freeField2 in DEFAULT_FREE_FIELD_2 or UPDATED_FREE_FIELD_2
        defaultProductClientAgreementShouldBeFound("freeField2.in=" + DEFAULT_FREE_FIELD_2 + "," + UPDATED_FREE_FIELD_2);

        // Get all the productClientAgreementList where freeField2 equals to UPDATED_FREE_FIELD_2
        defaultProductClientAgreementShouldNotBeFound("freeField2.in=" + UPDATED_FREE_FIELD_2);
    }

    @Test
    @Transactional
    void getAllProductClientAgreementsByFreeField2IsNullOrNotNull() throws Exception {
        // Initialize the database
        productClientAgreementRepository.saveAndFlush(productClientAgreement);

        // Get all the productClientAgreementList where freeField2 is not null
        defaultProductClientAgreementShouldBeFound("freeField2.specified=true");

        // Get all the productClientAgreementList where freeField2 is null
        defaultProductClientAgreementShouldNotBeFound("freeField2.specified=false");
    }

    @Test
    @Transactional
    void getAllProductClientAgreementsByFreeField2ContainsSomething() throws Exception {
        // Initialize the database
        productClientAgreementRepository.saveAndFlush(productClientAgreement);

        // Get all the productClientAgreementList where freeField2 contains DEFAULT_FREE_FIELD_2
        defaultProductClientAgreementShouldBeFound("freeField2.contains=" + DEFAULT_FREE_FIELD_2);

        // Get all the productClientAgreementList where freeField2 contains UPDATED_FREE_FIELD_2
        defaultProductClientAgreementShouldNotBeFound("freeField2.contains=" + UPDATED_FREE_FIELD_2);
    }

    @Test
    @Transactional
    void getAllProductClientAgreementsByFreeField2NotContainsSomething() throws Exception {
        // Initialize the database
        productClientAgreementRepository.saveAndFlush(productClientAgreement);

        // Get all the productClientAgreementList where freeField2 does not contain DEFAULT_FREE_FIELD_2
        defaultProductClientAgreementShouldNotBeFound("freeField2.doesNotContain=" + DEFAULT_FREE_FIELD_2);

        // Get all the productClientAgreementList where freeField2 does not contain UPDATED_FREE_FIELD_2
        defaultProductClientAgreementShouldBeFound("freeField2.doesNotContain=" + UPDATED_FREE_FIELD_2);
    }

    @Test
    @Transactional
    void getAllProductClientAgreementsByFreeField3IsEqualToSomething() throws Exception {
        // Initialize the database
        productClientAgreementRepository.saveAndFlush(productClientAgreement);

        // Get all the productClientAgreementList where freeField3 equals to DEFAULT_FREE_FIELD_3
        defaultProductClientAgreementShouldBeFound("freeField3.equals=" + DEFAULT_FREE_FIELD_3);

        // Get all the productClientAgreementList where freeField3 equals to UPDATED_FREE_FIELD_3
        defaultProductClientAgreementShouldNotBeFound("freeField3.equals=" + UPDATED_FREE_FIELD_3);
    }

    @Test
    @Transactional
    void getAllProductClientAgreementsByFreeField3IsNotEqualToSomething() throws Exception {
        // Initialize the database
        productClientAgreementRepository.saveAndFlush(productClientAgreement);

        // Get all the productClientAgreementList where freeField3 not equals to DEFAULT_FREE_FIELD_3
        defaultProductClientAgreementShouldNotBeFound("freeField3.notEquals=" + DEFAULT_FREE_FIELD_3);

        // Get all the productClientAgreementList where freeField3 not equals to UPDATED_FREE_FIELD_3
        defaultProductClientAgreementShouldBeFound("freeField3.notEquals=" + UPDATED_FREE_FIELD_3);
    }

    @Test
    @Transactional
    void getAllProductClientAgreementsByFreeField3IsInShouldWork() throws Exception {
        // Initialize the database
        productClientAgreementRepository.saveAndFlush(productClientAgreement);

        // Get all the productClientAgreementList where freeField3 in DEFAULT_FREE_FIELD_3 or UPDATED_FREE_FIELD_3
        defaultProductClientAgreementShouldBeFound("freeField3.in=" + DEFAULT_FREE_FIELD_3 + "," + UPDATED_FREE_FIELD_3);

        // Get all the productClientAgreementList where freeField3 equals to UPDATED_FREE_FIELD_3
        defaultProductClientAgreementShouldNotBeFound("freeField3.in=" + UPDATED_FREE_FIELD_3);
    }

    @Test
    @Transactional
    void getAllProductClientAgreementsByFreeField3IsNullOrNotNull() throws Exception {
        // Initialize the database
        productClientAgreementRepository.saveAndFlush(productClientAgreement);

        // Get all the productClientAgreementList where freeField3 is not null
        defaultProductClientAgreementShouldBeFound("freeField3.specified=true");

        // Get all the productClientAgreementList where freeField3 is null
        defaultProductClientAgreementShouldNotBeFound("freeField3.specified=false");
    }

    @Test
    @Transactional
    void getAllProductClientAgreementsByFreeField3ContainsSomething() throws Exception {
        // Initialize the database
        productClientAgreementRepository.saveAndFlush(productClientAgreement);

        // Get all the productClientAgreementList where freeField3 contains DEFAULT_FREE_FIELD_3
        defaultProductClientAgreementShouldBeFound("freeField3.contains=" + DEFAULT_FREE_FIELD_3);

        // Get all the productClientAgreementList where freeField3 contains UPDATED_FREE_FIELD_3
        defaultProductClientAgreementShouldNotBeFound("freeField3.contains=" + UPDATED_FREE_FIELD_3);
    }

    @Test
    @Transactional
    void getAllProductClientAgreementsByFreeField3NotContainsSomething() throws Exception {
        // Initialize the database
        productClientAgreementRepository.saveAndFlush(productClientAgreement);

        // Get all the productClientAgreementList where freeField3 does not contain DEFAULT_FREE_FIELD_3
        defaultProductClientAgreementShouldNotBeFound("freeField3.doesNotContain=" + DEFAULT_FREE_FIELD_3);

        // Get all the productClientAgreementList where freeField3 does not contain UPDATED_FREE_FIELD_3
        defaultProductClientAgreementShouldBeFound("freeField3.doesNotContain=" + UPDATED_FREE_FIELD_3);
    }

    @Test
    @Transactional
    void getAllProductClientAgreementsByFreeField4IsEqualToSomething() throws Exception {
        // Initialize the database
        productClientAgreementRepository.saveAndFlush(productClientAgreement);

        // Get all the productClientAgreementList where freeField4 equals to DEFAULT_FREE_FIELD_4
        defaultProductClientAgreementShouldBeFound("freeField4.equals=" + DEFAULT_FREE_FIELD_4);

        // Get all the productClientAgreementList where freeField4 equals to UPDATED_FREE_FIELD_4
        defaultProductClientAgreementShouldNotBeFound("freeField4.equals=" + UPDATED_FREE_FIELD_4);
    }

    @Test
    @Transactional
    void getAllProductClientAgreementsByFreeField4IsNotEqualToSomething() throws Exception {
        // Initialize the database
        productClientAgreementRepository.saveAndFlush(productClientAgreement);

        // Get all the productClientAgreementList where freeField4 not equals to DEFAULT_FREE_FIELD_4
        defaultProductClientAgreementShouldNotBeFound("freeField4.notEquals=" + DEFAULT_FREE_FIELD_4);

        // Get all the productClientAgreementList where freeField4 not equals to UPDATED_FREE_FIELD_4
        defaultProductClientAgreementShouldBeFound("freeField4.notEquals=" + UPDATED_FREE_FIELD_4);
    }

    @Test
    @Transactional
    void getAllProductClientAgreementsByFreeField4IsInShouldWork() throws Exception {
        // Initialize the database
        productClientAgreementRepository.saveAndFlush(productClientAgreement);

        // Get all the productClientAgreementList where freeField4 in DEFAULT_FREE_FIELD_4 or UPDATED_FREE_FIELD_4
        defaultProductClientAgreementShouldBeFound("freeField4.in=" + DEFAULT_FREE_FIELD_4 + "," + UPDATED_FREE_FIELD_4);

        // Get all the productClientAgreementList where freeField4 equals to UPDATED_FREE_FIELD_4
        defaultProductClientAgreementShouldNotBeFound("freeField4.in=" + UPDATED_FREE_FIELD_4);
    }

    @Test
    @Transactional
    void getAllProductClientAgreementsByFreeField4IsNullOrNotNull() throws Exception {
        // Initialize the database
        productClientAgreementRepository.saveAndFlush(productClientAgreement);

        // Get all the productClientAgreementList where freeField4 is not null
        defaultProductClientAgreementShouldBeFound("freeField4.specified=true");

        // Get all the productClientAgreementList where freeField4 is null
        defaultProductClientAgreementShouldNotBeFound("freeField4.specified=false");
    }

    @Test
    @Transactional
    void getAllProductClientAgreementsByFreeField4ContainsSomething() throws Exception {
        // Initialize the database
        productClientAgreementRepository.saveAndFlush(productClientAgreement);

        // Get all the productClientAgreementList where freeField4 contains DEFAULT_FREE_FIELD_4
        defaultProductClientAgreementShouldBeFound("freeField4.contains=" + DEFAULT_FREE_FIELD_4);

        // Get all the productClientAgreementList where freeField4 contains UPDATED_FREE_FIELD_4
        defaultProductClientAgreementShouldNotBeFound("freeField4.contains=" + UPDATED_FREE_FIELD_4);
    }

    @Test
    @Transactional
    void getAllProductClientAgreementsByFreeField4NotContainsSomething() throws Exception {
        // Initialize the database
        productClientAgreementRepository.saveAndFlush(productClientAgreement);

        // Get all the productClientAgreementList where freeField4 does not contain DEFAULT_FREE_FIELD_4
        defaultProductClientAgreementShouldNotBeFound("freeField4.doesNotContain=" + DEFAULT_FREE_FIELD_4);

        // Get all the productClientAgreementList where freeField4 does not contain UPDATED_FREE_FIELD_4
        defaultProductClientAgreementShouldBeFound("freeField4.doesNotContain=" + UPDATED_FREE_FIELD_4);
    }

    @Test
    @Transactional
    void getAllProductClientAgreementsByLastModifiedIsEqualToSomething() throws Exception {
        // Initialize the database
        productClientAgreementRepository.saveAndFlush(productClientAgreement);

        // Get all the productClientAgreementList where lastModified equals to DEFAULT_LAST_MODIFIED
        defaultProductClientAgreementShouldBeFound("lastModified.equals=" + DEFAULT_LAST_MODIFIED);

        // Get all the productClientAgreementList where lastModified equals to UPDATED_LAST_MODIFIED
        defaultProductClientAgreementShouldNotBeFound("lastModified.equals=" + UPDATED_LAST_MODIFIED);
    }

    @Test
    @Transactional
    void getAllProductClientAgreementsByLastModifiedIsNotEqualToSomething() throws Exception {
        // Initialize the database
        productClientAgreementRepository.saveAndFlush(productClientAgreement);

        // Get all the productClientAgreementList where lastModified not equals to DEFAULT_LAST_MODIFIED
        defaultProductClientAgreementShouldNotBeFound("lastModified.notEquals=" + DEFAULT_LAST_MODIFIED);

        // Get all the productClientAgreementList where lastModified not equals to UPDATED_LAST_MODIFIED
        defaultProductClientAgreementShouldBeFound("lastModified.notEquals=" + UPDATED_LAST_MODIFIED);
    }

    @Test
    @Transactional
    void getAllProductClientAgreementsByLastModifiedIsInShouldWork() throws Exception {
        // Initialize the database
        productClientAgreementRepository.saveAndFlush(productClientAgreement);

        // Get all the productClientAgreementList where lastModified in DEFAULT_LAST_MODIFIED or UPDATED_LAST_MODIFIED
        defaultProductClientAgreementShouldBeFound("lastModified.in=" + DEFAULT_LAST_MODIFIED + "," + UPDATED_LAST_MODIFIED);

        // Get all the productClientAgreementList where lastModified equals to UPDATED_LAST_MODIFIED
        defaultProductClientAgreementShouldNotBeFound("lastModified.in=" + UPDATED_LAST_MODIFIED);
    }

    @Test
    @Transactional
    void getAllProductClientAgreementsByLastModifiedIsNullOrNotNull() throws Exception {
        // Initialize the database
        productClientAgreementRepository.saveAndFlush(productClientAgreement);

        // Get all the productClientAgreementList where lastModified is not null
        defaultProductClientAgreementShouldBeFound("lastModified.specified=true");

        // Get all the productClientAgreementList where lastModified is null
        defaultProductClientAgreementShouldNotBeFound("lastModified.specified=false");
    }

    @Test
    @Transactional
    void getAllProductClientAgreementsByLastModifiedContainsSomething() throws Exception {
        // Initialize the database
        productClientAgreementRepository.saveAndFlush(productClientAgreement);

        // Get all the productClientAgreementList where lastModified contains DEFAULT_LAST_MODIFIED
        defaultProductClientAgreementShouldBeFound("lastModified.contains=" + DEFAULT_LAST_MODIFIED);

        // Get all the productClientAgreementList where lastModified contains UPDATED_LAST_MODIFIED
        defaultProductClientAgreementShouldNotBeFound("lastModified.contains=" + UPDATED_LAST_MODIFIED);
    }

    @Test
    @Transactional
    void getAllProductClientAgreementsByLastModifiedNotContainsSomething() throws Exception {
        // Initialize the database
        productClientAgreementRepository.saveAndFlush(productClientAgreement);

        // Get all the productClientAgreementList where lastModified does not contain DEFAULT_LAST_MODIFIED
        defaultProductClientAgreementShouldNotBeFound("lastModified.doesNotContain=" + DEFAULT_LAST_MODIFIED);

        // Get all the productClientAgreementList where lastModified does not contain UPDATED_LAST_MODIFIED
        defaultProductClientAgreementShouldBeFound("lastModified.doesNotContain=" + UPDATED_LAST_MODIFIED);
    }

    @Test
    @Transactional
    void getAllProductClientAgreementsByLastModifiedByIsEqualToSomething() throws Exception {
        // Initialize the database
        productClientAgreementRepository.saveAndFlush(productClientAgreement);

        // Get all the productClientAgreementList where lastModifiedBy equals to DEFAULT_LAST_MODIFIED_BY
        defaultProductClientAgreementShouldBeFound("lastModifiedBy.equals=" + DEFAULT_LAST_MODIFIED_BY);

        // Get all the productClientAgreementList where lastModifiedBy equals to UPDATED_LAST_MODIFIED_BY
        defaultProductClientAgreementShouldNotBeFound("lastModifiedBy.equals=" + UPDATED_LAST_MODIFIED_BY);
    }

    @Test
    @Transactional
    void getAllProductClientAgreementsByLastModifiedByIsNotEqualToSomething() throws Exception {
        // Initialize the database
        productClientAgreementRepository.saveAndFlush(productClientAgreement);

        // Get all the productClientAgreementList where lastModifiedBy not equals to DEFAULT_LAST_MODIFIED_BY
        defaultProductClientAgreementShouldNotBeFound("lastModifiedBy.notEquals=" + DEFAULT_LAST_MODIFIED_BY);

        // Get all the productClientAgreementList where lastModifiedBy not equals to UPDATED_LAST_MODIFIED_BY
        defaultProductClientAgreementShouldBeFound("lastModifiedBy.notEquals=" + UPDATED_LAST_MODIFIED_BY);
    }

    @Test
    @Transactional
    void getAllProductClientAgreementsByLastModifiedByIsInShouldWork() throws Exception {
        // Initialize the database
        productClientAgreementRepository.saveAndFlush(productClientAgreement);

        // Get all the productClientAgreementList where lastModifiedBy in DEFAULT_LAST_MODIFIED_BY or UPDATED_LAST_MODIFIED_BY
        defaultProductClientAgreementShouldBeFound("lastModifiedBy.in=" + DEFAULT_LAST_MODIFIED_BY + "," + UPDATED_LAST_MODIFIED_BY);

        // Get all the productClientAgreementList where lastModifiedBy equals to UPDATED_LAST_MODIFIED_BY
        defaultProductClientAgreementShouldNotBeFound("lastModifiedBy.in=" + UPDATED_LAST_MODIFIED_BY);
    }

    @Test
    @Transactional
    void getAllProductClientAgreementsByLastModifiedByIsNullOrNotNull() throws Exception {
        // Initialize the database
        productClientAgreementRepository.saveAndFlush(productClientAgreement);

        // Get all the productClientAgreementList where lastModifiedBy is not null
        defaultProductClientAgreementShouldBeFound("lastModifiedBy.specified=true");

        // Get all the productClientAgreementList where lastModifiedBy is null
        defaultProductClientAgreementShouldNotBeFound("lastModifiedBy.specified=false");
    }

    @Test
    @Transactional
    void getAllProductClientAgreementsByLastModifiedByContainsSomething() throws Exception {
        // Initialize the database
        productClientAgreementRepository.saveAndFlush(productClientAgreement);

        // Get all the productClientAgreementList where lastModifiedBy contains DEFAULT_LAST_MODIFIED_BY
        defaultProductClientAgreementShouldBeFound("lastModifiedBy.contains=" + DEFAULT_LAST_MODIFIED_BY);

        // Get all the productClientAgreementList where lastModifiedBy contains UPDATED_LAST_MODIFIED_BY
        defaultProductClientAgreementShouldNotBeFound("lastModifiedBy.contains=" + UPDATED_LAST_MODIFIED_BY);
    }

    @Test
    @Transactional
    void getAllProductClientAgreementsByLastModifiedByNotContainsSomething() throws Exception {
        // Initialize the database
        productClientAgreementRepository.saveAndFlush(productClientAgreement);

        // Get all the productClientAgreementList where lastModifiedBy does not contain DEFAULT_LAST_MODIFIED_BY
        defaultProductClientAgreementShouldNotBeFound("lastModifiedBy.doesNotContain=" + DEFAULT_LAST_MODIFIED_BY);

        // Get all the productClientAgreementList where lastModifiedBy does not contain UPDATED_LAST_MODIFIED_BY
        defaultProductClientAgreementShouldBeFound("lastModifiedBy.doesNotContain=" + UPDATED_LAST_MODIFIED_BY);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultProductClientAgreementShouldBeFound(String filter) throws Exception {
        restProductClientAgreementMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(productClientAgreement.getId().intValue())))
            .andExpect(jsonPath("$.[*].price").value(hasItem(DEFAULT_PRICE.intValue())))
            .andExpect(jsonPath("$.[*].creditPeriod").value(hasItem(DEFAULT_CREDIT_PERIOD.doubleValue())))
            .andExpect(jsonPath("$.[*].penalty").value(hasItem(DEFAULT_PENALTY.intValue())))
            .andExpect(jsonPath("$.[*].penaltyTerms").value(hasItem(DEFAULT_PENALTY_TERMS.toString())))
            .andExpect(jsonPath("$.[*].notes").value(hasItem(DEFAULT_NOTES)))
            .andExpect(jsonPath("$.[*].image").value(hasItem(DEFAULT_IMAGE)))
            .andExpect(jsonPath("$.[*].agreementPeriod").value(hasItem(DEFAULT_AGREEMENT_PERIOD.intValue())))
            .andExpect(jsonPath("$.[*].termAndConditions").value(hasItem(DEFAULT_TERM_AND_CONDITIONS)))
            .andExpect(jsonPath("$.[*].freeField1").value(hasItem(DEFAULT_FREE_FIELD_1)))
            .andExpect(jsonPath("$.[*].freeField2").value(hasItem(DEFAULT_FREE_FIELD_2)))
            .andExpect(jsonPath("$.[*].freeField3").value(hasItem(DEFAULT_FREE_FIELD_3)))
            .andExpect(jsonPath("$.[*].freeField4").value(hasItem(DEFAULT_FREE_FIELD_4)))
            .andExpect(jsonPath("$.[*].lastModified").value(hasItem(DEFAULT_LAST_MODIFIED)))
            .andExpect(jsonPath("$.[*].lastModifiedBy").value(hasItem(DEFAULT_LAST_MODIFIED_BY)));

        // Check, that the count call also returns 1
        restProductClientAgreementMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultProductClientAgreementShouldNotBeFound(String filter) throws Exception {
        restProductClientAgreementMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restProductClientAgreementMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingProductClientAgreement() throws Exception {
        // Get the productClientAgreement
        restProductClientAgreementMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewProductClientAgreement() throws Exception {
        // Initialize the database
        productClientAgreementRepository.saveAndFlush(productClientAgreement);

        int databaseSizeBeforeUpdate = productClientAgreementRepository.findAll().size();

        // Update the productClientAgreement
        ProductClientAgreement updatedProductClientAgreement = productClientAgreementRepository
            .findById(productClientAgreement.getId())
            .get();
        // Disconnect from session so that the updates on updatedProductClientAgreement are not directly saved in db
        em.detach(updatedProductClientAgreement);
        updatedProductClientAgreement
            .price(UPDATED_PRICE)
            .creditPeriod(UPDATED_CREDIT_PERIOD)
            .penalty(UPDATED_PENALTY)
            .penaltyTerms(UPDATED_PENALTY_TERMS)
            .notes(UPDATED_NOTES)
            .image(UPDATED_IMAGE)
            .agreementPeriod(UPDATED_AGREEMENT_PERIOD)
            .termAndConditions(UPDATED_TERM_AND_CONDITIONS)
            .freeField1(UPDATED_FREE_FIELD_1)
            .freeField2(UPDATED_FREE_FIELD_2)
            .freeField3(UPDATED_FREE_FIELD_3)
            .freeField4(UPDATED_FREE_FIELD_4)
            .lastModified(UPDATED_LAST_MODIFIED)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY);
        ProductClientAgreementDTO productClientAgreementDTO = productClientAgreementMapper.toDto(updatedProductClientAgreement);

        restProductClientAgreementMockMvc
            .perform(
                put(ENTITY_API_URL_ID, productClientAgreementDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(productClientAgreementDTO))
            )
            .andExpect(status().isOk());

        // Validate the ProductClientAgreement in the database
        List<ProductClientAgreement> productClientAgreementList = productClientAgreementRepository.findAll();
        assertThat(productClientAgreementList).hasSize(databaseSizeBeforeUpdate);
        ProductClientAgreement testProductClientAgreement = productClientAgreementList.get(productClientAgreementList.size() - 1);
        assertThat(testProductClientAgreement.getPrice()).isEqualTo(UPDATED_PRICE);
        assertThat(testProductClientAgreement.getCreditPeriod()).isEqualTo(UPDATED_CREDIT_PERIOD);
        assertThat(testProductClientAgreement.getPenalty()).isEqualTo(UPDATED_PENALTY);
        assertThat(testProductClientAgreement.getPenaltyTerms()).isEqualTo(UPDATED_PENALTY_TERMS);
        assertThat(testProductClientAgreement.getNotes()).isEqualTo(UPDATED_NOTES);
        assertThat(testProductClientAgreement.getImage()).isEqualTo(UPDATED_IMAGE);
        assertThat(testProductClientAgreement.getAgreementPeriod()).isEqualTo(UPDATED_AGREEMENT_PERIOD);
        assertThat(testProductClientAgreement.getTermAndConditions()).isEqualTo(UPDATED_TERM_AND_CONDITIONS);
        assertThat(testProductClientAgreement.getFreeField1()).isEqualTo(UPDATED_FREE_FIELD_1);
        assertThat(testProductClientAgreement.getFreeField2()).isEqualTo(UPDATED_FREE_FIELD_2);
        assertThat(testProductClientAgreement.getFreeField3()).isEqualTo(UPDATED_FREE_FIELD_3);
        assertThat(testProductClientAgreement.getFreeField4()).isEqualTo(UPDATED_FREE_FIELD_4);
        assertThat(testProductClientAgreement.getLastModified()).isEqualTo(UPDATED_LAST_MODIFIED);
        assertThat(testProductClientAgreement.getLastModifiedBy()).isEqualTo(UPDATED_LAST_MODIFIED_BY);
    }

    @Test
    @Transactional
    void putNonExistingProductClientAgreement() throws Exception {
        int databaseSizeBeforeUpdate = productClientAgreementRepository.findAll().size();
        productClientAgreement.setId(count.incrementAndGet());

        // Create the ProductClientAgreement
        ProductClientAgreementDTO productClientAgreementDTO = productClientAgreementMapper.toDto(productClientAgreement);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProductClientAgreementMockMvc
            .perform(
                put(ENTITY_API_URL_ID, productClientAgreementDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(productClientAgreementDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProductClientAgreement in the database
        List<ProductClientAgreement> productClientAgreementList = productClientAgreementRepository.findAll();
        assertThat(productClientAgreementList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchProductClientAgreement() throws Exception {
        int databaseSizeBeforeUpdate = productClientAgreementRepository.findAll().size();
        productClientAgreement.setId(count.incrementAndGet());

        // Create the ProductClientAgreement
        ProductClientAgreementDTO productClientAgreementDTO = productClientAgreementMapper.toDto(productClientAgreement);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProductClientAgreementMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(productClientAgreementDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProductClientAgreement in the database
        List<ProductClientAgreement> productClientAgreementList = productClientAgreementRepository.findAll();
        assertThat(productClientAgreementList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamProductClientAgreement() throws Exception {
        int databaseSizeBeforeUpdate = productClientAgreementRepository.findAll().size();
        productClientAgreement.setId(count.incrementAndGet());

        // Create the ProductClientAgreement
        ProductClientAgreementDTO productClientAgreementDTO = productClientAgreementMapper.toDto(productClientAgreement);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProductClientAgreementMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(productClientAgreementDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ProductClientAgreement in the database
        List<ProductClientAgreement> productClientAgreementList = productClientAgreementRepository.findAll();
        assertThat(productClientAgreementList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateProductClientAgreementWithPatch() throws Exception {
        // Initialize the database
        productClientAgreementRepository.saveAndFlush(productClientAgreement);

        int databaseSizeBeforeUpdate = productClientAgreementRepository.findAll().size();

        // Update the productClientAgreement using partial update
        ProductClientAgreement partialUpdatedProductClientAgreement = new ProductClientAgreement();
        partialUpdatedProductClientAgreement.setId(productClientAgreement.getId());

        partialUpdatedProductClientAgreement
            .penaltyTerms(UPDATED_PENALTY_TERMS)
            .image(UPDATED_IMAGE)
            .freeField1(UPDATED_FREE_FIELD_1)
            .freeField2(UPDATED_FREE_FIELD_2)
            .freeField3(UPDATED_FREE_FIELD_3)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY);

        restProductClientAgreementMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedProductClientAgreement.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedProductClientAgreement))
            )
            .andExpect(status().isOk());

        // Validate the ProductClientAgreement in the database
        List<ProductClientAgreement> productClientAgreementList = productClientAgreementRepository.findAll();
        assertThat(productClientAgreementList).hasSize(databaseSizeBeforeUpdate);
        ProductClientAgreement testProductClientAgreement = productClientAgreementList.get(productClientAgreementList.size() - 1);
        assertThat(testProductClientAgreement.getPrice()).isEqualTo(DEFAULT_PRICE);
        assertThat(testProductClientAgreement.getCreditPeriod()).isEqualTo(DEFAULT_CREDIT_PERIOD);
        assertThat(testProductClientAgreement.getPenalty()).isEqualTo(DEFAULT_PENALTY);
        assertThat(testProductClientAgreement.getPenaltyTerms()).isEqualTo(UPDATED_PENALTY_TERMS);
        assertThat(testProductClientAgreement.getNotes()).isEqualTo(DEFAULT_NOTES);
        assertThat(testProductClientAgreement.getImage()).isEqualTo(UPDATED_IMAGE);
        assertThat(testProductClientAgreement.getAgreementPeriod()).isEqualTo(DEFAULT_AGREEMENT_PERIOD);
        assertThat(testProductClientAgreement.getTermAndConditions()).isEqualTo(DEFAULT_TERM_AND_CONDITIONS);
        assertThat(testProductClientAgreement.getFreeField1()).isEqualTo(UPDATED_FREE_FIELD_1);
        assertThat(testProductClientAgreement.getFreeField2()).isEqualTo(UPDATED_FREE_FIELD_2);
        assertThat(testProductClientAgreement.getFreeField3()).isEqualTo(UPDATED_FREE_FIELD_3);
        assertThat(testProductClientAgreement.getFreeField4()).isEqualTo(DEFAULT_FREE_FIELD_4);
        assertThat(testProductClientAgreement.getLastModified()).isEqualTo(DEFAULT_LAST_MODIFIED);
        assertThat(testProductClientAgreement.getLastModifiedBy()).isEqualTo(UPDATED_LAST_MODIFIED_BY);
    }

    @Test
    @Transactional
    void fullUpdateProductClientAgreementWithPatch() throws Exception {
        // Initialize the database
        productClientAgreementRepository.saveAndFlush(productClientAgreement);

        int databaseSizeBeforeUpdate = productClientAgreementRepository.findAll().size();

        // Update the productClientAgreement using partial update
        ProductClientAgreement partialUpdatedProductClientAgreement = new ProductClientAgreement();
        partialUpdatedProductClientAgreement.setId(productClientAgreement.getId());

        partialUpdatedProductClientAgreement
            .price(UPDATED_PRICE)
            .creditPeriod(UPDATED_CREDIT_PERIOD)
            .penalty(UPDATED_PENALTY)
            .penaltyTerms(UPDATED_PENALTY_TERMS)
            .notes(UPDATED_NOTES)
            .image(UPDATED_IMAGE)
            .agreementPeriod(UPDATED_AGREEMENT_PERIOD)
            .termAndConditions(UPDATED_TERM_AND_CONDITIONS)
            .freeField1(UPDATED_FREE_FIELD_1)
            .freeField2(UPDATED_FREE_FIELD_2)
            .freeField3(UPDATED_FREE_FIELD_3)
            .freeField4(UPDATED_FREE_FIELD_4)
            .lastModified(UPDATED_LAST_MODIFIED)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY);

        restProductClientAgreementMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedProductClientAgreement.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedProductClientAgreement))
            )
            .andExpect(status().isOk());

        // Validate the ProductClientAgreement in the database
        List<ProductClientAgreement> productClientAgreementList = productClientAgreementRepository.findAll();
        assertThat(productClientAgreementList).hasSize(databaseSizeBeforeUpdate);
        ProductClientAgreement testProductClientAgreement = productClientAgreementList.get(productClientAgreementList.size() - 1);
        assertThat(testProductClientAgreement.getPrice()).isEqualTo(UPDATED_PRICE);
        assertThat(testProductClientAgreement.getCreditPeriod()).isEqualTo(UPDATED_CREDIT_PERIOD);
        assertThat(testProductClientAgreement.getPenalty()).isEqualTo(UPDATED_PENALTY);
        assertThat(testProductClientAgreement.getPenaltyTerms()).isEqualTo(UPDATED_PENALTY_TERMS);
        assertThat(testProductClientAgreement.getNotes()).isEqualTo(UPDATED_NOTES);
        assertThat(testProductClientAgreement.getImage()).isEqualTo(UPDATED_IMAGE);
        assertThat(testProductClientAgreement.getAgreementPeriod()).isEqualTo(UPDATED_AGREEMENT_PERIOD);
        assertThat(testProductClientAgreement.getTermAndConditions()).isEqualTo(UPDATED_TERM_AND_CONDITIONS);
        assertThat(testProductClientAgreement.getFreeField1()).isEqualTo(UPDATED_FREE_FIELD_1);
        assertThat(testProductClientAgreement.getFreeField2()).isEqualTo(UPDATED_FREE_FIELD_2);
        assertThat(testProductClientAgreement.getFreeField3()).isEqualTo(UPDATED_FREE_FIELD_3);
        assertThat(testProductClientAgreement.getFreeField4()).isEqualTo(UPDATED_FREE_FIELD_4);
        assertThat(testProductClientAgreement.getLastModified()).isEqualTo(UPDATED_LAST_MODIFIED);
        assertThat(testProductClientAgreement.getLastModifiedBy()).isEqualTo(UPDATED_LAST_MODIFIED_BY);
    }

    @Test
    @Transactional
    void patchNonExistingProductClientAgreement() throws Exception {
        int databaseSizeBeforeUpdate = productClientAgreementRepository.findAll().size();
        productClientAgreement.setId(count.incrementAndGet());

        // Create the ProductClientAgreement
        ProductClientAgreementDTO productClientAgreementDTO = productClientAgreementMapper.toDto(productClientAgreement);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProductClientAgreementMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, productClientAgreementDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(productClientAgreementDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProductClientAgreement in the database
        List<ProductClientAgreement> productClientAgreementList = productClientAgreementRepository.findAll();
        assertThat(productClientAgreementList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchProductClientAgreement() throws Exception {
        int databaseSizeBeforeUpdate = productClientAgreementRepository.findAll().size();
        productClientAgreement.setId(count.incrementAndGet());

        // Create the ProductClientAgreement
        ProductClientAgreementDTO productClientAgreementDTO = productClientAgreementMapper.toDto(productClientAgreement);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProductClientAgreementMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(productClientAgreementDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProductClientAgreement in the database
        List<ProductClientAgreement> productClientAgreementList = productClientAgreementRepository.findAll();
        assertThat(productClientAgreementList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamProductClientAgreement() throws Exception {
        int databaseSizeBeforeUpdate = productClientAgreementRepository.findAll().size();
        productClientAgreement.setId(count.incrementAndGet());

        // Create the ProductClientAgreement
        ProductClientAgreementDTO productClientAgreementDTO = productClientAgreementMapper.toDto(productClientAgreement);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProductClientAgreementMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(productClientAgreementDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ProductClientAgreement in the database
        List<ProductClientAgreement> productClientAgreementList = productClientAgreementRepository.findAll();
        assertThat(productClientAgreementList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteProductClientAgreement() throws Exception {
        // Initialize the database
        productClientAgreementRepository.saveAndFlush(productClientAgreement);

        int databaseSizeBeforeDelete = productClientAgreementRepository.findAll().size();

        // Delete the productClientAgreement
        restProductClientAgreementMockMvc
            .perform(delete(ENTITY_API_URL_ID, productClientAgreement.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ProductClientAgreement> productClientAgreementList = productClientAgreementRepository.findAll();
        assertThat(productClientAgreementList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
