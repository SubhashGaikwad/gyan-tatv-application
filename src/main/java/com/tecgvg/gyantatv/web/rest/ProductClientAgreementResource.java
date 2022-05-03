package com.tecgvg.gyantatv.web.rest;

import com.tecgvg.gyantatv.repository.ProductClientAgreementRepository;
import com.tecgvg.gyantatv.service.ProductClientAgreementQueryService;
import com.tecgvg.gyantatv.service.ProductClientAgreementService;
import com.tecgvg.gyantatv.service.criteria.ProductClientAgreementCriteria;
import com.tecgvg.gyantatv.service.dto.ProductClientAgreementDTO;
import com.tecgvg.gyantatv.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.tecgvg.gyantatv.domain.ProductClientAgreement}.
 */
@RestController
@RequestMapping("/api")
public class ProductClientAgreementResource {

    private final Logger log = LoggerFactory.getLogger(ProductClientAgreementResource.class);

    private static final String ENTITY_NAME = "productClientAgreement";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProductClientAgreementService productClientAgreementService;

    private final ProductClientAgreementRepository productClientAgreementRepository;

    private final ProductClientAgreementQueryService productClientAgreementQueryService;

    public ProductClientAgreementResource(
        ProductClientAgreementService productClientAgreementService,
        ProductClientAgreementRepository productClientAgreementRepository,
        ProductClientAgreementQueryService productClientAgreementQueryService
    ) {
        this.productClientAgreementService = productClientAgreementService;
        this.productClientAgreementRepository = productClientAgreementRepository;
        this.productClientAgreementQueryService = productClientAgreementQueryService;
    }

    /**
     * {@code POST  /product-client-agreements} : Create a new productClientAgreement.
     *
     * @param productClientAgreementDTO the productClientAgreementDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new productClientAgreementDTO, or with status {@code 400 (Bad Request)} if the productClientAgreement has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/product-client-agreements")
    public ResponseEntity<ProductClientAgreementDTO> createProductClientAgreement(
        @RequestBody ProductClientAgreementDTO productClientAgreementDTO
    ) throws URISyntaxException {
        log.debug("REST request to save ProductClientAgreement : {}", productClientAgreementDTO);
        if (productClientAgreementDTO.getId() != null) {
            throw new BadRequestAlertException("A new productClientAgreement cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProductClientAgreementDTO result = productClientAgreementService.save(productClientAgreementDTO);
        return ResponseEntity
            .created(new URI("/api/product-client-agreements/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /product-client-agreements/:id} : Updates an existing productClientAgreement.
     *
     * @param id the id of the productClientAgreementDTO to save.
     * @param productClientAgreementDTO the productClientAgreementDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated productClientAgreementDTO,
     * or with status {@code 400 (Bad Request)} if the productClientAgreementDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the productClientAgreementDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/product-client-agreements/{id}")
    public ResponseEntity<ProductClientAgreementDTO> updateProductClientAgreement(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ProductClientAgreementDTO productClientAgreementDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ProductClientAgreement : {}, {}", id, productClientAgreementDTO);
        if (productClientAgreementDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, productClientAgreementDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!productClientAgreementRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ProductClientAgreementDTO result = productClientAgreementService.update(productClientAgreementDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, productClientAgreementDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /product-client-agreements/:id} : Partial updates given fields of an existing productClientAgreement, field will ignore if it is null
     *
     * @param id the id of the productClientAgreementDTO to save.
     * @param productClientAgreementDTO the productClientAgreementDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated productClientAgreementDTO,
     * or with status {@code 400 (Bad Request)} if the productClientAgreementDTO is not valid,
     * or with status {@code 404 (Not Found)} if the productClientAgreementDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the productClientAgreementDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/product-client-agreements/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ProductClientAgreementDTO> partialUpdateProductClientAgreement(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ProductClientAgreementDTO productClientAgreementDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update ProductClientAgreement partially : {}, {}", id, productClientAgreementDTO);
        if (productClientAgreementDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, productClientAgreementDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!productClientAgreementRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ProductClientAgreementDTO> result = productClientAgreementService.partialUpdate(productClientAgreementDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, productClientAgreementDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /product-client-agreements} : get all the productClientAgreements.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of productClientAgreements in body.
     */
    @GetMapping("/product-client-agreements")
    public ResponseEntity<List<ProductClientAgreementDTO>> getAllProductClientAgreements(
        ProductClientAgreementCriteria criteria,
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get ProductClientAgreements by criteria: {}", criteria);
        Page<ProductClientAgreementDTO> page = productClientAgreementQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /product-client-agreements/count} : count all the productClientAgreements.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/product-client-agreements/count")
    public ResponseEntity<Long> countProductClientAgreements(ProductClientAgreementCriteria criteria) {
        log.debug("REST request to count ProductClientAgreements by criteria: {}", criteria);
        return ResponseEntity.ok().body(productClientAgreementQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /product-client-agreements/:id} : get the "id" productClientAgreement.
     *
     * @param id the id of the productClientAgreementDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the productClientAgreementDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/product-client-agreements/{id}")
    public ResponseEntity<ProductClientAgreementDTO> getProductClientAgreement(@PathVariable Long id) {
        log.debug("REST request to get ProductClientAgreement : {}", id);
        Optional<ProductClientAgreementDTO> productClientAgreementDTO = productClientAgreementService.findOne(id);
        return ResponseUtil.wrapOrNotFound(productClientAgreementDTO);
    }

    /**
     * {@code DELETE  /product-client-agreements/:id} : delete the "id" productClientAgreement.
     *
     * @param id the id of the productClientAgreementDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/product-client-agreements/{id}")
    public ResponseEntity<Void> deleteProductClientAgreement(@PathVariable Long id) {
        log.debug("REST request to delete ProductClientAgreement : {}", id);
        productClientAgreementService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
