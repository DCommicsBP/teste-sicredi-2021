package br.com.sicredi.assembly.meeting.business;

import br.com.sicredi.assembly.agenda.business.AgendaBusiness;
import br.com.sicredi.assembly.core.interfaces.ServiceInterface;
import br.com.sicredi.assembly.meeting.api.converter.MeetingConverter;
import br.com.sicredi.assembly.meeting.dto.MeetingDTO;
import br.com.sicredi.assembly.meeting.entity.MeetingEntity;
import br.com.sicredi.assembly.meeting.service.MeetingService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MeetingBusiness implements ServiceInterface<MeetingDTO> {

    private final MeetingService service;
    private final MeetingConverter converter;
    private static final Logger log = LogManager.getLogger(AgendaBusiness.class);

    @Override
    public MeetingDTO create(MeetingDTO meetingDTO) {
        log.info("Entrou no serviço que cria nova assembléia. ");
        MeetingEntity meetingEntity = service.create(converter.convertFromDto(meetingDTO));
        return converter.convertFromEntity(meetingEntity);
    }

    @Override
    public Optional<MeetingDTO> get(String id) {
        log.info("Entrou no serviço que busca assembléia. ");
        return service.get(id).map(converter::convertFromEntity);
    }

    @Override
    public void edit(String id, MeetingDTO meetingDTO) {
        log.info("Entrou no serviço que edita assembléia. ");
        service.edit(id,converter.convertFromDto(meetingDTO));
    }

    @Override
    public List<MeetingDTO> list() {
        log.info("Entrou no serviço que lista assembléias. ");
        return converter.convertListEntityToDto(service.list());
    }

    @Override
    public void delete(String id) {
        log.info("Entrou no serviço que deleta assembléia. ");
        service.delete(id);
    }
}
