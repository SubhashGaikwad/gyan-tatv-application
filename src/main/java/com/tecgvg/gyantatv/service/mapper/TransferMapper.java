package com.tecgvg.gyantatv.service.mapper;

import com.tecgvg.gyantatv.domain.SecurityUser;
import com.tecgvg.gyantatv.domain.Transfer;
import com.tecgvg.gyantatv.domain.WareHouse;
import com.tecgvg.gyantatv.service.dto.SecurityUserDTO;
import com.tecgvg.gyantatv.service.dto.TransferDTO;
import com.tecgvg.gyantatv.service.dto.WareHouseDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Transfer} and its DTO {@link TransferDTO}.
 */
@Mapper(componentModel = "spring")
public interface TransferMapper extends EntityMapper<TransferDTO, Transfer> {
    @Mapping(target = "securityUser", source = "securityUser", qualifiedByName = "securityUserLogin")
    @Mapping(target = "wareHouse", source = "wareHouse", qualifiedByName = "wareHouseWhName")
    TransferDTO toDto(Transfer s);

    @Named("securityUserLogin")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "login", source = "login")
    SecurityUserDTO toDtoSecurityUserLogin(SecurityUser securityUser);

    @Named("wareHouseWhName")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "whName", source = "whName")
    WareHouseDTO toDtoWareHouseWhName(WareHouse wareHouse);
}
