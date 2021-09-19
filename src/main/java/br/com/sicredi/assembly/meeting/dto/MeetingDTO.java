package br.com.sicredi.assembly.meeting.dto;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MeetingDTO {

    private String id;
    private String title;
    private String description;
    private LocalDateTime initDate;
    private LocalDateTime finishDate;
}
