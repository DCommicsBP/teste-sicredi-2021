package br.com.sicredi.assembly.vote.meeting.service;

import br.com.sicredi.assembly.vote.core.interfaces.ServiceInterface;
import br.com.sicredi.assembly.vote.meeting.entity.MeetingEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MeetingService implements ServiceInterface<MeetingEntity> {
    @Override
    public MeetingEntity create(MeetingEntity meetingEntity) {
        return null;
    }

    @Override
    public Optional<MeetingEntity> get(String id) {
        return Optional.empty();
    }

    @Override
    public void edit(String id, MeetingEntity meetingEntity) {

    }

    @Override
    public List<MeetingEntity> list() {
        return null;
    }

    @Override
    public void delete(String id) {

    }
}
