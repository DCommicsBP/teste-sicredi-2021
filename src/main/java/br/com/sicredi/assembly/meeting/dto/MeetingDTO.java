package br.com.sicredi.assembly.meeting.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MeetingDTO {

    private String id;
    @NotEmpty(message = "O título da reunião deve ser fornecido. ")
    private String title;
    @NotEmpty(message = "Uma breve descrição sobre o encontro deve ser postada. ")
    private String description;
    @NotNull(message = "Você deve fornecer a data de incicio do encontro.")
    private LocalDateTime initDate;
    @NotNull(message = "Você deve fornecer a data final do encontro.")
    private LocalDateTime finishDate;
}
