package com.tecgvg.gyantatv.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.tecgvg.gyantatv.IntegrationTest;
import com.tecgvg.gyantatv.domain.Categories;
import com.tecgvg.gyantatv.domain.Product;
import com.tecgvg.gyantatv.domain.ProductClientAgreement;
import com.tecgvg.gyantatv.domain.SecurityUser;
import com.tecgvg.gyantatv.domain.TransferDetails;
import com.tecgvg.gyantatv.domain.Unit;
import com.tecgvg.gyantatv.domain.enumeration.ProductType;
import com.tecgvg.gyantatv.repository.ProductRepository;
import com.tecgvg.gyantatv.service.ProductService;
import com.tecgvg.gyantatv.service.criteria.ProductCriteria;
import com.tecgvg.gyantatv.service.dto.ProductDTO;
import com.tecgvg.gyantatv.service.mapper.ProductMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;

/**
 * Integration tests for the {@link ProductResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class ProductResourceIT {

    private static final String DEFAULT_SHORT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SHORT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_HSN_NO = "AAAAAAAAAA";
    private static final String UPDATED_HSN_NO = "BBBBBBBBBB";

    private static final byte[] DEFAULT_MATERIAL_IMAGE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_MATERIAL_IMAGE = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_MATERIAL_IMAGE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_MATERIAL_IMAGE_CONTENT_TYPE = "image/png";

    private static final Boolean DEFAULT_IS_DELETED = false;
    private static final Boolean UPDATED_IS_DELETED = true;

    private static final Boolean DEFAULT_IS_ACTIVE = false;
    private static final Boolean UPDATED_IS_ACTIVE = true;

    private static final String DEFAULT_PRODUCT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ALERT_UNITS = "AAAAAAAAAA";
    private static final String UPDATED_ALERT_UNITS = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Double DEFAULT_BASE_PRICE = 1D;
    private static final Double UPDATED_BASE_PRICE = 2D;
    private static final Double SMALLER_BASE_PRICE = 1D - 1D;

    private static final String DEFAULT_QUALITY = "AAAAAAAAAA";
    private static final String UPDATED_QUALITY = "BBBBBBBBBB";

    private static final String DEFAULT_QR_CODE = "AAAAAAAAAA";
    private static final String UPDATED_QR_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_BAR_CODE = "AAAAAAAAAA";
    private static final String UPDATED_BAR_CODE = "BBBBBBBBBB";

    private static final Double DEFAULT_GST_PERCENTAGE = 1D;
    private static final Double UPDATED_GST_PERCENTAGE = 2D;
    private static final Double SMALLER_GST_PERCENTAGE = 1D - 1D;

    private static final ProductType DEFAULT_PRODUCT_TYPE = ProductType.RAWMATERIAL;
    private static final ProductType UPDATED_PRODUCT_TYPE = ProductType.PRODUCT;

    private static final String DEFAULT_LAST_MODIFIED = "AAAAAAAAAA";
    private static final String UPDATED_LAST_MODIFIED = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_MODIFIED_BY = "AAAAAAAAAA";
    private static final String UPDATED_LAST_MODIFIED_BY = "BBBBBBBBBB";

    private static final String DEFAULT_FREE_FIELD_1 = "AAAAAAAAAA";
    private static final String UPDATED_FREE_FIELD_1 = "BBBBBBBBBB";

    private static final String DEFAULT_FREE_FIELD_2 = "AAAAAAAAAA";
    private static final String UPDATED_FREE_FIELD_2 = "BBBBBBBBBB";

    private static final String DEFAULT_FREE_FIELD_3 = "AAAAAAAAAA";
    private static final String UPDATED_FREE_FIELD_3 = "BBBBBBBBBB";

    private static final String DEFAULT_FREE_FIELD_4 = "AAAAAAAAAA";
    private static final String UPDATED_FREE_FIELD_4 = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/products";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ProductRepository productRepository;

    @Mock
    private ProductRepository productRepositoryMock;

    @Autowired
    private ProductMapper productMapper;

    @Mock
    private ProductService productServiceMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restProductMockMvc;

    private Product product;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Product createEntity(EntityManager em) {
        Product product = new Product()
            .shortName(DEFAULT_SHORT_NAME)
            .hsnNo(DEFAULT_HSN_NO)
            .materialImage(DEFAULT_MATERIAL_IMAGE)
            .materialImageContentType(DEFAULT_MATERIAL_IMAGE_CONTENT_TYPE)
            .isDeleted(DEFAULT_IS_DELETED)
            .isActive(DEFAULT_IS_ACTIVE)
            .productName(DEFAULT_PRODUCT_NAME)
            .alertUnits(DEFAULT_ALERT_UNITS)
            .description(DEFAULT_DESCRIPTION)
            .basePrice(DEFAULT_BASE_PRICE)
            .quality(DEFAULT_QUALITY)
            .qrCode(DEFAULT_QR_CODE)
            .barCode(DEFAULT_BAR_CODE)
            .gstPercentage(DEFAULT_GST_PERCENTAGE)
            .productType(DEFAULT_PRODUCT_TYPE)
            .lastModified(DEFAULT_LAST_MODIFIED)
            .lastModifiedBy(DEFAULT_LAST_MODIFIED_BY)
            .freeField1(DEFAULT_FREE_FIELD_1)
            .freeField2(DEFAULT_FREE_FIELD_2)
            .freeField3(DEFAULT_FREE_FIELD_3)
            .freeField4(DEFAULT_FREE_FIELD_4);
        return product;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Product createUpdatedEntity(EntityManager em) {
        Product product = new Product()
            .shortName(UPDATED_SHORT_NAME)
            .hsnNo(UPDATED_HSN_NO)
            .materialImage(UPDATED_MATERIAL_IMAGE)
            .materialImageContentType(UPDATED_MATERIAL_IMAGE_CONTENT_TYPE)
            .isDeleted(UPDATED_IS_DELETED)
            .isActive(UPDATED_IS_ACTIVE)
            .productName(UPDATED_PRODUCT_NAME)
            .alertUnits(UPDATED_ALERT_UNITS)
            .description(UPDATED_DESCRIPTION)
            .basePrice(UPDATED_BASE_PRICE)
            .quality(UPDATED_QUALITY)
            .qrCode(UPDATED_QR_CODE)
            .barCode(UPDATED_BAR_CODE)
            .gstPercentage(UPDATED_GST_PERCENTAGE)
            .productType(UPDATED_PRODUCT_TYPE)
            .lastModified(UPDATED_LAST_MODIFIED)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
            .freeField1(UPDATED_FREE_FIELD_1)
            .freeField2(UPDATED_FREE_FIELD_2)
            .freeField3(UPDATED_FREE_FIELD_3)
            .freeField4(UPDATED_FREE_FIELD_4);
        return product;
    }

    @BeforeEach
    public void initTest() {
        product = createEntity(em);
    }

    @Test
    @Transactional
    void createProduct() throws Exception {
        int databaseSizeBeforeCreate = productRepository.findAll().size();
        // Create the Product
        ProductDTO productDTO = productMapper.toDto(product);
        restProductMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(productDTO)))
            .andExpect(status().isCreated());

        // Validate the Product in the database
        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeCreate + 1);
        Product testProduct = productList.get(productList.size() - 1);
        assertThat(testProduct.getShortName()).isEqualTo(DEFAULT_SHORT_NAME);
        assertThat(testProduct.getHsnNo()).isEqualTo(DEFAULT_HSN_NO);
        assertThat(testProduct.getMaterialImage()).isEqualTo(DEFAULT_MATERIAL_IMAGE);
        assertThat(testProduct.getMaterialImageContentType()).isEqualTo(DEFAULT_MATERIAL_IMAGE_CONTENT_TYPE);
        assertThat(testProduct.getIsDeleted()).isEqualTo(DEFAULT_IS_DELETED);
        assertThat(testProduct.getIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
        assertThat(testProduct.getProductName()).isEqualTo(DEFAULT_PRODUCT_NAME);
        assertThat(testProduct.getAlertUnits()).isEqualTo(DEFAULT_ALERT_UNITS);
        assertThat(testProduct.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testProduct.getBasePrice()).isEqualTo(DEFAULT_BASE_PRICE);
        assertThat(testProduct.getQuality()).isEqualTo(DEFAULT_QUALITY);
        assertThat(testProduct.getQrCode()).isEqualTo(DEFAULT_QR_CODE);
        assertThat(testProduct.getBarCode()).isEqualTo(DEFAULT_BAR_CODE);
        assertThat(testProduct.getGstPercentage()).isEqualTo(DEFAULT_GST_PERCENTAGE);
        assertThat(testProduct.getProductType()).isEqualTo(DEFAULT_PRODUCT_TYPE);
        assertThat(testProduct.getLastModified()).isEqualTo(DEFAULT_LAST_MODIFIED);
        assertThat(testProduct.getLastModifiedBy()).isEqualTo(DEFAULT_LAST_MODIFIED_BY);
        assertThat(testProduct.getFreeField1()).isEqualTo(DEFAULT_FREE_FIELD_1);
        assertThat(testProduct.getFreeField2()).isEqualTo(DEFAULT_FREE_FIELD_2);
        assertThat(testProduct.getFreeField3()).isEqualTo(DEFAULT_FREE_FIELD_3);
        assertThat(testProduct.getFreeField4()).isEqualTo(DEFAULT_FREE_FIELD_4);
    }

    @Test
    @Transactional
    void createProductWithExistingId() throws Exception {
        // Create the Product with an existing ID
        product.setId(1L);
        ProductDTO productDTO = productMapper.toDto(product);

        int databaseSizeBeforeCreate = productRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restProductMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(productDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Product in the database
        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllProducts() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList
        restProductMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(product.getId().intValue())))
            .andExpect(jsonPath("$.[*].shortName").value(hasItem(DEFAULT_SHORT_NAME)))
            .andExpect(jsonPath("$.[*].hsnNo").value(hasItem(DEFAULT_HSN_NO)))
            .andExpect(jsonPath("$.[*].materialImageContentType").value(hasItem(DEFAULT_MATERIAL_IMAGE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].materialImage").value(hasItem(Base64Utils.encodeToString(DEFAULT_MATERIAL_IMAGE))))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].productName").value(hasItem(DEFAULT_PRODUCT_NAME)))
            .andExpect(jsonPath("$.[*].alertUnits").value(hasItem(DEFAULT_ALERT_UNITS)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].basePrice").value(hasItem(DEFAULT_BASE_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].quality").value(hasItem(DEFAULT_QUALITY)))
            .andExpect(jsonPath("$.[*].qrCode").value(hasItem(DEFAULT_QR_CODE)))
            .andExpect(jsonPath("$.[*].barCode").value(hasItem(DEFAULT_BAR_CODE)))
            .andExpect(jsonPath("$.[*].gstPercentage").value(hasItem(DEFAULT_GST_PERCENTAGE.doubleValue())))
            .andExpect(jsonPath("$.[*].productType").value(hasItem(DEFAULT_PRODUCT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].lastModified").value(hasItem(DEFAULT_LAST_MODIFIED)))
            .andExpect(jsonPath("$.[*].lastModifiedBy").value(hasItem(DEFAULT_LAST_MODIFIED_BY)))
            .andExpect(jsonPath("$.[*].freeField1").value(hasItem(DEFAULT_FREE_FIELD_1)))
            .andExpect(jsonPath("$.[*].freeField2").value(hasItem(DEFAULT_FREE_FIELD_2)))
            .andExpect(jsonPath("$.[*].freeField3").value(hasItem(DEFAULT_FREE_FIELD_3)))
            .andExpect(jsonPath("$.[*].freeField4").value(hasItem(DEFAULT_FREE_FIELD_4)));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllProductsWithEagerRelationshipsIsEnabled() throws Exception {
        when(productServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restProductMockMvc.perform(get(ENTITY_API_URL + "?eagerload=true")).andExpect(status().isOk());

        verify(productServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllProductsWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(productServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restProductMockMvc.perform(get(ENTITY_API_URL + "?eagerload=true")).andExpect(status().isOk());

        verify(productServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    void getProduct() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get the product
        restProductMockMvc
            .perform(get(ENTITY_API_URL_ID, product.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(product.getId().intValue()))
            .andExpect(jsonPath("$.shortName").value(DEFAULT_SHORT_NAME))
            .andExpect(jsonPath("$.hsnNo").value(DEFAULT_HSN_NO))
            .andExpect(jsonPath("$.materialImageContentType").value(DEFAULT_MATERIAL_IMAGE_CONTENT_TYPE))
            .andExpect(jsonPath("$.materialImage").value(Base64Utils.encodeToString(DEFAULT_MATERIAL_IMAGE)))
            .andExpect(jsonPath("$.isDeleted").value(DEFAULT_IS_DELETED.booleanValue()))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE.booleanValue()))
            .andExpect(jsonPath("$.productName").value(DEFAULT_PRODUCT_NAME))
            .andExpect(jsonPath("$.alertUnits").value(DEFAULT_ALERT_UNITS))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.basePrice").value(DEFAULT_BASE_PRICE.doubleValue()))
            .andExpect(jsonPath("$.quality").value(DEFAULT_QUALITY))
            .andExpect(jsonPath("$.qrCode").value(DEFAULT_QR_CODE))
            .andExpect(jsonPath("$.barCode").value(DEFAULT_BAR_CODE))
            .andExpect(jsonPath("$.gstPercentage").value(DEFAULT_GST_PERCENTAGE.doubleValue()))
            .andExpect(jsonPath("$.productType").value(DEFAULT_PRODUCT_TYPE.toString()))
            .andExpect(jsonPath("$.lastModified").value(DEFAULT_LAST_MODIFIED))
            .andExpect(jsonPath("$.lastModifiedBy").value(DEFAULT_LAST_MODIFIED_BY))
            .andExpect(jsonPath("$.freeField1").value(DEFAULT_FREE_FIELD_1))
            .andExpect(jsonPath("$.freeField2").value(DEFAULT_FREE_FIELD_2))
            .andExpect(jsonPath("$.freeField3").value(DEFAULT_FREE_FIELD_3))
            .andExpect(jsonPath("$.freeField4").value(DEFAULT_FREE_FIELD_4));
    }

    @Test
    @Transactional
    void getProductsByIdFiltering() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        Long id = product.getId();

        defaultProductShouldBeFound("id.equals=" + id);
        defaultProductShouldNotBeFound("id.notEquals=" + id);

        defaultProductShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultProductShouldNotBeFound("id.greaterThan=" + id);

        defaultProductShouldBeFound("id.lessThanOrEqual=" + id);
        defaultProductShouldNotBeFound("id.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllProductsByShortNameIsEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where shortName equals to DEFAULT_SHORT_NAME
        defaultProductShouldBeFound("shortName.equals=" + DEFAULT_SHORT_NAME);

        // Get all the productList where shortName equals to UPDATED_SHORT_NAME
        defaultProductShouldNotBeFound("shortName.equals=" + UPDATED_SHORT_NAME);
    }

    @Test
    @Transactional
    void getAllProductsByShortNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where shortName not equals to DEFAULT_SHORT_NAME
        defaultProductShouldNotBeFound("shortName.notEquals=" + DEFAULT_SHORT_NAME);

        // Get all the productList where shortName not equals to UPDATED_SHORT_NAME
        defaultProductShouldBeFound("shortName.notEquals=" + UPDATED_SHORT_NAME);
    }

    @Test
    @Transactional
    void getAllProductsByShortNameIsInShouldWork() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where shortName in DEFAULT_SHORT_NAME or UPDATED_SHORT_NAME
        defaultProductShouldBeFound("shortName.in=" + DEFAULT_SHORT_NAME + "," + UPDATED_SHORT_NAME);

        // Get all the productList where shortName equals to UPDATED_SHORT_NAME
        defaultProductShouldNotBeFound("shortName.in=" + UPDATED_SHORT_NAME);
    }

    @Test
    @Transactional
    void getAllProductsByShortNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where shortName is not null
        defaultProductShouldBeFound("shortName.specified=true");

        // Get all the productList where shortName is null
        defaultProductShouldNotBeFound("shortName.specified=false");
    }

    @Test
    @Transactional
    void getAllProductsByShortNameContainsSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where shortName contains DEFAULT_SHORT_NAME
        defaultProductShouldBeFound("shortName.contains=" + DEFAULT_SHORT_NAME);

        // Get all the productList where shortName contains UPDATED_SHORT_NAME
        defaultProductShouldNotBeFound("shortName.contains=" + UPDATED_SHORT_NAME);
    }

    @Test
    @Transactional
    void getAllProductsByShortNameNotContainsSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where shortName does not contain DEFAULT_SHORT_NAME
        defaultProductShouldNotBeFound("shortName.doesNotContain=" + DEFAULT_SHORT_NAME);

        // Get all the productList where shortName does not contain UPDATED_SHORT_NAME
        defaultProductShouldBeFound("shortName.doesNotContain=" + UPDATED_SHORT_NAME);
    }

    @Test
    @Transactional
    void getAllProductsByHsnNoIsEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where hsnNo equals to DEFAULT_HSN_NO
        defaultProductShouldBeFound("hsnNo.equals=" + DEFAULT_HSN_NO);

        // Get all the productList where hsnNo equals to UPDATED_HSN_NO
        defaultProductShouldNotBeFound("hsnNo.equals=" + UPDATED_HSN_NO);
    }

    @Test
    @Transactional
    void getAllProductsByHsnNoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where hsnNo not equals to DEFAULT_HSN_NO
        defaultProductShouldNotBeFound("hsnNo.notEquals=" + DEFAULT_HSN_NO);

        // Get all the productList where hsnNo not equals to UPDATED_HSN_NO
        defaultProductShouldBeFound("hsnNo.notEquals=" + UPDATED_HSN_NO);
    }

    @Test
    @Transactional
    void getAllProductsByHsnNoIsInShouldWork() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where hsnNo in DEFAULT_HSN_NO or UPDATED_HSN_NO
        defaultProductShouldBeFound("hsnNo.in=" + DEFAULT_HSN_NO + "," + UPDATED_HSN_NO);

        // Get all the productList where hsnNo equals to UPDATED_HSN_NO
        defaultProductShouldNotBeFound("hsnNo.in=" + UPDATED_HSN_NO);
    }

    @Test
    @Transactional
    void getAllProductsByHsnNoIsNullOrNotNull() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where hsnNo is not null
        defaultProductShouldBeFound("hsnNo.specified=true");

        // Get all the productList where hsnNo is null
        defaultProductShouldNotBeFound("hsnNo.specified=false");
    }

    @Test
    @Transactional
    void getAllProductsByHsnNoContainsSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where hsnNo contains DEFAULT_HSN_NO
        defaultProductShouldBeFound("hsnNo.contains=" + DEFAULT_HSN_NO);

        // Get all the productList where hsnNo contains UPDATED_HSN_NO
        defaultProductShouldNotBeFound("hsnNo.contains=" + UPDATED_HSN_NO);
    }

    @Test
    @Transactional
    void getAllProductsByHsnNoNotContainsSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where hsnNo does not contain DEFAULT_HSN_NO
        defaultProductShouldNotBeFound("hsnNo.doesNotContain=" + DEFAULT_HSN_NO);

        // Get all the productList where hsnNo does not contain UPDATED_HSN_NO
        defaultProductShouldBeFound("hsnNo.doesNotContain=" + UPDATED_HSN_NO);
    }

    @Test
    @Transactional
    void getAllProductsByIsDeletedIsEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where isDeleted equals to DEFAULT_IS_DELETED
        defaultProductShouldBeFound("isDeleted.equals=" + DEFAULT_IS_DELETED);

        // Get all the productList where isDeleted equals to UPDATED_IS_DELETED
        defaultProductShouldNotBeFound("isDeleted.equals=" + UPDATED_IS_DELETED);
    }

    @Test
    @Transactional
    void getAllProductsByIsDeletedIsNotEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where isDeleted not equals to DEFAULT_IS_DELETED
        defaultProductShouldNotBeFound("isDeleted.notEquals=" + DEFAULT_IS_DELETED);

        // Get all the productList where isDeleted not equals to UPDATED_IS_DELETED
        defaultProductShouldBeFound("isDeleted.notEquals=" + UPDATED_IS_DELETED);
    }

    @Test
    @Transactional
    void getAllProductsByIsDeletedIsInShouldWork() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where isDeleted in DEFAULT_IS_DELETED or UPDATED_IS_DELETED
        defaultProductShouldBeFound("isDeleted.in=" + DEFAULT_IS_DELETED + "," + UPDATED_IS_DELETED);

        // Get all the productList where isDeleted equals to UPDATED_IS_DELETED
        defaultProductShouldNotBeFound("isDeleted.in=" + UPDATED_IS_DELETED);
    }

    @Test
    @Transactional
    void getAllProductsByIsDeletedIsNullOrNotNull() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where isDeleted is not null
        defaultProductShouldBeFound("isDeleted.specified=true");

        // Get all the productList where isDeleted is null
        defaultProductShouldNotBeFound("isDeleted.specified=false");
    }

    @Test
    @Transactional
    void getAllProductsByIsActiveIsEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where isActive equals to DEFAULT_IS_ACTIVE
        defaultProductShouldBeFound("isActive.equals=" + DEFAULT_IS_ACTIVE);

        // Get all the productList where isActive equals to UPDATED_IS_ACTIVE
        defaultProductShouldNotBeFound("isActive.equals=" + UPDATED_IS_ACTIVE);
    }

    @Test
    @Transactional
    void getAllProductsByIsActiveIsNotEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where isActive not equals to DEFAULT_IS_ACTIVE
        defaultProductShouldNotBeFound("isActive.notEquals=" + DEFAULT_IS_ACTIVE);

        // Get all the productList where isActive not equals to UPDATED_IS_ACTIVE
        defaultProductShouldBeFound("isActive.notEquals=" + UPDATED_IS_ACTIVE);
    }

    @Test
    @Transactional
    void getAllProductsByIsActiveIsInShouldWork() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where isActive in DEFAULT_IS_ACTIVE or UPDATED_IS_ACTIVE
        defaultProductShouldBeFound("isActive.in=" + DEFAULT_IS_ACTIVE + "," + UPDATED_IS_ACTIVE);

        // Get all the productList where isActive equals to UPDATED_IS_ACTIVE
        defaultProductShouldNotBeFound("isActive.in=" + UPDATED_IS_ACTIVE);
    }

    @Test
    @Transactional
    void getAllProductsByIsActiveIsNullOrNotNull() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where isActive is not null
        defaultProductShouldBeFound("isActive.specified=true");

        // Get all the productList where isActive is null
        defaultProductShouldNotBeFound("isActive.specified=false");
    }

    @Test
    @Transactional
    void getAllProductsByProductNameIsEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where productName equals to DEFAULT_PRODUCT_NAME
        defaultProductShouldBeFound("productName.equals=" + DEFAULT_PRODUCT_NAME);

        // Get all the productList where productName equals to UPDATED_PRODUCT_NAME
        defaultProductShouldNotBeFound("productName.equals=" + UPDATED_PRODUCT_NAME);
    }

    @Test
    @Transactional
    void getAllProductsByProductNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where productName not equals to DEFAULT_PRODUCT_NAME
        defaultProductShouldNotBeFound("productName.notEquals=" + DEFAULT_PRODUCT_NAME);

        // Get all the productList where productName not equals to UPDATED_PRODUCT_NAME
        defaultProductShouldBeFound("productName.notEquals=" + UPDATED_PRODUCT_NAME);
    }

    @Test
    @Transactional
    void getAllProductsByProductNameIsInShouldWork() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where productName in DEFAULT_PRODUCT_NAME or UPDATED_PRODUCT_NAME
        defaultProductShouldBeFound("productName.in=" + DEFAULT_PRODUCT_NAME + "," + UPDATED_PRODUCT_NAME);

        // Get all the productList where productName equals to UPDATED_PRODUCT_NAME
        defaultProductShouldNotBeFound("productName.in=" + UPDATED_PRODUCT_NAME);
    }

    @Test
    @Transactional
    void getAllProductsByProductNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where productName is not null
        defaultProductShouldBeFound("productName.specified=true");

        // Get all the productList where productName is null
        defaultProductShouldNotBeFound("productName.specified=false");
    }

    @Test
    @Transactional
    void getAllProductsByProductNameContainsSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where productName contains DEFAULT_PRODUCT_NAME
        defaultProductShouldBeFound("productName.contains=" + DEFAULT_PRODUCT_NAME);

        // Get all the productList where productName contains UPDATED_PRODUCT_NAME
        defaultProductShouldNotBeFound("productName.contains=" + UPDATED_PRODUCT_NAME);
    }

    @Test
    @Transactional
    void getAllProductsByProductNameNotContainsSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where productName does not contain DEFAULT_PRODUCT_NAME
        defaultProductShouldNotBeFound("productName.doesNotContain=" + DEFAULT_PRODUCT_NAME);

        // Get all the productList where productName does not contain UPDATED_PRODUCT_NAME
        defaultProductShouldBeFound("productName.doesNotContain=" + UPDATED_PRODUCT_NAME);
    }

    @Test
    @Transactional
    void getAllProductsByAlertUnitsIsEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where alertUnits equals to DEFAULT_ALERT_UNITS
        defaultProductShouldBeFound("alertUnits.equals=" + DEFAULT_ALERT_UNITS);

        // Get all the productList where alertUnits equals to UPDATED_ALERT_UNITS
        defaultProductShouldNotBeFound("alertUnits.equals=" + UPDATED_ALERT_UNITS);
    }

    @Test
    @Transactional
    void getAllProductsByAlertUnitsIsNotEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where alertUnits not equals to DEFAULT_ALERT_UNITS
        defaultProductShouldNotBeFound("alertUnits.notEquals=" + DEFAULT_ALERT_UNITS);

        // Get all the productList where alertUnits not equals to UPDATED_ALERT_UNITS
        defaultProductShouldBeFound("alertUnits.notEquals=" + UPDATED_ALERT_UNITS);
    }

    @Test
    @Transactional
    void getAllProductsByAlertUnitsIsInShouldWork() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where alertUnits in DEFAULT_ALERT_UNITS or UPDATED_ALERT_UNITS
        defaultProductShouldBeFound("alertUnits.in=" + DEFAULT_ALERT_UNITS + "," + UPDATED_ALERT_UNITS);

        // Get all the productList where alertUnits equals to UPDATED_ALERT_UNITS
        defaultProductShouldNotBeFound("alertUnits.in=" + UPDATED_ALERT_UNITS);
    }

    @Test
    @Transactional
    void getAllProductsByAlertUnitsIsNullOrNotNull() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where alertUnits is not null
        defaultProductShouldBeFound("alertUnits.specified=true");

        // Get all the productList where alertUnits is null
        defaultProductShouldNotBeFound("alertUnits.specified=false");
    }

    @Test
    @Transactional
    void getAllProductsByAlertUnitsContainsSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where alertUnits contains DEFAULT_ALERT_UNITS
        defaultProductShouldBeFound("alertUnits.contains=" + DEFAULT_ALERT_UNITS);

        // Get all the productList where alertUnits contains UPDATED_ALERT_UNITS
        defaultProductShouldNotBeFound("alertUnits.contains=" + UPDATED_ALERT_UNITS);
    }

    @Test
    @Transactional
    void getAllProductsByAlertUnitsNotContainsSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where alertUnits does not contain DEFAULT_ALERT_UNITS
        defaultProductShouldNotBeFound("alertUnits.doesNotContain=" + DEFAULT_ALERT_UNITS);

        // Get all the productList where alertUnits does not contain UPDATED_ALERT_UNITS
        defaultProductShouldBeFound("alertUnits.doesNotContain=" + UPDATED_ALERT_UNITS);
    }

    @Test
    @Transactional
    void getAllProductsByDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where description equals to DEFAULT_DESCRIPTION
        defaultProductShouldBeFound("description.equals=" + DEFAULT_DESCRIPTION);

        // Get all the productList where description equals to UPDATED_DESCRIPTION
        defaultProductShouldNotBeFound("description.equals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    void getAllProductsByDescriptionIsNotEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where description not equals to DEFAULT_DESCRIPTION
        defaultProductShouldNotBeFound("description.notEquals=" + DEFAULT_DESCRIPTION);

        // Get all the productList where description not equals to UPDATED_DESCRIPTION
        defaultProductShouldBeFound("description.notEquals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    void getAllProductsByDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where description in DEFAULT_DESCRIPTION or UPDATED_DESCRIPTION
        defaultProductShouldBeFound("description.in=" + DEFAULT_DESCRIPTION + "," + UPDATED_DESCRIPTION);

        // Get all the productList where description equals to UPDATED_DESCRIPTION
        defaultProductShouldNotBeFound("description.in=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    void getAllProductsByDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where description is not null
        defaultProductShouldBeFound("description.specified=true");

        // Get all the productList where description is null
        defaultProductShouldNotBeFound("description.specified=false");
    }

    @Test
    @Transactional
    void getAllProductsByDescriptionContainsSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where description contains DEFAULT_DESCRIPTION
        defaultProductShouldBeFound("description.contains=" + DEFAULT_DESCRIPTION);

        // Get all the productList where description contains UPDATED_DESCRIPTION
        defaultProductShouldNotBeFound("description.contains=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    void getAllProductsByDescriptionNotContainsSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where description does not contain DEFAULT_DESCRIPTION
        defaultProductShouldNotBeFound("description.doesNotContain=" + DEFAULT_DESCRIPTION);

        // Get all the productList where description does not contain UPDATED_DESCRIPTION
        defaultProductShouldBeFound("description.doesNotContain=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    void getAllProductsByBasePriceIsEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where basePrice equals to DEFAULT_BASE_PRICE
        defaultProductShouldBeFound("basePrice.equals=" + DEFAULT_BASE_PRICE);

        // Get all the productList where basePrice equals to UPDATED_BASE_PRICE
        defaultProductShouldNotBeFound("basePrice.equals=" + UPDATED_BASE_PRICE);
    }

    @Test
    @Transactional
    void getAllProductsByBasePriceIsNotEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where basePrice not equals to DEFAULT_BASE_PRICE
        defaultProductShouldNotBeFound("basePrice.notEquals=" + DEFAULT_BASE_PRICE);

        // Get all the productList where basePrice not equals to UPDATED_BASE_PRICE
        defaultProductShouldBeFound("basePrice.notEquals=" + UPDATED_BASE_PRICE);
    }

    @Test
    @Transactional
    void getAllProductsByBasePriceIsInShouldWork() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where basePrice in DEFAULT_BASE_PRICE or UPDATED_BASE_PRICE
        defaultProductShouldBeFound("basePrice.in=" + DEFAULT_BASE_PRICE + "," + UPDATED_BASE_PRICE);

        // Get all the productList where basePrice equals to UPDATED_BASE_PRICE
        defaultProductShouldNotBeFound("basePrice.in=" + UPDATED_BASE_PRICE);
    }

    @Test
    @Transactional
    void getAllProductsByBasePriceIsNullOrNotNull() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where basePrice is not null
        defaultProductShouldBeFound("basePrice.specified=true");

        // Get all the productList where basePrice is null
        defaultProductShouldNotBeFound("basePrice.specified=false");
    }

    @Test
    @Transactional
    void getAllProductsByBasePriceIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where basePrice is greater than or equal to DEFAULT_BASE_PRICE
        defaultProductShouldBeFound("basePrice.greaterThanOrEqual=" + DEFAULT_BASE_PRICE);

        // Get all the productList where basePrice is greater than or equal to UPDATED_BASE_PRICE
        defaultProductShouldNotBeFound("basePrice.greaterThanOrEqual=" + UPDATED_BASE_PRICE);
    }

    @Test
    @Transactional
    void getAllProductsByBasePriceIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where basePrice is less than or equal to DEFAULT_BASE_PRICE
        defaultProductShouldBeFound("basePrice.lessThanOrEqual=" + DEFAULT_BASE_PRICE);

        // Get all the productList where basePrice is less than or equal to SMALLER_BASE_PRICE
        defaultProductShouldNotBeFound("basePrice.lessThanOrEqual=" + SMALLER_BASE_PRICE);
    }

    @Test
    @Transactional
    void getAllProductsByBasePriceIsLessThanSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where basePrice is less than DEFAULT_BASE_PRICE
        defaultProductShouldNotBeFound("basePrice.lessThan=" + DEFAULT_BASE_PRICE);

        // Get all the productList where basePrice is less than UPDATED_BASE_PRICE
        defaultProductShouldBeFound("basePrice.lessThan=" + UPDATED_BASE_PRICE);
    }

    @Test
    @Transactional
    void getAllProductsByBasePriceIsGreaterThanSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where basePrice is greater than DEFAULT_BASE_PRICE
        defaultProductShouldNotBeFound("basePrice.greaterThan=" + DEFAULT_BASE_PRICE);

        // Get all the productList where basePrice is greater than SMALLER_BASE_PRICE
        defaultProductShouldBeFound("basePrice.greaterThan=" + SMALLER_BASE_PRICE);
    }

    @Test
    @Transactional
    void getAllProductsByQualityIsEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where quality equals to DEFAULT_QUALITY
        defaultProductShouldBeFound("quality.equals=" + DEFAULT_QUALITY);

        // Get all the productList where quality equals to UPDATED_QUALITY
        defaultProductShouldNotBeFound("quality.equals=" + UPDATED_QUALITY);
    }

    @Test
    @Transactional
    void getAllProductsByQualityIsNotEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where quality not equals to DEFAULT_QUALITY
        defaultProductShouldNotBeFound("quality.notEquals=" + DEFAULT_QUALITY);

        // Get all the productList where quality not equals to UPDATED_QUALITY
        defaultProductShouldBeFound("quality.notEquals=" + UPDATED_QUALITY);
    }

    @Test
    @Transactional
    void getAllProductsByQualityIsInShouldWork() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where quality in DEFAULT_QUALITY or UPDATED_QUALITY
        defaultProductShouldBeFound("quality.in=" + DEFAULT_QUALITY + "," + UPDATED_QUALITY);

        // Get all the productList where quality equals to UPDATED_QUALITY
        defaultProductShouldNotBeFound("quality.in=" + UPDATED_QUALITY);
    }

    @Test
    @Transactional
    void getAllProductsByQualityIsNullOrNotNull() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where quality is not null
        defaultProductShouldBeFound("quality.specified=true");

        // Get all the productList where quality is null
        defaultProductShouldNotBeFound("quality.specified=false");
    }

    @Test
    @Transactional
    void getAllProductsByQualityContainsSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where quality contains DEFAULT_QUALITY
        defaultProductShouldBeFound("quality.contains=" + DEFAULT_QUALITY);

        // Get all the productList where quality contains UPDATED_QUALITY
        defaultProductShouldNotBeFound("quality.contains=" + UPDATED_QUALITY);
    }

    @Test
    @Transactional
    void getAllProductsByQualityNotContainsSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where quality does not contain DEFAULT_QUALITY
        defaultProductShouldNotBeFound("quality.doesNotContain=" + DEFAULT_QUALITY);

        // Get all the productList where quality does not contain UPDATED_QUALITY
        defaultProductShouldBeFound("quality.doesNotContain=" + UPDATED_QUALITY);
    }

    @Test
    @Transactional
    void getAllProductsByQrCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where qrCode equals to DEFAULT_QR_CODE
        defaultProductShouldBeFound("qrCode.equals=" + DEFAULT_QR_CODE);

        // Get all the productList where qrCode equals to UPDATED_QR_CODE
        defaultProductShouldNotBeFound("qrCode.equals=" + UPDATED_QR_CODE);
    }

    @Test
    @Transactional
    void getAllProductsByQrCodeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where qrCode not equals to DEFAULT_QR_CODE
        defaultProductShouldNotBeFound("qrCode.notEquals=" + DEFAULT_QR_CODE);

        // Get all the productList where qrCode not equals to UPDATED_QR_CODE
        defaultProductShouldBeFound("qrCode.notEquals=" + UPDATED_QR_CODE);
    }

    @Test
    @Transactional
    void getAllProductsByQrCodeIsInShouldWork() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where qrCode in DEFAULT_QR_CODE or UPDATED_QR_CODE
        defaultProductShouldBeFound("qrCode.in=" + DEFAULT_QR_CODE + "," + UPDATED_QR_CODE);

        // Get all the productList where qrCode equals to UPDATED_QR_CODE
        defaultProductShouldNotBeFound("qrCode.in=" + UPDATED_QR_CODE);
    }

    @Test
    @Transactional
    void getAllProductsByQrCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where qrCode is not null
        defaultProductShouldBeFound("qrCode.specified=true");

        // Get all the productList where qrCode is null
        defaultProductShouldNotBeFound("qrCode.specified=false");
    }

    @Test
    @Transactional
    void getAllProductsByQrCodeContainsSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where qrCode contains DEFAULT_QR_CODE
        defaultProductShouldBeFound("qrCode.contains=" + DEFAULT_QR_CODE);

        // Get all the productList where qrCode contains UPDATED_QR_CODE
        defaultProductShouldNotBeFound("qrCode.contains=" + UPDATED_QR_CODE);
    }

    @Test
    @Transactional
    void getAllProductsByQrCodeNotContainsSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where qrCode does not contain DEFAULT_QR_CODE
        defaultProductShouldNotBeFound("qrCode.doesNotContain=" + DEFAULT_QR_CODE);

        // Get all the productList where qrCode does not contain UPDATED_QR_CODE
        defaultProductShouldBeFound("qrCode.doesNotContain=" + UPDATED_QR_CODE);
    }

    @Test
    @Transactional
    void getAllProductsByBarCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where barCode equals to DEFAULT_BAR_CODE
        defaultProductShouldBeFound("barCode.equals=" + DEFAULT_BAR_CODE);

        // Get all the productList where barCode equals to UPDATED_BAR_CODE
        defaultProductShouldNotBeFound("barCode.equals=" + UPDATED_BAR_CODE);
    }

    @Test
    @Transactional
    void getAllProductsByBarCodeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where barCode not equals to DEFAULT_BAR_CODE
        defaultProductShouldNotBeFound("barCode.notEquals=" + DEFAULT_BAR_CODE);

        // Get all the productList where barCode not equals to UPDATED_BAR_CODE
        defaultProductShouldBeFound("barCode.notEquals=" + UPDATED_BAR_CODE);
    }

    @Test
    @Transactional
    void getAllProductsByBarCodeIsInShouldWork() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where barCode in DEFAULT_BAR_CODE or UPDATED_BAR_CODE
        defaultProductShouldBeFound("barCode.in=" + DEFAULT_BAR_CODE + "," + UPDATED_BAR_CODE);

        // Get all the productList where barCode equals to UPDATED_BAR_CODE
        defaultProductShouldNotBeFound("barCode.in=" + UPDATED_BAR_CODE);
    }

    @Test
    @Transactional
    void getAllProductsByBarCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where barCode is not null
        defaultProductShouldBeFound("barCode.specified=true");

        // Get all the productList where barCode is null
        defaultProductShouldNotBeFound("barCode.specified=false");
    }

    @Test
    @Transactional
    void getAllProductsByBarCodeContainsSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where barCode contains DEFAULT_BAR_CODE
        defaultProductShouldBeFound("barCode.contains=" + DEFAULT_BAR_CODE);

        // Get all the productList where barCode contains UPDATED_BAR_CODE
        defaultProductShouldNotBeFound("barCode.contains=" + UPDATED_BAR_CODE);
    }

    @Test
    @Transactional
    void getAllProductsByBarCodeNotContainsSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where barCode does not contain DEFAULT_BAR_CODE
        defaultProductShouldNotBeFound("barCode.doesNotContain=" + DEFAULT_BAR_CODE);

        // Get all the productList where barCode does not contain UPDATED_BAR_CODE
        defaultProductShouldBeFound("barCode.doesNotContain=" + UPDATED_BAR_CODE);
    }

    @Test
    @Transactional
    void getAllProductsByGstPercentageIsEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where gstPercentage equals to DEFAULT_GST_PERCENTAGE
        defaultProductShouldBeFound("gstPercentage.equals=" + DEFAULT_GST_PERCENTAGE);

        // Get all the productList where gstPercentage equals to UPDATED_GST_PERCENTAGE
        defaultProductShouldNotBeFound("gstPercentage.equals=" + UPDATED_GST_PERCENTAGE);
    }

    @Test
    @Transactional
    void getAllProductsByGstPercentageIsNotEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where gstPercentage not equals to DEFAULT_GST_PERCENTAGE
        defaultProductShouldNotBeFound("gstPercentage.notEquals=" + DEFAULT_GST_PERCENTAGE);

        // Get all the productList where gstPercentage not equals to UPDATED_GST_PERCENTAGE
        defaultProductShouldBeFound("gstPercentage.notEquals=" + UPDATED_GST_PERCENTAGE);
    }

    @Test
    @Transactional
    void getAllProductsByGstPercentageIsInShouldWork() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where gstPercentage in DEFAULT_GST_PERCENTAGE or UPDATED_GST_PERCENTAGE
        defaultProductShouldBeFound("gstPercentage.in=" + DEFAULT_GST_PERCENTAGE + "," + UPDATED_GST_PERCENTAGE);

        // Get all the productList where gstPercentage equals to UPDATED_GST_PERCENTAGE
        defaultProductShouldNotBeFound("gstPercentage.in=" + UPDATED_GST_PERCENTAGE);
    }

    @Test
    @Transactional
    void getAllProductsByGstPercentageIsNullOrNotNull() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where gstPercentage is not null
        defaultProductShouldBeFound("gstPercentage.specified=true");

        // Get all the productList where gstPercentage is null
        defaultProductShouldNotBeFound("gstPercentage.specified=false");
    }

    @Test
    @Transactional
    void getAllProductsByGstPercentageIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where gstPercentage is greater than or equal to DEFAULT_GST_PERCENTAGE
        defaultProductShouldBeFound("gstPercentage.greaterThanOrEqual=" + DEFAULT_GST_PERCENTAGE);

        // Get all the productList where gstPercentage is greater than or equal to UPDATED_GST_PERCENTAGE
        defaultProductShouldNotBeFound("gstPercentage.greaterThanOrEqual=" + UPDATED_GST_PERCENTAGE);
    }

    @Test
    @Transactional
    void getAllProductsByGstPercentageIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where gstPercentage is less than or equal to DEFAULT_GST_PERCENTAGE
        defaultProductShouldBeFound("gstPercentage.lessThanOrEqual=" + DEFAULT_GST_PERCENTAGE);

        // Get all the productList where gstPercentage is less than or equal to SMALLER_GST_PERCENTAGE
        defaultProductShouldNotBeFound("gstPercentage.lessThanOrEqual=" + SMALLER_GST_PERCENTAGE);
    }

    @Test
    @Transactional
    void getAllProductsByGstPercentageIsLessThanSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where gstPercentage is less than DEFAULT_GST_PERCENTAGE
        defaultProductShouldNotBeFound("gstPercentage.lessThan=" + DEFAULT_GST_PERCENTAGE);

        // Get all the productList where gstPercentage is less than UPDATED_GST_PERCENTAGE
        defaultProductShouldBeFound("gstPercentage.lessThan=" + UPDATED_GST_PERCENTAGE);
    }

    @Test
    @Transactional
    void getAllProductsByGstPercentageIsGreaterThanSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where gstPercentage is greater than DEFAULT_GST_PERCENTAGE
        defaultProductShouldNotBeFound("gstPercentage.greaterThan=" + DEFAULT_GST_PERCENTAGE);

        // Get all the productList where gstPercentage is greater than SMALLER_GST_PERCENTAGE
        defaultProductShouldBeFound("gstPercentage.greaterThan=" + SMALLER_GST_PERCENTAGE);
    }

    @Test
    @Transactional
    void getAllProductsByProductTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where productType equals to DEFAULT_PRODUCT_TYPE
        defaultProductShouldBeFound("productType.equals=" + DEFAULT_PRODUCT_TYPE);

        // Get all the productList where productType equals to UPDATED_PRODUCT_TYPE
        defaultProductShouldNotBeFound("productType.equals=" + UPDATED_PRODUCT_TYPE);
    }

    @Test
    @Transactional
    void getAllProductsByProductTypeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where productType not equals to DEFAULT_PRODUCT_TYPE
        defaultProductShouldNotBeFound("productType.notEquals=" + DEFAULT_PRODUCT_TYPE);

        // Get all the productList where productType not equals to UPDATED_PRODUCT_TYPE
        defaultProductShouldBeFound("productType.notEquals=" + UPDATED_PRODUCT_TYPE);
    }

    @Test
    @Transactional
    void getAllProductsByProductTypeIsInShouldWork() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where productType in DEFAULT_PRODUCT_TYPE or UPDATED_PRODUCT_TYPE
        defaultProductShouldBeFound("productType.in=" + DEFAULT_PRODUCT_TYPE + "," + UPDATED_PRODUCT_TYPE);

        // Get all the productList where productType equals to UPDATED_PRODUCT_TYPE
        defaultProductShouldNotBeFound("productType.in=" + UPDATED_PRODUCT_TYPE);
    }

    @Test
    @Transactional
    void getAllProductsByProductTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where productType is not null
        defaultProductShouldBeFound("productType.specified=true");

        // Get all the productList where productType is null
        defaultProductShouldNotBeFound("productType.specified=false");
    }

    @Test
    @Transactional
    void getAllProductsByLastModifiedIsEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where lastModified equals to DEFAULT_LAST_MODIFIED
        defaultProductShouldBeFound("lastModified.equals=" + DEFAULT_LAST_MODIFIED);

        // Get all the productList where lastModified equals to UPDATED_LAST_MODIFIED
        defaultProductShouldNotBeFound("lastModified.equals=" + UPDATED_LAST_MODIFIED);
    }

    @Test
    @Transactional
    void getAllProductsByLastModifiedIsNotEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where lastModified not equals to DEFAULT_LAST_MODIFIED
        defaultProductShouldNotBeFound("lastModified.notEquals=" + DEFAULT_LAST_MODIFIED);

        // Get all the productList where lastModified not equals to UPDATED_LAST_MODIFIED
        defaultProductShouldBeFound("lastModified.notEquals=" + UPDATED_LAST_MODIFIED);
    }

    @Test
    @Transactional
    void getAllProductsByLastModifiedIsInShouldWork() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where lastModified in DEFAULT_LAST_MODIFIED or UPDATED_LAST_MODIFIED
        defaultProductShouldBeFound("lastModified.in=" + DEFAULT_LAST_MODIFIED + "," + UPDATED_LAST_MODIFIED);

        // Get all the productList where lastModified equals to UPDATED_LAST_MODIFIED
        defaultProductShouldNotBeFound("lastModified.in=" + UPDATED_LAST_MODIFIED);
    }

    @Test
    @Transactional
    void getAllProductsByLastModifiedIsNullOrNotNull() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where lastModified is not null
        defaultProductShouldBeFound("lastModified.specified=true");

        // Get all the productList where lastModified is null
        defaultProductShouldNotBeFound("lastModified.specified=false");
    }

    @Test
    @Transactional
    void getAllProductsByLastModifiedContainsSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where lastModified contains DEFAULT_LAST_MODIFIED
        defaultProductShouldBeFound("lastModified.contains=" + DEFAULT_LAST_MODIFIED);

        // Get all the productList where lastModified contains UPDATED_LAST_MODIFIED
        defaultProductShouldNotBeFound("lastModified.contains=" + UPDATED_LAST_MODIFIED);
    }

    @Test
    @Transactional
    void getAllProductsByLastModifiedNotContainsSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where lastModified does not contain DEFAULT_LAST_MODIFIED
        defaultProductShouldNotBeFound("lastModified.doesNotContain=" + DEFAULT_LAST_MODIFIED);

        // Get all the productList where lastModified does not contain UPDATED_LAST_MODIFIED
        defaultProductShouldBeFound("lastModified.doesNotContain=" + UPDATED_LAST_MODIFIED);
    }

    @Test
    @Transactional
    void getAllProductsByLastModifiedByIsEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where lastModifiedBy equals to DEFAULT_LAST_MODIFIED_BY
        defaultProductShouldBeFound("lastModifiedBy.equals=" + DEFAULT_LAST_MODIFIED_BY);

        // Get all the productList where lastModifiedBy equals to UPDATED_LAST_MODIFIED_BY
        defaultProductShouldNotBeFound("lastModifiedBy.equals=" + UPDATED_LAST_MODIFIED_BY);
    }

    @Test
    @Transactional
    void getAllProductsByLastModifiedByIsNotEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where lastModifiedBy not equals to DEFAULT_LAST_MODIFIED_BY
        defaultProductShouldNotBeFound("lastModifiedBy.notEquals=" + DEFAULT_LAST_MODIFIED_BY);

        // Get all the productList where lastModifiedBy not equals to UPDATED_LAST_MODIFIED_BY
        defaultProductShouldBeFound("lastModifiedBy.notEquals=" + UPDATED_LAST_MODIFIED_BY);
    }

    @Test
    @Transactional
    void getAllProductsByLastModifiedByIsInShouldWork() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where lastModifiedBy in DEFAULT_LAST_MODIFIED_BY or UPDATED_LAST_MODIFIED_BY
        defaultProductShouldBeFound("lastModifiedBy.in=" + DEFAULT_LAST_MODIFIED_BY + "," + UPDATED_LAST_MODIFIED_BY);

        // Get all the productList where lastModifiedBy equals to UPDATED_LAST_MODIFIED_BY
        defaultProductShouldNotBeFound("lastModifiedBy.in=" + UPDATED_LAST_MODIFIED_BY);
    }

    @Test
    @Transactional
    void getAllProductsByLastModifiedByIsNullOrNotNull() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where lastModifiedBy is not null
        defaultProductShouldBeFound("lastModifiedBy.specified=true");

        // Get all the productList where lastModifiedBy is null
        defaultProductShouldNotBeFound("lastModifiedBy.specified=false");
    }

    @Test
    @Transactional
    void getAllProductsByLastModifiedByContainsSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where lastModifiedBy contains DEFAULT_LAST_MODIFIED_BY
        defaultProductShouldBeFound("lastModifiedBy.contains=" + DEFAULT_LAST_MODIFIED_BY);

        // Get all the productList where lastModifiedBy contains UPDATED_LAST_MODIFIED_BY
        defaultProductShouldNotBeFound("lastModifiedBy.contains=" + UPDATED_LAST_MODIFIED_BY);
    }

    @Test
    @Transactional
    void getAllProductsByLastModifiedByNotContainsSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where lastModifiedBy does not contain DEFAULT_LAST_MODIFIED_BY
        defaultProductShouldNotBeFound("lastModifiedBy.doesNotContain=" + DEFAULT_LAST_MODIFIED_BY);

        // Get all the productList where lastModifiedBy does not contain UPDATED_LAST_MODIFIED_BY
        defaultProductShouldBeFound("lastModifiedBy.doesNotContain=" + UPDATED_LAST_MODIFIED_BY);
    }

    @Test
    @Transactional
    void getAllProductsByFreeField1IsEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where freeField1 equals to DEFAULT_FREE_FIELD_1
        defaultProductShouldBeFound("freeField1.equals=" + DEFAULT_FREE_FIELD_1);

        // Get all the productList where freeField1 equals to UPDATED_FREE_FIELD_1
        defaultProductShouldNotBeFound("freeField1.equals=" + UPDATED_FREE_FIELD_1);
    }

    @Test
    @Transactional
    void getAllProductsByFreeField1IsNotEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where freeField1 not equals to DEFAULT_FREE_FIELD_1
        defaultProductShouldNotBeFound("freeField1.notEquals=" + DEFAULT_FREE_FIELD_1);

        // Get all the productList where freeField1 not equals to UPDATED_FREE_FIELD_1
        defaultProductShouldBeFound("freeField1.notEquals=" + UPDATED_FREE_FIELD_1);
    }

    @Test
    @Transactional
    void getAllProductsByFreeField1IsInShouldWork() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where freeField1 in DEFAULT_FREE_FIELD_1 or UPDATED_FREE_FIELD_1
        defaultProductShouldBeFound("freeField1.in=" + DEFAULT_FREE_FIELD_1 + "," + UPDATED_FREE_FIELD_1);

        // Get all the productList where freeField1 equals to UPDATED_FREE_FIELD_1
        defaultProductShouldNotBeFound("freeField1.in=" + UPDATED_FREE_FIELD_1);
    }

    @Test
    @Transactional
    void getAllProductsByFreeField1IsNullOrNotNull() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where freeField1 is not null
        defaultProductShouldBeFound("freeField1.specified=true");

        // Get all the productList where freeField1 is null
        defaultProductShouldNotBeFound("freeField1.specified=false");
    }

    @Test
    @Transactional
    void getAllProductsByFreeField1ContainsSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where freeField1 contains DEFAULT_FREE_FIELD_1
        defaultProductShouldBeFound("freeField1.contains=" + DEFAULT_FREE_FIELD_1);

        // Get all the productList where freeField1 contains UPDATED_FREE_FIELD_1
        defaultProductShouldNotBeFound("freeField1.contains=" + UPDATED_FREE_FIELD_1);
    }

    @Test
    @Transactional
    void getAllProductsByFreeField1NotContainsSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where freeField1 does not contain DEFAULT_FREE_FIELD_1
        defaultProductShouldNotBeFound("freeField1.doesNotContain=" + DEFAULT_FREE_FIELD_1);

        // Get all the productList where freeField1 does not contain UPDATED_FREE_FIELD_1
        defaultProductShouldBeFound("freeField1.doesNotContain=" + UPDATED_FREE_FIELD_1);
    }

    @Test
    @Transactional
    void getAllProductsByFreeField2IsEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where freeField2 equals to DEFAULT_FREE_FIELD_2
        defaultProductShouldBeFound("freeField2.equals=" + DEFAULT_FREE_FIELD_2);

        // Get all the productList where freeField2 equals to UPDATED_FREE_FIELD_2
        defaultProductShouldNotBeFound("freeField2.equals=" + UPDATED_FREE_FIELD_2);
    }

    @Test
    @Transactional
    void getAllProductsByFreeField2IsNotEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where freeField2 not equals to DEFAULT_FREE_FIELD_2
        defaultProductShouldNotBeFound("freeField2.notEquals=" + DEFAULT_FREE_FIELD_2);

        // Get all the productList where freeField2 not equals to UPDATED_FREE_FIELD_2
        defaultProductShouldBeFound("freeField2.notEquals=" + UPDATED_FREE_FIELD_2);
    }

    @Test
    @Transactional
    void getAllProductsByFreeField2IsInShouldWork() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where freeField2 in DEFAULT_FREE_FIELD_2 or UPDATED_FREE_FIELD_2
        defaultProductShouldBeFound("freeField2.in=" + DEFAULT_FREE_FIELD_2 + "," + UPDATED_FREE_FIELD_2);

        // Get all the productList where freeField2 equals to UPDATED_FREE_FIELD_2
        defaultProductShouldNotBeFound("freeField2.in=" + UPDATED_FREE_FIELD_2);
    }

    @Test
    @Transactional
    void getAllProductsByFreeField2IsNullOrNotNull() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where freeField2 is not null
        defaultProductShouldBeFound("freeField2.specified=true");

        // Get all the productList where freeField2 is null
        defaultProductShouldNotBeFound("freeField2.specified=false");
    }

    @Test
    @Transactional
    void getAllProductsByFreeField2ContainsSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where freeField2 contains DEFAULT_FREE_FIELD_2
        defaultProductShouldBeFound("freeField2.contains=" + DEFAULT_FREE_FIELD_2);

        // Get all the productList where freeField2 contains UPDATED_FREE_FIELD_2
        defaultProductShouldNotBeFound("freeField2.contains=" + UPDATED_FREE_FIELD_2);
    }

    @Test
    @Transactional
    void getAllProductsByFreeField2NotContainsSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where freeField2 does not contain DEFAULT_FREE_FIELD_2
        defaultProductShouldNotBeFound("freeField2.doesNotContain=" + DEFAULT_FREE_FIELD_2);

        // Get all the productList where freeField2 does not contain UPDATED_FREE_FIELD_2
        defaultProductShouldBeFound("freeField2.doesNotContain=" + UPDATED_FREE_FIELD_2);
    }

    @Test
    @Transactional
    void getAllProductsByFreeField3IsEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where freeField3 equals to DEFAULT_FREE_FIELD_3
        defaultProductShouldBeFound("freeField3.equals=" + DEFAULT_FREE_FIELD_3);

        // Get all the productList where freeField3 equals to UPDATED_FREE_FIELD_3
        defaultProductShouldNotBeFound("freeField3.equals=" + UPDATED_FREE_FIELD_3);
    }

    @Test
    @Transactional
    void getAllProductsByFreeField3IsNotEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where freeField3 not equals to DEFAULT_FREE_FIELD_3
        defaultProductShouldNotBeFound("freeField3.notEquals=" + DEFAULT_FREE_FIELD_3);

        // Get all the productList where freeField3 not equals to UPDATED_FREE_FIELD_3
        defaultProductShouldBeFound("freeField3.notEquals=" + UPDATED_FREE_FIELD_3);
    }

    @Test
    @Transactional
    void getAllProductsByFreeField3IsInShouldWork() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where freeField3 in DEFAULT_FREE_FIELD_3 or UPDATED_FREE_FIELD_3
        defaultProductShouldBeFound("freeField3.in=" + DEFAULT_FREE_FIELD_3 + "," + UPDATED_FREE_FIELD_3);

        // Get all the productList where freeField3 equals to UPDATED_FREE_FIELD_3
        defaultProductShouldNotBeFound("freeField3.in=" + UPDATED_FREE_FIELD_3);
    }

    @Test
    @Transactional
    void getAllProductsByFreeField3IsNullOrNotNull() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where freeField3 is not null
        defaultProductShouldBeFound("freeField3.specified=true");

        // Get all the productList where freeField3 is null
        defaultProductShouldNotBeFound("freeField3.specified=false");
    }

    @Test
    @Transactional
    void getAllProductsByFreeField3ContainsSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where freeField3 contains DEFAULT_FREE_FIELD_3
        defaultProductShouldBeFound("freeField3.contains=" + DEFAULT_FREE_FIELD_3);

        // Get all the productList where freeField3 contains UPDATED_FREE_FIELD_3
        defaultProductShouldNotBeFound("freeField3.contains=" + UPDATED_FREE_FIELD_3);
    }

    @Test
    @Transactional
    void getAllProductsByFreeField3NotContainsSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where freeField3 does not contain DEFAULT_FREE_FIELD_3
        defaultProductShouldNotBeFound("freeField3.doesNotContain=" + DEFAULT_FREE_FIELD_3);

        // Get all the productList where freeField3 does not contain UPDATED_FREE_FIELD_3
        defaultProductShouldBeFound("freeField3.doesNotContain=" + UPDATED_FREE_FIELD_3);
    }

    @Test
    @Transactional
    void getAllProductsByFreeField4IsEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where freeField4 equals to DEFAULT_FREE_FIELD_4
        defaultProductShouldBeFound("freeField4.equals=" + DEFAULT_FREE_FIELD_4);

        // Get all the productList where freeField4 equals to UPDATED_FREE_FIELD_4
        defaultProductShouldNotBeFound("freeField4.equals=" + UPDATED_FREE_FIELD_4);
    }

    @Test
    @Transactional
    void getAllProductsByFreeField4IsNotEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where freeField4 not equals to DEFAULT_FREE_FIELD_4
        defaultProductShouldNotBeFound("freeField4.notEquals=" + DEFAULT_FREE_FIELD_4);

        // Get all the productList where freeField4 not equals to UPDATED_FREE_FIELD_4
        defaultProductShouldBeFound("freeField4.notEquals=" + UPDATED_FREE_FIELD_4);
    }

    @Test
    @Transactional
    void getAllProductsByFreeField4IsInShouldWork() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where freeField4 in DEFAULT_FREE_FIELD_4 or UPDATED_FREE_FIELD_4
        defaultProductShouldBeFound("freeField4.in=" + DEFAULT_FREE_FIELD_4 + "," + UPDATED_FREE_FIELD_4);

        // Get all the productList where freeField4 equals to UPDATED_FREE_FIELD_4
        defaultProductShouldNotBeFound("freeField4.in=" + UPDATED_FREE_FIELD_4);
    }

    @Test
    @Transactional
    void getAllProductsByFreeField4IsNullOrNotNull() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where freeField4 is not null
        defaultProductShouldBeFound("freeField4.specified=true");

        // Get all the productList where freeField4 is null
        defaultProductShouldNotBeFound("freeField4.specified=false");
    }

    @Test
    @Transactional
    void getAllProductsByFreeField4ContainsSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where freeField4 contains DEFAULT_FREE_FIELD_4
        defaultProductShouldBeFound("freeField4.contains=" + DEFAULT_FREE_FIELD_4);

        // Get all the productList where freeField4 contains UPDATED_FREE_FIELD_4
        defaultProductShouldNotBeFound("freeField4.contains=" + UPDATED_FREE_FIELD_4);
    }

    @Test
    @Transactional
    void getAllProductsByFreeField4NotContainsSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList where freeField4 does not contain DEFAULT_FREE_FIELD_4
        defaultProductShouldNotBeFound("freeField4.doesNotContain=" + DEFAULT_FREE_FIELD_4);

        // Get all the productList where freeField4 does not contain UPDATED_FREE_FIELD_4
        defaultProductShouldBeFound("freeField4.doesNotContain=" + UPDATED_FREE_FIELD_4);
    }

    @Test
    @Transactional
    void getAllProductsByTransferDetailsIsEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);
        TransferDetails transferDetails;
        if (TestUtil.findAll(em, TransferDetails.class).isEmpty()) {
            transferDetails = TransferDetailsResourceIT.createEntity(em);
            em.persist(transferDetails);
            em.flush();
        } else {
            transferDetails = TestUtil.findAll(em, TransferDetails.class).get(0);
        }
        em.persist(transferDetails);
        em.flush();
        product.addTransferDetails(transferDetails);
        productRepository.saveAndFlush(product);
        Long transferDetailsId = transferDetails.getId();

        // Get all the productList where transferDetails equals to transferDetailsId
        defaultProductShouldBeFound("transferDetailsId.equals=" + transferDetailsId);

        // Get all the productList where transferDetails equals to (transferDetailsId + 1)
        defaultProductShouldNotBeFound("transferDetailsId.equals=" + (transferDetailsId + 1));
    }

    @Test
    @Transactional
    void getAllProductsByProductClientAgreementIsEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);
        ProductClientAgreement productClientAgreement;
        if (TestUtil.findAll(em, ProductClientAgreement.class).isEmpty()) {
            productClientAgreement = ProductClientAgreementResourceIT.createEntity(em);
            em.persist(productClientAgreement);
            em.flush();
        } else {
            productClientAgreement = TestUtil.findAll(em, ProductClientAgreement.class).get(0);
        }
        em.persist(productClientAgreement);
        em.flush();
        product.addProductClientAgreement(productClientAgreement);
        productRepository.saveAndFlush(product);
        Long productClientAgreementId = productClientAgreement.getId();

        // Get all the productList where productClientAgreement equals to productClientAgreementId
        defaultProductShouldBeFound("productClientAgreementId.equals=" + productClientAgreementId);

        // Get all the productList where productClientAgreement equals to (productClientAgreementId + 1)
        defaultProductShouldNotBeFound("productClientAgreementId.equals=" + (productClientAgreementId + 1));
    }

    @Test
    @Transactional
    void getAllProductsByCategoriesIsEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);
        Categories categories;
        if (TestUtil.findAll(em, Categories.class).isEmpty()) {
            categories = CategoriesResourceIT.createEntity(em);
            em.persist(categories);
            em.flush();
        } else {
            categories = TestUtil.findAll(em, Categories.class).get(0);
        }
        em.persist(categories);
        em.flush();
        product.setCategories(categories);
        productRepository.saveAndFlush(product);
        Long categoriesId = categories.getId();

        // Get all the productList where categories equals to categoriesId
        defaultProductShouldBeFound("categoriesId.equals=" + categoriesId);

        // Get all the productList where categories equals to (categoriesId + 1)
        defaultProductShouldNotBeFound("categoriesId.equals=" + (categoriesId + 1));
    }

    @Test
    @Transactional
    void getAllProductsByUnitIsEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);
        Unit unit;
        if (TestUtil.findAll(em, Unit.class).isEmpty()) {
            unit = UnitResourceIT.createEntity(em);
            em.persist(unit);
            em.flush();
        } else {
            unit = TestUtil.findAll(em, Unit.class).get(0);
        }
        em.persist(unit);
        em.flush();
        product.setUnit(unit);
        productRepository.saveAndFlush(product);
        Long unitId = unit.getId();

        // Get all the productList where unit equals to unitId
        defaultProductShouldBeFound("unitId.equals=" + unitId);

        // Get all the productList where unit equals to (unitId + 1)
        defaultProductShouldNotBeFound("unitId.equals=" + (unitId + 1));
    }

    @Test
    @Transactional
    void getAllProductsBySecurityUserIsEqualToSomething() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);
        SecurityUser securityUser;
        if (TestUtil.findAll(em, SecurityUser.class).isEmpty()) {
            securityUser = SecurityUserResourceIT.createEntity(em);
            em.persist(securityUser);
            em.flush();
        } else {
            securityUser = TestUtil.findAll(em, SecurityUser.class).get(0);
        }
        em.persist(securityUser);
        em.flush();
        product.setSecurityUser(securityUser);
        productRepository.saveAndFlush(product);
        Long securityUserId = securityUser.getId();

        // Get all the productList where securityUser equals to securityUserId
        defaultProductShouldBeFound("securityUserId.equals=" + securityUserId);

        // Get all the productList where securityUser equals to (securityUserId + 1)
        defaultProductShouldNotBeFound("securityUserId.equals=" + (securityUserId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultProductShouldBeFound(String filter) throws Exception {
        restProductMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(product.getId().intValue())))
            .andExpect(jsonPath("$.[*].shortName").value(hasItem(DEFAULT_SHORT_NAME)))
            .andExpect(jsonPath("$.[*].hsnNo").value(hasItem(DEFAULT_HSN_NO)))
            .andExpect(jsonPath("$.[*].materialImageContentType").value(hasItem(DEFAULT_MATERIAL_IMAGE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].materialImage").value(hasItem(Base64Utils.encodeToString(DEFAULT_MATERIAL_IMAGE))))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].productName").value(hasItem(DEFAULT_PRODUCT_NAME)))
            .andExpect(jsonPath("$.[*].alertUnits").value(hasItem(DEFAULT_ALERT_UNITS)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].basePrice").value(hasItem(DEFAULT_BASE_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].quality").value(hasItem(DEFAULT_QUALITY)))
            .andExpect(jsonPath("$.[*].qrCode").value(hasItem(DEFAULT_QR_CODE)))
            .andExpect(jsonPath("$.[*].barCode").value(hasItem(DEFAULT_BAR_CODE)))
            .andExpect(jsonPath("$.[*].gstPercentage").value(hasItem(DEFAULT_GST_PERCENTAGE.doubleValue())))
            .andExpect(jsonPath("$.[*].productType").value(hasItem(DEFAULT_PRODUCT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].lastModified").value(hasItem(DEFAULT_LAST_MODIFIED)))
            .andExpect(jsonPath("$.[*].lastModifiedBy").value(hasItem(DEFAULT_LAST_MODIFIED_BY)))
            .andExpect(jsonPath("$.[*].freeField1").value(hasItem(DEFAULT_FREE_FIELD_1)))
            .andExpect(jsonPath("$.[*].freeField2").value(hasItem(DEFAULT_FREE_FIELD_2)))
            .andExpect(jsonPath("$.[*].freeField3").value(hasItem(DEFAULT_FREE_FIELD_3)))
            .andExpect(jsonPath("$.[*].freeField4").value(hasItem(DEFAULT_FREE_FIELD_4)));

        // Check, that the count call also returns 1
        restProductMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultProductShouldNotBeFound(String filter) throws Exception {
        restProductMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restProductMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingProduct() throws Exception {
        // Get the product
        restProductMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewProduct() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        int databaseSizeBeforeUpdate = productRepository.findAll().size();

        // Update the product
        Product updatedProduct = productRepository.findById(product.getId()).get();
        // Disconnect from session so that the updates on updatedProduct are not directly saved in db
        em.detach(updatedProduct);
        updatedProduct
            .shortName(UPDATED_SHORT_NAME)
            .hsnNo(UPDATED_HSN_NO)
            .materialImage(UPDATED_MATERIAL_IMAGE)
            .materialImageContentType(UPDATED_MATERIAL_IMAGE_CONTENT_TYPE)
            .isDeleted(UPDATED_IS_DELETED)
            .isActive(UPDATED_IS_ACTIVE)
            .productName(UPDATED_PRODUCT_NAME)
            .alertUnits(UPDATED_ALERT_UNITS)
            .description(UPDATED_DESCRIPTION)
            .basePrice(UPDATED_BASE_PRICE)
            .quality(UPDATED_QUALITY)
            .qrCode(UPDATED_QR_CODE)
            .barCode(UPDATED_BAR_CODE)
            .gstPercentage(UPDATED_GST_PERCENTAGE)
            .productType(UPDATED_PRODUCT_TYPE)
            .lastModified(UPDATED_LAST_MODIFIED)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
            .freeField1(UPDATED_FREE_FIELD_1)
            .freeField2(UPDATED_FREE_FIELD_2)
            .freeField3(UPDATED_FREE_FIELD_3)
            .freeField4(UPDATED_FREE_FIELD_4);
        ProductDTO productDTO = productMapper.toDto(updatedProduct);

        restProductMockMvc
            .perform(
                put(ENTITY_API_URL_ID, productDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(productDTO))
            )
            .andExpect(status().isOk());

        // Validate the Product in the database
        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeUpdate);
        Product testProduct = productList.get(productList.size() - 1);
        assertThat(testProduct.getShortName()).isEqualTo(UPDATED_SHORT_NAME);
        assertThat(testProduct.getHsnNo()).isEqualTo(UPDATED_HSN_NO);
        assertThat(testProduct.getMaterialImage()).isEqualTo(UPDATED_MATERIAL_IMAGE);
        assertThat(testProduct.getMaterialImageContentType()).isEqualTo(UPDATED_MATERIAL_IMAGE_CONTENT_TYPE);
        assertThat(testProduct.getIsDeleted()).isEqualTo(UPDATED_IS_DELETED);
        assertThat(testProduct.getIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testProduct.getProductName()).isEqualTo(UPDATED_PRODUCT_NAME);
        assertThat(testProduct.getAlertUnits()).isEqualTo(UPDATED_ALERT_UNITS);
        assertThat(testProduct.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testProduct.getBasePrice()).isEqualTo(UPDATED_BASE_PRICE);
        assertThat(testProduct.getQuality()).isEqualTo(UPDATED_QUALITY);
        assertThat(testProduct.getQrCode()).isEqualTo(UPDATED_QR_CODE);
        assertThat(testProduct.getBarCode()).isEqualTo(UPDATED_BAR_CODE);
        assertThat(testProduct.getGstPercentage()).isEqualTo(UPDATED_GST_PERCENTAGE);
        assertThat(testProduct.getProductType()).isEqualTo(UPDATED_PRODUCT_TYPE);
        assertThat(testProduct.getLastModified()).isEqualTo(UPDATED_LAST_MODIFIED);
        assertThat(testProduct.getLastModifiedBy()).isEqualTo(UPDATED_LAST_MODIFIED_BY);
        assertThat(testProduct.getFreeField1()).isEqualTo(UPDATED_FREE_FIELD_1);
        assertThat(testProduct.getFreeField2()).isEqualTo(UPDATED_FREE_FIELD_2);
        assertThat(testProduct.getFreeField3()).isEqualTo(UPDATED_FREE_FIELD_3);
        assertThat(testProduct.getFreeField4()).isEqualTo(UPDATED_FREE_FIELD_4);
    }

    @Test
    @Transactional
    void putNonExistingProduct() throws Exception {
        int databaseSizeBeforeUpdate = productRepository.findAll().size();
        product.setId(count.incrementAndGet());

        // Create the Product
        ProductDTO productDTO = productMapper.toDto(product);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProductMockMvc
            .perform(
                put(ENTITY_API_URL_ID, productDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(productDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Product in the database
        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchProduct() throws Exception {
        int databaseSizeBeforeUpdate = productRepository.findAll().size();
        product.setId(count.incrementAndGet());

        // Create the Product
        ProductDTO productDTO = productMapper.toDto(product);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProductMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(productDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Product in the database
        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamProduct() throws Exception {
        int databaseSizeBeforeUpdate = productRepository.findAll().size();
        product.setId(count.incrementAndGet());

        // Create the Product
        ProductDTO productDTO = productMapper.toDto(product);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProductMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(productDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Product in the database
        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateProductWithPatch() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        int databaseSizeBeforeUpdate = productRepository.findAll().size();

        // Update the product using partial update
        Product partialUpdatedProduct = new Product();
        partialUpdatedProduct.setId(product.getId());

        partialUpdatedProduct
            .isActive(UPDATED_IS_ACTIVE)
            .productName(UPDATED_PRODUCT_NAME)
            .qrCode(UPDATED_QR_CODE)
            .productType(UPDATED_PRODUCT_TYPE)
            .freeField2(UPDATED_FREE_FIELD_2)
            .freeField3(UPDATED_FREE_FIELD_3)
            .freeField4(UPDATED_FREE_FIELD_4);

        restProductMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedProduct.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedProduct))
            )
            .andExpect(status().isOk());

        // Validate the Product in the database
        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeUpdate);
        Product testProduct = productList.get(productList.size() - 1);
        assertThat(testProduct.getShortName()).isEqualTo(DEFAULT_SHORT_NAME);
        assertThat(testProduct.getHsnNo()).isEqualTo(DEFAULT_HSN_NO);
        assertThat(testProduct.getMaterialImage()).isEqualTo(DEFAULT_MATERIAL_IMAGE);
        assertThat(testProduct.getMaterialImageContentType()).isEqualTo(DEFAULT_MATERIAL_IMAGE_CONTENT_TYPE);
        assertThat(testProduct.getIsDeleted()).isEqualTo(DEFAULT_IS_DELETED);
        assertThat(testProduct.getIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testProduct.getProductName()).isEqualTo(UPDATED_PRODUCT_NAME);
        assertThat(testProduct.getAlertUnits()).isEqualTo(DEFAULT_ALERT_UNITS);
        assertThat(testProduct.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testProduct.getBasePrice()).isEqualTo(DEFAULT_BASE_PRICE);
        assertThat(testProduct.getQuality()).isEqualTo(DEFAULT_QUALITY);
        assertThat(testProduct.getQrCode()).isEqualTo(UPDATED_QR_CODE);
        assertThat(testProduct.getBarCode()).isEqualTo(DEFAULT_BAR_CODE);
        assertThat(testProduct.getGstPercentage()).isEqualTo(DEFAULT_GST_PERCENTAGE);
        assertThat(testProduct.getProductType()).isEqualTo(UPDATED_PRODUCT_TYPE);
        assertThat(testProduct.getLastModified()).isEqualTo(DEFAULT_LAST_MODIFIED);
        assertThat(testProduct.getLastModifiedBy()).isEqualTo(DEFAULT_LAST_MODIFIED_BY);
        assertThat(testProduct.getFreeField1()).isEqualTo(DEFAULT_FREE_FIELD_1);
        assertThat(testProduct.getFreeField2()).isEqualTo(UPDATED_FREE_FIELD_2);
        assertThat(testProduct.getFreeField3()).isEqualTo(UPDATED_FREE_FIELD_3);
        assertThat(testProduct.getFreeField4()).isEqualTo(UPDATED_FREE_FIELD_4);
    }

    @Test
    @Transactional
    void fullUpdateProductWithPatch() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        int databaseSizeBeforeUpdate = productRepository.findAll().size();

        // Update the product using partial update
        Product partialUpdatedProduct = new Product();
        partialUpdatedProduct.setId(product.getId());

        partialUpdatedProduct
            .shortName(UPDATED_SHORT_NAME)
            .hsnNo(UPDATED_HSN_NO)
            .materialImage(UPDATED_MATERIAL_IMAGE)
            .materialImageContentType(UPDATED_MATERIAL_IMAGE_CONTENT_TYPE)
            .isDeleted(UPDATED_IS_DELETED)
            .isActive(UPDATED_IS_ACTIVE)
            .productName(UPDATED_PRODUCT_NAME)
            .alertUnits(UPDATED_ALERT_UNITS)
            .description(UPDATED_DESCRIPTION)
            .basePrice(UPDATED_BASE_PRICE)
            .quality(UPDATED_QUALITY)
            .qrCode(UPDATED_QR_CODE)
            .barCode(UPDATED_BAR_CODE)
            .gstPercentage(UPDATED_GST_PERCENTAGE)
            .productType(UPDATED_PRODUCT_TYPE)
            .lastModified(UPDATED_LAST_MODIFIED)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
            .freeField1(UPDATED_FREE_FIELD_1)
            .freeField2(UPDATED_FREE_FIELD_2)
            .freeField3(UPDATED_FREE_FIELD_3)
            .freeField4(UPDATED_FREE_FIELD_4);

        restProductMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedProduct.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedProduct))
            )
            .andExpect(status().isOk());

        // Validate the Product in the database
        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeUpdate);
        Product testProduct = productList.get(productList.size() - 1);
        assertThat(testProduct.getShortName()).isEqualTo(UPDATED_SHORT_NAME);
        assertThat(testProduct.getHsnNo()).isEqualTo(UPDATED_HSN_NO);
        assertThat(testProduct.getMaterialImage()).isEqualTo(UPDATED_MATERIAL_IMAGE);
        assertThat(testProduct.getMaterialImageContentType()).isEqualTo(UPDATED_MATERIAL_IMAGE_CONTENT_TYPE);
        assertThat(testProduct.getIsDeleted()).isEqualTo(UPDATED_IS_DELETED);
        assertThat(testProduct.getIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testProduct.getProductName()).isEqualTo(UPDATED_PRODUCT_NAME);
        assertThat(testProduct.getAlertUnits()).isEqualTo(UPDATED_ALERT_UNITS);
        assertThat(testProduct.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testProduct.getBasePrice()).isEqualTo(UPDATED_BASE_PRICE);
        assertThat(testProduct.getQuality()).isEqualTo(UPDATED_QUALITY);
        assertThat(testProduct.getQrCode()).isEqualTo(UPDATED_QR_CODE);
        assertThat(testProduct.getBarCode()).isEqualTo(UPDATED_BAR_CODE);
        assertThat(testProduct.getGstPercentage()).isEqualTo(UPDATED_GST_PERCENTAGE);
        assertThat(testProduct.getProductType()).isEqualTo(UPDATED_PRODUCT_TYPE);
        assertThat(testProduct.getLastModified()).isEqualTo(UPDATED_LAST_MODIFIED);
        assertThat(testProduct.getLastModifiedBy()).isEqualTo(UPDATED_LAST_MODIFIED_BY);
        assertThat(testProduct.getFreeField1()).isEqualTo(UPDATED_FREE_FIELD_1);
        assertThat(testProduct.getFreeField2()).isEqualTo(UPDATED_FREE_FIELD_2);
        assertThat(testProduct.getFreeField3()).isEqualTo(UPDATED_FREE_FIELD_3);
        assertThat(testProduct.getFreeField4()).isEqualTo(UPDATED_FREE_FIELD_4);
    }

    @Test
    @Transactional
    void patchNonExistingProduct() throws Exception {
        int databaseSizeBeforeUpdate = productRepository.findAll().size();
        product.setId(count.incrementAndGet());

        // Create the Product
        ProductDTO productDTO = productMapper.toDto(product);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProductMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, productDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(productDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Product in the database
        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchProduct() throws Exception {
        int databaseSizeBeforeUpdate = productRepository.findAll().size();
        product.setId(count.incrementAndGet());

        // Create the Product
        ProductDTO productDTO = productMapper.toDto(product);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProductMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(productDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Product in the database
        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamProduct() throws Exception {
        int databaseSizeBeforeUpdate = productRepository.findAll().size();
        product.setId(count.incrementAndGet());

        // Create the Product
        ProductDTO productDTO = productMapper.toDto(product);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProductMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(productDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Product in the database
        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteProduct() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        int databaseSizeBeforeDelete = productRepository.findAll().size();

        // Delete the product
        restProductMockMvc
            .perform(delete(ENTITY_API_URL_ID, product.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
