package com.tts.PositiveVibes.repository;

import org.springframework.data.repository.CrudRepository;

import com.tts.PositiveVibes.model.Tag;

import org.springframework.data.repository.CrudRepository;


public interface TagRepository extends CrudRepository<Tag, Long>{
	Tag findByPhrase(String phrase);
}
