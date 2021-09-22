package br.com.sicredi.assembly.meeting.api.converter;

import br.com.sicredi.assembly.meeting.dto.MeetingDTO;
import br.com.sicredi.assembly.meeting.entity.MeetingEntity;
import br.com.sicredi.assembly.meeting.utils.MeetingUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static br.com.sicredi.assembly.agenda.utils.AgendaUtils.dto;
import static br.com.sicredi.assembly.agenda.utils.AgendaUtils.entity2;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class MeetingConverterTest {
    @InjectMocks
    MeetingConverter converter;

    @Test
    public void convertFromEntity() {
        MeetingDTO meetingDTO = converter.convertFromEntity(MeetingUtils.entity());
        assertEquals(meetingDTO.getDescription(), MeetingUtils.entity().getDescription());
    }

    @Test
    public void convertFromDto() {
        MeetingEntity entity = converter.convertFromDto(MeetingUtils.dto());
        assertEquals(MeetingUtils.dto().getDescription(), entity.getDescription());

    }

    @Test
    public void convertListEntityToDto() {
        List<MeetingEntity> meetingEntities = Collections.singletonList(MeetingUtils.entity2());
        List<MeetingDTO> meetingDTOList = converter.convertListEntityToDto(meetingEntities);
        assertEquals(meetingDTOList.size(), meetingEntities.size());
    }

    @Test
    public void convertListDtoToEntity() {
        List<MeetingDTO> meetingDTOS = Collections.singletonList(MeetingUtils.dto());
        List<MeetingEntity> meetEntyties = converter.convertListDtoToEntity(meetingDTOS);
        assertEquals(meetingDTOS.size(), meetEntyties.size());
    }
}
