package com.univercityweb.server.dto.user;

import com.univercityweb.server.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {
    private String user;
    private String name;
    private String password;

    public User toEntity(PasswordEncoder passwordEncoder) {
        return User.builder()
                .name(name)
                .user(user)
                .password(passwordEncoder.encode(password))
                .build();
    }

    public User toEntity() {
        return User.builder().build();
    }
}