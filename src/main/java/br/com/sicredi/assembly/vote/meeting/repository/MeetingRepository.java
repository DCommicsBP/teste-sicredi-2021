package br.com.sicredi.assembly.vote.meeting.repository;

import br.com.sicredi.assembly.vote.meeting.entity.MeetingEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MeetingRepository extends MongoRepository<MeetingEntity, String> {
}
