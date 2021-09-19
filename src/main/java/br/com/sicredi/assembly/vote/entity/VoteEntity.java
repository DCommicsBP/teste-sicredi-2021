package br.com.sicredi.assembly.vote.entity;

import br.com.sicredi.assembly.vote.enums.VoteEnum;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(value = "vote")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VoteEntity {
    @Id
    private String id ;
    private List<String> membersCPF;
    private LocalDateTime initDate;
    private LocalDateTime finalDate;
    private VoteEnum voteEnum;

}
