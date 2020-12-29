package com.tts.PositiveVibes.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tts.PositiveVibes.model.User;
import com.tts.PositiveVibes.model.Vibe;



@Repository
public interface VibeRepository extends CrudRepository<Vibe, Long> {
    List<Vibe> findAllByOrderByCreatedAtDesc();
    List<Vibe> findAllByUserOrderByCreatedAtDesc(User user);
    List<Vibe> findAllByUserInOrderByCreatedAtDesc(List<User> users);
    List<Vibe> findByTags_PhraseOrderByCreatedAtDesc(String phase);
}