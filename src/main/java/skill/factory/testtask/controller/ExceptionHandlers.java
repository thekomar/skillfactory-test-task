package skill.factory.testtask.controller;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import skill.factory.testtask.controller.model.ErrorResponse;
import skill.factory.testtask.exceptions.NegateUserBalanceException;
import skill.factory.testtask.exceptions.UserNotFoundException;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionHandlers extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleFileNameIsEmptyException(
            UserNotFoundException ex, HttpServletRequest request) {
        return getResponse(
                ex, HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(NegateUserBalanceException.class)
    public ResponseEntity<ErrorResponse> handleUnsupportedExtensionException(NegateUserBalanceException ex, HttpServletRequest request) {
        return getResponse(
                ex, HttpStatus.BAD_REQUEST
        );
    }
    private ResponseEntity<ErrorResponse> getResponse(Exception ex,HttpStatus status){
        ErrorResponse errorResponse = new ErrorResponse(status.value(), -1, ex.getMessage());
        return ResponseEntity
                .status(status)
                .body(errorResponse);
    }
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        body.put("errors", errors);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }


}
