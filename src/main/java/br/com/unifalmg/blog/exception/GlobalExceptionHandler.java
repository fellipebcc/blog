package br.com.unifalmg.blog.exception;

import br.com.unifalmg.blog.dto.ProblemDetails;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Arrays;

@Log4j2
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { UserNotFoundException.class })
    private ResponseEntity<?> handleServiceUnavailable(RuntimeException ex, WebRequest request) {

        ProblemDetails problem = ProblemDetails.builder()
                .timestamp(LocalDateTime.now())
                .path(request.getContextPath())
                .error(HttpStatus.NOT_FOUND.getReasonPhrase())
                .status(HttpStatus.NOT_FOUND.value())
                .userMessage(ex.getMessage())
//                .trace(Arrays.toString(ex.getStackTrace()))
                .build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(),
                HttpStatus.NOT_FOUND, request);
    }

}
