package com.example.commoncode.model.dto;

import lombok.Builder;

@Builder
public record ErrorResponseDto(
    int code,
    String message) {

}