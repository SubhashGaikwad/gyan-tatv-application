package com.tecgvg.gyantatv.service.mapper;

import com.tecgvg.gyantatv.domain.Product;
import com.tecgvg.gyantatv.domain.Transfer;
import com.tecgvg.gyantatv.domain.TransferDetails;
import com.tecgvg.gyantatv.service.dto.ProductDTO;
import com.tecgvg.gyantatv.service.dto.TransferDTO;
import com.tecgvg.gyantatv.service.dto.TransferDetailsDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TransferDetails} and its DTO {@link TransferDetailsDTO}.
 */
@Mapper(componentModel = "spring")
public interface TransferDetailsMapper extends EntityMapper<TransferDetailsDTO, TransferDetails> {
    @Mapping(target = "product", source = "product", qualifiedByName = "productId")
    @Mapping(target = "transfer", source = "transfer", qualifiedByName = "transferId")
    TransferDetailsDTO toDto(TransferDetails s);

    @Named("productId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ProductDTO toDtoProductId(Product product);

    @Named("transferId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    TransferDTO toDtoTransferId(Transfer transfer);
}
