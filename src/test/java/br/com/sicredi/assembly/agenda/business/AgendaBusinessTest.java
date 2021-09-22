package br.com.sicredi.assembly.agenda.business;

import br.com.sicredi.assembly.agenda.api.converter.AgendaConverter;
import br.com.sicredi.assembly.agenda.dto.AgendaDTO;
import br.com.sicredi.assembly.agenda.dto.ResultAgendaDTO;
import br.com.sicredi.assembly.agenda.entity.AgendaEntity;
import br.com.sicredi.assembly.agenda.service.AgendaService;
import br.com.sicredi.assembly.agenda.utils.AgendaUtils;
import br.com.sicredi.assembly.core.exceptions.NotFoundException;
import br.com.sicredi.assembly.meeting.entity.MeetingEntity;
import br.com.sicredi.assembly.meeting.service.MeetingService;
import br.com.sicredi.assembly.meeting.utils.MeetingUtils;
import br.com.sicredi.assembly.vote.enums.VoteEnum;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import static br.com.sicredi.assembly.agenda.utils.AgendaUtils.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
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

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void createTest() {
        AgendaDTO agenda = dto();
        MeetingEntity meetingEntity = MeetingUtils.entity();

        AgendaEntity agendaEntity = AgendaUtils.entity(agenda);

        when(meetingService.get(anyString())).thenReturn(Optional.of(meetingEntity));
        when(converter.convertFromDto(agenda)).thenReturn(agendaEntity);
        when(converter.convertFromEntity(agendaEntity)).thenReturn(agenda);

        AgendaDTO agendaDTO = business.create(agenda);
        assertEquals(agendaDTO.getId(), agendaEntity.getId());
        assertEquals(agendaDTO.getDescription(), agendaEntity.getDescription());
        assertEquals(agendaDTO.getFinishDate(), agendaEntity.getFinishVote());
        assertEquals(agendaDTO.getTitle(), agendaEntity.getTitle());

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
        MeetingEntity meetingEntity = MeetingUtils.entity();

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
    public void resultAgendaTest(){
        ResultAgendaDTO agendaDTO = ResultAgendaDTO.builder()
                .idAgenda("id01").description("description01")
                .resultYes(1).resultNo(4).title("title 1")
                .build();
        AgendaDTO dto = AgendaDTO.builder()
                .id("id01").description("description01")
                .title("title 1").finishDate(LocalDateTime.now())
                .initDate(LocalDateTime.now())
                .meetingId("idMeeting")
                .voteEnum(Collections.singletonList(VoteEnum.YES))
                .build();

        when(converter.convertFromEntity(any(AgendaEntity.class))).thenReturn(dto);
        when(service.get(agendaDTO.getIdAgenda())).thenReturn(Optional.of(entity(dto())));
        ResultAgendaDTO resultAgendaDTO = business.returnResult(agendaDTO.getIdAgenda());
        assertNotNull(resultAgendaDTO);

        verify(service).get(agendaDTO.getIdAgenda());
        verify(converter).convertFromEntity(any(AgendaEntity.class));

    }

    @Test
    public void resultAgendaWhenNotFoundAgendaTest(){

        exception.expect(NotFoundException.class);
        when(service.get("ID01")).thenThrow(NotFoundException.class);
        business.returnResult("ID01");

    }

    @Test
    public void deleteTest() {
        business.delete("any");
    }
}