package br.com.sicredi.assembly.membership.api.converter;

import br.com.sicredi.assembly.core.interfaces.ConverterInterface;
import br.com.sicredi.assembly.membership.dto.MembershipDTO;
import br.com.sicredi.assembly.membership.entity.MembershipEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MemberShipConverter implements ConverterInterface<MembershipDTO, MembershipEntity> {
    @Override
    public MembershipDTO convertFromEntity(MembershipEntity entity) {
        return MembershipDTO.builder()
                .cpf(entity.getCpf())
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }

    @Override
    public MembershipEntity convertFromDto(MembershipDTO dto) {
        return MembershipEntity.builder()
                .cpf(dto.getCpf())
                .id(dto.getId())
                .name(dto.getName())
                .build();
    }

    @Override
    public List<MembershipDTO> convertListEntityToDto(List<MembershipEntity> entityList) {
        return entityList.stream().map(this::convertFromEntity).collect(Collectors.toList());
    }

    @Override
    public List<MembershipEntity> convertListDtoToEntity(List<MembershipDTO> entityList) {
        return entityList.stream().map(this::convertFromDto).collect(Collectors.toList());
    }
}
