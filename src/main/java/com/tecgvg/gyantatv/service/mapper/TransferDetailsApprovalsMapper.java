package com.tecgvg.gyantatv.service.mapper;

import com.tecgvg.gyantatv.domain.SecurityUser;
import com.tecgvg.gyantatv.domain.Transfer;
import com.tecgvg.gyantatv.domain.TransferDetailsApprovals;
import com.tecgvg.gyantatv.service.dto.SecurityUserDTO;
import com.tecgvg.gyantatv.service.dto.TransferDTO;
import com.tecgvg.gyantatv.service.dto.TransferDetailsApprovalsDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TransferDetailsApprovals} and its DTO {@link TransferDetailsApprovalsDTO}.
 */
@Mapper(componentModel = "spring")
public interface TransferDetailsApprovalsMapper extends EntityMapper<TransferDetailsApprovalsDTO, TransferDetailsApprovals> {
    @Mapping(target = "securityUser", source = "securityUser", qualifiedByName = "securityUserLogin")
    @Mapping(target = "transfer", source = "transfer", qualifiedByName = "transferId")
    TransferDetailsApprovalsDTO toDto(TransferDetailsApprovals s);

    @Named("securityUserLogin")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "login", source = "login")
    SecurityUserDTO toDtoSecurityUserLogin(SecurityUser securityUser);

    @Named("transferId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    TransferDTO toDtoTransferId(Transfer transfer);
}
