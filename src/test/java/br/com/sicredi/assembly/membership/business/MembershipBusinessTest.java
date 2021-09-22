package br.com.sicredi.assembly.membership.business;
import br.com.sicredi.assembly.membership.api.converter.MemberShipConverter;
import br.com.sicredi.assembly.membership.dto.MembershipDTO;
import br.com.sicredi.assembly.membership.entity.MembershipEntity;
import br.com.sicredi.assembly.membership.service.MembershipService;
import br.com.sicredi.assembly.membership.utils.MembershipUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MembershipBusinessTest {

    @Mock
    MembershipService service;
    @Mock
    MemberShipConverter converter;
    @Test
    public void getTest() {
        MembershipDTO membershipDTO = MembershipUtils.dto();
        MembershipEntity membershipEntity = MembershipUtils.entity1();

        when(service.get(membershipEntity.getId())).thenReturn(Optional.of(membershipEntity));

        business.get(membershipEntity.getId()).ifPresent(membershipDTO1 -> {
            assertEquals(membershipDTO1.getId(), membershipEntity.getId());
            assertEquals(membershipDTO1.getCpf(), membershipEntity.getCpf());
        });

        verify(service).get(membershipEntity.getId());
        verify(converter).convertFromEntity(membershipEntity);


    }

    @InjectMocks
    MembershipBusiness business;

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void createTest() {
        MembershipDTO membershipDTO = MembershipUtils.dto();
        MembershipEntity membershipEntity = MembershipUtils.entity1();

        when(service.list()).thenReturn(Collections.singletonList(MembershipUtils.entity2()));
        when(service.create(membershipEntity)).thenReturn(membershipEntity);
        when(converter.convertFromDto(membershipDTO)).thenReturn(membershipEntity);
        when(converter.convertFromEntity(membershipEntity)).thenReturn(membershipDTO);

        membershipDTO.setCpf("66938017069");
        MembershipDTO membershipDTO1 = business.create(membershipDTO);

        assertEquals(membershipDTO.getId(),membershipDTO1.getId());
        assertEquals(membershipDTO.getCpf(), membershipDTO1.getCpf());

        verify(converter).convertFromDto(membershipDTO);
        verify(converter).convertFromEntity(membershipEntity);
    }

    @Test
    public void editTest() {
        MembershipDTO membershipDTO = MembershipUtils.dto();
        MembershipEntity membershipEntity = MembershipUtils.entity1();

        when(service.list()).thenReturn(Collections.singletonList(membershipEntity));
        when(converter.convertFromDto(membershipDTO)).thenReturn(membershipEntity);
        business.edit(membershipEntity.getId(), membershipDTO);

        verify(converter).convertFromDto(membershipDTO);

    }

    @Test
    public void listTest() {
        List<MembershipDTO> membershipDTOS = Arrays.asList(MembershipUtils.dto(), MembershipUtils.dto2());
        List<MembershipEntity> membershipEntities = Arrays.asList(MembershipUtils.entity1(), MembershipUtils.entity2());

        when(converter.convertListEntityToDto(membershipEntities)).thenReturn(membershipDTOS);

        when(service.list()).thenReturn(membershipEntities);

        List<MembershipDTO> list = business.list();
        AtomicInteger counter = new AtomicInteger(0);
        list.forEach(item-> {
            assertEquals(item.getCpf(), membershipDTOS.get(counter.get()).getCpf());
            assertEquals(item.getId(), membershipDTOS.get(counter.get()).getId());
            counter.addAndGet(1);
        });
        verify(converter).convertListEntityToDto(membershipEntities);
    }


    @Test
    public void deleteTest() {
        business.delete("any");
    }
}