package com.tecgvg.gyantatv.service.mapper;

import com.tecgvg.gyantatv.domain.ClientDetails;
import com.tecgvg.gyantatv.service.dto.ClientDetailsDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ClientDetails} and its DTO {@link ClientDetailsDTO}.
 */
@Mapper(componentModel = "spring")
public interface ClientDetailsMapper extends EntityMapper<ClientDetailsDTO, ClientDetails> {}
