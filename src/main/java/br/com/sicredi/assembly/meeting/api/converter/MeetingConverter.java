package br.com.sicredi.assembly.meeting.api.converter;

import br.com.sicredi.assembly.core.interfaces.ConverterInterface;
import br.com.sicredi.assembly.meeting.dto.MeetingDTO;
import br.com.sicredi.assembly.meeting.entity.MeetingEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MeetingConverter implements ConverterInterface<MeetingDTO, MeetingEntity> {
    @Override
    public MeetingDTO convertFromEntity(MeetingEntity entity) {
        return MeetingDTO.builder()
                .id(entity.getId())
                .description(entity.getDescription())
                .finishDate(entity.getFinishDate())
                .initDate(entity.getInitDate())
                .title(entity.getTitle())
                .build();
    }

    @Override
    public MeetingEntity convertFromDto(MeetingDTO dto) {
        return MeetingEntity.builder()
                .id(dto.getId())
                .description(dto.getDescription())
                .finishDate(dto.getFinishDate())
                .initDate(dto.getInitDate())
                .title(dto.getTitle())
                .build();
    }

    @Override
    public List<MeetingDTO> convertListEntityToDto(List<MeetingEntity> entityList) {
        return entityList.stream().map(this::convertFromEntity).collect(Collectors.toList());
    }

    @Override
    public List<MeetingEntity> convertListDtoToEntity(List<MeetingDTO> entityList) {
        return entityList.stream().map(this::convertFromDto).collect(Collectors.toList());
    }
}
