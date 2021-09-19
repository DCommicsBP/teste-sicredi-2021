package br.com.sicredi.assembly.vote.agenda.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Document(value = "agenda")
public class AgendaEntity {

    @Id
    private String id;
    private String titulo;
    private String descricao;

}
