package com.example.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Locale;
import java.util.Map;
import java.util.Objects;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class ApiExceptionHandler {
    private final MessageSource messageSource;

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<Map<String, Object>> handleApiExceptions(ApiException ex,
                                                                   Locale locale, HttpServletRequest request) {
        ex.setPath(extractQueryPath(request));
        return ex.toLocalResponseEntity(messageSource, locale);
    }

    private String extractQueryPath(HttpServletRequest request) {
        return Objects.nonNull(request.getQueryString()) ?
                String.format("%s?%s", request.getRequestURI(), request.getQueryString()) :
                request.getRequestURI();
    }
}
