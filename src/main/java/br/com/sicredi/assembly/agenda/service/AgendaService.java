package br.com.sicredi.assembly.agenda.service;

import br.com.sicredi.assembly.agenda.entity.AgendaEntity;
import br.com.sicredi.assembly.agenda.repository.AgendaRepository;
import br.com.sicredi.assembly.core.interfaces.ServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AgendaService implements ServiceInterface<AgendaEntity> {

    private final AgendaRepository repository;


    @Override
    public AgendaEntity create(AgendaEntity entity) {
        return repository.save(entity);
    }

    @Override
    public Optional<AgendaEntity> get(String id) {
        return repository.findById(id);
    }

    @Override
    public void edit(String id, AgendaEntity entity) {
        get(id)
                .ifPresent((old) -> {
                    entity.setId(old.getId());
                    repository.save(entity);
                });
    }

    @Override
    public List<AgendaEntity> list() {
        return repository.findAll();
    }

    @Override
    public void delete(String id) {
        get(id)
                .ifPresent((old) -> repository.deleteById(old.getId()));
    }
}
