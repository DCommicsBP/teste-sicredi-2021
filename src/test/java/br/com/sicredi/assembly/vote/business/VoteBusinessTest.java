package br.com.sicredi.assembly.vote.business;

import br.com.sicredi.assembly.agenda.business.AgendaBusiness;
import br.com.sicredi.assembly.agenda.dto.AgendaDTO;
import br.com.sicredi.assembly.agenda.util.AgendaUtil;
import br.com.sicredi.assembly.agenda.utils.AgendaUtils;
import br.com.sicredi.assembly.meeting.business.MeetingBusiness;
import br.com.sicredi.assembly.membership.business.MembershipBusiness;
import br.com.sicredi.assembly.membership.dto.MembershipDTO;
import br.com.sicredi.assembly.membership.utils.MembershipUtils;
import br.com.sicredi.assembly.vote.api.converter.VoteConverter;
import br.com.sicredi.assembly.vote.dto.VoteDTO;
import br.com.sicredi.assembly.vote.entity.VoteEntity;
import br.com.sicredi.assembly.vote.enums.VoteEnum;
import br.com.sicredi.assembly.vote.service.VoteService;
import br.com.sicredi.assembly.vote.utils.VoteUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class VoteBusinessTest {

    @Mock
    VoteService service;
    @Mock
    VoteConverter converter;
    @Mock
    MembershipBusiness membershipBusiness;
    @Mock
    AgendaBusiness agendaBusiness;

    @InjectMocks
    VoteBusiness business;

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void getTest() {
        VoteDTO voteDTO = VoteUtils.dto();
        VoteEntity voteEntity = VoteUtils.entity1();

        when(service.get(voteEntity.getId())).thenReturn(Optional.of(voteEntity));

        business.get(voteEntity.getId()).ifPresent(voteDTO1 -> {
            assertEquals(voteDTO1.getId(), voteEntity.getId());
            assertEquals(voteDTO1.getAgendaId(), voteEntity.getAgendaId());
        });

        verify(service).get(voteEntity.getId());
        verify(converter).convertFromEntity(voteEntity);


    }


    @Test
    public void createTest() {
        VoteDTO voteDTO = VoteUtils.dto();
        voteDTO.setTime(LocalDateTime.now().plusSeconds(30));
        voteDTO.setMemberCpf("66938017069");
        voteDTO.setVoteEnum(VoteEnum.NO);

        VoteEntity voteEntity = VoteUtils.entity1();
        when(service.create(voteEntity)).thenReturn(voteEntity);
        when(converter.convertFromDto(voteDTO)).thenReturn(voteEntity);
        when(converter.convertFromEntity(voteEntity)).thenReturn(voteDTO);

        MembershipDTO membershipDTO = MembershipUtils.dto();
        when(membershipBusiness.getByCpf(voteDTO.getMemberCpf())).thenReturn(Optional.of(membershipDTO));

        AgendaDTO agendaDTO = AgendaUtils.dto();
        agendaDTO.setId(voteDTO.getAgendaId());
        agendaDTO.setMembershipCpf(Arrays.asList("02425501063"));
        when(agendaBusiness.get(voteDTO.getAgendaId())).thenReturn(Optional.of(agendaDTO));

        VoteDTO membershipDTO1 = business.create(voteDTO);

        assertEquals(voteDTO.getId(), membershipDTO1.getId());
        assertEquals(voteDTO.getAgendaId(), membershipDTO1.getAgendaId());

        verify(converter).convertFromDto(voteDTO);
        verify(converter).convertFromEntity(voteEntity);
    }

    @Test
    public void editTest() {
        VoteDTO voteDTO = VoteUtils.dto();
        VoteEntity voteEntity = VoteUtils.entity1();

        when(converter.convertFromDto(voteDTO)).thenReturn(voteEntity);
        business.edit(voteEntity.getId(), voteDTO);

        verify(converter).convertFromDto(voteDTO);

    }

    @Test
    public void listTest() {
        List<VoteDTO> voteDTOList = Arrays.asList(VoteUtils.dto(), VoteUtils.dto2());
        List<VoteEntity> voteEntities = Arrays.asList(VoteUtils.entity1(), VoteUtils.entity2());

        when(converter.convertListEntityToDto(voteEntities)).thenReturn(voteDTOList);

        when(service.list()).thenReturn(voteEntities);

        List<VoteDTO> list = business.list();
        AtomicInteger counter = new AtomicInteger(0);
        list.forEach(item -> {
            assertEquals(item.getId(), voteDTOList.get(counter.get()).getId());
            counter.addAndGet(1);
        });
        verify(converter).convertListEntityToDto(voteEntities);
    }


    @Test
    public void deleteTest() {
        business.delete("any");
    }
}