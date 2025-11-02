package com.example.proman.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.proman.entity.UserEntity;
import java.util.Optional;


public interface UserRepository extends JpaRepository<UserEntity, Long>{
    Optional<UserEntity> findByUsername(String username);
}
