package br.com.sicredi.assembly.vote.vote.api.converter;

import br.com.sicredi.assembly.vote.core.interfaces.ConverterInterface;
import br.com.sicredi.assembly.vote.vote.dto.VoteDTO;
import br.com.sicredi.assembly.vote.vote.entity.VoteEntity;

import java.util.List;
import java.util.stream.Collectors;

public class VoteConverter implements ConverterInterface<VoteDTO, VoteEntity> {
    @Override
    public VoteDTO convertFromEntity(VoteEntity entity) {
        return VoteDTO.builder()
                .finalDate(entity.getFinalDate())
                .id(entity.getId())
                .initDate(entity.getInitDate())
                .membersCPF(entity.getMembersCPF())
                .build();
    }

    @Override
    public VoteEntity convertFromDto(VoteDTO dto) {
        return VoteEntity.builder()
                .finalDate(dto.getFinalDate())
                .id(dto.getId())
                .initDate(dto.getInitDate())
                .membersCPF(dto.getMembersCPF())
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
