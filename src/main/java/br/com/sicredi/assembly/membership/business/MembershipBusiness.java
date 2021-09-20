package br.com.sicredi.assembly.membership.business;

import br.com.sicredi.assembly.agenda.business.AgendaBusiness;
import br.com.sicredi.assembly.core.exceptions.BadRequestException;
import br.com.sicredi.assembly.core.interfaces.ServiceInterface;
import br.com.sicredi.assembly.core.validate.CpfValidator;
import br.com.sicredi.assembly.membership.api.converter.MemberShipConverter;
import br.com.sicredi.assembly.membership.dto.MembershipDTO;
import br.com.sicredi.assembly.membership.entity.MembershipEntity;
import br.com.sicredi.assembly.membership.service.MembershipService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MembershipBusiness implements ServiceInterface<MembershipDTO> {

    private final MembershipService service;
    private final MemberShipConverter converter;
    private static final Logger log = LogManager.getLogger(AgendaBusiness.class);



    @Override
    public MembershipDTO create(MembershipDTO membershipDTO) {
        try{
            CpfValidator.isValid(membershipDTO.getCpf());
            MembershipEntity entity = converter.convertFromDto(membershipDTO);
            log.info("Entrou no serviço que cria novo cooperado. ");
            return converter.convertFromEntity(service.create(entity));
        }catch (Exception exception){
            log.error(exception.getMessage());
            throw new BadRequestException("Não foi possível inserir o cooperado, pois o CPF é invalido. ");
        }
    }

    @Override
    public Optional<MembershipDTO> get(String id) {
        log.info("Entrou no serviço que busca cooperado. ");
        return service.get(id).map(converter::convertFromEntity);
    }

    @Override
    public void edit(String id, MembershipDTO membershipDTO) {
        log.info("Entrou no serviço que edita cooperado. ");

        service.edit(id, converter.convertFromDto(membershipDTO));
    }

    @Override
    public List<MembershipDTO> list() {
        log.info("Entrou no serviço que lista cooperados. ");
        return converter.convertListEntityToDto(service.list());
    }

    @Override
    public void delete(String id) {
        log.info("Entrou no serviço que exclui cooperado. ");
        service.delete(id);
    }

    public Optional<MembershipDTO> getByCpf(String memberCpf) {
        log.info("Entrou no serviço que busca cooperado pelo cpf. ");
        return service.getByCpf(memberCpf).map(converter::convertFromEntity);
    }
}
