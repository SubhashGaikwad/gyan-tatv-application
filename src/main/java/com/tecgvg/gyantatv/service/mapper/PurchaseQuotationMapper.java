package com.tecgvg.gyantatv.service.mapper;

import com.tecgvg.gyantatv.domain.ClientDetails;
import com.tecgvg.gyantatv.domain.Project;
import com.tecgvg.gyantatv.domain.PurchaseQuotation;
import com.tecgvg.gyantatv.domain.SecurityUser;
import com.tecgvg.gyantatv.service.dto.ClientDetailsDTO;
import com.tecgvg.gyantatv.service.dto.ProjectDTO;
import com.tecgvg.gyantatv.service.dto.PurchaseQuotationDTO;
import com.tecgvg.gyantatv.service.dto.SecurityUserDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link PurchaseQuotation} and its DTO {@link PurchaseQuotationDTO}.
 */
@Mapper(componentModel = "spring")
public interface PurchaseQuotationMapper extends EntityMapper<PurchaseQuotationDTO, PurchaseQuotation> {
    @Mapping(target = "securityUser", source = "securityUser", qualifiedByName = "securityUserLogin")
    @Mapping(target = "project", source = "project", qualifiedByName = "projectProjectName")
    @Mapping(target = "clientDetails", source = "clientDetails", qualifiedByName = "clientDetailsId")
    PurchaseQuotationDTO toDto(PurchaseQuotation s);

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

    @Named("clientDetailsId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ClientDetailsDTO toDtoClientDetailsId(ClientDetails clientDetails);
}
