package com.example.commoncode.exception;

public class OrderProcessingException extends RuntimeException {

  public OrderProcessingException(String message) {
    super(message);
  }
}