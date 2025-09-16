package com.financesapi.controller;

import com.financesapi.dto.UserResponse;
import com.financesapi.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    @GetMapping("/by-email")
    public ResponseEntity<UserResponse> getUserByEmail(@RequestParam String email){
        UserResponse user = userService.getUserByEmail(email);
        return ResponseEntity.ok(user);
    }
}
