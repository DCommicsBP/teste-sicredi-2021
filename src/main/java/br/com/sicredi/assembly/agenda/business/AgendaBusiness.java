package br.com.sicredi.assembly.agenda.business;

import br.com.sicredi.assembly.agenda.api.converter.AgendaConverter;
import br.com.sicredi.assembly.agenda.dto.AgendaDTO;
import br.com.sicredi.assembly.agenda.entity.AgendaEntity;
import br.com.sicredi.assembly.agenda.service.AgendaService;
import br.com.sicredi.assembly.core.interfaces.ServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AgendaBusiness implements ServiceInterface<AgendaDTO> {

    private final AgendaConverter converter;
    private final AgendaService service;

    @Override
    public AgendaDTO create(AgendaDTO agendaDTO) {
        AgendaEntity entity = converter.convertFromDto(agendaDTO);
        return converter.convertFromEntity(service.create(entity));
    }

    @Override
    public Optional<AgendaDTO> get(String id) {
        return service.get(id)
                .map(converter::convertFromEntity);
    }

    @Override
    public void edit(String id, AgendaDTO agendaDTO) {
        service.edit(id,converter.convertFromDto(agendaDTO));

    }

    @Override
    public List<AgendaDTO> list() {
        return converter
                .convertListEntityToDto(service.list());
    }

    @Override
    public void delete(String id) {
        service.delete(id);

    }
}
