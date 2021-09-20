package br.com.sicredi.assembly.vote.service;

import br.com.sicredi.assembly.core.exceptions.NotFoundException;
import br.com.sicredi.assembly.core.interfaces.ServiceInterface;
import br.com.sicredi.assembly.vote.entity.VoteEntity;
import br.com.sicredi.assembly.vote.repository.VoteRepository;
import lombok.AllArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class VoteService implements ServiceInterface<VoteEntity> {

    final Logger log = Logger.getLogger(VoteService.class);

    private final VoteRepository repository;

    @Override
    public VoteEntity create(VoteEntity voteEntity) {
        return repository.save(voteEntity);
    }

    @Override
    public Optional<VoteEntity> get(String id) {
        Optional<VoteEntity> vote = this.repository.findById(id);
        if(vote.isEmpty()){
            log.error("Não foi possível encontrar o registro de voto. ");
            throw new NotFoundException("Não foi possível encontrar o registro de voto pelo ID informado. ");
        }
        return this.repository.findById(id);
    }

    @Override
    public void edit(String id, VoteEntity voteEntity) {
        get(id).ifPresent(vote-> {
            voteEntity.setId(vote.getId());
            this.repository.save(voteEntity);
        });
    }

    @Override
    public List<VoteEntity> list() {
        return this.repository.findAll();
    }

    @Override
    public void delete(String id) {
        get(id).ifPresent(this.repository::delete);
    }
}
