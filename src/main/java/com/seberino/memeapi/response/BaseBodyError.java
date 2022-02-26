package com.seberino.memeapi.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaseBodyError {
    private String message;
    private Integer httpCode;
    private LocalDateTime dateTime;
}
