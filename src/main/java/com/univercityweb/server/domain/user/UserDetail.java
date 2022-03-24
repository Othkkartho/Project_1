package com.univercityweb.server.domain.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;

@Builder
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users_detail")
public class UserDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dbId;

    @Column(length = 100)
    private String department;

    @Column(length = 100)
    private String address;

    @Column(length = 100)
    private String phoneNum;

    @Column(length = 100, unique = true)
    private String email;

    @Column
    private LocalDateTime enterDate;

    @Column
    private LocalDateTime gradDate;

    private String userId;
}
