/**
 * 
 */
package com.nisum.foodmanagement.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.nisum.foodmanagement.model.BookMeetingRoom;

/**
 * @author Raunak
 *
 */
@Repository
@Transactional
public interface BookMeetingRoomRepository extends CrudRepository<BookMeetingRoom, Integer>{
	
	//public List<Holiday> findByYear(String year);
	
	public BookMeetingRoom findByBookMeetingRoomId(int bookMeetingRoomId);
	
	@Query(value = "select mr.meeting_room_id, mr.name, mr.description from meeting_room mr, location l where l.location_id = mr.location_id and l.location_id=:locationId and mr.meeting_room_id NOT IN (select res.meeting_room_id from book_meeting_room as res where (:beginTime BETWEEN res.begin_time AND res.end_time) OR  (:endTime BETWEEN res.begin_time AND res.end_time))" , nativeQuery = true)
	public List<Object[]> getAvailableMeetingRoom(@Param("locationId") int locationId, @Param("beginTime") String beginTime, @Param("endTime") String endTime);
	
	@Query(value = "select loc.name as locationname, mroom.name as meetingroomname, bookmroom.meeting_title, bookmroom.meeting_room_id, bookmroom.begin_time, bookmroom.end_time, bookmroom.head_count from book_meeting_room bookmroom, user user, meeting_room mroom, location loc where bookmroom.user_id=:userId and bookmroom.user_id = user.user_id and bookmroom.meeting_room_id = mroom.meeting_room_id and mroom.location_id = loc.location_id" , nativeQuery = true)
	public List<Object[]> getUserBooking(@Param("userId") int userId);
	
}
