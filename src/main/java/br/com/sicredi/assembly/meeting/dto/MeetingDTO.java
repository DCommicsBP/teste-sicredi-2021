package br.com.sicredi.assembly.meeting.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
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
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime initDate;
    @NotNull(message = "Você deve fornecer a data final do encontro.")
    private LocalDateTime finishDate;
}
