package com.financesapi.service;

import com.financesapi.dto.AuthResponse;
import com.financesapi.dto.LoginRequest;
import com.financesapi.dto.RegisterRequest;
import com.financesapi.entity.User;

public interface AuthService {
    AuthResponse register(RegisterRequest registerRequest);
    AuthResponse login(LoginRequest loginRequest);
}
