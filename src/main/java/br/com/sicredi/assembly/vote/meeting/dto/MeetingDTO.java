package br.com.sicredi.assembly.vote.meeting.dto;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MeetingDTO {

    private String id;
    private String titulo;
    private String descricao;
    private LocalDateTime initDate;
    private LocalDateTime finishDate;
}
