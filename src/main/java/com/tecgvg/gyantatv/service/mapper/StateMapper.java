package com.tecgvg.gyantatv.service.mapper;

import com.tecgvg.gyantatv.domain.State;
import com.tecgvg.gyantatv.service.dto.StateDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link State} and its DTO {@link StateDTO}.
 */
@Mapper(componentModel = "spring")
public interface StateMapper extends EntityMapper<StateDTO, State> {}
