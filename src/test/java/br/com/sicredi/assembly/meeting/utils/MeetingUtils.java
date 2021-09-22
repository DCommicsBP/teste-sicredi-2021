package br.com.sicredi.assembly.meeting.utils;

import br.com.sicredi.assembly.meeting.dto.MeetingDTO;
import br.com.sicredi.assembly.meeting.entity.MeetingEntity;

import java.time.LocalDateTime;

public abstract class MeetingUtils {
    public static MeetingEntity entity(){
        return MeetingEntity.builder()
                .id("id1")
                .description("Description 1")
                .finishDate(LocalDateTime.now().plusHours(3))
                .initDate(LocalDateTime.now().minusHours(1))
                .title("title1")
                .build();
    }

    public static MeetingEntity entity2(){
        return MeetingEntity.builder()
                .id("id2")
                .description("Description 2")
                .finishDate(LocalDateTime.now().plusHours(3))
                .initDate(LocalDateTime.now().minusHours(1))
                .title("title2")
                .build();
    }

    public static MeetingDTO dto(){
        return MeetingDTO.builder()
                .id("id2")
                .description("Description 2")
                .finishDate(LocalDateTime.now())
                .initDate(LocalDateTime.now())
                .title("title2")
                .build();
    }
    public static MeetingDTO dto2(){
        return MeetingDTO.builder()
                .id("id1")
                .description("Description 1")
                .finishDate(LocalDateTime.now())
                .initDate(LocalDateTime.now())
                .title("title1")
                .build();
    }
}
