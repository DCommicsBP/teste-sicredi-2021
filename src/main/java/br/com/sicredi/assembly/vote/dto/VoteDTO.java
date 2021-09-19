package br.com.sicredi.assembly.vote.dto;

import br.com.sicredi.assembly.vote.enums.VoteEnum;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VoteDTO {
    private String id ;
    private List<String> membersCPF;
    private LocalDateTime time;
    private VoteEnum voteEnum;
}
