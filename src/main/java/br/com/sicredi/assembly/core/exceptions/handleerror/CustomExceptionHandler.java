package br.com.sicredi.assembly.core.exceptions.handleerror;

import br.com.sicredi.assembly.core.exceptions.BadRequestException;
import br.com.sicredi.assembly.core.exceptions.InternalServerException;
import br.com.sicredi.assembly.core.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    protected ResponseEntity<ErrorModel> handleNotFoundException(NotFoundException ex) {

        ErrorModel error = ErrorModel.builder()
                .date(LocalDateTime.now())
                .message(ex.getLocalizedMessage())
                .statusCode(HttpStatus.NOT_FOUND)
                .build();
         return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<ErrorModel> handleBadRequestException(BadRequestException ex) {

        ErrorModel error = ErrorModel.builder()
                .date(LocalDateTime.now())
                .message(ex.getLocalizedMessage())
                .statusCode(HttpStatus.BAD_REQUEST)
                .build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Error.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorModel handleDefaultException(Error ex) {
        ErrorModel response = ErrorModel
                .builder()
                .date(LocalDateTime.now())
                .message(ex.getMessage())
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR)
                .build();
        return response;
    }

    @ExceptionHandler(InternalServerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorModel handleDefaultInternalServierException(InternalServerException ex) {
        ErrorModel response = ErrorModel
                .builder()
                .date(LocalDateTime.now())
                .message(ex.getMessage())
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR)
                .build();
        return response;
    }
}
