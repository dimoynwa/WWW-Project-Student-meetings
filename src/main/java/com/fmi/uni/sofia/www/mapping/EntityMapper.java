package com.fmi.uni.sofia.www.mapping;

import com.fmi.uni.sofia.www.entities.Conference;
import com.fmi.uni.sofia.www.entities.User;
import com.fmi.uni.sofia.www.objects.ConferenceDTO;
import com.fmi.uni.sofia.www.objects.NewConferenceDTO;
import com.fmi.uni.sofia.www.objects.UserDTO;
import com.fmi.uni.sofia.www.objects.UserRegDTO;

public class EntityMapper {
	
	
	private EntityMapper(){};
	
	public static User userFromUserRegDTO(UserRegDTO dto) {
		if(dto == null) {
			return null;
		}
		
		User u = new User();
		
		u.setFirstName(dto.getFirstName());
		u.setLastName(dto.getLastName());
		u.setPassword(dto.getPassword());
		u.setUserName(dto.getUserName());
		u.setImage(dto.getImage());
		
		return u;
	}
	
	public static Conference conferenceFromNewConferenceDTO(NewConferenceDTO dto, Integer userId) {
		if(dto == null) {
			return null;
		}
		
		Conference c = new Conference();
		
		c.setId(dto.getId());
		c.setEndDate(dto.getEndDate());
		c.setLongDescription(dto.getLongDescription());
		c.setName(dto.getName());
		c.setShortDescription(dto.getShortDescription());
		c.setStartDate(dto.getStartDate());
		c.setTheme(dto.getTheme());
		c.setPlace(dto.getPlace());
		
		User u = new User();
		u.setId(userId);
		
		c.setUser(u);
		
		return c;
	}
	
	public static ConferenceDTO conferenceDTOFromConference(Conference c) {
		if(c == null) {
			return null;
		}
		
		ConferenceDTO dto = new ConferenceDTO();
		
		dto.setEndDate(c.getEndDate());
		dto.setId(c.getId());
		dto.setName(c.getName());
		dto.setShortDescription(c.getShortDescription());
		dto.setStartDate(c.getStartDate());
		dto.setTheme(c.getTheme());
		dto.setPlace(c.getPlace());
		dto.setLongDescription(c.getLongDescription());
		
		return dto;
		
	}
	
	public static UserDTO userDTOFromUser(User u) {
		if(u == null) {
			return null;
		}
		
		UserDTO dto = new UserDTO();
		
		dto.setFirstName(u.getFirstName());
		dto.setId(u.getId());
		dto.setImage(u.getImage());
		dto.setLastName(u.getLastName());
		
		return dto;
	}
	
}
