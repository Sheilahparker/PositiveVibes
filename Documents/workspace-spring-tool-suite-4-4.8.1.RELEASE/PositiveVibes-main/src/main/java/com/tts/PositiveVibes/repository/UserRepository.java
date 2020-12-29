package com.tts.PositiveVibes.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tts.PositiveVibes.model.User;



@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}