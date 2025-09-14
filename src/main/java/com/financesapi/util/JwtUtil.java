package com.financesapi.util;

import org.springframework.stereotype.Component;

@Component
public class JwtUtil {
    private final String SECRET = "secret";
    private final long EXPIRATION = 1000 * 60 * 60 * 24;


}
