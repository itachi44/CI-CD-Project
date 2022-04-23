package sn.ept.git.seminaire.cicd.exceptions.handler;

import sn.ept.git.seminaire.cicd.exceptions.ForbiddenException;
import sn.ept.git.seminaire.cicd.exceptions.InvalidException;
import sn.ept.git.seminaire.cicd.exceptions.ItemExistsException;
import sn.ept.git.seminaire.cicd.exceptions.ItemNotFoundException;
import sn.ept.git.seminaire.cicd.exceptions.message.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

/**
 *
 * @author ISENE
 */

@RestControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value= { ItemNotFoundException.class })
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    protected ResponseEntity<ErrorMessage> notFound(
            ItemNotFoundException ex, WebRequest request) {
        ErrorMessage message =  ErrorMessage.of(
                HttpStatus.NOT_FOUND.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value= { ItemExistsException.class })
    @ResponseStatus(value = HttpStatus.CONFLICT)
    protected ResponseEntity<ErrorMessage> conflict(
            ItemExistsException ex, WebRequest request) {
        ErrorMessage message =  ErrorMessage.of(
                HttpStatus.CONFLICT.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(message, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value= { InvalidException.class })
    @ResponseStatus(value = HttpStatus.CONFLICT)
    protected ResponseEntity<ErrorMessage> badRequest(
            InvalidException ex, WebRequest request) {
        ErrorMessage message =  ErrorMessage.of(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(value= {ForbiddenException.class })
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    protected ResponseEntity<ErrorMessage> permissionDenied(
            ForbiddenException ex, WebRequest request) {
        ErrorMessage message =  ErrorMessage.of(
                HttpStatus.FORBIDDEN.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(message, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value= { Exception.class ,RuntimeException.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    protected ResponseEntity<ErrorMessage> internalError(
            Exception ex, WebRequest request) {
        ErrorMessage message =  ErrorMessage.of(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value= {ResponseStatusException.class })
    protected ResponseEntity<ErrorMessage> responseStatus(
            ResponseStatusException ex, WebRequest request) {
        ErrorMessage message =  ErrorMessage.of(
                ex.getStatus().value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(message,   ex.getStatus());
    }


}