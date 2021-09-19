package br.com.sicredi.assembly.meeting.service;

import br.com.sicredi.assembly.meeting.entity.MeetingEntity;
import br.com.sicredi.assembly.core.interfaces.ServiceInterface;
import br.com.sicredi.assembly.meeting.repository.MeetingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MeetingService implements ServiceInterface<MeetingEntity> {

    private final MeetingRepository repository;

    @Override
    public MeetingEntity create(MeetingEntity meetingEntity) {
        return repository.save(meetingEntity);
    }

    @Override
    public Optional<MeetingEntity> get(String id) {
        return repository.findById(id);
    }

    @Override
    public void edit(String id, MeetingEntity meetingEntity) {
        get(id).ifPresent(meetingEntity1->{
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
