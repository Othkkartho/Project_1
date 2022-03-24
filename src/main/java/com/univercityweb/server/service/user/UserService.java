package com.univercityweb.server.service.user;

import com.univercityweb.server.advice.exception.CUserNotFoundException;
import com.univercityweb.server.domain.user.User;
import com.univercityweb.server.domain.user.UserJpaRepo;
import com.univercityweb.server.dto.user.UserRequestDto;
import com.univercityweb.server.dto.user.UserResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {
    private UserJpaRepo userJpaRepo;

    @Transactional
    public String save(UserRequestDto userDto) {
        User saved = userJpaRepo.save(userDto.toEntity());
        return saved.getUser();
    }

    @Transactional(readOnly = true)
    public UserResponseDto findById(Long id) {
        User user = userJpaRepo.findById(id).orElseThrow(CUserNotFoundException::new);
        return new UserResponseDto(user);
    }

    @Transactional(readOnly = true)
    public List<UserResponseDto> findAllUser() {
        return userJpaRepo.findAll()
                .stream()
                .map(UserResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public String update(String id, UserRequestDto userRequestDto) {
        User modifiedUser = userJpaRepo.findByUser(id).orElseThrow(CUserNotFoundException::new);
        modifiedUser.updatePassword(userRequestDto.getPassword());
        return id;
    }

    @Transactional
    public void delete(Long id) {
        userJpaRepo.deleteById(id);
    }
}

