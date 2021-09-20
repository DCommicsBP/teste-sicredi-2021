package br.com.sicredi.assembly.membership.dto;

import br.com.sicredi.assembly.vote.enums.VoteEnum;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MembershipDTO {
    private String id;
    private String cpf;
    private String name;
    private VoteEnum voteEnum;

}
