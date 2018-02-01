package com.fmi.uni.sofia.www.services.impl;

import java.util.List;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;

import com.fmi.uni.sofia.www.entities.User;
import com.fmi.uni.sofia.www.jpa_repositories.UserRepository;
import com.fmi.uni.sofia.www.mapping.EntityMapper;
import com.fmi.uni.sofia.www.objects.StatusMessage;
import com.fmi.uni.sofia.www.objects.UserDTO;
import com.fmi.uni.sofia.www.objects.UserLoginDTO;
import com.fmi.uni.sofia.www.objects.UserRegDTO;
import com.fmi.uni.sofia.www.services.UserService;

public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public StatusMessage registrateUser(UserRegDTO body) {
		List<User> l = userRepository.findByUserName(body.getUserName());
		if(l != null && l.size() > 0 ) {
			//Throw exception
		}
		User ent = EntityMapper.userFromUserRegDTO(body);
		User saved = userRepository.save(ent);
		if(saved == null || saved.getId() == null) {
			//Throw exception
		}
		return StatusMessage.successStatusMessage(saved.getId());
	}

	@Override
	public StatusMessage login(UserLoginDTO body) {
		User u = userRepository.findByUserNameAndPassword(body.getUsername(), body.getPassword());
		if(u == null) {
			throw new ValidationException();
		}
		return StatusMessage.successStatusMessage(u.getId());
	}

	@Override
	public UserDTO getUserInfo(Integer userId) {
		User u = userRepository.findOne(userId);
		return EntityMapper.userDTOFromUser(u);
	}

}
