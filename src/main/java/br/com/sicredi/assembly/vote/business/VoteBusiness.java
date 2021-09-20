package br.com.sicredi.assembly.vote.business;

import br.com.sicredi.assembly.agenda.business.AgendaBusiness;
import br.com.sicredi.assembly.agenda.dto.AgendaDTO;
import br.com.sicredi.assembly.core.exceptions.BadRequestException;
import br.com.sicredi.assembly.core.interfaces.ServiceInterface;
import br.com.sicredi.assembly.core.validate.DateValidator;
import br.com.sicredi.assembly.membership.business.MembershipBusiness;
import br.com.sicredi.assembly.membership.dto.MembershipDTO;
import br.com.sicredi.assembly.vote.api.converter.VoteConverter;
import br.com.sicredi.assembly.vote.dto.VoteDTO;
import br.com.sicredi.assembly.vote.entity.VoteEntity;
import br.com.sicredi.assembly.vote.service.VoteService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;


@Service
@AllArgsConstructor
public class VoteBusiness implements ServiceInterface<VoteDTO> {

    private final VoteService service;

    private final AgendaBusiness agendaBusiness;
    private final MembershipBusiness membershipBusiness;

    private final VoteConverter converter;

    private static final Logger log = LogManager.getLogger(AgendaBusiness.class);

    @Override
    public VoteDTO create(VoteDTO voteDTO) {
        log.info("Entrando no serviço que cria novo voto. ");
        AgendaDTO agendaDTO = agendaBusiness.get(voteDTO.getAgendaId()).get();
        Optional<MembershipDTO> membershipDTO = membershipBusiness.getByCpf(voteDTO.getMemberCpf());
        AtomicReference<VoteDTO> vote = new AtomicReference<>(voteDTO);

        membershipDTO.ifPresent(member -> {
            agendaDTO.getMembershipCpf().stream().filter(agenda -> agenda.equalsIgnoreCase(member.getCpf()))
                    .findFirst()
                    .ifPresentOrElse(memberPresent -> {
                        log.error("O Cooperado já votou essa pauta. ");
                        throw new BadRequestException("O cooperativado com o cpf ".concat(memberPresent).concat(", não poderá votar novamente na mesma pauta. "));
                    }, () -> {
                        log.info("O usuário pode votar,  pois ainda não votou.");

                        DateValidator.validateVotingDateBetweenTwoDates(agendaDTO.getInitDate(), agendaDTO.getFinishDate(), voteDTO.getTime(),
                                "O voto não pode ser computado porque não está de acordo com o limite de tempo. ");

                        VoteEntity voteEntity = service.create(converter.convertFromDto(voteDTO));
                        agendaDTO.getMembershipCpf().add(voteDTO.getMemberCpf());
                        agendaBusiness.edit(agendaDTO.getId(), agendaDTO);
                        vote.set(converter.convertFromEntity(service.create(voteEntity)));

                    });
        });
        // todo Buscar o membro pelo cpf - OK
        // todo ver se o membro já votou - OK
        // todo se ele nao votou deixar ele votar - OK
        // todo atualizar agenda business com a adição de novo cpf na lista de votantes - OK
        // todo se ele já votou não pode votar - OK
        //
        // todo tem que validar o horário do voto com relação a pauta - OK

        return vote.get();
    }

    @Override
    public Optional<VoteDTO> get(String id) {
        return service.get(id)
                .map(converter::convertFromEntity);
    }

    @Override
    public void edit(String id, VoteDTO voteDTO) {
        service.edit(id, converter.convertFromDto(voteDTO));

    }

    @Override
    public List<VoteDTO> list() {
        return converter
                .convertListEntityToDto(service.list());
    }

    @Override
    public void delete(String id) {
        service.delete(id);
    }
}
