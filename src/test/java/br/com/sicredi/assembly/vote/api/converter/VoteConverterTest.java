package br.com.sicredi.assembly.vote.api.converter;

import br.com.sicredi.assembly.vote.dto.VoteDTO;
import br.com.sicredi.assembly.vote.entity.VoteEntity;
import br.com.sicredi.assembly.vote.utils.VoteUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static br.com.sicredi.assembly.membership.utils.MembershipUtils.dto;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class VoteConverterTest {
    @InjectMocks
    VoteConverter converter;

    @Test
    public void convertFromEntity() {
        VoteDTO voteDTO = converter.convertFromEntity(VoteUtils.entity2());
        assertEquals(voteDTO.getId(), VoteUtils.entity2().getId());
    }

    @Test
    public void convertFromDto() {
        VoteEntity entity = converter.convertFromDto(VoteUtils.dto());
        assertEquals(dto().getId(), entity.getId());

    }

    @Test
    public void convertListEntityToDto() {
        List<VoteEntity> voteEntities = Collections.singletonList(VoteUtils.entity2());
        List<VoteDTO> voteDTOS = converter.convertListEntityToDto(voteEntities);
        assertEquals(voteDTOS.size(), voteEntities.size());
    }

    @Test
    public void convertListDtoToEntity() {
        List<VoteDTO> voteDTOS = Collections.singletonList(VoteUtils.dto());
        List<VoteEntity> voteEntities = converter.convertListDtoToEntity(voteDTOS);
        assertEquals(voteEntities.size(), voteDTOS.size());
    }
}