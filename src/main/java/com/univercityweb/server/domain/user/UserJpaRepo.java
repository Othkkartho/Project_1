package com.univercityweb.server.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserJpaRepo extends JpaRepository<User, Long> {
    Optional<User> findByUser(String userId);
}
