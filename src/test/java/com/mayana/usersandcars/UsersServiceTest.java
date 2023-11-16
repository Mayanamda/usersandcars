package com.mayana.usersandcars;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.mayana.usersandcars.entity.Users;
import com.mayana.usersandcars.repository.UsersRepository;
import com.mayana.usersandcars.service.UsersService;

@SpringBootTest
@ActiveProfiles("testUsersService")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

public class UsersServiceTest {
	
	@Autowired
    private UsersService usersService;

    @Autowired
    private UsersRepository usersRepository;

    @Test
    public void testGetAllUsers() {
        Users user1 = new Users();
        Users user2 = new Users();
        when(usersRepository.findAll()).thenReturn(Arrays.asList(user1, user2));

        List<Users> userList = usersService.getAllUsers();

        assertEquals(2, userList.size());
    }

    @Test
    public void testGetUserById() {
        Users user = new Users();
        when(usersRepository.findById(1L)).thenReturn(Optional.of(user));

        Users retrievedUser = usersService.getUserById(1L);

        assertNotNull(retrievedUser);
    }

    @Test
    public void testCreateUser() {
        Users user = new Users();
        when(usersRepository.save(any(Users.class))).thenReturn(user);

        Users createdUser = usersService.createUser(user);

        assertNotNull(createdUser);
    }

    @Test
    public void testUpdateUser() {
        Users existingUser = new Users();
        existingUser.setId(1L);
        when(usersRepository.findById(1L)).thenReturn(Optional.of(existingUser));
        when(usersRepository.save(any(Users.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Users updatedUser = new Users();
        updatedUser.setFirstName("UpdatedFirstName");

        Users result = usersService.updateUser(1L, updatedUser);

        assertNotNull(result);
        assertEquals("UpdatedFirstName", result.getFirstName());
    }

    @Test
    public void testDeleteUser() {
        Users user = new Users();
        user.setId(1L);
        when(usersRepository.findById(1L)).thenReturn(Optional.of(user));

        usersService.deleteUser(1L);

        verify(usersRepository, times(1)).deleteById(1L);
    }
}
