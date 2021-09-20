package br.com.sicredi.assembly.membership.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MembershipDTO {
    private String id;
    @NotEmpty(message = "Você deve fornecer o seu CPF para votar. ")
    @Size(min= 11, max = 11, message = "O tamanho do CPF é 11.")
    private String cpf;
    @NotEmpty(message = "Você deve fornecer o seu nome para votar. ")
    private String name;

}
