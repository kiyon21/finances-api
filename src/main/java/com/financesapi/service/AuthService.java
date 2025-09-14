package com.financesapi.service;

import com.financesapi.entity.User;

public interface AuthService {
    User createUser(User user);
    User findByEmail(String email);
}
