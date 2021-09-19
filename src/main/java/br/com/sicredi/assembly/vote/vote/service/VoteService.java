package br.com.sicredi.assembly.vote.vote.service;

import br.com.sicredi.assembly.vote.core.interfaces.ServiceInterface;
import br.com.sicredi.assembly.vote.vote.entity.VoteEntity;
import br.com.sicredi.assembly.vote.vote.repository.VoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class VoteService implements ServiceInterface<VoteEntity> {

    private final VoteRepository repository;

    @Override
    public VoteEntity create(VoteEntity voteEntity) {
        return repository.save(voteEntity);
    }

    @Override
    public Optional<VoteEntity> get(String id) {
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
