package br.com.sicredi.assembly.meeting.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(value= "meeting")
public class MeetingEntity {
    @Id
    private String id;
    private String title;
    private String description;
    private LocalDateTime initDate;
    private LocalDateTime finishDate;

}
