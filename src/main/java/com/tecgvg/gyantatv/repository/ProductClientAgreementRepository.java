package com.tecgvg.gyantatv.repository;

import com.tecgvg.gyantatv.domain.ProductClientAgreement;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the ProductClientAgreement entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProductClientAgreementRepository
    extends JpaRepository<ProductClientAgreement, Long>, JpaSpecificationExecutor<ProductClientAgreement> {}
