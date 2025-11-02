package com.example.proman.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.proman.entity.UserEntity;
import com.example.proman.repository.UserRepository;
import com.example.util.Sha256Hasher;

import jakarta.transaction.Transactional;

@Service
public class UserService extends BaseService<UserRepository, UserEntity> {

    public UserService(UserRepository repository) {
        super(repository);
    }

    @Override
    @Transactional
    public UserEntity save(UserEntity user) {
        if (user.getId() == null) {
            user.setCreatedDate(LocalDateTime.now());
        } else {
            user.setUpdatedDate(LocalDateTime.now());
        }
        if (!user.getPassword().isEmpty()) {
            user.setPassword(Sha256Hasher.hash(user.getPassword()));
        }
        return getRepository().save(user);
    }

    public UserEntity login(UserEntity user) {
        Optional<UserEntity> userOpt = getRepository().findByUsername(user.getUsername());

        if (userOpt.isPresent() &&
                Sha256Hasher.verify(user.getPassword(), userOpt.get().getPassword())) {
            return userOpt.get();
        }

        return new UserEntity();
    }

}
