package br.com.sicredi.assembly.vote.agenda.api.converter;

import br.com.sicredi.assembly.vote.agenda.dto.AgendaDTO;
import br.com.sicredi.assembly.vote.agenda.entity.AgendaEntity;
import br.com.sicredi.assembly.vote.core.interfaces.ConverterInterface;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AgendaConverter implements ConverterInterface<AgendaDTO, AgendaEntity> {

    @Override
    public AgendaDTO convertFromEntity(AgendaEntity entity) {
        return AgendaDTO.builder()
                .id(entity.getId())
                .titulo(entity.getTitulo())
                .descricao(entity.getDescricao())
                .build();
    }

    @Override
    public AgendaEntity convertFromDto(AgendaDTO dto) {
        return AgendaEntity.builder()
                .id(dto.getId())
                .titulo(dto.getTitulo())
                .descricao(dto.getDescricao())
                .build();
    }

    @Override
    public List<AgendaDTO> convertListEntityToDto(List<AgendaEntity> entityList) {
        return entityList.stream().map(this::convertFromEntity).collect(Collectors.toList());
    }

    @Override
    public List<AgendaEntity> convertListDtoToEntity(List<AgendaDTO> entityList){
        return entityList.stream().map(this::convertFromDto).collect(Collectors.toList());
    }
}
