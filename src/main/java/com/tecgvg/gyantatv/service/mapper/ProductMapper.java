package com.tecgvg.gyantatv.service.mapper;

import com.tecgvg.gyantatv.domain.Categories;
import com.tecgvg.gyantatv.domain.Product;
import com.tecgvg.gyantatv.domain.SecurityUser;
import com.tecgvg.gyantatv.domain.Unit;
import com.tecgvg.gyantatv.service.dto.CategoriesDTO;
import com.tecgvg.gyantatv.service.dto.ProductDTO;
import com.tecgvg.gyantatv.service.dto.SecurityUserDTO;
import com.tecgvg.gyantatv.service.dto.UnitDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Product} and its DTO {@link ProductDTO}.
 */
@Mapper(componentModel = "spring")
public interface ProductMapper extends EntityMapper<ProductDTO, Product> {
    @Mapping(target = "categories", source = "categories", qualifiedByName = "categoriesId")
    @Mapping(target = "unit", source = "unit", qualifiedByName = "unitId")
    @Mapping(target = "securityUser", source = "securityUser", qualifiedByName = "securityUserLogin")
    ProductDTO toDto(Product s);

    @Named("categoriesId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    CategoriesDTO toDtoCategoriesId(Categories categories);

    @Named("unitId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    UnitDTO toDtoUnitId(Unit unit);

    @Named("securityUserLogin")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "login", source = "login")
    SecurityUserDTO toDtoSecurityUserLogin(SecurityUser securityUser);
}
