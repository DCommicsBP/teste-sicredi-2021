package br.com.sicredi.assembly.core.exceptions.handleerror;

import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ErrorResponseModel {
    private String message;
    private int statusValue;
    private String reasonPhrase;
    private String objectName;
    private List<ErrorModel> errorModel;


}
