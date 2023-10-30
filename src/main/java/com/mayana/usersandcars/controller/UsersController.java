package com.mayana.usersandcars.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mayana.usersandcars.entity.Users;
import com.mayana.usersandcars.repository.UsersRepository;

@RestController
@RequestMapping("/api/users")
public class UsersController {
	
    @Autowired
    private UsersRepository usersRepository;

    @GetMapping("/")
    public List<Users> getAllUsers() {
        return (List<Users>) usersRepository.findAll();
    }

    @GetMapping("/{id}")
    public Users getUserById(@PathVariable Long id) {
        return usersRepository.findById(id).orElse(null);
    }

    @PostMapping("/")
    public Users createUser(@RequestBody Users users) {
        return usersRepository.save(users);
    }

    @PutMapping("/{id}")
    public Users updateUser(@PathVariable Long id, @RequestBody Users updatedUser) {
        Users existingUser = usersRepository.findById(id).orElse(null);
        if (existingUser != null) {
            existingUser.setFirstName(updatedUser.getFirstName());
            existingUser.setLastName(updatedUser.getLastName());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setBirthday(updatedUser.getBirthday());
            existingUser.setLogin(updatedUser.getLogin());
            existingUser.setPassword(updatedUser.getPassword());
            existingUser.setPhone(updatedUser.getPhone());

            return usersRepository.save(existingUser);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        usersRepository.deleteById(id);
    }
    
    
}
