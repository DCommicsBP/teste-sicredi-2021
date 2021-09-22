package br.com.sicredi.assembly.membership.service;

import br.com.sicredi.assembly.core.interfaces.ServiceInterface;
import br.com.sicredi.assembly.membership.entity.MembershipEntity;
import br.com.sicredi.assembly.membership.repository.MembershipRepository;
import lombok.AllArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class MembershipService implements ServiceInterface<MembershipEntity> {
    final Logger log = Logger.getLogger(MembershipService.class);
    private final MembershipRepository repository;

    @Override
    public MembershipEntity create(MembershipEntity membershipEntity) {
        return repository.save(membershipEntity);
    }

    @Override
    public Optional<MembershipEntity> get(String id) {
        Optional<MembershipEntity> findMembership = repository.findById(id);
        if(findMembership.isEmpty()){
            log.error("Não foi possivel encontrar o registro do cooperado.");
        }
        return findMembership;
    }

    @Override
    public void edit(String id, MembershipEntity membershipEntity) {

            repository.findById(id).ifPresent(membershipEntity1 -> {

                membershipEntity.setId(membershipEntity1.getId());
                repository.save(membershipEntity);
            });
    }

    @Override
    public List<MembershipEntity> list() {
        return repository.findAll();
    }

    @Override
    public void delete(String id) {
        get(id).ifPresent(repository::delete);
    }

    public  Optional<MembershipEntity> getByCpf(String memberCpf) {
        return this.repository.findByCpf(memberCpf);
    }

}
