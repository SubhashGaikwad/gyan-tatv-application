package com.tecgvg.gyantatv.service.mapper;

import com.tecgvg.gyantatv.domain.Unit;
import com.tecgvg.gyantatv.service.dto.UnitDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Unit} and its DTO {@link UnitDTO}.
 */
@Mapper(componentModel = "spring")
public interface UnitMapper extends EntityMapper<UnitDTO, Unit> {}
