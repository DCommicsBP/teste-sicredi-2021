package br.com.sicredi.assembly.meeting.business;

import br.com.sicredi.assembly.meeting.api.converter.MeetingConverter;
import br.com.sicredi.assembly.meeting.dto.MeetingDTO;
import br.com.sicredi.assembly.meeting.entity.MeetingEntity;
import br.com.sicredi.assembly.meeting.service.MeetingService;
import br.com.sicredi.assembly.meeting.util.MeetingUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MeetingBusinessTest {

    @Mock
    MeetingService service;

    @Mock
    MeetingConverter converter;
    @InjectMocks
    MeetingBusiness business;

    @Test
    public void createTest() {
        MeetingEntity meetingEntity = MeetingUtil.entity();
        MeetingEntity meetingEntity2 = MeetingUtil.entity();
        MeetingDTO meetingDTO = MeetingUtil.dto();


        when(converter.convertFromDto(meetingDTO)).thenReturn(meetingEntity2);
        when(converter.convertFromEntity(meetingEntity2)).thenReturn(meetingDTO);

        MeetingDTO meetingDTO1 = business.create(meetingDTO);
        assertEquals(meetingDTO1.getId(), meetingDTO.getId());
        assertEquals(meetingDTO1.getDescription(), meetingDTO.getDescription());
        assertEquals(meetingDTO1.getFinishDate(), meetingDTO.getFinishDate());
        assertEquals(meetingDTO1.getTitle(), meetingDTO.getTitle());

        verify(converter).convertFromDto(meetingDTO);
        verify(converter).convertFromEntity(meetingEntity2);
    }

    @Test
    public void getTest() {
        MeetingDTO meetingDTO = MeetingUtil.dto();
        MeetingEntity meetingEntity = MeetingUtil.entity();

        when(service.get(meetingEntity.getId())).thenReturn(Optional.of(meetingEntity));

        business.get(meetingEntity.getId()).ifPresent(meetingDTO1 -> {
            assertEquals(meetingDTO1.getId(), meetingDTO.getId());
            assertEquals(meetingDTO1.getDescription(), meetingDTO.getDescription());
            assertEquals(meetingDTO1.getFinishDate(), meetingDTO.getFinishDate());
            assertEquals(meetingDTO1.getTitle(), meetingDTO.getTitle());
        });

        verify(service).get(meetingEntity.getId());
        verify(converter).convertFromEntity(meetingEntity);


    }

    @Test
    public void editTest() {
        MeetingDTO meetingDTO = MeetingUtil.dto();
        MeetingEntity meetingEntity = MeetingUtil.entity();

        when(converter.convertFromDto(meetingDTO)).thenReturn(meetingEntity);
        business.edit(meetingDTO.getId(), meetingDTO);

        verify(converter).convertFromDto(meetingDTO);

    }

    @Test
    public void listTest() {
        List<MeetingDTO> meetingDTOList = Arrays.asList(MeetingUtil.dto(), MeetingUtil.dto2());
        List<MeetingEntity> meetingEntities = Arrays.asList(MeetingUtil.entity(),MeetingUtil.entity2());

        when(converter.convertListEntityToDto(meetingEntities)).thenReturn(meetingDTOList);

        when(service.list()).thenReturn(meetingEntities);

        List<MeetingDTO> list = business.list();
        AtomicInteger counter = new AtomicInteger(0);
        list.forEach(item-> {
            assertEquals(item.getDescription(), meetingDTOList.get(counter.get()).getDescription());
            assertEquals(item.getTitle(), meetingDTOList.get(counter.get()).getTitle());
            counter.addAndGet(1);
        });
        verify(converter).convertListEntityToDto(meetingEntities);
    }

    @Test
    public void deleteTest() {
        business.delete("any");
    }
}