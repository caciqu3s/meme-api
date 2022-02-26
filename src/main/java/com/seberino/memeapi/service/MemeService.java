package com.seberino.memeapi.service;

import com.seberino.memeapi.entity.Meme;
import com.seberino.memeapi.mapper.MemeMapper;
import com.seberino.memeapi.repository.MemeRepository;
import com.seberino.memeapi.request.CreateMemeRequest;
import com.seberino.memeapi.request.UpdateMemeRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemeService {
    private final MemeRepository repository;

    @Transactional
    public Meme create(CreateMemeRequest request) {
        log.debug("[create]: create method called with request: {}", request);
        Meme meme = repository.save(MemeMapper.mapToMeme(request));
        log.debug("[create]: meme created: {}", meme);

        return meme;
    }

    public Page<Meme> search(Integer page, Integer size) {
        log.debug("[search] search page {} with size {}", page, size);
        Page<Meme> memes = repository.findAll(PageRequest.of(page, size));
        log.debug("[search] page found: {}", memes);

        return memes;
    }

    public Meme getById(String id) {
        log.debug("[getById]: get Meme by id {}", id);
        return repository.findById(id).orElseThrow(() -> new RuntimeException("No Meme found using id" + id));
    }

    @Transactional
    public Meme update(Meme meme, UpdateMemeRequest request) {
        log.debug("[update]: update meme {} with request {}", meme, request);
        meme = MemeMapper.updateMeme(meme, request);
        return meme;
    }

    @Transactional
    public void delete(Meme meme) {
        log.trace("[delete]: delete meme {}", meme);
        repository.delete(meme);
    }
}
