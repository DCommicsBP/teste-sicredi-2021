package br.com.sicredi.assembly.agenda.api.converter;

import br.com.sicredi.assembly.agenda.entity.AgendaEntity;
import br.com.sicredi.assembly.core.interfaces.ConverterInterface;
import br.com.sicredi.assembly.agenda.dto.AgendaDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AgendaConverter implements ConverterInterface<AgendaDTO, AgendaEntity> {

    @Override
    public AgendaDTO convertFromEntity(AgendaEntity entity) {
        return AgendaDTO.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .initDate(entity.getInitVote())
                .finishDate(entity.getFinishVote())
                .build();
    }

    @Override
    public AgendaEntity convertFromDto(AgendaDTO dto) {
        return AgendaEntity.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .description(dto.getDescription())
                .finishVote(dto.getFinishDate())
                .initVote(dto.getInitDate())
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
