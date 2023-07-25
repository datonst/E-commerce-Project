package com.shopme.admin.user;

import com.shopme.common.entity.Role;
import com.shopme.common.entity.User;
import jakarta.persistence.Id;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;


import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {
    @Autowired
    private UserRepository repo;
    @Autowired
    private TestEntityManager entityManager;
    @Test
    public void testCreateUser(){
        Role roleAdmin=entityManager.find(Role.class, 2);
        User userNameHM= new User("nam@codejave.net","name2020","Nam","Haa Minh");
        userNameHM.addRoles(roleAdmin);
        User savedUser=repo.save(userNameHM);
        assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    public void testCreateNewUserWithTwoRoles(){
        User userRavi= new User ("ravi@gmail.com","ravi2020","Ravi","Kumar");
        Role roleEditor =new Role(3);
        Role roleAssistant=new Role(5);

        userRavi.addRoles(roleEditor);
        userRavi.addRoles(roleAssistant);
        User saveUser=repo.save(userRavi);
        assertThat(saveUser.getId()).isGreaterThan(0);
    }
    @Test
    public void testListAllUsers(){
        Iterable<User> listUser =repo.findAll();
        listUser.forEach(user->System.out.println(user));
    }
    @Test
    public void testGetUserById(){
        User userNam=repo.findById(1).get();
        System.out.println(userNam);

    }

    @Test
    public void testUpdateUserDetails(){
        User userNam=repo.findById(1).get();
        userNam.setEnabled(true);
        userNam.setEmail("hello@gmail.com");
        repo.save(userNam);
    }

    @Test
    public void testUpdateUserRoles(){
        User userRavi=repo.findById(1).get();
        userRavi.getRoles().remove(new Role(5));
        Role newRole =new Role(2);
        userRavi.addRoles(newRole);
        repo.save(userRavi);
    }

    @Test
    public void testDeleteUser(){
        Integer user=2;
        repo.deleteById(user);
    }
}
