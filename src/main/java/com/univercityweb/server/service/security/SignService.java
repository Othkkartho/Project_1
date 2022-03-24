package com.univercityweb.server.service.security;

import com.univercityweb.server.advice.exception.CEmailLoginFailedException;
import com.univercityweb.server.advice.exception.CEmailSignupFailedException;
import com.univercityweb.server.domain.user.User;
import com.univercityweb.server.domain.user.UserJpaRepo;
import com.univercityweb.server.dto.sign.UserLoginRequestDto;
import com.univercityweb.server.dto.sign.UserSignupRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service
@RequiredArgsConstructor
public class SignService {
    private final UserJpaRepo userJpaRepo;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public String login(UserLoginRequestDto userLoginRequestDto) {

        // 회원 정보 존재하는지 확인
        User user = userJpaRepo.findByUser(userLoginRequestDto.getUser()).orElseThrow(CEmailLoginFailedException::new);

        // 회원 패스워드 일치 여부 확인
        if (!passwordEncoder.matches(userLoginRequestDto.getPassword(), user.getPassword()))
            throw new CEmailLoginFailedException();

        return "true";
    }

    @Transactional // 회원가입
    public String signup(UserSignupRequestDto userSignupDto) {
        if (userJpaRepo.findByUser(userSignupDto.getUser()).isPresent())
            throw new CEmailSignupFailedException();
        return userJpaRepo.save(userSignupDto.toEntity(passwordEncoder)).getUser();
    }
}