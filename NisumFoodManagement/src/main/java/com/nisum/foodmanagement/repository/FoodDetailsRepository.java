package com.nisum.foodmanagement.repository;

import java.sql.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nisum.foodmanagement.model.FoodDetails;


/**
 * @author Raunak
 *
 */
@Repository
@Transactional
public interface FoodDetailsRepository extends CrudRepository<FoodDetails, Integer>{
	
	@Query("select f from FoodDetails f, User u where f.userId = u.userId and f.userId=:userId and f.year=:year and f.month=:month")
	public List<FoodDetails> findAllByUserIdAndYearAndMonth(@Param("userId") int userId, @Param("year") String year,
			@Param("month") String month);
	
	public FoodDetails findByUserIdAndYearAndMonthAndDate(int userId, String year, String month, String date);
	
	@Query("select u.empId, u.name from FoodDetails f,  User u where f.updatedDate=:updatedDate and f.breakFast=:breakFast and u.userId = f.userId")
	public List<Object[]> findAllByCreatedDateAndBreakFast(@Param("updatedDate") Date updatedDate, @Param("breakFast") String breakFast);
	
	@Query("select u.empId, u.name from FoodDetails f,  User u where f.updatedDate=:updatedDate and f.lunch=:lunch and u.userId = f.userId")
	public List<Object[]> findAllByCreatedDateAndLunch(@Param("updatedDate") Date updatedDate, @Param("lunch") String lunch);
	
	@Query("select u.empId, u.name from FoodDetails f,  User u where f.updatedDate=:updatedDate and f.dinner=:dinner and u.userId = f.userId")
	public List<Object[]> findAllByCreatedDateAndDinner(@Param("updatedDate") Date updatedDate, @Param("dinner") String dinner);
	
}
