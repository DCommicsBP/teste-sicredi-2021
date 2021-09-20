package br.com.sicredi.assembly.membership.repository;

import br.com.sicredi.assembly.membership.entity.MembershipEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MembershipRepository extends MongoRepository<MembershipEntity, String> {
    Optional<MembershipEntity> findByCpf(String memberCpf);
}
