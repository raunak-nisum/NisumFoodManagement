/**
 * 
 */
package com.nisum.foodmanagement.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nisum.foodmanagement.model.MeetingRoom;

/**
 * @author Raunak
 *
 */
@Repository
@Transactional
public interface MeetingRoomRepository extends CrudRepository<MeetingRoom, Integer>{
	
public MeetingRoom findByMeetingRoomId(int meetingRoomId);
	
	public List<MeetingRoom> findAllByLocationId(int locationId);

}
