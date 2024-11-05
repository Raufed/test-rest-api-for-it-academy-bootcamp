package by.payzov.it_academy.errors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @ExceptionHandler
    public ResponseEntity<ErrorMessage> catchResourceNotFoundException(ResourceNotFoundException e) {
        logger.error("Resource not found exception: {}", e.getMessage());
        return new ResponseEntity<>(new ErrorMessage(HttpStatus.NOT_FOUND, e.getMessage()),
                HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    public ResponseEntity<ErrorMessage> catchResourceAlreadyExistsException(ResourceAlreadyExistsException e) {
        logger.error("Resource already exists exception: {}", e.getMessage());
        return new ResponseEntity<>(new ErrorMessage(HttpStatus.CONFLICT, e.getMessage()),
                HttpStatus.CONFLICT);
    }
}