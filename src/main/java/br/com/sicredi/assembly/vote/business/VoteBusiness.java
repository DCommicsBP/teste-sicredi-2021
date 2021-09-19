package br.com.sicredi.assembly.vote.business;

import br.com.sicredi.assembly.core.interfaces.ServiceInterface;
import br.com.sicredi.assembly.vote.api.converter.VoteConverter;
import br.com.sicredi.assembly.vote.dto.VoteDTO;
import br.com.sicredi.assembly.vote.entity.VoteEntity;
import br.com.sicredi.assembly.vote.service.VoteService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class VoteBusiness implements ServiceInterface<VoteDTO> {
    private final VoteService service;
    private final VoteConverter converter;
    @Override
    public VoteDTO create(VoteDTO voteDTO) {
        VoteEntity voteEntity = service.create(converter.convertFromDto(voteDTO));
        return converter.convertFromEntity(service.create(voteEntity));
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
