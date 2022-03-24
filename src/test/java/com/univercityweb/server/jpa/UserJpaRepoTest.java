package com.univercityweb.server.jpa;

import com.univercityweb.server.advice.exception.CUserNotFoundException;
import com.univercityweb.server.domain.user.User;
import com.univercityweb.server.domain.user.UserJpaRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserJpaRepoTest {

    @Autowired
    private UserJpaRepo userJpaRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private String name = "name1";
    private String user = "20202020";
    private String password = "password1";


    @Test
    public void 회원저장_후_유저ID로_회원검색() throws Exception {

        //given
        userJpaRepo.save(User.builder()
                .name(name)
                .user(user)
                .password(passwordEncoder.encode(password))
                .roles("ROLE_USER")
                .build());

        //when
        User users = userJpaRepo.findByUser(user).orElseThrow(CUserNotFoundException::new);

        //then
        assertNotNull(users);
        assertEquals(users.getUsername(), users.getUsername());
        assertThat(users.getName()).isEqualTo(name);
        assertThat(users.getUser()).isEqualTo(user);
    }
}