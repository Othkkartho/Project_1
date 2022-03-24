package com.univercityweb.server.dto.sign;

import com.univercityweb.server.domain.user.User;
import com.univercityweb.server.domain.user.UserDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserSignupRequestDto {
    private String user;
    private String name;
    private String password;


    public User toEntity(PasswordEncoder passwordEncoder) {
        return User.builder()
                .user(user)
                .name(name)
                .password(passwordEncoder.encode(password))
                .roles("ROLE_USER")
                .userDetails(Collections.singletonList(UserDetail.builder().userId(user).build()))
                .build();
    }
}