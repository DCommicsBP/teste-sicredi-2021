package br.com.sicredi.assembly.agenda.util;

import br.com.sicredi.assembly.agenda.dto.AgendaDTO;
import br.com.sicredi.assembly.agenda.dto.ResultAgendaDTO;
import br.com.sicredi.assembly.vote.enums.VoteEnum;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AgendaUtil {

    public static int calculateResult(List<VoteEnum> voteEnums, VoteEnum filter){

        return  voteEnums.stream()
                .filter(agendaInternal -> agendaInternal.equals(filter))
                .collect(Collectors.toList()).size();
    }

    public static ResultAgendaDTO resultAgendaDTOBuilder(AgendaDTO agendaDTO, int countYes, int countNo){
        return ResultAgendaDTO.builder()
                .resultNo(countNo)
                .resultYes(countYes)
                .idAgenda(agendaDTO.getId())
                .description(agendaDTO.getDescription())
                .title(agendaDTO.getTitle())
                .build();
    }
}
