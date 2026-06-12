package com.ambientwave.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ambientwave.demo.models.ResUser;

import java.util.Optional;

@Repository
public interface ResUserRepository extends JpaRepository<ResUser, Long> {
    Optional<ResUser> findByUsername(String username);
}
