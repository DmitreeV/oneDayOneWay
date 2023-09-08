package dmitreev.petproject.java.oneDayOneWay.error.handler;

import dmitreev.petproject.java.oneDayOneWay.error.exception.BadRequestException;
import dmitreev.petproject.java.oneDayOneWay.error.exception.ConflictException;
import dmitreev.petproject.java.oneDayOneWay.error.exception.NotFoundException;
import dmitreev.petproject.java.oneDayOneWay.error.exception.UnauthorizedException;
import dmitreev.petproject.java.oneDayOneWay.error.model.AppError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.expression.AccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
@Slf4j
public class ErrorHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> handleBadRequestException(BadRequestException e) {
        log.error("Bad request exception.");
        return buildResponseEntity(new AppError(HttpStatus.BAD_REQUEST.value(),
                "The conditions for the requested operation are not met.", e.getMessage(), LocalDateTime.now()));
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<Object> handleUnauthorizedException(UnauthorizedException e) {
        log.error("Unauthorized exception.");
        return buildResponseEntity(new AppError(HttpStatus.UNAUTHORIZED.value(),
                "The conditions for the requested operation are not met.", e.getMessage(), LocalDateTime.now()));
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<Object> handleConflictException(final ConflictException e) {
        log.error("Conflict exception.");
        return buildResponseEntity(new AppError(HttpStatus.CONFLICT.value(),
                "The request contradicts the established restrictions.",
                e.getMessage(), LocalDateTime.now()));
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AccessException.class)
    public ResponseEntity<Object> handleAccessException(final AccessException e) {
        log.error("Access exception.");
        return buildResponseEntity(new AppError(HttpStatus.FORBIDDEN.value(),
                "The request contradicts the established restrictions, there is no access.",
                e.getMessage(), LocalDateTime.now()));
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(final NotFoundException e) {
        log.error("Not found exception.");
        return buildResponseEntity(new AppError(HttpStatus.NOT_FOUND.value(), "The required object was not found.",
                e.getMessage(), LocalDateTime.now()));
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<Object> handleException(Throwable e) {
        log.error("Internal server error.");
        return buildResponseEntity(new AppError("Invalid request.",
                e.getMessage(), LocalDateTime.now()));
    }

    private ResponseEntity<Object> buildResponseEntity(AppError apiError) {
        return new ResponseEntity<>(apiError, HttpStatus.valueOf(apiError.getStatus()));
    }
}
