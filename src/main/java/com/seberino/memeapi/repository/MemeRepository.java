package com.seberino.memeapi.repository;

import com.seberino.memeapi.entity.Meme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemeRepository extends JpaRepository<Meme, String> {
}
