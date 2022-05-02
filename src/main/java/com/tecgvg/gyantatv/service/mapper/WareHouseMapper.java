package com.tecgvg.gyantatv.service.mapper;

import com.tecgvg.gyantatv.domain.WareHouse;
import com.tecgvg.gyantatv.service.dto.WareHouseDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link WareHouse} and its DTO {@link WareHouseDTO}.
 */
@Mapper(componentModel = "spring")
public interface WareHouseMapper extends EntityMapper<WareHouseDTO, WareHouse> {}
