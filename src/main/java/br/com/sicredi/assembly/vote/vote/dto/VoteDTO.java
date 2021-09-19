package br.com.sicredi.assembly.vote.vote.dto;

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
    private LocalDateTime initDate;
    private LocalDateTime finalDate;
}
