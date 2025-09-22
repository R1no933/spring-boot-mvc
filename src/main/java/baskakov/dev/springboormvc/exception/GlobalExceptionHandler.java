package baskakov.dev.springboormvc.exception;

import baskakov.dev.springboormvc.model.ServerErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ServerErrorDTO> validationException(MethodArgumentNotValidException ex){
        String detailsMessage = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(er -> er.getField() + ": " + er.getDefaultMessage())
                .collect(Collectors.joining(", "));

        var error = new ServerErrorDTO(
                "Validation Error",
                detailsMessage,
                LocalDateTime.now()
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(error);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ServerErrorDTO> notFoundException(NoSuchElementException ex){
        var error = new ServerErrorDTO(
                "Not Found Error",
                ex.getMessage(),
                LocalDateTime.now()
        );

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ServerErrorDTO> otherServerException(Exception ex){
        var error = new ServerErrorDTO(
                "Internal Server Error",
                ex.getMessage(),
                LocalDateTime.now()
        );

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(error);
    }
}
