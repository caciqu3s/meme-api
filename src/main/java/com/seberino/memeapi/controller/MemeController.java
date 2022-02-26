package com.seberino.memeapi.controller;

import com.seberino.memeapi.dto.MemeDto;
import com.seberino.memeapi.entity.Meme;
import com.seberino.memeapi.mapper.MemeMapper;
import com.seberino.memeapi.request.CreateMemeRequest;
import com.seberino.memeapi.request.UpdateMemeRequest;
import com.seberino.memeapi.response.BaseBodyResponse;
import com.seberino.memeapi.response.BasePageResponse;
import com.seberino.memeapi.service.MemeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/meme")
@RequiredArgsConstructor
@Slf4j
public class MemeController {
    private final MemeService service;

    @GetMapping
    public ResponseEntity<BasePageResponse<MemeDto>> search(@RequestParam Integer page, @RequestParam Integer size) {
        log.debug("[search]: searh Meme from page {} with size {}", page, size);

        Page<Meme> memes = service.search(page, size);

        if(memes.isEmpty()) {
            log.trace("[search]: meme page {} is empty", page);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(MemeMapper.mapToPageResponse(memes));
    }

    @GetMapping("{id}")
    public ResponseEntity<BaseBodyResponse<MemeDto>> getById(@PathVariable String id) {
        log.debug("[getById]: get Meme by id {}", id);

        Meme meme = service.getById(id);

        return ResponseEntity.ok(MemeMapper.mapToBodyResponse(meme));
    }

    @PostMapping
    public ResponseEntity<BaseBodyResponse<MemeDto>> create(@RequestBody @Validated CreateMemeRequest request) {
        log.debug("[create]: create meme with request {}", request);

        Meme meme = service.create(request);

        return ResponseEntity.ok(MemeMapper.mapToBodyResponse(meme));
    }

    @PutMapping("{id}")
    public ResponseEntity<BaseBodyResponse<MemeDto>> update(@PathVariable String id, @RequestBody @Validated UpdateMemeRequest request) {
        log.debug("[update]: update meme from id {} with request {}", id, request);

        Meme meme = service.update(service.getById(id), request);

        return ResponseEntity.accepted().body(MemeMapper.mapToBodyResponse(meme));
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable String id) {
        log.debug("[delete]: delete meme from id {}", id);
        service.delete(service.getById(id));

        return ResponseEntity.accepted().build();
    }
}
