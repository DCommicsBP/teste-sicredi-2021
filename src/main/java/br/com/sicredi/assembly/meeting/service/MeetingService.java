package br.com.sicredi.assembly.meeting.service;

import br.com.sicredi.assembly.core.exceptions.NotFoundException;
import br.com.sicredi.assembly.meeting.entity.MeetingEntity;
import br.com.sicredi.assembly.core.interfaces.ServiceInterface;
import br.com.sicredi.assembly.meeting.repository.MeetingRepository;
import lombok.AllArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MeetingService implements ServiceInterface<MeetingEntity> {
    final Logger log = Logger.getLogger(MeetingService.class);
    private final MeetingRepository repository;

    @Override
    public MeetingEntity create(MeetingEntity meetingEntity) {
        return repository.save(meetingEntity);
    }

    @Override
    public Optional<MeetingEntity> get(String id) {
        Optional<MeetingEntity> meeting = repository.findById(id);
        if(!meeting.isPresent()){
            log.error("Não foi possível encontrar o registro da assembléia. ");
            throw new NotFoundException("Não foi possível encontrar o dado. ");
        }
        return meeting;
    }

    @Override
    public void edit(String id, MeetingEntity meetingEntity) {
        repository.findById(id).ifPresent(meetingEntity1->{
            meetingEntity.setId(meetingEntity.getId());
            repository.save(meetingEntity);
        });
    }

    @Override
    public List<MeetingEntity> list() {
        return repository.findAll();
    }

    @Override
    public void delete(String id) {
        get(id).ifPresent(meetingEntity -> repository.deleteById(id));

    }
}
