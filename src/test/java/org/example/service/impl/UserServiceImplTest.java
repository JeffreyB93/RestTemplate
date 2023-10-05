package org.example.service.impl;

import org.example.model.User;
import org.example.repository.UserRepository;
import org.example.repository.impl.UserRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService = new UserServiceImpl();
    @Mock
    private UserRepositoryImpl userRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void findById() {
        User user = new User();
        user.setId(1L);
        user.setName("Коля");
        Mockito.when(userRepository.findById(1L)).thenReturn(user);
        Assertions.assertEquals(user, userService.findById(1L));
    }
    @Test
    void deleteById() {
        Mockito.when(userRepository.deleteById(1L)).thenReturn(true);
        Assertions.assertTrue(userService.deleteById(1L));
    }

    @Test
    void save() {
        User user = new User();
        user.setId(1L);
        user.setName("Коля");
        Mockito.when(userRepository.save(user)).thenReturn(user);
        Assertions.assertEquals(user, userService.save(user));
    }

    @Test
    void findAll() {
        User user1 = new User();
        user1.setId(1L);
        user1.setName("Коля");

        User user2 = new User();
        user2.setId(2L);
        user2.setName("Катя");
        ArrayList<User> users = new ArrayList<User>(List.of(user1, user2));

        Mockito.when(userRepository.findAll()).thenReturn(users);
        Assertions.assertEquals(users, userService.findAll());
    }
}