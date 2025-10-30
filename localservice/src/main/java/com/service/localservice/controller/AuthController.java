package com.service.localservice.controller;

import com.service.localservice.model.User;
import com.service.localservice.service.ProviderService;
import com.service.localservice.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:5174"})
public class AuthController {
    private final UserService userService;
    private final ProviderService providerService;

    public AuthController(UserService userService, ProviderService providerService) { this.userService = userService; this.providerService = providerService; }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        if (user.getEmail() == null) return ResponseEntity.badRequest().body(Map.of("error", "email required"));
        if (userService.findByEmail(user.getEmail()).isPresent()) return ResponseEntity.status(409).body(Map.of("error", "email exists"));
        User saved = userService.register(user);
        // hide password
        saved.setPassword(null);
        return ResponseEntity.ok(saved);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
        String email = body.get("email");
        String password = body.get("password");
        if (email == null || password == null) return ResponseEntity.badRequest().body(Map.of("error", "email and password required"));
        return userService.findByEmail(email)
                .map(u -> {
                    if (password.equals(u.getPassword())) {
                        u.setPassword(null);
                        return ResponseEntity.ok(u);
                    }
                    return ResponseEntity.status(401).body(Map.of("error", "invalid credentials"));
                })
                .orElseGet(() -> ResponseEntity.status(404).body(Map.of("error", "user not found")));
    }

    // provider login (simple plaintext check for demo)
    @PostMapping("/provider/login")
    public ResponseEntity<?> providerLogin(@RequestBody Map<String, String> body) {
        String email = body.get("email");
        String password = body.get("password");
        if (email == null || password == null) return ResponseEntity.badRequest().body(Map.of("error", "email and password required"));
        return providerService.findByEmail(email)
                .map(p -> {
                    if (password.equals(p.getPassword())) {
                        p.setPassword(null); // don't send password to client
                        return ResponseEntity.ok(p);
                    }
                    return ResponseEntity.status(401).body(Map.of("error", "invalid credentials"));
                })
                .orElseGet(() -> ResponseEntity.status(404).body(Map.of("error", "provider not found")));
    }
}
