package br.com.sicredi.assembly.meeting.service;

import br.com.sicredi.assembly.agenda.entity.AgendaEntity;
import br.com.sicredi.assembly.meeting.entity.MeetingEntity;
import br.com.sicredi.assembly.meeting.repository.MeetingRepository;
import br.com.sicredi.assembly.meeting.util.MeetingUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static br.com.sicredi.assembly.agenda.utils.AgendaUtils.*;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MeetingServiceTest {


    @Mock
    private MeetingRepository repository;

    @InjectMocks
    private MeetingService service;

    @Test
    public void createTest() {
        MeetingEntity entity = MeetingUtil.entity2();
        when(repository.save(entity)).thenReturn(entity);

        MeetingEntity newEntity = service.create(entity);

        assertEquals(newEntity.getDescription(), entity.getDescription());
        assertEquals(newEntity.getTitle(), entity.getTitle());

        verify(repository).save(entity);

    }

    @Test
    public void getTest() {
        Optional<MeetingEntity> entity = Optional.of(MeetingUtil.entity());
        when(repository.findById(entity.get().getId())).thenReturn(entity);

        Optional<MeetingEntity> entity2 = service.get(entity.get().getId());

        assertEquals(entity.get().getTitle(), entity2.get().getTitle());

        verify(repository).findById(entity.get().getId());
    }

    @Test
    public void editTest() {

        when(repository.findById(entity(dto()).getId())).thenReturn(Optional.of(MeetingUtil.entity2()));

        service.edit(entity(dto()).getId(), MeetingUtil.entity());
    }

    @Test
    public void listTest() {
        List<MeetingEntity> entities = Arrays.asList(MeetingUtil.entity2(), MeetingUtil.entity());
        when(repository.findAll()).thenReturn(entities);

        List<MeetingEntity> entitiesList = service.list();

        assertEquals(entities.size(), entitiesList.size());

        verify(repository).findAll();
    }

    @Test
    public void deleteTest() {
        String id = anyString();
        this.service.delete(id);
        verify(repository).findById(id);
    }
}
