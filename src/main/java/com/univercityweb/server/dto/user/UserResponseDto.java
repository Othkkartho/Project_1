package com.univercityweb.server.dto.user;

import com.univercityweb.server.domain.user.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Getter
public class UserResponseDto {
    private final String user;
    private final String name;
    private final String roles;
    private Collection<? extends GrantedAuthority> authorities;

    public UserResponseDto(User user) {
        this.user = user.getUser();
        this.name = user.getName();
        this.roles = user.getRoles();
        this.authorities = user.getAuthorities();
    }
}
