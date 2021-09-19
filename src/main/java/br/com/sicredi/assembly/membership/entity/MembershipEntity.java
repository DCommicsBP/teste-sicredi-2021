package br.com.sicredi.assembly.membership.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MembershipEntity {
    @Id
    private String id;
    private String cpf;
    private String name;
}
