package com.financesapi.service;

import com.financesapi.dto.UserResponse;
import com.financesapi.entity.User;
import com.financesapi.repository.UserRepository;
import com.financesapi.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Override
    public UserResponse getUserByEmail(String email) {
        if (userRepository.findByEmail(email).isPresent()) {
            User user = userRepository.findByEmail(email).get();
            return new UserResponse(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail());
        }
        throw new RuntimeException("User not found");

    }

    @Override
    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();

        if(users.isEmpty()) {
            throw new RuntimeException("No Users not found");
        }

        // return all users filtering out password
        return users.stream()
                .map(user -> new UserResponse(
                        user.getId(),
                        user.getEmail(),
                        user.getFirstName(),
                        user.getLastName()
                ))
                .toList();

    }

    @Override
    public boolean deleteUser(String email) {
        if(userRepository.deleteByEmail(email) > 1 ){
            return true;
        };
        return false;
    }
}
