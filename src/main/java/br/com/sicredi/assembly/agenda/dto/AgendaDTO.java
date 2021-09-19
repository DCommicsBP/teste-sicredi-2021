package br.com.sicredi.assembly.agenda.dto;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AgendaDTO {
    private String id;
    private String title;
    private String description;
    private LocalDateTime initDate;
    private LocalDateTime finishDate;

}
