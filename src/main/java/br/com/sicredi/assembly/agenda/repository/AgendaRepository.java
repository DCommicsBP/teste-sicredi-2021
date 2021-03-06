package br.com.sicredi.assembly.agenda.repository;

import br.com.sicredi.assembly.agenda.entity.AgendaEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgendaRepository extends MongoRepository<AgendaEntity, String> {
}
