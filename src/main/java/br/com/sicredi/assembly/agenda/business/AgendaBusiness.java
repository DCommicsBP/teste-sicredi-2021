package br.com.sicredi.assembly.agenda.business;

import br.com.sicredi.assembly.agenda.api.converter.AgendaConverter;
import br.com.sicredi.assembly.agenda.dto.AgendaDTO;
import br.com.sicredi.assembly.agenda.dto.ResultAgendaDTO;
import br.com.sicredi.assembly.agenda.entity.AgendaEntity;
import br.com.sicredi.assembly.agenda.service.AgendaService;
import br.com.sicredi.assembly.agenda.util.AgendaUtil;
import br.com.sicredi.assembly.core.interfaces.ServiceInterface;
import br.com.sicredi.assembly.core.validate.DateValidator;
import br.com.sicredi.assembly.meeting.entity.MeetingEntity;
import br.com.sicredi.assembly.meeting.service.MeetingService;
import br.com.sicredi.assembly.vote.enums.VoteEnum;
import lombok.AllArgsConstructor;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
@AllArgsConstructor
public class AgendaBusiness implements ServiceInterface<AgendaDTO> {

    private final AgendaConverter converter;
    private final AgendaService service;
    private final MeetingService meetingService;

    private final Logger log = LogManager.getLogger(AgendaBusiness.class);

    @Override
    public AgendaDTO create(AgendaDTO agendaDTO) {
        Optional<MeetingEntity> meetingEntity = meetingService.get(agendaDTO.getMeetingId());
        AtomicReference<AgendaEntity> agendaEntityAtomicReference = new AtomicReference<>();
        meetingEntity.ifPresent(meeting -> {
            log.debug("Encontrou o evento.");
            DateValidator.validateAgendaDate(agendaDTO.getInitDate(), agendaDTO.getFinishDate(),

                    meeting.getInitDate(), meeting.getFinishDate());
            log.debug("Validou as datas da pauta.");

            agendaEntityAtomicReference.set(converter.convertFromDto(agendaDTO));
        });

        return converter.convertFromEntity(agendaEntityAtomicReference.get());
    }

    @Override
    public Optional<AgendaDTO> get(String id) {
        log.info("Entrou no serviço de buscar a pauta.");

        return service.get(id)
                .map(converter::convertFromEntity);
    }

    @Override
    public void edit(String id, AgendaDTO agendaDTO) {
        Optional<MeetingEntity> meetingEntity = meetingService.get(agendaDTO.getMeetingId());
        meetingEntity.ifPresent(meeting -> {
            log.info("entrou no serviço de editar pauta. ");
            DateValidator.validateAgendaDate(agendaDTO.getInitDate(), agendaDTO.getFinishDate(),
                    meeting.getInitDate(), meeting.getFinishDate());
            service.edit(id,converter.convertFromDto(agendaDTO));

        });
    }

    @Override
    public List<AgendaDTO> list() {
        log.info("Entrou no serviço de listar pauta. ");
        return converter
                .convertListEntityToDto(service.list());
    }

    @Override
    public void delete(String id) {
        log.info("Entrou no serviço de excluir pauta. ");

        service.delete(id);

    }

    public ResultAgendaDTO returnResult(String id){
        AtomicReference<ResultAgendaDTO> result = new AtomicReference<>();
        log.info("Entrou no serviço que monta o resultado da votação. ");

        get(id).ifPresent(agenda-> {
            int countNo = AgendaUtil.calculateResult(agenda.getVoteEnum(), VoteEnum.NO);
            int countYes = AgendaUtil.calculateResult(agenda.getVoteEnum(), VoteEnum.YES);

            result.set(AgendaUtil.resultAgendaDTOBuilder(agenda, countYes, countNo));
            log.info("Processou a votação. ");

            //todo processar a quantidade dos que foram yes - Ok
            //todo processar a quantidade dos que foram no - OK
            //todo adicionar no result agenda dto - OK
        });

        return result.get();
    }
}
