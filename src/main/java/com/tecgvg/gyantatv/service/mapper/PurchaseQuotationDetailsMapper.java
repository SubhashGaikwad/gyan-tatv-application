package com.tecgvg.gyantatv.service.mapper;

import com.tecgvg.gyantatv.domain.Product;
import com.tecgvg.gyantatv.domain.PurchaseQuotation;
import com.tecgvg.gyantatv.domain.PurchaseQuotationDetails;
import com.tecgvg.gyantatv.service.dto.ProductDTO;
import com.tecgvg.gyantatv.service.dto.PurchaseQuotationDTO;
import com.tecgvg.gyantatv.service.dto.PurchaseQuotationDetailsDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link PurchaseQuotationDetails} and its DTO {@link PurchaseQuotationDetailsDTO}.
 */
@Mapper(componentModel = "spring")
public interface PurchaseQuotationDetailsMapper extends EntityMapper<PurchaseQuotationDetailsDTO, PurchaseQuotationDetails> {
    @Mapping(target = "product", source = "product", qualifiedByName = "productId")
    @Mapping(target = "purchaseQuotation", source = "purchaseQuotation", qualifiedByName = "purchaseQuotationId")
    PurchaseQuotationDetailsDTO toDto(PurchaseQuotationDetails s);

    @Named("productId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ProductDTO toDtoProductId(Product product);

    @Named("purchaseQuotationId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    PurchaseQuotationDTO toDtoPurchaseQuotationId(PurchaseQuotation purchaseQuotation);
}
