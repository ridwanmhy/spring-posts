package com.example.proman.service;

import com.example.proman.entity.PostEntity;
import com.example.proman.repository.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class PostService extends BaseService<PostRepository, PostEntity>{

    public PostService(PostRepository repository) {
        super(repository);
    }
    
}
