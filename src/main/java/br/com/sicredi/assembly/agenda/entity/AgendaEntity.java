package br.com.sicredi.assembly.agenda.entity;

import lombok.*;
import org.apache.tomcat.jni.Local;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Document(value = "agenda")
public class AgendaEntity {

    @Id
    private String id;
    private String title;
    private String description;
    private String meetingId;
    private LocalDateTime initVote;
    private LocalDateTime finishVote;
    private List<String> membershipCpf;

}
