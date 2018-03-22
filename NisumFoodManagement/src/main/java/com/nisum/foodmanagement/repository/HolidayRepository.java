/**
 * 
 */
package com.nisum.foodmanagement.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nisum.foodmanagement.model.Holiday;


/**
 * @author Raunak
 *
 */
@Repository
@Transactional
public interface HolidayRepository extends CrudRepository<Holiday, Integer>{
	
	public List<Holiday> findByYear(String year);

}
