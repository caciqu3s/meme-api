package com.seberino.memeapi.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BasePageResponse<T> {
    private List<T> result;
    private Integer page;
    private Integer size;
    private String next;
    private String previous;
    private BaseBodyError error;
}
