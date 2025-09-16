package com.financesapi.service;

import com.financesapi.dto.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse getUserByEmail(String email);
    List<UserResponse> getAllUsers();
    boolean deleteUser(String email);

}
