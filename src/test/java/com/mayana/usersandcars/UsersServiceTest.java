package com.mayana.usersandcars;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import com.mayana.usersandcars.entity.Users;
import com.mayana.usersandcars.repository.UsersRepository;
import com.mayana.usersandcars.service.UsersService;

@SpringBootTest
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UsersServiceTest {
    @Autowired
    private UsersService usersService;

    @Autowired
    private UsersRepository usersRepository;

    @Test
    public void testCreateUser() {
        Users users = new Users();
        users.setFirstName("John");
        users.setLastName("Doe");
        users.setEmail("john.doe@example.com");
        users.setLogin("johndoe");
        users.setPassword("password");

        Users createdUser = usersService.createUser(users);
        assertNotNull(createdUser.getId());

        Users retrievedUser = usersRepository.findById(createdUser.getId()).orElse(null);
        assertNotNull(retrievedUser);
        assertEquals("John", retrievedUser.getFirstName());
    }

    @Test
    public void testUpdateUser() {
        Users users = new Users();
        users.setFirstName("Alice");
        users.setLastName("Smith");
        users.setEmail("alice.smith@example.com");
        users.setLogin("alicesmith");
        users.setPassword("password");

        Users createdUser = usersService.createUser(users);
        Long userId = createdUser.getId();

        Users updatedUser = new Users();
        updatedUser.setFirstName("Bob");
        updatedUser.setLastName("Johnson");

        Users result = usersService.updateUser(userId, updatedUser);
        assertNotNull(result);
        assertEquals("Bob", result.getFirstName());
    }

    @Test
    public void testDeleteUser() {
        Users users = new Users();
        users.setFirstName("Eve");
        users.setLastName("Taylor");
        users.setEmail("eve.taylor@example.com");
        users.setLogin("evetaylor");
        users.setPassword("password");

        Users createdUser = usersService.createUser(users);
        Long userId = createdUser.getId();

        usersService.deleteUser(userId);

        Users deletedUser = usersRepository.findById(userId).orElse(null);
        assertNull(deletedUser);
    }
}
