package br.com.sicredi.assembly.vote.vote.repository;

import br.com.sicredi.assembly.vote.vote.entity.VoteEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends MongoRepository<VoteEntity, String> {
}
