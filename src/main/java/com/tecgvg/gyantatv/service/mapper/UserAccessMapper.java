package com.tecgvg.gyantatv.service.mapper;

import com.tecgvg.gyantatv.domain.SecurityUser;
import com.tecgvg.gyantatv.domain.UserAccess;
import com.tecgvg.gyantatv.service.dto.SecurityUserDTO;
import com.tecgvg.gyantatv.service.dto.UserAccessDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link UserAccess} and its DTO {@link UserAccessDTO}.
 */
@Mapper(componentModel = "spring")
public interface UserAccessMapper extends EntityMapper<UserAccessDTO, UserAccess> {
    @Mapping(target = "securityUser", source = "securityUser", qualifiedByName = "securityUserLogin")
    UserAccessDTO toDto(UserAccess s);

    @Named("securityUserLogin")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "login", source = "login")
    SecurityUserDTO toDtoSecurityUserLogin(SecurityUser securityUser);
}
