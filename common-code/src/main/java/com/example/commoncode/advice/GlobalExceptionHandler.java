package com.example.commoncode.advice;

import com.example.commoncode.exception.BookCoverException;
import com.example.commoncode.exception.BookExportException;
import com.example.commoncode.exception.DuplicateException;
import com.example.commoncode.model.dto.ErrorResponseDto;
import com.example.commoncode.model.dto.ValidationErrorDto;
import feign.FeignException;
import jakarta.persistence.EntityNotFoundException;
import java.io.FileNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(FeignException.BadRequest.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponseDto handleFeignBadRequest(FeignException.BadRequest e) {
    return new ErrorResponseDto(e.getMessage());
  }

  @ExceptionHandler(FeignException.NotFound.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ErrorResponseDto handleFeignNotFound(FeignException.NotFound e) {
    return new ErrorResponseDto(e.getMessage());
  }

  @ExceptionHandler(DuplicateException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponseDto handleDuplicateException(DuplicateException e) {
    return new ErrorResponseDto(e.getMessage());
  }

  @ExceptionHandler(EntityNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ErrorResponseDto handleEntityNotFoundException(EntityNotFoundException e) {
    return new ErrorResponseDto(e.getMessage());
  }

  @ExceptionHandler(AuthenticationException.class)
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  public ErrorResponseDto handleAuthenticationException(AuthenticationException e) {
    return new ErrorResponseDto(e.getMessage());
  }

  @ExceptionHandler(IllegalArgumentException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponseDto handleIllegalArgumentException(IllegalArgumentException e) {
    return new ErrorResponseDto(e.getMessage());
  }

  @ExceptionHandler(BookCoverException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ErrorResponseDto handleBookCoverStorageException(BookCoverException e) {
    return new ErrorResponseDto(e.getMessage());
  }

  @ExceptionHandler(BookExportException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ErrorResponseDto handleBookExportException(BookExportException e) {
    return new ErrorResponseDto(e.getMessage());
  }

  @ExceptionHandler(FileNotFoundException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ErrorResponseDto handleFileNotFoundException(FileNotFoundException e) {
    return new ErrorResponseDto(e.getMessage());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ValidationErrorDto handleValidationException(MethodArgumentNotValidException ex) {
    ValidationErrorDto validationError = new ValidationErrorDto();
    ex.getBindingResult().getFieldErrors().forEach(
        error -> validationError.addFieldError(
            error.getField(), error.getDefaultMessage()));
    return validationError;
  }
}