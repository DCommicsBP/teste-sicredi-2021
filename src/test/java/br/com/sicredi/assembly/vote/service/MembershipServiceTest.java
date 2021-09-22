package br.com.sicredi.assembly.vote.service;

import br.com.sicredi.assembly.vote.entity.VoteEntity;
import br.com.sicredi.assembly.vote.repository.VoteRepository;
import br.com.sicredi.assembly.vote.utils.VoteUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MembershipServiceTest {

    @Mock
    private VoteRepository repository;

    @InjectMocks
    private VoteService service;

    @Test
    public void createTest() {
        VoteEntity entity = VoteUtils.entity2();
        when(repository.save(entity)).thenReturn(entity);

        VoteEntity newEntity = service.create(entity);

        assertEquals(newEntity.getAgendaId(), entity.getAgendaId());
        assertEquals(newEntity.getTime(), entity.getTime());

        verify(repository).save(entity);

    }

    @Test
    public void getTest() {
        Optional<VoteEntity> entity = Optional.of(VoteUtils.entity1());
        when(repository.findById(entity.get().getId())).thenReturn(entity);

        Optional<VoteEntity> entity2 = service.get(entity.get().getId());

        assertEquals(entity.get().getId(), entity2.get().getId());

        verify(repository).findById(entity.get().getId());
    }

    @Test
    public void editTest() {

        when(repository.findById(VoteUtils.entity1().getId())).thenReturn(Optional.of(VoteUtils.entity1()));
        service.edit(VoteUtils.entity1().getId(), VoteUtils.entity2());

        verify(repository).findById(VoteUtils.entity1().getId());
    }

    @Test
    public void listTest() {
        List<VoteEntity> entities = Arrays.asList(VoteUtils.entity2(),VoteUtils.entity1());
        when(repository.findAll()).thenReturn(entities);

        List<VoteEntity> entitiesList = service.list();

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