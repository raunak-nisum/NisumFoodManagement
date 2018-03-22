/**
 * 
 */
package com.nisum.foodmanagement.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nisum.foodmanagement.model.BookMeetingRoom;
import com.nisum.foodmanagement.model.Location;
import com.nisum.foodmanagement.model.MeetingRoom;
import com.nisum.foodmanagement.repository.BookMeetingRoomRepository;
import com.nisum.foodmanagement.repository.LocationRepository;
import com.nisum.foodmanagement.repository.MeetingRoomRepository;
import com.nisum.foodmanagement.util.DateUtil;

/**
 * @author Raunak
 *
 */
@Service
public class BookingService {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	BookMeetingRoomRepository bookMeetingRoomRepository;
	
	@Autowired
	MeetingRoomRepository meetingRoomRepository;
	
	@Autowired
	LocationRepository locationRepository;
	
	public Map<String, Object> registerLocation(Location location){
		logger.info("In registerLocation()..Service...");
		Location savedLocation = null;
		Map<String,Object> map = new HashMap<>();
		try{
			Location tempLocation = locationRepository.findByLocationId(location.getLocationId());
			if(tempLocation == null){
				savedLocation = locationRepository.save(location);	
				map.put("status", "SUCCESS");
				map.put("userId", savedLocation.getLocationId());
				logger.info("Saved Successfully...");
			}else{
				tempLocation.setName(location.getName());
				tempLocation.setDescription(location.getDescription());
				savedLocation = locationRepository.save(tempLocation);
				map.put("status", "UPDATED");
				map.put("userId", savedLocation.getLocationId());
				logger.info("Updated Successfully...");
			}
		}catch(Exception e){
			e.printStackTrace();
			map.put("status", "FAILED");
		}
		 return map;
	}
	
	public Map<String, List<Location>> getAllLocation(){
		logger.info("In service layer....getAllLocation()....");
		List<Location> LocationList = locationRepository.findAll();
		logger.info("Holiday list..."+LocationList);
		Map<String, List<Location>> map = new HashMap<>();
		map.put("locationList", LocationList);
		return map;
	}
	
	public Map<String, Object> registerMeetingRoom(MeetingRoom meetingRoom){
		logger.info("In registerMeetingRoom()..Service...");
		MeetingRoom savedMeetingRoom = null;
		Map<String,Object> map = new HashMap<>();
		try{
			MeetingRoom tempMeetingRoom = meetingRoomRepository.findByMeetingRoomId(meetingRoom.getMeetingRoomId());
			if(tempMeetingRoom == null){
				savedMeetingRoom = meetingRoomRepository.save(meetingRoom);	
				map.put("status", "SUCCESS");
				map.put("userId", savedMeetingRoom.getMeetingRoomId());
				logger.info("Saved Successfully...");
			}else{
				tempMeetingRoom.setName(meetingRoom.getName());
				tempMeetingRoom.setDescription(meetingRoom.getDescription());
				savedMeetingRoom = meetingRoomRepository.save(tempMeetingRoom);
				map.put("status", "UPDATED");
				map.put("userId", savedMeetingRoom.getLocationId());
				logger.info("Updated Successfully...");
			}
		}catch(Exception e){
			e.printStackTrace();
			map.put("status", "FAILED");
		}
		 return map;
	}
	
	public Map<String, List<MeetingRoom>> getAllMeetingRoom(int locationId){
		logger.info("In service layer....getAllMeetingRoom()....");
		List<MeetingRoom> meetingRoomList = meetingRoomRepository.findAllByLocationId(locationId);
		logger.info("Holiday list..."+meetingRoomList);
		Map<String, List<MeetingRoom>> map = new HashMap<>();
		map.put("meetingRoomList", meetingRoomList);
		return map;
	}
	
	
	
	public Map<String, List<Object[]>> getAvailableMeetingRoom(int locationId, String beginTime, String endTime){
		logger.info("In service layer....getAvailableMeetingRoom()....");
		Map<String, List<Object[]>> map = new HashMap<>();
		try{
			List<Object[]> list = bookMeetingRoomRepository.getAvailableMeetingRoom(locationId, beginTime, endTime);
			System.out.println(list);
			//map.put("status", "SUCCESS");
			map.put("availableMeetingRoom", list);
		}catch(Exception e){
			e.printStackTrace();
			//map.put("status", "FAILED");
		}
		return map;
	}
	
	public Map<String, Object> bookMeetingRoom(BookMeetingRoom bookMeetingRoom){
		logger.info("In bookMeetingRoom()..Service...");
		Map<String,Object> map = new HashMap<>();
		try{
			if(bookMeetingRoom.getBookMeetingRoomId() == 0){
			 	bookMeetingRoom.setBeginTime(DateUtil.getSQLDateAndTime(bookMeetingRoom.getBeginTimeString()));
			 	bookMeetingRoom.setEndTime(DateUtil.getSQLDateAndTime(bookMeetingRoom.getEndTimeString()));
			 	bookMeetingRoom.setCreatedTime(DateUtil.getCurrentDateAndTime());
			 	bookMeetingRoom.setUpdatedTime(bookMeetingRoom.getCreatedTime());
			 	bookMeetingRoom = bookMeetingRoomRepository.save(bookMeetingRoom);	
				//map.put("bookedMeetingRoomId", bookMeetingRoom.getMeetingRoomId());
				logger.info("Saved Successfully...");
			}else{
				System.out.println(bookMeetingRoom.getBookMeetingRoomId());
				BookMeetingRoom tempBookMeetingRoom = bookMeetingRoomRepository.findByBookMeetingRoomId(bookMeetingRoom.getBookMeetingRoomId());
				tempBookMeetingRoom.setBeginTime(DateUtil.getSQLDateAndTime(bookMeetingRoom.getBeginTimeString()));
				tempBookMeetingRoom.setEndTime(DateUtil.getSQLDateAndTime(bookMeetingRoom.getEndTimeString()));
				tempBookMeetingRoom.setUpdatedTime(DateUtil.getCurrentDateAndTime());
				tempBookMeetingRoom.setMeetingRoomId(bookMeetingRoom.getMeetingRoomId());
				tempBookMeetingRoom.setHeadCount(bookMeetingRoom.getHeadCount());
				bookMeetingRoom = bookMeetingRoomRepository.save(tempBookMeetingRoom);
				logger.info("Updated Successfully...");
			}
		}catch(Exception e){
			e.printStackTrace();
			map.put("status", "FAILED");
		}
		map.put("status", "SUCCESS");
		 return map;
	}
	
	public Map<String, List<Object[]>> getUserBooking(int userId){
		logger.info("In service layer....getUserBooking()....");
		Map<String, List<Object[]>> map = new HashMap<>();
		try{
			List<Object[]> list = bookMeetingRoomRepository.getUserBooking(userId);
			for (Object[] object : list) {
				object[3] = object[3].toString();
				object[4] = object[4].toString();
			}
			//map.put("status", "SUCCESS");
			map.put("userBooking", list);
		}catch(Exception e){
			e.printStackTrace();
			//map.put("status", "FAILED");
		}
		return map;
	}

}
