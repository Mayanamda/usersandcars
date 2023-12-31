package com.mayana.usersandcars.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mayana.usersandcars.entity.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
	Users findByLogin(String login);
}
