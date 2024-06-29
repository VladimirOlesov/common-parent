package com.example.commoncode.advice;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;

import com.example.commoncode.exception.BookCoverException;
import com.example.commoncode.exception.BookExportException;
import com.example.commoncode.exception.DuplicateException;
import com.example.commoncode.model.dto.ErrorResponseDto;
import com.example.commoncode.model.dto.ValidationErrorDto;
import jakarta.persistence.EntityNotFoundException;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(OptimisticLockingFailureException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  public ErrorResponseDto handleOptimisticLockingFailureException(
      OptimisticLockingFailureException e) {
    return ErrorResponseDto.builder()
        .code(HttpStatus.CONFLICT.value())
        .message(e.getMessage())
        .build();
  }

  @ExceptionHandler(ResponseStatusException.class)
  public ResponseEntity<ErrorResponseDto> handleResponseStatusException(ResponseStatusException e) {
    ErrorResponseDto errorResponse = ErrorResponseDto.builder()
        .code(e.getStatusCode().value())
        .message(e.getReason())
        .build();
    return ResponseEntity.status(e.getStatusCode()).body(errorResponse);
  }

  @ExceptionHandler(DuplicateException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponseDto handleDuplicateException(DuplicateException e) {
    return ErrorResponseDto.builder()
        .code(HttpStatus.BAD_REQUEST.value())
        .message(e.getMessage())
        .build();
  }

  @ExceptionHandler(EntityNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ErrorResponseDto handleEntityNotFoundException(EntityNotFoundException e) {
    return ErrorResponseDto.builder()
        .code(HttpStatus.NOT_FOUND.value())
        .message(e.getMessage())
        .build();
  }

  @ExceptionHandler(AuthenticationException.class)
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  public ErrorResponseDto handleAuthenticationException(AuthenticationException e) {
    return ErrorResponseDto.builder()
        .code(HttpStatus.UNAUTHORIZED.value())
        .message(e.getMessage())
        .build();
  }

  @ExceptionHandler(IllegalArgumentException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponseDto handleIllegalArgumentException(IllegalArgumentException e) {
    return ErrorResponseDto.builder()
        .code(HttpStatus.BAD_REQUEST.value())
        .message(e.getMessage())
        .build();
  }

  @ExceptionHandler(BookCoverException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ErrorResponseDto handleBookCoverStorageException(BookCoverException e) {
    return ErrorResponseDto.builder()
        .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
        .message(e.getMessage())
        .build();
  }

  @ExceptionHandler(BookExportException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ErrorResponseDto handleBookExportException(BookExportException e) {
    return ErrorResponseDto.builder()
        .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
        .message(e.getMessage())
        .build();
  }

  @ExceptionHandler(FileNotFoundException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ErrorResponseDto handleFileNotFoundException(FileNotFoundException e) {
    return ErrorResponseDto.builder()
        .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
        .message(e.getMessage())
        .build();
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ValidationErrorDto handleValidationException(MethodArgumentNotValidException e) {
    return ValidationErrorDto.builder()
        .code(HttpStatus.BAD_REQUEST.value())
        .message(getMessage(e))
        .build();
  }

  private Map<String, List<String>> getMessage(MethodArgumentNotValidException e) {
    return e.getBindingResult().getFieldErrors().stream()
        .collect(groupingBy(FieldError::getField,
            mapping(FieldError::getDefaultMessage, toList())));
  }
}