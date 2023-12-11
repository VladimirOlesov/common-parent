package com.example.commoncode.exception;

public class DuplicateException extends RuntimeException {

  public DuplicateException(String message) {
    super(message);
  }
}