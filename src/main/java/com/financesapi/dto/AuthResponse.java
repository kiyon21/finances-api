package com.financesapi.dto;

public record AuthResponse(String token, String tokenType, long expiresIn) {
}
