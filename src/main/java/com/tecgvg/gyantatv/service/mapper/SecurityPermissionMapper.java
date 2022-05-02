package com.tecgvg.gyantatv.service.mapper;

import com.tecgvg.gyantatv.domain.SecurityPermission;
import com.tecgvg.gyantatv.service.dto.SecurityPermissionDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link SecurityPermission} and its DTO {@link SecurityPermissionDTO}.
 */
@Mapper(componentModel = "spring")
public interface SecurityPermissionMapper extends EntityMapper<SecurityPermissionDTO, SecurityPermission> {}
