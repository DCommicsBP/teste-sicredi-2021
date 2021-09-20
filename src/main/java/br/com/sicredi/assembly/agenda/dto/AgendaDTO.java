package br.com.sicredi.assembly.agenda.dto;

import br.com.sicredi.assembly.vote.enums.VoteEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime initDate;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime finishDate;
    private List<String> membershipCpf;

}
