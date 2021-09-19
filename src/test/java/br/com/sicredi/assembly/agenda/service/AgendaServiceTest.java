package br.com.sicredi.assembly.agenda.service;

;
import br.com.sicredi.assembly.agenda.entity.AgendaEntity;
import br.com.sicredi.assembly.agenda.repository.AgendaRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static br.com.sicredi.assembly.agenda.utils.AgendaUtils.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AgendaServiceTest {

    @Mock
    private AgendaRepository repository;

    @InjectMocks
    private AgendaService service;

    @Test
    public void createTest() {
        AgendaEntity entity = entity2();
        when(repository.save(entity)).thenReturn(entity);

        AgendaEntity newEntity = service.create(entity);

        assertEquals(newEntity.getDescription(), entity.getDescription());
        assertEquals(newEntity.getFinishVote(), entity.getFinishVote());
        assertEquals(newEntity.getTitle(), entity.getTitle());

        verify(repository).save(entity);

    }

    @Test
    public void getTest() {
        Optional<AgendaEntity> entity = Optional.of(entity(dto()));
        when(repository.findById(entity.get().getId())).thenReturn(entity);

        Optional<AgendaEntity> entity2 = service.get(entity.get().getId());

        assertEquals(entity.get().getTitle(), entity2.get().getTitle());

        verify(repository).findById(entity.get().getId());
    }

    @Test
    public void editTest() {

        when(repository.findById(entity(dto()).getId())).thenReturn(Optional.of(entity2()));

        service.edit(entity(dto()).getId(), entity(dto()));


    }

    @Test
    public void list() {
        List<AgendaEntity> entities = Arrays.asList(entity2(), entity(dto()));
        when(repository.findAll()).thenReturn(entities);

        List<AgendaEntity> entitiesList = service.list();

        assertEquals(entities.size(), entitiesList.size());

        verify(repository).findAll();
    }

    @Test
    public void delete() {
        String id = anyString();
        this.service.delete(id);
        verify(repository).findById(id);
    }
}