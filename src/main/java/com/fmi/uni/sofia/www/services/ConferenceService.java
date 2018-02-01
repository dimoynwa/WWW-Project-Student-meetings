package com.fmi.uni.sofia.www.services;

import java.util.List;

import com.fmi.uni.sofia.www.objects.ConferenceDTO;
import com.fmi.uni.sofia.www.objects.NewConferenceDTO;
import com.fmi.uni.sofia.www.objects.StatusMessage;

public interface ConferenceService {
	
	public StatusMessage createConference(NewConferenceDTO body, Integer userId);
	public List<ConferenceDTO> getUserConferences(Integer userId);
	public List<ConferenceDTO> getAllConferences(Integer userId);
	public StatusMessage attend(ConferenceDTO dto, Integer userId);
	public List<ConferenceDTO> userAttendances(Integer userId);
	public StatusMessage deleteAttendance(Integer confId, Integer userId);
	public ConferenceDTO getConferenceById(Integer confId);
}
