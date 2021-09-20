package br.com.sicredi.assembly.vote.api.converter;

import br.com.sicredi.assembly.core.interfaces.ConverterInterface;
import br.com.sicredi.assembly.vote.dto.VoteDTO;
import br.com.sicredi.assembly.vote.entity.VoteEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class VoteConverter implements ConverterInterface<VoteDTO, VoteEntity> {
    @Override
    public VoteDTO convertFromEntity(VoteEntity entity) {
        return VoteDTO.builder()
                .id(entity.getId())
                .time(entity.getTime())
                .build();
    }

    @Override
    public VoteEntity convertFromDto(VoteDTO dto) {
        return VoteEntity.builder()
                .id(dto.getId())
                .time(dto.getTime())
                .agendaId(dto.getAgendaId())
                .build();
    }

    @Override
    public List<VoteDTO> convertListEntityToDto(List<VoteEntity> entityList) {
        return entityList.stream().map(this::convertFromEntity).collect(Collectors.toList());
    }

    @Override
    public List<VoteEntity> convertListDtoToEntity(List<VoteDTO> entityList) {
        return entityList.stream().map(this::convertFromDto).collect(Collectors.toList());
    }
}
