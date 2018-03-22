package com.nisum.foodmanagement.controller;

import java.util.HashMap;
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

import com.nisum.foodmanagement.model.FoodDetails;
import com.nisum.foodmanagement.model.Suggestion;
import com.nisum.foodmanagement.model.User;
import com.nisum.foodmanagement.service.UserService;

/**
 * @author Raunak
 *
 */
@RestController
public class UserController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public Map<String, Object> register(@RequestBody User user){
		logger.info(".....In register() controller...");
		Map<String,Object> map = new HashMap<>();
		return userService.register(user);
	}
	
	@RequestMapping(value = "/showUser", method = RequestMethod.GET)
	public Map<String, Object> showUser(@RequestParam String emailId){
		logger.info("In showUser() controller....with emp id..."+emailId);
		return userService.showUser(emailId);
	}
	
	@RequestMapping(value = "/saveFoodDetails", method = RequestMethod.POST)
	public Map<String, Object> saveFoodDetails(@RequestBody Map<String, List<FoodDetails>>  foodDetailsMap){
		logger.info("In saveFoodDetails() controller..."+foodDetailsMap);
		List<FoodDetails> foodDetails = foodDetailsMap.get("monthlyList");
		/*for (FoodDetails foodDetails2 : foodDetails) {
			System.out.println(foodDetails2);
		}*/
		return userService.saveFoodDetails(foodDetails);
	}
	
	@RequestMapping(value = "/getMonthDetails", method = RequestMethod.GET)
	public Map<String, List<FoodDetails>> getMonthDetails(@RequestParam String month, @RequestParam String year, @RequestParam int userId){
		logger.info("in getMonthDetails Controller......"+userId+"----"+month+"----"+year);
		return userService.getMonthDetails(userId, month, year);
	}
	
	@RequestMapping(value = "/getHolidayList", method = RequestMethod.GET)
	public List<String> getHolidayList(){
		logger.info("In getHolidayList() controller....");
		return userService.getHolidayList();
	}
	
	@RequestMapping(value = "/saveSuggestion", method = RequestMethod.POST)
	public Map<String, String> saveSuggestion(@RequestBody Suggestion  suggestion){
		logger.info("In saveSuggestion() controller....");
		return userService.saveSuggestion(suggestion);
	}

}
