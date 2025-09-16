package com.financesapi.service;

import com.financesapi.dto.AuthResponse;
import com.financesapi.dto.LoginRequest;
import com.financesapi.dto.RegisterRequest;
import com.financesapi.entity.User;
import com.financesapi.repository.UserRepository;
import com.financesapi.util.JwtUtil;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public AuthResponse register(RegisterRequest registerRequest) {
        if(userRepository.findByEmail(registerRequest.email()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        // create user
        User user = new User();
        user.setEmail(registerRequest.email());
        user.setPassword(passwordEncoder.encode(registerRequest.password()));
        user.setFirstName(registerRequest.firstName());
        user.setLastName(registerRequest.lastName());
        user.setCreatedAt(new Date());

        // save user to repo
        userRepository.save(user);

        // create token to send
        String token = jwtUtil.generateToken(user.getEmail());

        return new AuthResponse(token);
    }

    @Override
    public AuthResponse login(LoginRequest loginRequest) {
        if(userRepository.findByEmail(loginRequest.email()).isEmpty()) {
            throw new RuntimeException("Email not found. Please sign up.");
        }
        User user = userRepository.findByEmail(loginRequest.email()).get();
        System.out.println(user);
        if (passwordEncoder.matches(loginRequest.password(), user.getPassword())){
            return new AuthResponse(jwtUtil.generateToken(user.getEmail()));
        } else {
            throw new RuntimeException("Wrong password. Please try again.");
        }
    }
}
