package com.service.localservice.service;

import com.service.localservice.model.User;
import com.service.localservice.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository repo;

    public UserService(UserRepository repo) { this.repo = repo; }

    public User register(User u) {
        // NOTE: password should be hashed in production. Keep plaintext only for demo.
        return repo.save(u);
    }

    public Optional<User> findByEmail(String email) { return repo.findByEmail(email); }
}
