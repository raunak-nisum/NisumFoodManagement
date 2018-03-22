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

import com.nisum.foodmanagement.model.FoodDetails;


/**
 * @author Raunak
 *
 */
@Repository
@Transactional
public interface ReportRepository extends CrudRepository<FoodDetails, Integer>{
	
	@Query("select u.empId, u.name, COUNT(CASE WHEN break_fast='Y' THEN 1 END) AS break_fast_count, COUNT(CASE WHEN lunch='Y' THEN 1 END) AS lunch_count, COUNT(CASE WHEN dinner='Y' THEN 1 END) AS dinner_count from FoodDetails f,  User u where f.month=:month and f.year=:year and u.userId = f.userId group by f.userId")
	public List<Object[]> getMonthlyReport(@Param("month") String month, @Param("year") String year);
	
	@Query(value = "Select temp.emp_id, temp.name, temp.break_fast_count, temp.lunch_count, temp.dinner_count, (temp.break_fast_count*(select break_fast_rate from foodMgmtDB.ratecard)+temp.lunch_count*(select lunch_rate from foodMgmtDB.ratecard)+temp.dinner_count*(select dinner_rate from foodMgmtDB.ratecard)) total from (select food_details.user_id, u.emp_id, u.name, COUNT(CASE WHEN break_fast='Y' THEN 1 END) break_fast_count, COUNT(CASE WHEN lunch='Y' THEN 1 END) lunch_count,COUNT(CASE WHEN dinner='Y' THEN 1 END) dinner_count from foodMgmtDB.food_details,  foodMgmtDB.User u where month=:month and year=:year and u.user_id = food_details.user_id group by user_id) AS temp" , nativeQuery = true)
	public List<Object[]> getMonthlyReportWithTotalAmount(@Param("month") String month, @Param("year") String year);
	
	@Query(value = "Select temp.emp_id, temp.name, temp.break_fast_count, temp.lunch_count, temp.dinner_count, (temp.break_fast_count*(select break_fast_rate from foodMgmtDB.ratecard)+temp.lunch_count*(select lunch_rate from foodMgmtDB.ratecard)+temp.dinner_count*(select dinner_rate from foodMgmtDB.ratecard)) total from (select food_details.user_id, u.emp_id, u.name, COUNT(CASE WHEN break_fast='Y' THEN 1 END) break_fast_count, COUNT(CASE WHEN lunch='Y' THEN 1 END) lunch_count,COUNT(CASE WHEN dinner='Y' THEN 1 END) dinner_count from foodMgmtDB.food_details,  foodMgmtDB.User u where food_details.updated_date between :fromDate and :toDate and u.user_id = food_details.user_id group by user_id) AS temp" , nativeQuery = true)
	public List<Object[]> getMonthlyReportBetweenTwoDate(@Param("fromDate") String fromDate, @Param("toDate") String toDate);

}
