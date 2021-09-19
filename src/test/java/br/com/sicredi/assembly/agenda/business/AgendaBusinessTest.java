package br.com.sicredi.assembly.agenda.business;

import br.com.sicredi.assembly.agenda.api.converter.AgendaConverter;
import br.com.sicredi.assembly.agenda.dto.AgendaDTO;
import br.com.sicredi.assembly.agenda.entity.AgendaEntity;
import br.com.sicredi.assembly.agenda.service.AgendaService;
import br.com.sicredi.assembly.agenda.utils.AgendaUtils;
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

import static br.com.sicredi.assembly.agenda.utils.AgendaUtils.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AgendaBusinessTest {

    @Mock
    AgendaService service;

    @Mock
    MeetingService meetingService;

    @Mock
    AgendaConverter converter;

    @InjectMocks
    AgendaBusiness business;


    @Test
    public void createTest() {
        AgendaDTO agenda = dto();
        MeetingEntity meetingEntity = MeetingUtil.entity();

        AgendaEntity agendaEntity = AgendaUtils.entity(agenda);

        when(meetingService.get(anyString())).thenReturn(Optional.of(meetingEntity));
        when(service.create(agendaEntity)).thenReturn(agendaEntity);
        when(converter.convertFromDto(agenda)).thenReturn(agendaEntity);
        when(converter.convertFromEntity(agendaEntity)).thenReturn(agenda);

        AgendaDTO agendaDTO = business.create(agenda);
        assertEquals(agendaDTO.getId(), agendaEntity.getId());
        assertEquals(agendaDTO.getDescription(), agendaEntity.getDescription());
        assertEquals(agendaDTO.getFinishDate(), agendaEntity.getFinishVote());
        assertEquals(agendaDTO.getTitle(), agendaEntity.getTitle());

        verify(service).create(agendaEntity);
        verify(converter).convertFromDto(agenda);
        verify(converter).convertFromEntity(agendaEntity);
    }

    @Test
    public void getTest() {
        AgendaDTO agenda = dto();
        AgendaEntity agendaEntity = AgendaUtils.entity(agenda);

        when(service.get(agendaEntity.getId())).thenReturn(Optional.of(agendaEntity));

        business.get(agendaEntity.getId()).ifPresent(agendaDTO -> {
            assertEquals(agendaDTO.getId(), agendaEntity.getId());
            assertEquals(agendaDTO.getDescription(), agendaEntity.getDescription());
            assertEquals(agendaDTO.getFinishDate(), agendaEntity.getFinishVote());
            assertEquals(agendaDTO.getTitle(), agendaEntity.getTitle());
        });

        verify(service).get(agendaEntity.getId());
        verify(converter).convertFromEntity(agendaEntity);


    }

    @Test
    public void editTest() {
        AgendaDTO agenda = dto();
        AgendaEntity agendaEntity = AgendaUtils.entity(agenda);
        MeetingEntity meetingEntity = MeetingUtil.entity();

        when(meetingService.get(anyString())).thenReturn(Optional.of(meetingEntity));
        when(converter.convertFromDto(agenda)).thenReturn(agendaEntity);
        business.edit(agenda.getId(), agenda);

        verify(converter).convertFromDto(agenda);

    }

    @Test
    public void listTest() {
        List<AgendaDTO> agendaDTOS = Arrays.asList(dto(), dto2());
        List<AgendaEntity> agendaEntities = Arrays.asList(entity(dto()), entity2());

        when(converter.convertListEntityToDto(agendaEntities)).thenReturn(agendaDTOS);

        when(service.list()).thenReturn(agendaEntities);

        List<AgendaDTO> list = business.list();
        AtomicInteger counter = new AtomicInteger(0);
        list.forEach(item-> {
            assertEquals(item.getDescription(), agendaDTOS.get(counter.get()).getDescription());
            assertEquals(item.getTitle(), agendaDTOS.get(counter.get()).getTitle());
            counter.addAndGet(1);
        });
        verify(converter).convertListEntityToDto(agendaEntities);
    }

    @Test
    public void deleteTest() {
        business.delete("any");
    }
}