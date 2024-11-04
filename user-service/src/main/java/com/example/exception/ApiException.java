package com.example.exception;

import lombok.Setter;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

@Setter
public class ApiException extends ResponseStatusException {

    private final transient Object[] args;
    private String path;

    public ApiException(HttpStatus status, String reason, Object... args) {
        super(status, reason);
        this.args = args;
    }

    public ApiException(HttpStatus status, String reason, String path, Object... args) {
        this(status, reason, args);
        this.path = path;
    }

    public Map<String, Object> getResponseBodyFields(MessageSource messageSource, Locale locale) {
        String message = getMessageFromSource(messageSource, locale);
        return createResponseBody(message);
    }

    private String getMessageFromSource(MessageSource messageSource, Locale locale) {
        try {
            return messageSource.getMessage(Objects.requireNonNull(getReason()), args, locale);
        } catch (NoSuchMessageException e) {
            return getReason();
        }
    }

    private Map<String, Object> createResponseBody(String message) {
        int statusCode = getStatusCode().value();
        Map<String, Object> fields = new LinkedHashMap<>();
        fields.put("timestamp", new Date());
        fields.put("status", statusCode);
        fields.put("error", message);
        fields.put("path", path);
        return fields;
    }

    public ResponseEntity<Map<String, Object>> toLocalResponseEntity(MessageSource messageSource, Locale locale) {
        return ResponseEntity.status(getStatusCode()).body(getResponseBodyFields(messageSource, locale));
    }
}
