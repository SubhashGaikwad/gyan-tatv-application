package com.tecgvg.gyantatv.service.mapper;

import com.tecgvg.gyantatv.domain.GoodsRecived;
import com.tecgvg.gyantatv.domain.PurchaseQuotation;
import com.tecgvg.gyantatv.service.dto.GoodsRecivedDTO;
import com.tecgvg.gyantatv.service.dto.PurchaseQuotationDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link GoodsRecived} and its DTO {@link GoodsRecivedDTO}.
 */
@Mapper(componentModel = "spring")
public interface GoodsRecivedMapper extends EntityMapper<GoodsRecivedDTO, GoodsRecived> {
    @Mapping(target = "purchaseQuotation", source = "purchaseQuotation", qualifiedByName = "purchaseQuotationId")
    GoodsRecivedDTO toDto(GoodsRecived s);

    @Named("purchaseQuotationId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    PurchaseQuotationDTO toDtoPurchaseQuotationId(PurchaseQuotation purchaseQuotation);
}
