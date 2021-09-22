package br.com.sicredi.assembly.meeting.service;

import br.com.sicredi.assembly.agenda.utils.AgendaUtils;
import br.com.sicredi.assembly.meeting.entity.MeetingEntity;
import br.com.sicredi.assembly.meeting.repository.MeetingRepository;
import br.com.sicredi.assembly.meeting.utils.MeetingUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static br.com.sicredi.assembly.meeting.utils.MeetingUtils.*;
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
        MeetingEntity entity = entity2();
        when(repository.save(entity)).thenReturn(entity);

        MeetingEntity newEntity = service.create(entity);

        assertEquals(newEntity.getDescription(), entity.getDescription());
        assertEquals(newEntity.getTitle(), entity.getTitle());

        verify(repository).save(entity);

    }

    @Test
    public void getTest() {
        Optional<MeetingEntity> entity = Optional.of(entity());
        when(repository.findById(entity.get().getId())).thenReturn(entity);

        Optional<MeetingEntity> entity2 = service.get(entity.get().getId());

        assertEquals(entity.get().getTitle(), entity2.get().getTitle());

        verify(repository).findById(entity.get().getId());
    }

    @Test
    public void editTest() {

        when(repository.findById(entity().getId())).thenReturn(Optional.of(entity2()));

        service.edit(entity().getId(), entity());
    }

    @Test
    public void listTest() {
        List<MeetingEntity> entities = Arrays.asList(entity2(), entity());
        when(repository.findAll()).thenReturn(entities);

        List<MeetingEntity> entitiesList = service.list();

        assertEquals(entities.size(), entitiesList.size());

        verify(repository).findAll();
    }

    @Test
    public void deleteTest() {
        Optional<MeetingEntity> entity = Optional.of(MeetingUtils.entity());
        when(repository.findById(entity.get().getId())).thenReturn(entity);
        this.service.delete(entity.get().getId());
        verify(repository).findById(entity.get().getId());
    }
}
