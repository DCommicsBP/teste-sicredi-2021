package br.com.sicredi.assembly.membership.api.converter;

import br.com.sicredi.assembly.core.interfaces.ConverterInterface;
import br.com.sicredi.assembly.membership.dto.MembershipDTO;
import br.com.sicredi.assembly.membership.entity.MembershipEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MemberShipConverter implements ConverterInterface<MembershipDTO, MembershipEntity> {
    @Override
    public MembershipDTO convertFromEntity(MembershipEntity entity) {
        return null;
    }

    @Override
    public MembershipEntity convertFromDto(MembershipDTO dto) {
        return null;
    }

    @Override
    public List<MembershipDTO> convertListEntityToDto(List<MembershipEntity> entityList) {
        return null;
    }

    @Override
    public List<MembershipEntity> convertListDtoToEntity(List<MembershipDTO> entityList) {
        return null;
    }
}
