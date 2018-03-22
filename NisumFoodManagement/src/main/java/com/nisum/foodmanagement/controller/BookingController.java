/**
 * 
 */
package com.nisum.foodmanagement.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nisum.foodmanagement.model.BookMeetingRoom;
import com.nisum.foodmanagement.model.Location;
import com.nisum.foodmanagement.model.MeetingRoom;
import com.nisum.foodmanagement.service.BookingService;

/**
 * @author Raunak
 *
 */
@RestController
public class BookingController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	BookingService bookingService;
	
	
	@RequestMapping(value = "/registerLocation", method = RequestMethod.POST)
	public Map<String, Object> registerLocation(@RequestBody Location location){
		logger.info(".....In registerLocation() controller...");
		return bookingService.registerLocation(location);
	}
	
	@RequestMapping(value = "/getAllLocation", method = RequestMethod.GET)
	public Map<String, List<Location>> getAllLocation(){
		logger.info(".....In getAllLocation() controller...");
		return bookingService.getAllLocation();
	}
	
	@RequestMapping(value = "/registerMeetingRoom", method = RequestMethod.POST)
	public Map<String, Object> registerMeetingRoom(@RequestBody MeetingRoom meetingRoom){
		logger.info(".....In registerMeetingRoom() controller...");
		return bookingService.registerMeetingRoom(meetingRoom);
	}
	
	@RequestMapping(value = "/getAllMeetingRoom", method = RequestMethod.GET)
	public Map<String, List<MeetingRoom>> getAllMeetingRoom(@RequestParam int locationId){
		logger.info(".....In getAllMeetingRoom() controller...");
		return bookingService.getAllMeetingRoom(locationId);
	}
	
	
	@RequestMapping(value = "/getAvailableMeetingRoom", method = RequestMethod.GET)
	public Map<String, List<Object[]>> getAvailableMeetingRoom(@RequestParam int locationId, @RequestParam String beginTime, @RequestParam String endTime){
		logger.info("In getAvailableMeetingRoom() controller....");
		return bookingService.getAvailableMeetingRoom(locationId, beginTime, endTime);
	}
	
	@RequestMapping(value = "/bookMeetingRoom", method = RequestMethod.POST)
	public Map<String, Object> bookMeetingRoom(@RequestBody BookMeetingRoom bookMeetingRoom){
		logger.info(".....In bookMeetingRoom() controller...");
		return bookingService.bookMeetingRoom(bookMeetingRoom);
	}
	
	@RequestMapping(value = "/getUserBooking", method = RequestMethod.GET)
	public Map<String, List<Object[]>> getUserBooking(@RequestParam int userId){
		logger.info("In getUserBooking() controller....");
		return bookingService.getUserBooking(userId);
	}

}
