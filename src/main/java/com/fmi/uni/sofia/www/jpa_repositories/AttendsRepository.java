package com.fmi.uni.sofia.www.jpa_repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.fmi.uni.sofia.www.entities.Attends;

public interface AttendsRepository extends CrudRepository<Attends, Integer> {
	
//	@EntityGraph(value="attends.graph", type = EntityGraph.EntityGraphType.FETCH)
	@Query(value="select a from attends a where user_id = ?1")
	public List<Attends> getUserAttends(Integer userId);
	
//	@EntityGraph(value="attends.graph", type = EntityGraph.EntityGraphType.FETCH)
	@Query(value="select a from attends a where conference_id = ?1")
	public List<Attends> getConferenceAttenders(Integer conferenceId);
	
	@Modifying
	@Query(value="delete from attends where conference_id=?1 and user_id=?2", nativeQuery=true)
	@Transactional
	public void deleteAttendance(Integer conferenceId, Integer userId);
}
