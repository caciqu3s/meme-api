package com.seberino.memeapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemeDto {
    private String id;

    private String name;

    private String country;

    private String url;

    private LocalDate firstApparition;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
