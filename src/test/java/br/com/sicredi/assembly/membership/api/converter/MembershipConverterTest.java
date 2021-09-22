package br.com.sicredi.assembly.membership.api.converter;

import br.com.sicredi.assembly.membership.dto.MembershipDTO;
import br.com.sicredi.assembly.membership.entity.MembershipEntity;
import br.com.sicredi.assembly.membership.utils.MembershipUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static br.com.sicredi.assembly.membership.utils.MembershipUtils.dto;
import static br.com.sicredi.assembly.membership.utils.MembershipUtils.entity2;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class MembershipConverterTest {
    @InjectMocks
    MemberShipConverter converter;

    @Test
    public void convertFromEntity() {
        MembershipDTO membershipDTO = converter.convertFromEntity(MembershipUtils.entity2());
        assertEquals(membershipDTO.getCpf(), entity2().getCpf());
    }

    @Test
    public void convertFromDto() {
        MembershipEntity entity = converter.convertFromDto(dto());
        assertEquals(dto().getCpf(), entity.getCpf());

    }

    @Test
    public void convertListEntityToDto() {
        List<MembershipEntity> membershipEntities = Collections.singletonList(entity2());
        List<MembershipDTO> membershipDTOS = converter.convertListEntityToDto(membershipEntities);
        assertEquals(membershipDTOS.size(),membershipEntities.size());
    }

    @Test
    public void convertListDtoToEntity() {
        List<MembershipDTO> membershipDTOS = Collections.singletonList(dto());
        List<MembershipEntity> membershipEntities = converter.convertListDtoToEntity(membershipDTOS);
        assertEquals(membershipEntities.size(), membershipDTOS.size());
    }
}
