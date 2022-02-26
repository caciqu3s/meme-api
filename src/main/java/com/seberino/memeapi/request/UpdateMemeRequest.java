package com.seberino.memeapi.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateMemeRequest {
    private String name;
    private String country;
    private String url;
    private LocalDate firstApparition;
}
