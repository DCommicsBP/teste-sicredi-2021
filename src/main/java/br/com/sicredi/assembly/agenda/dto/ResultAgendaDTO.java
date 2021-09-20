package br.com.sicredi.assembly.agenda.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResultAgendaDTO {
    private String idAgenda;
    private String title;
    private String description;
    private int resultYes;
    private int resultNo;
}
