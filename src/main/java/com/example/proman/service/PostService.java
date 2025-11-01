package com.example.proman.service;

import com.example.proman.entity.PostEntity;
import com.example.proman.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<PostEntity> findAll() {
        return postRepository.findAll();
    }

    public Optional<PostEntity> findById(Long id) {
        return postRepository.findById(id);
    }

    public PostEntity save(PostEntity post) {
        return postRepository.save(post);
    }

    public void deleteById(Long id) {
        postRepository.deleteById(id);
    }

}
