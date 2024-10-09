package com.example.pleno_spring.controller;

import com.example.pleno_spring.model.User;
import com.example.pleno_spring.service.UserService;
import com.example.pleno_spring.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginRequest) {
        String email = loginRequest.get("email");
        String password = loginRequest.get("password");

        System.out.println("Email: " + email);
        System.out.println("Password: " + password);

        User user = userService.findByEmail(email);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            String accessToken = jwtUtil.generateToken(user.getId());
            String refreshToken = jwtUtil.generateToken(user.getId()); // In a real scenario, you'd use a different method for refresh tokens

            Map<String, Object> response = new HashMap<>();
            response.put("user_id", user.getId());
            response.put("accessToken", accessToken);
            response.put("refreshToken", refreshToken);

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body("Invalid email or password");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, String> registerRequest) {
        String fullName = registerRequest.get("full_name");
        String email = registerRequest.get("email");
        String password = registerRequest.get("password");
        String username = registerRequest.get("username");

        try {
            userService.registerUser(fullName, email, password, username);
            return ResponseEntity.ok("User registered successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Registration failed: " + e.getMessage());
        }
    }
}


