package br.com.sicredi.assembly.agenda.utils;

import br.com.sicredi.assembly.agenda.dto.AgendaDTO;
import br.com.sicredi.assembly.agenda.entity.AgendaEntity;
import org.apache.tomcat.jni.Local;

import java.time.LocalDateTime;

public abstract class AgendaUtils {
    public static AgendaDTO dto() {
        return AgendaDTO.builder()
                .finishDate(LocalDateTime.now().plusHours(2))
                .initDate(LocalDateTime.now())
                .description("Description")
                .id("id")
                .meetingId("id1")
                .title("title")
                .build();
    }

    public static AgendaEntity entity(AgendaDTO dto) {
        return AgendaEntity.builder()
                .finishVote(dto.getFinishDate())
                .initVote(dto.getInitDate())
                .description("Description")
                .id("id")
                .meetingId("id1")
                .title("title")
                .build();
    }

    public static AgendaDTO dto2() {
        return AgendaDTO.builder()
                .finishDate(LocalDateTime.now())
                .finishDate(LocalDateTime.now())
                .description("Description2")
                .id("id")
                .meetingId("id1")
                .title("title2")
                .build();
    }

    public static AgendaEntity entity2() {
        return AgendaEntity.builder()
                .finishVote(LocalDateTime.now())
                .initVote(LocalDateTime.now())
                .description("Description2")
                .id("id2")
                .meetingId("id1")
                .title("title2")
                .build();
    }
}
