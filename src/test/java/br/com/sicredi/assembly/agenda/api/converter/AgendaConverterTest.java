package br.com.sicredi.assembly.agenda.api.converter;

import br.com.sicredi.assembly.agenda.dto.AgendaDTO;
import br.com.sicredi.assembly.agenda.entity.AgendaEntity;
import br.com.sicredi.assembly.agenda.utils.AgendaUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static br.com.sicredi.assembly.agenda.utils.AgendaUtils.dto;
import static br.com.sicredi.assembly.agenda.utils.AgendaUtils.entity2;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class AgendaConverterTest {
    @InjectMocks
    AgendaConverter converter;

    @Test
    public void convertFromEntity() {
        AgendaDTO agendaDTO = converter.convertFromEntity(AgendaUtils.entity2());
        assertEquals(agendaDTO.getDescription(), entity2().getDescription());
    }

    @Test
    public void convertFromDto() {
        AgendaEntity entity = converter.convertFromDto(dto());
        assertEquals(dto().getDescription(), entity.getDescription());

    }

    @Test
    public void convertListEntityToDto() {
        List<AgendaEntity> agendaEntities = Collections.singletonList(entity2());
        List<AgendaDTO> agendaDTOS = converter.convertListEntityToDto(agendaEntities);
        assertEquals(agendaDTOS.size(), agendaEntities.size());
    }

    @Test
    public void convertListDtoToEntity() {
        List<AgendaDTO> agendaEntities = Collections.singletonList(dto());
        List<AgendaEntity> agendaDTOS = converter.convertListDtoToEntity(agendaEntities);
        assertEquals(agendaDTOS.size(), agendaEntities.size());
    }
}