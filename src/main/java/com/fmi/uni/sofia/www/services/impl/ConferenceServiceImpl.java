package com.fmi.uni.sofia.www.services.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.fmi.uni.sofia.www.entities.Attends;
import com.fmi.uni.sofia.www.entities.Conference;
import com.fmi.uni.sofia.www.entities.User;
import com.fmi.uni.sofia.www.jpa_repositories.AttendsRepository;
import com.fmi.uni.sofia.www.jpa_repositories.ConferenceRepository;
import com.fmi.uni.sofia.www.jpa_repositories.UserRepository;
import com.fmi.uni.sofia.www.mapping.EntityMapper;
import com.fmi.uni.sofia.www.objects.ConferenceDTO;
import com.fmi.uni.sofia.www.objects.NewConferenceDTO;
import com.fmi.uni.sofia.www.objects.StatusMessage;
import com.fmi.uni.sofia.www.services.ConferenceService;

public class ConferenceServiceImpl implements ConferenceService {

	@Autowired
	private ConferenceRepository conferenceRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AttendsRepository attendsRepository;
	
	@Override
	public StatusMessage createConference(NewConferenceDTO body, Integer userId) {
		Conference ent = EntityMapper.conferenceFromNewConferenceDTO(body, userId);
		Conference saved = conferenceRepository.save(ent);
		if(saved == null || saved.getId() == null) {
			return StatusMessage.errorStatusMessage("Not successfull save!");
		}
		return StatusMessage.successStatusMessage(saved.getId());
	}

	@Override
	public List<ConferenceDTO> getUserConferences(Integer userId) {
		List<Conference> list = conferenceRepository.getUserConferences(userId);
		return list.stream().map(EntityMapper::conferenceDTOFromConference).collect(Collectors.toList());
	}

	@Override
	public List<ConferenceDTO> getAllConferences(Integer userId) {
		List<ConferenceDTO> at = userAttendances(userId);
		Set<Integer> aten = at.stream().map(a -> a.getId()).collect(Collectors.toSet());
		List<Conference> list = conferenceRepository.getAllConferences(userId);
		List<ConferenceDTO> res = new LinkedList<>();
		for(Conference c : list) {
			ConferenceDTO dto = EntityMapper.conferenceDTOFromConference(c);
			dto.setAttend(aten.contains(c.getId()));
			res.add(dto);
		}
		return res;
	}
	
	@Transactional
	@Override
	public StatusMessage attend(ConferenceDTO dto, Integer userId) {
		User u = userRepository.findOne(userId);
		Conference c = conferenceRepository.findOne(dto.getId());
		Attends a = new Attends();
		a.setConference(c);
		a.setUser(u);
		a = attendsRepository.save(a);
		if(a == null || a.getId() == null) {
			return StatusMessage.errorStatusMessage("ERROR");
		}
		return StatusMessage.successStatusMessage(a.getId());
		
	}

	@Override
	public List<ConferenceDTO> userAttendances(Integer userId) {
		List<Attends> attends = attendsRepository.getUserAttends(userId);
		return attends.stream().map(a -> EntityMapper.conferenceDTOFromConference(a.getConference())).collect(Collectors.toList());
	}

	@Override
	public StatusMessage deleteAttendance(Integer confId, Integer userId) {
		attendsRepository.deleteAttendance(confId, userId);
		return StatusMessage.successStatusMessage(null);
	}

	@Override
	public ConferenceDTO getConferenceById(Integer confId) {
		Conference c = conferenceRepository.findOne(confId);
		return EntityMapper.conferenceDTOFromConference(c);
	}

}
