package br.com.sicredi.assembly.membership.repository;

import br.com.sicredi.assembly.membership.entity.MembershipEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembershipRepository extends MongoRepository<MembershipEntity, String> {
}
