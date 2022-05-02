package com.tecgvg.gyantatv.service.mapper;

import com.tecgvg.gyantatv.domain.ParameterLookup;
import com.tecgvg.gyantatv.service.dto.ParameterLookupDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ParameterLookup} and its DTO {@link ParameterLookupDTO}.
 */
@Mapper(componentModel = "spring")
public interface ParameterLookupMapper extends EntityMapper<ParameterLookupDTO, ParameterLookup> {}
