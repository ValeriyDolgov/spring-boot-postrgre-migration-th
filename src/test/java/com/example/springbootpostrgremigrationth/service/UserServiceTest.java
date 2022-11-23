package com.example.springbootpostrgremigrationth.service;

import com.example.springbootpostrgremigrationth.model.Role;
import com.example.springbootpostrgremigrationth.model.User;
import com.example.springbootpostrgremigrationth.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService service;
    @Autowired
    private UserRepository repo;

    @Test
    @Transactional
    void findByUsername() { // ТОкены
        String nameTrue = "site_admin";
        User user = service.findByUsername(nameTrue);
        User user2 = new User();
        user2.setId(2L);
        user2.setUsername(nameTrue);
        user2.setPassword("$2a$12$rju313pqH9J3fxe7pJPhEeNM9d1/mJ65WZoGwL2dA.AcJQjG9BNcS");
        user2.setEmail("admin@gmail.com");
        user2.setRoles(Collections.emptyList());
        Collection<Role> roles = user.getRoles();
        assertEquals(user, user2);
    }
}