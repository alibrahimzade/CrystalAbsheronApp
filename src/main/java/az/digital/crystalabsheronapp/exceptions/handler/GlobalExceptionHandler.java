package az.digital.crystalabsheronapp.exceptions.handler;

import az.digital.crystalabsheronapp.dto.ExceptionDto;
import az.digital.crystalabsheronapp.exceptions.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    public ExceptionDto handle(UserNotFoundException exception){
        log.error("not found: ",exception);
        return new ExceptionDto(NOT_FOUND.value(), exception.getMessage());
    }
    @ExceptionHandler(NoSuchBuilding.class)
    public ExceptionDto handle(NoSuchBuilding exception){
        log.error("not found: ",exception);
        return new ExceptionDto(BAD_REQUEST.value(), exception.getMessage());
    }
    @ExceptionHandler(ResidenceAlreadyExist.class)
    public ExceptionDto handle(ResidenceAlreadyExist exception){
        log.error("not found: ",exception);
        return new ExceptionDto(BAD_REQUEST.value(), exception.getMessage());
    }
    @ExceptionHandler(NoSuchResidence.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionDto handle(NoSuchResidence exception){
        log.error("ClientException: ",exception);
        return new ExceptionDto(NOT_FOUND.value(), exception.getMessage());
    }

    @ExceptionHandler(UserAlreadyExist.class)
    public ExceptionDto handle(UserAlreadyExist exception){
        log.error("ClientException: ",exception);
        return new ExceptionDto(CONFLICT.value(),exception.getMessage());
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ExceptionDto handle(MethodArgumentNotValidException exception) {
        var fieldError = exception.getFieldErrors().get(0);
        log.error("MethodArgumentNotValidException: ", exception);
        return new ExceptionDto(BAD_REQUEST.value(), exception.getMessage());
    }
}
