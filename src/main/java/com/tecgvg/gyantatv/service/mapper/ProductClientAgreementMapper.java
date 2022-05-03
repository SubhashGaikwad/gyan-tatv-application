package com.tecgvg.gyantatv.service.mapper;

import com.tecgvg.gyantatv.domain.ClientDetails;
import com.tecgvg.gyantatv.domain.Product;
import com.tecgvg.gyantatv.domain.ProductClientAgreement;
import com.tecgvg.gyantatv.service.dto.ClientDetailsDTO;
import com.tecgvg.gyantatv.service.dto.ProductClientAgreementDTO;
import com.tecgvg.gyantatv.service.dto.ProductDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ProductClientAgreement} and its DTO {@link ProductClientAgreementDTO}.
 */
@Mapper(componentModel = "spring")
public interface ProductClientAgreementMapper extends EntityMapper<ProductClientAgreementDTO, ProductClientAgreement> {
    @Mapping(target = "product", source = "product", qualifiedByName = "productId")
    @Mapping(target = "clientDetails", source = "clientDetails", qualifiedByName = "clientDetailsId")
    ProductClientAgreementDTO toDto(ProductClientAgreement s);

    @Named("productId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ProductDTO toDtoProductId(Product product);

    @Named("clientDetailsId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ClientDetailsDTO toDtoClientDetailsId(ClientDetails clientDetails);
}
