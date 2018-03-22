package com.nisum.foodmanagement.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nisum.foodmanagement.model.FoodDetails;
import com.nisum.foodmanagement.model.Holiday;
import com.nisum.foodmanagement.model.Suggestion;
import com.nisum.foodmanagement.model.User;
import com.nisum.foodmanagement.repository.FoodDetailsRepository;
import com.nisum.foodmanagement.repository.HolidayRepository;
import com.nisum.foodmanagement.repository.SuggestionRepository;
import com.nisum.foodmanagement.repository.UserRepository;
import com.nisum.foodmanagement.util.DateUtil;


/**
 * @author Raunak
 *
 */
@Service
public class UserService {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	FoodDetailsRepository foodDetailsRepository;
	
	@Autowired
	HolidayRepository holidayRepository;
	
	@Autowired
	SuggestionRepository suggestionRepository;
	
	
	public Map<String, Object> register(User user){
		logger.info("In register()..Service...");
		User u = null;
		Map<String,Object> map = new HashMap<>();
		try{
			User tempUser = userRepository.findByEmailId(user.getEmailId());
			if(tempUser == null){
				u = userRepository.save(user);	
				map.put("status", "SUCCESS");
				map.put("userId", u.getUserId());
				logger.info("Saved Successfully...");
			}else{
				tempUser.setName(user.getName());
				tempUser.setMobileNo(user.getMobileNo());
				tempUser.setWorkingPlace(user.getWorkingPlace());
				u = userRepository.save(tempUser);
				map.put("status", "UPDATED");
				map.put("userId", u.getUserId());
				logger.info("Updated Successfully...");
			}
		  
		 //System.out.println("In service layer..."+u);
		}catch(Exception e){
			e.printStackTrace();
			map.put("status", "FAILED");
		}
		 return map;
	}
	
	public Map<String, Object> showUser(String emailId){
		logger.info("In showUser()..Service...");
		Map<String,Object> map = new HashMap<>();
		User user = null;
		try{
			 user = userRepository.findByEmailId(emailId);
			System.out.println("user details....."+user);
			if(user == null)
				map.put("status", "NOTEXISTED");
			else{
				map.put("status", "EXISTED");
				map.put("userDetails", user);
			}
		}catch(Exception e){
			e.printStackTrace();
			map.put("status", "FAILED");
		}
		return map;
	}
	
	public Map<String, Object> saveFoodDetails(List<FoodDetails> foodDetailsList){
		logger.info("In saveFoodDetails()..Service...");
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			for (FoodDetails foodDetails : foodDetailsList) {
				FoodDetails tempData = foodDetailsRepository.findByUserIdAndYearAndMonthAndDate(foodDetails.getUserId(), foodDetails.getYear(), foodDetails.getMonth(), foodDetails.getDate());
				System.out.println("temp data...."+tempData);
				FoodDetails fd = null;
				Date date = DateUtil.getSQLDate(foodDetails.getCompleteDate());
				if(tempData == null){
					foodDetails.setCreatedDate(date);
					 foodDetails.setUpdatedDate(date);
					 fd = foodDetailsRepository.save(foodDetails);
					 logger.info("Saved Successfully...");
				}else{
					tempData.setBreakFast(foodDetails.getBreakFast());
					tempData.setLunch(foodDetails.getLunch());
					tempData.setDinner(foodDetails.getDinner());
					tempData.setUpdatedDate(date);
					fd = foodDetailsRepository.save(tempData);
					logger.info("Updated Successfully...");
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			map.put("status", "FAILED");
			return map;
		}
		map.put("status", "SUCCESS");
		return map;
	}
	
	public Map<String, List<FoodDetails>> getMonthDetails(int userId, String month, String year){
		logger.info("In getMonthDetails()..Service...");
		Map<String, List<FoodDetails>> map = new HashMap<>();
		List<FoodDetails> list = foodDetailsRepository.findAllByUserIdAndYearAndMonth(userId, year, month);
		//logger.info("Monthly data..."+list);
		map.put("monthlyList", list);
		return map;
	}
	
	public List<String> getHolidayList(){
		logger.info("In service layer....getMonthlyReport()....");
		Calendar now = Calendar.getInstance();
		String year = String.valueOf(now.get(Calendar.YEAR));
		logger.info("Current year...."+year);
		List<Holiday> list = holidayRepository.findByYear(year);
		logger.info("Holiday list..."+list);
		
		List<String> result = new ArrayList<>();
		for (Holiday holiday : list) {
			String temp = holiday.getYear()+"-"+holiday.getMonth()+"-"+holiday.getDate();
			result.add(temp);
		}
		
		return result;
	}
	
	public Map<String, String> saveSuggestion(Suggestion suggestion){
		logger.info("In service layer....saveSuggestion()....");
		Map<String, String> map = new HashMap<String, String>();
		try{
				Suggestion tempData = suggestionRepository.save(suggestion);
		}catch(Exception e){
			e.printStackTrace();
			map.put("status", "FAILED");
			return map;
		}
		map.put("status", "SUCCESS");
		System.out.println("Saved Successfully...");
		return map;
	}

}
