package br.com.sicredi.assembly.core.exceptions;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Builder
@Setter
@Getter
public class ErrorModel {
    private String message;
    private LocalDateTime date;
    private HttpStatus statusCode;
}
