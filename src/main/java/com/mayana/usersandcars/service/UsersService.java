package com.mayana.usersandcars.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mayana.usersandcars.entity.Users;
import com.mayana.usersandcars.repository.UsersRepository;


@Service
public class UsersService {

	 @Autowired
	    private UsersRepository usersRepository;

	    public List<Users> getAllUsers() {
	        return (List<Users>) usersRepository.findAll();
	    }

	    public Users getUserById(Long id) {
	        return usersRepository.findById(id).orElse(null);
	    }

	    public Users createUser(Users users) {
	        return usersRepository.save(users);
	    }

	    public Users updateUser(Long id, Users updatedUser) {
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

	    public void deleteUser(Long id) {
	        usersRepository.deleteById(id);
	    }
}