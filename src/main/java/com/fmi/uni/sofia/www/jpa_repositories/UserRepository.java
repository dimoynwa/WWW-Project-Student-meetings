package com.fmi.uni.sofia.www.jpa_repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.fmi.uni.sofia.www.entities.User;

public interface UserRepository extends CrudRepository<User, Integer> {
	
	public List<User> findByUserName(String userName);
	public User findByUserNameAndPassword(String userName, String password);
}
