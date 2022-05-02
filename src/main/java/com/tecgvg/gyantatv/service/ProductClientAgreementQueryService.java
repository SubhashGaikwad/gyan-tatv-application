package com.tecgvg.gyantatv.service;

import com.tecgvg.gyantatv.domain.*; // for static metamodels
import com.tecgvg.gyantatv.domain.ProductClientAgreement;
import com.tecgvg.gyantatv.repository.ProductClientAgreementRepository;
import com.tecgvg.gyantatv.service.criteria.ProductClientAgreementCriteria;
import com.tecgvg.gyantatv.service.dto.ProductClientAgreementDTO;
import com.tecgvg.gyantatv.service.mapper.ProductClientAgreementMapper;
import java.util.List;
import javax.persistence.criteria.JoinType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link ProductClientAgreement} entities in the database.
 * The main input is a {@link ProductClientAgreementCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ProductClientAgreementDTO} or a {@link Page} of {@link ProductClientAgreementDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ProductClientAgreementQueryService extends QueryService<ProductClientAgreement> {

    private final Logger log = LoggerFactory.getLogger(ProductClientAgreementQueryService.class);

    private final ProductClientAgreementRepository productClientAgreementRepository;

    private final ProductClientAgreementMapper productClientAgreementMapper;

    public ProductClientAgreementQueryService(
        ProductClientAgreementRepository productClientAgreementRepository,
        ProductClientAgreementMapper productClientAgreementMapper
    ) {
        this.productClientAgreementRepository = productClientAgreementRepository;
        this.productClientAgreementMapper = productClientAgreementMapper;
    }

    /**
     * Return a {@link List} of {@link ProductClientAgreementDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ProductClientAgreementDTO> findByCriteria(ProductClientAgreementCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ProductClientAgreement> specification = createSpecification(criteria);
        return productClientAgreementMapper.toDto(productClientAgreementRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ProductClientAgreementDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ProductClientAgreementDTO> findByCriteria(ProductClientAgreementCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ProductClientAgreement> specification = createSpecification(criteria);
        return productClientAgreementRepository.findAll(specification, page).map(productClientAgreementMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ProductClientAgreementCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ProductClientAgreement> specification = createSpecification(criteria);
        return productClientAgreementRepository.count(specification);
    }

    /**
     * Function to convert {@link ProductClientAgreementCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<ProductClientAgreement> createSpecification(ProductClientAgreementCriteria criteria) {
        Specification<ProductClientAgreement> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), ProductClientAgreement_.id));
            }
            if (criteria.getPrice() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPrice(), ProductClientAgreement_.price));
            }
            if (criteria.getCreditPeriod() != null) {
                specification =
                    specification.and(buildRangeSpecification(criteria.getCreditPeriod(), ProductClientAgreement_.creditPeriod));
            }
            if (criteria.getPenalty() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPenalty(), ProductClientAgreement_.penalty));
            }
            if (criteria.getPenaltyTerms() != null) {
                specification = specification.and(buildSpecification(criteria.getPenaltyTerms(), ProductClientAgreement_.penaltyTerms));
            }
            if (criteria.getNotes() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNotes(), ProductClientAgreement_.notes));
            }
            if (criteria.getImage() != null) {
                specification = specification.and(buildStringSpecification(criteria.getImage(), ProductClientAgreement_.image));
            }
            if (criteria.getAgreementPeriod() != null) {
                specification =
                    specification.and(buildRangeSpecification(criteria.getAgreementPeriod(), ProductClientAgreement_.agreementPeriod));
            }
            if (criteria.getTermAndConditions() != null) {
                specification =
                    specification.and(buildStringSpecification(criteria.getTermAndConditions(), ProductClientAgreement_.termAndConditions));
            }
            if (criteria.getFreeField1() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFreeField1(), ProductClientAgreement_.freeField1));
            }
            if (criteria.getFreeField2() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFreeField2(), ProductClientAgreement_.freeField2));
            }
            if (criteria.getFreeField3() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFreeField3(), ProductClientAgreement_.freeField3));
            }
            if (criteria.getFreeField4() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFreeField4(), ProductClientAgreement_.freeField4));
            }
            if (criteria.getLastModified() != null) {
                specification =
                    specification.and(buildStringSpecification(criteria.getLastModified(), ProductClientAgreement_.lastModified));
            }
            if (criteria.getLastModifiedBy() != null) {
                specification =
                    specification.and(buildStringSpecification(criteria.getLastModifiedBy(), ProductClientAgreement_.lastModifiedBy));
            }
        }
        return specification;
    }
}
