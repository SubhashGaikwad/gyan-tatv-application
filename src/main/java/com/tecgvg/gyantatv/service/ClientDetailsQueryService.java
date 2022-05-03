package com.tecgvg.gyantatv.service;

import com.tecgvg.gyantatv.domain.*; // for static metamodels
import com.tecgvg.gyantatv.domain.ClientDetails;
import com.tecgvg.gyantatv.repository.ClientDetailsRepository;
import com.tecgvg.gyantatv.service.criteria.ClientDetailsCriteria;
import com.tecgvg.gyantatv.service.dto.ClientDetailsDTO;
import com.tecgvg.gyantatv.service.mapper.ClientDetailsMapper;
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
 * Service for executing complex queries for {@link ClientDetails} entities in the database.
 * The main input is a {@link ClientDetailsCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ClientDetailsDTO} or a {@link Page} of {@link ClientDetailsDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ClientDetailsQueryService extends QueryService<ClientDetails> {

    private final Logger log = LoggerFactory.getLogger(ClientDetailsQueryService.class);

    private final ClientDetailsRepository clientDetailsRepository;

    private final ClientDetailsMapper clientDetailsMapper;

    public ClientDetailsQueryService(ClientDetailsRepository clientDetailsRepository, ClientDetailsMapper clientDetailsMapper) {
        this.clientDetailsRepository = clientDetailsRepository;
        this.clientDetailsMapper = clientDetailsMapper;
    }

    /**
     * Return a {@link List} of {@link ClientDetailsDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ClientDetailsDTO> findByCriteria(ClientDetailsCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ClientDetails> specification = createSpecification(criteria);
        return clientDetailsMapper.toDto(clientDetailsRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ClientDetailsDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ClientDetailsDTO> findByCriteria(ClientDetailsCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ClientDetails> specification = createSpecification(criteria);
        return clientDetailsRepository.findAll(specification, page).map(clientDetailsMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ClientDetailsCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ClientDetails> specification = createSpecification(criteria);
        return clientDetailsRepository.count(specification);
    }

    /**
     * Function to convert {@link ClientDetailsCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<ClientDetails> createSpecification(ClientDetailsCriteria criteria) {
        Specification<ClientDetails> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), ClientDetails_.id));
            }
            if (criteria.getCustomerID() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCustomerID(), ClientDetails_.customerID));
            }
            if (criteria.getClientName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getClientName(), ClientDetails_.clientName));
            }
            if (criteria.getClientType() != null) {
                specification = specification.and(buildSpecification(criteria.getClientType(), ClientDetails_.clientType));
            }
            if (criteria.getMobileNo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMobileNo(), ClientDetails_.mobileNo));
            }
            if (criteria.getEmail() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEmail(), ClientDetails_.email));
            }
            if (criteria.getPincode() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPincode(), ClientDetails_.pincode));
            }
            if (criteria.getBillingAddress() != null) {
                specification = specification.and(buildStringSpecification(criteria.getBillingAddress(), ClientDetails_.billingAddress));
            }
            if (criteria.getCompanyName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCompanyName(), ClientDetails_.companyName));
            }
            if (criteria.getCompanyContactNo() != null) {
                specification =
                    specification.and(buildStringSpecification(criteria.getCompanyContactNo(), ClientDetails_.companyContactNo));
            }
            if (criteria.getWebsite() != null) {
                specification = specification.and(buildStringSpecification(criteria.getWebsite(), ClientDetails_.website));
            }
            if (criteria.getGstinNumber() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGstinNumber(), ClientDetails_.gstinNumber));
            }
            if (criteria.getCompanyPan() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCompanyPan(), ClientDetails_.companyPan));
            }
            if (criteria.getCompanyTan() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCompanyTan(), ClientDetails_.companyTan));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), ClientDetails_.description));
            }
            if (criteria.getNameOfBeneficiary() != null) {
                specification =
                    specification.and(buildStringSpecification(criteria.getNameOfBeneficiary(), ClientDetails_.nameOfBeneficiary));
            }
            if (criteria.getAccountNumber() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAccountNumber(), ClientDetails_.accountNumber));
            }
            if (criteria.getBankName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getBankName(), ClientDetails_.bankName));
            }
            if (criteria.getAccountType() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAccountType(), ClientDetails_.accountType));
            }
            if (criteria.getIfscCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getIfscCode(), ClientDetails_.ifscCode));
            }
            if (criteria.getRegistrationCategory() != null) {
                specification =
                    specification.and(buildStringSpecification(criteria.getRegistrationCategory(), ClientDetails_.registrationCategory));
            }
            if (criteria.getRegistrationLevel() != null) {
                specification = specification.and(buildSpecification(criteria.getRegistrationLevel(), ClientDetails_.registrationLevel));
            }
            if (criteria.getIsApproved() != null) {
                specification = specification.and(buildSpecification(criteria.getIsApproved(), ClientDetails_.isApproved));
            }
            if (criteria.getIsActivated() != null) {
                specification = specification.and(buildSpecification(criteria.getIsActivated(), ClientDetails_.isActivated));
            }
            if (criteria.getFreeField1() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFreeField1(), ClientDetails_.freeField1));
            }
            if (criteria.getFreeField2() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFreeField2(), ClientDetails_.freeField2));
            }
            if (criteria.getFreeField3() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFreeField3(), ClientDetails_.freeField3));
            }
            if (criteria.getFreeField4() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFreeField4(), ClientDetails_.freeField4));
            }
            if (criteria.getLastModified() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLastModified(), ClientDetails_.lastModified));
            }
            if (criteria.getLastModifiedBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLastModifiedBy(), ClientDetails_.lastModifiedBy));
            }
            if (criteria.getDistrictId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getDistrictId(),
                            root -> root.join(ClientDetails_.district, JoinType.LEFT).get(District_.id)
                        )
                    );
            }
            if (criteria.getProductClientAgreementId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getProductClientAgreementId(),
                            root -> root.join(ClientDetails_.productClientAgreements, JoinType.LEFT).get(ProductClientAgreement_.id)
                        )
                    );
            }
        }
        return specification;
    }
}
