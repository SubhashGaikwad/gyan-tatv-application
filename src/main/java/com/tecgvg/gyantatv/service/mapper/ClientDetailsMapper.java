package com.tecgvg.gyantatv.service.mapper;

import com.tecgvg.gyantatv.domain.ClientDetails;
import com.tecgvg.gyantatv.domain.District;
import com.tecgvg.gyantatv.service.dto.ClientDetailsDTO;
import com.tecgvg.gyantatv.service.dto.DistrictDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ClientDetails} and its DTO {@link ClientDetailsDTO}.
 */
@Mapper(componentModel = "spring")
public interface ClientDetailsMapper extends EntityMapper<ClientDetailsDTO, ClientDetails> {
    @Mapping(target = "district", source = "district", qualifiedByName = "districtId")
    ClientDetailsDTO toDto(ClientDetails s);

    @Named("districtId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    DistrictDTO toDtoDistrictId(District district);
}
