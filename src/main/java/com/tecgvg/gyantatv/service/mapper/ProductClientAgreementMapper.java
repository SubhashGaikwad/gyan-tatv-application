package com.tecgvg.gyantatv.service.mapper;

import com.tecgvg.gyantatv.domain.ProductClientAgreement;
import com.tecgvg.gyantatv.service.dto.ProductClientAgreementDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ProductClientAgreement} and its DTO {@link ProductClientAgreementDTO}.
 */
@Mapper(componentModel = "spring")
public interface ProductClientAgreementMapper extends EntityMapper<ProductClientAgreementDTO, ProductClientAgreement> {}
