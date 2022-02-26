package com.seberino.memeapi.mapper;

import com.seberino.memeapi.dto.MemeDto;
import com.seberino.memeapi.entity.Meme;
import com.seberino.memeapi.request.CreateMemeRequest;
import com.seberino.memeapi.request.UpdateMemeRequest;
import com.seberino.memeapi.response.BaseBodyResponse;
import com.seberino.memeapi.response.BasePageResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.stream.Collectors;

@Component
@Slf4j
public class MemeMapper {
    public static Meme mapToMeme(CreateMemeRequest request) {
        log.trace("[mapToMeme]: map to meme this request: {}", request);
        Meme meme = Meme.builder()
                .id(String.valueOf(UUID.randomUUID()))
                .country(request.getCountry())
                .firstApparition(request.getFirstApparition())
                .name(request.getName())
                .url(request.getUrl())
                .build();
        log.trace("[mapToMeme]: Request mapped to: {}", meme);

        return meme;
    }

    public static Meme updateMeme(Meme meme, UpdateMemeRequest request) {
        log.trace("[updateMeme]: update meme {} using this request: {}", meme, request);

        meme.setCountry(request.getCountry());
        meme.setFirstApparition(request.getFirstApparition());
        meme.setName(request.getName());
        meme.setUrl(request.getUrl());

        log.trace("[updateMeme]> meme changed to: {}", meme);

        return meme;
    }

    public static MemeDto mapToMemeDto(Meme meme) {
        log.trace("[mapToMemeDto]: map to MemeDto using this meme: {}", meme);
        MemeDto memeDto = MemeDto.builder()
                .country(meme.getCountry())
                .createdAt(meme.getCreatedAt())
                .id(meme.getId())
                .firstApparition(meme.getFirstApparition())
                .name(meme.getName())
                .updatedAt(meme.getUpdatedAt())
                .url(meme.getUrl())
                .build();
        log.trace("[mapToMemeDto]: Meme mapped to {}", memeDto);

        return memeDto;
    }

    public static BasePageResponse<MemeDto> mapToPageResponse(Page<Meme> memes) {
        log.trace("[mapToPageResponse]: map to BasePageResponse with this Meme Page: {}", memes);

        BasePageResponse pageResponse = BasePageResponse.builder()
                .page(memes.getPageable().getPageNumber())
                .size(memes.getSize())
                .next("")
                .previous("")
                .result(memes.getContent().stream().map(MemeMapper::mapToMemeDto).collect(Collectors.toList()))
                .build();

        log.trace("[mapToPageResponse]: Meme Page mapped to {}", pageResponse);

        return pageResponse;
    }

    public static BaseBodyResponse<MemeDto> mapToBodyResponse(Meme meme) {
        log.trace("[mapToBodyResponse]: map to BaseBodyResponse with this meme: {}", meme);

        BaseBodyResponse bodyResponse = BaseBodyResponse.builder()
                .result(MemeMapper.mapToMemeDto(meme))
                .build();

        log.trace("[mapToBodyResponse]: Meme mapped to {}", bodyResponse);

        return bodyResponse;
    }
}
