package com.tecgvg.gyantatv.service.mapper;

import com.tecgvg.gyantatv.domain.ConsumptionDetails;
import com.tecgvg.gyantatv.domain.ProductInventory;
import com.tecgvg.gyantatv.domain.Project;
import com.tecgvg.gyantatv.domain.SecurityUser;
import com.tecgvg.gyantatv.service.dto.ConsumptionDetailsDTO;
import com.tecgvg.gyantatv.service.dto.ProductInventoryDTO;
import com.tecgvg.gyantatv.service.dto.ProjectDTO;
import com.tecgvg.gyantatv.service.dto.SecurityUserDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ConsumptionDetails} and its DTO {@link ConsumptionDetailsDTO}.
 */
@Mapper(componentModel = "spring")
public interface ConsumptionDetailsMapper extends EntityMapper<ConsumptionDetailsDTO, ConsumptionDetails> {
    @Mapping(target = "securityUser", source = "securityUser", qualifiedByName = "securityUserLogin")
    @Mapping(target = "project", source = "project", qualifiedByName = "projectProjectName")
    @Mapping(target = "productInventory", source = "productInventory", qualifiedByName = "productInventoryId")
    ConsumptionDetailsDTO toDto(ConsumptionDetails s);

    @Named("securityUserLogin")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "login", source = "login")
    SecurityUserDTO toDtoSecurityUserLogin(SecurityUser securityUser);

    @Named("projectProjectName")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "projectName", source = "projectName")
    ProjectDTO toDtoProjectProjectName(Project project);

    @Named("productInventoryId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ProductInventoryDTO toDtoProductInventoryId(ProductInventory productInventory);
}
