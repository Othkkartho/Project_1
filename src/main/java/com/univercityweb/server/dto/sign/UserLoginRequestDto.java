package com.univercityweb.server.dto.sign;

import com.univercityweb.server.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserLoginRequestDto {
    private String user;
    private String password;

    public User toUser(PasswordEncoder passwordEncoder) {
        return User.builder()
                .user(user)
                .password(passwordEncoder.encode(password))
                .build();
    }
}
