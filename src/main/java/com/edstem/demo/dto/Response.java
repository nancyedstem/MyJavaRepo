package com.edstem.demo.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Response {
    private final String message;
    private final boolean success;
}
