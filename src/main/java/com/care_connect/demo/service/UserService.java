package com.care_connect.demo.service;

import com.care_connect.demo.domain.User;
import com.care_connect.demo.infra.security.UserRole;
import com.care_connect.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Transactional
    public User createUser(String email, String password,  UserRole role) {
        // Verifica se o e-mail já está em uso
        if (userRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("E-mail já cadastrado!");
        }

        // Criar novo usuário
        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(role);

        return userRepository.save(user);
    }

    public User findById(UUID userId) {
        return userRepository.findUserById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }
}

