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

import com.nisum.foodmanagement.model.Suggestion;


/**
 * @author Raunak
 *
 */
@Repository
@Transactional
public interface SuggestionRepository extends CrudRepository<Suggestion, Integer>{

	@Query(value = "select u.emp_id, u.name, s.title, s.comment from Suggestion s, User u where s.updated_date between :fromDate and :toDate and u.user_id = s.user_id" , nativeQuery = true)
	public List<Object[]> findAllSuggestionBetweenTwoDate(@Param("fromDate") String fromDate, @Param("toDate") String toDate);
	
}
