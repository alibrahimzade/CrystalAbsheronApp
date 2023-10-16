package az.digital.crystalabsheronapp.exceptions.handler;

import az.digital.crystalabsheronapp.dto.ExceptionDto;
import az.digital.crystalabsheronapp.exceptions.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    public ExceptionDto handle(UserNotFoundException exception) {
        log.error("not found: ", exception);
        return new ExceptionDto(NOT_FOUND.value(), exception.getMessage());
    }

    @ExceptionHandler(NoSuchBuildingException.class)
    public ExceptionDto handle(NoSuchBuildingException exception) {
        log.error("not found: ", exception);
        return new ExceptionDto(BAD_REQUEST.value(), exception.getMessage());
    }

    @ExceptionHandler(ResidenceAlreadyExistException.class)
    public ExceptionDto handle(ResidenceAlreadyExistException exception) {
        log.error("not found: ", exception);
        return new ExceptionDto(BAD_REQUEST.value(), exception.getMessage());
    }

    @ExceptionHandler(NoSuchResidenceException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionDto handle(NoSuchResidenceException exception) {
        log.error("ClientException: ", exception);
        return new ExceptionDto(NOT_FOUND.value(), exception.getMessage());
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    public ExceptionDto handle(UserAlreadyExistException exception) {
        log.error("ClientException: ", exception);
        return new ExceptionDto(CONFLICT.value(), exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ExceptionDto handle(MethodArgumentNotValidException exception) {
        var fieldError = exception.getFieldErrors().get(0);
        log.error("MethodArgumentNotValidException: ", exception);
        return new ExceptionDto(BAD_REQUEST.value(), exception.getMessage());
    }

    @ExceptionHandler(NoSuchContractException.class)
    @ResponseStatus(NOT_FOUND)
    public ExceptionDto handleContractNotFound(NoSuchContractException exception) {
        log.error("handleContractNotFound {}", exception);
        return new ExceptionDto(NOT_FOUND.value(), exception.getMessage());
    }

    @ExceptionHandler(NoSuchCustomerException.class)
    @ResponseStatus(NOT_FOUND)
    public ExceptionDto handleNoSuchCustomerNotFound(NoSuchCustomerException exception) {
        log.error("handleNoSuchCustomerNotFound {}", exception);
        return new ExceptionDto(NOT_FOUND.value(), exception.getMessage());
    }

    @ExceptionHandler(NoSuchBlocksException.class)
    @ResponseStatus(NOT_FOUND)
    public ExceptionDto handleNoSuchCustomerNotFound(NoSuchBlocksException exception) {
        log.error("handleNoSuchBlocksNotFound {}", exception);
        return new ExceptionDto(NOT_FOUND.value(), exception.getMessage());
    }
}
