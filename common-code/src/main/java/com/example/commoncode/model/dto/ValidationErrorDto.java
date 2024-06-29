package com.example.commoncode.model.dto;

import java.util.List;
import java.util.Map;
import lombok.Builder;

@Builder
public record ValidationErrorDto(
    int code,
    Map<String, List<String>> message) {

}
