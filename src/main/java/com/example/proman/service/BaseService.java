package com.example.proman.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.proman.entity.BaseEntity;

import jakarta.transaction.Transactional;

public abstract class BaseService<T extends JpaRepository<U, Long>, U extends BaseEntity> {

    private final T repository;

    public BaseService(T repository) {
        this.repository = repository;
    }

    public List<U> findAll() {
        List<U> posts = repository.findAll();
        posts.removeIf(post -> Boolean.FALSE.equals(post.getIsActive()));
        return posts;
    }

    public Optional<U> findById(Long id) {
        Optional<U> postOpt = repository.findById(id);
        if (postOpt.isPresent() && postOpt.get().getIsActive()) {
            return postOpt;
        } else {
            return Optional.empty();
        }

    }

    @Transactional
    public U save(U post) {
        if (post.getId() == null) {
            post.setCreatedDate(LocalDateTime.now());
        } else {
            post.setUpdatedDate(LocalDateTime.now());
        }
        return repository.save(post);
    }

    @Transactional
    public void deleteById(Long id) {
        Optional<U> postOpt = repository.findById(id);
        if (postOpt.isPresent() && postOpt.get().getIsActive()) {
            postOpt.get().setIsActive(Boolean.FALSE);
            save(postOpt.get());
        }
    }

    protected T getRepository() {
        return repository;
    }
}
