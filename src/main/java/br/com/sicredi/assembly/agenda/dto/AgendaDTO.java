package br.com.sicredi.assembly.agenda.dto;

import br.com.sicredi.assembly.vote.enums.VoteEnum;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AgendaDTO {
    private String id;

    private String title;
    private String description;
    private String meetingId;
    private List<VoteEnum> voteEnum;
    private LocalDateTime initDate;
    private LocalDateTime finishDate;
    private List<String> membershipCpf;
}
