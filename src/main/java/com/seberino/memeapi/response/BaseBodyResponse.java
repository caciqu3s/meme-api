package com.seberino.memeapi.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaseBodyResponse<T> {
    private T result;
    private BaseBodyError error;
}
