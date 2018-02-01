package com.fmi.uni.sofia.www.jpa_repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.fmi.uni.sofia.www.entities.Conference;

public interface ConferenceRepository extends CrudRepository<Conference, Integer> {
	
	@Query("select c from conference c where user_id = ?1")
	public List<Conference> getUserConferences(Integer id);
	
	@Query("select c from conference c where user_id <> ?1")
	public List<Conference> getAllConferences(Integer id);
	
	@Query(value="insert into attends (?1, ?2) select a from attends", nativeQuery=true)
	public void attend(Integer confId, Integer userId);
	
}
