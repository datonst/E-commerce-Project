package com.shopme.admin.user;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;


public class PasswordEnconderTest {
    @Test
    public void testEncodePassword(){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String rawPassword="nam2020";
        String encodedPassword =passwordEncoder.encode(rawPassword);
        System.out.println(encodedPassword);
        boolean matches=passwordEncoder.matches(rawPassword,encodedPassword);
        assertThat(matches).isTrue();
    }
}
