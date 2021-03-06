package br.com.sicredi.assembly.membership.service;

import br.com.sicredi.assembly.membership.entity.MembershipEntity;
import br.com.sicredi.assembly.membership.repository.MembershipRepository;
import br.com.sicredi.assembly.membership.utils.MembershipUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@RunWith(MockitoJUnitRunner.class)
public class MembershipServiceTest {

    @Mock
    private MembershipRepository repository;

    @InjectMocks
    private MembershipService service;

    @Test
    public void createTest() {
        MembershipEntity entity = MembershipUtils.entity2();
        when(repository.save(entity)).thenReturn(entity);

        MembershipEntity newEntity = service.create(entity);

        assertEquals(newEntity.getCpf(), entity.getCpf());
        assertEquals(newEntity.getName(), entity.getName());

        verify(repository).save(entity);

    }

    @Test
    public void getTest() {
        Optional<MembershipEntity> entity = Optional.of(MembershipUtils.entity1());
        when(repository.findById(entity.get().getId())).thenReturn(entity);

        Optional<MembershipEntity> entity2 = service.get(entity.get().getId());

        assertEquals(entity.get().getId(), entity2.get().getId());

        verify(repository).findById(entity.get().getId());
    }

    @Test
    public void editTest() {

        when(repository.findById(MembershipUtils.entity1().getId())).thenReturn(Optional.of(MembershipUtils.entity1()));
        service.edit(MembershipUtils.entity1().getId(), MembershipUtils.entity2());

        verify(repository).findById(MembershipUtils.entity1().getId());
    }

    @Test
    public void listTest() {
        List<MembershipEntity> entities = Arrays.asList(MembershipUtils.entity2(), MembershipUtils.entity1());
        when(repository.findAll()).thenReturn(entities);

        List<MembershipEntity> entitiesList = service.list();

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