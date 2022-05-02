package com.tecgvg.gyantatv.repository;

import com.tecgvg.gyantatv.domain.PurchaseQuotationDetails;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the PurchaseQuotationDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PurchaseQuotationDetailsRepository
    extends JpaRepository<PurchaseQuotationDetails, Long>, JpaSpecificationExecutor<PurchaseQuotationDetails> {}
