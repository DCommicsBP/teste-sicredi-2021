package br.com.sicredi.assembly.membership.business;

import br.com.sicredi.assembly.core.interfaces.ServiceInterface;
import br.com.sicredi.assembly.membership.api.converter.MemberShipConverter;
import br.com.sicredi.assembly.membership.dto.MembershipDTO;
import br.com.sicredi.assembly.membership.entity.MembershipEntity;
import br.com.sicredi.assembly.membership.service.MembershipService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MembershipBusiness implements ServiceInterface<MembershipDTO> {

    private final MembershipService service;
    private final MemberShipConverter converter;


    @Override
    public MembershipDTO create(MembershipDTO membershipDTO) {
        MembershipEntity entity = converter.convertFromDto(membershipDTO);
        return converter.convertFromEntity(service.create(entity));
    }

    @Override
    public Optional<MembershipDTO> get(String id) {
        return service.get(id).map(converter::convertFromEntity);
    }

    @Override
    public void edit(String id, MembershipDTO membershipDTO) {
        service.edit(id,converter.convertFromDto(membershipDTO));
    }

    @Override
    public List<MembershipDTO> list() {
        return converter.convertListEntityToDto(service.list());
    }

    @Override
    public void delete(String id) {
        service.delete(id);
    }
}
