package com.tecgvg.gyantatv.service.mapper;

import com.tecgvg.gyantatv.domain.Product;
import com.tecgvg.gyantatv.domain.ProductInventory;
import com.tecgvg.gyantatv.domain.ProductTransaction;
import com.tecgvg.gyantatv.domain.SecurityUser;
import com.tecgvg.gyantatv.domain.WareHouse;
import com.tecgvg.gyantatv.service.dto.ProductDTO;
import com.tecgvg.gyantatv.service.dto.ProductInventoryDTO;
import com.tecgvg.gyantatv.service.dto.ProductTransactionDTO;
import com.tecgvg.gyantatv.service.dto.SecurityUserDTO;
import com.tecgvg.gyantatv.service.dto.WareHouseDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ProductInventory} and its DTO {@link ProductInventoryDTO}.
 */
@Mapper(componentModel = "spring")
public interface ProductInventoryMapper extends EntityMapper<ProductInventoryDTO, ProductInventory> {
    @Mapping(target = "product", source = "product", qualifiedByName = "productProductName")
    @Mapping(target = "productTransaction", source = "productTransaction", qualifiedByName = "productTransactionId")
    @Mapping(target = "securityUser", source = "securityUser", qualifiedByName = "securityUserLogin")
    @Mapping(target = "wareHouse", source = "wareHouse", qualifiedByName = "wareHouseId")
    ProductInventoryDTO toDto(ProductInventory s);

    @Named("productProductName")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "productName", source = "productName")
    ProductDTO toDtoProductProductName(Product product);

    @Named("productTransactionId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ProductTransactionDTO toDtoProductTransactionId(ProductTransaction productTransaction);

    @Named("securityUserLogin")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "login", source = "login")
    SecurityUserDTO toDtoSecurityUserLogin(SecurityUser securityUser);

    @Named("wareHouseId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    WareHouseDTO toDtoWareHouseId(WareHouse wareHouse);
}
