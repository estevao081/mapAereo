package dev.estv.mapAereo.infra;

import dev.estv.mapAereo.exceptions.InvalidAddressException;
import dev.estv.mapAereo.exceptions.InvalidCodeException;
import dev.estv.mapAereo.exceptions.InvalidDateException;
import dev.estv.mapAereo.exceptions.InvalidNameException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidCodeException.class)
    private ResponseEntity<String> InvalidCodeHandler(InvalidCodeException exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
    }

    @ExceptionHandler(InvalidNameException.class)
    private ResponseEntity<String> InvalidNameHandler(InvalidNameException exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
    }

    @ExceptionHandler(InvalidDateException.class)
    private ResponseEntity<String> InvalidDateHandler(InvalidDateException exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
    }

    @ExceptionHandler(InvalidAddressException.class)
    private ResponseEntity<String> InvalidAddressHandler(InvalidAddressException exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
    }
}