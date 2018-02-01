package com.fmi.uni.sofia.www.services;

import com.fmi.uni.sofia.www.objects.StatusMessage;
import com.fmi.uni.sofia.www.objects.UserDTO;
import com.fmi.uni.sofia.www.objects.UserLoginDTO;
import com.fmi.uni.sofia.www.objects.UserRegDTO;

public interface UserService {
	
	public StatusMessage registrateUser(UserRegDTO body);
	public StatusMessage login(UserLoginDTO body);
	public UserDTO getUserInfo(Integer userId);
	
}
