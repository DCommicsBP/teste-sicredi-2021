package br.com.sicredi.assembly.vote.dto;

import br.com.sicredi.assembly.vote.enums.VoteEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VoteDTO {
    private String id ;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
    @ApiModelProperty(required = true, example = "2021-08-20T00:00:00")
    @NotNull(message = "Você d eve fornecer o horário do voto. ")
    private LocalDateTime time;
    @NotNull(message = "Você d eve fornecer o horário do voto. ")
    private VoteEnum voteEnum;
    @NotNull(message = "O identificador da pauta deve ser adicionado. ")
    private String agendaId;
    @NotNull(message = "O identificador do cooperado deve ser fornecido. ")
    private String memberCpf;
}
