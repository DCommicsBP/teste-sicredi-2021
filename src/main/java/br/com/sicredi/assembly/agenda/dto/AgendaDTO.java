package br.com.sicredi.assembly.agenda.dto;

import br.com.sicredi.assembly.vote.enums.VoteEnum;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AgendaDTO {
    private String id;
    @NotEmpty( message = "Você precisa adicionar um título para a pauta.")
    private String title;
    @NotEmpty( message = "Você precisa adicionar uma descrição.")
    private String description;
    @NotEmpty( message = "Você precisa relacionar a uma assembléia a pauta.")
    private String meetingId;
    private List<VoteEnum> voteEnum;
    private LocalDateTime initDate;
    private LocalDateTime finishDate;
    private List<String> membershipCpf;
}
