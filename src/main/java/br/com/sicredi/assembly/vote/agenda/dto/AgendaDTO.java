package br.com.sicredi.assembly.vote.agenda.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AgendaDTO {
    private String id;
    private String titulo;
    private String descricao;
}
