package br.com.sicredi.assembly.agenda.service;

import br.com.sicredi.assembly.agenda.entity.AgendaEntity;
import br.com.sicredi.assembly.agenda.repository.AgendaRepository;
import br.com.sicredi.assembly.core.exceptions.NotFoundException;
import br.com.sicredi.assembly.core.interfaces.ServiceInterface;
import lombok.AllArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AgendaService implements ServiceInterface<AgendaEntity> {

    final Logger log = Logger.getLogger(AgendaService.class);
    private final AgendaRepository repository;


    @Override
    public AgendaEntity create(AgendaEntity entity) {

        return repository.save(entity);
    }

    @Override
    public Optional<AgendaEntity> get(String id) {
        Optional<AgendaEntity> getAgenda = repository.findById(id);
        if(getAgenda.isEmpty()){
            log.error("Não foi possível encontrar o registro. ");
            throw new NotFoundException("Não foi possível encontrar a pauta.");
        }
        return getAgenda;
    }

    @Override
    public void edit(String id, AgendaEntity entity) {
        repository.findById(id)
                .ifPresentOrElse((old) -> {
                    entity.setId(old.getId());
                    repository.save(entity);
                }, ()-> {
                    throw new NotFoundException("Não é possível editar a pauta da assembléia porque o registro não existe.");
                });
    }

    @Override
    public List<AgendaEntity> list() {
        return repository.findAll();
    }

    @Override
    public void delete(String id) {
        repository.findById(id)
                .ifPresent((old) -> repository.deleteById(old.getId()));
    }
}
