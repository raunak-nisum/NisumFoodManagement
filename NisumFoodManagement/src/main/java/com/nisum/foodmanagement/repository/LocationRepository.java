/**
 * 
 */
package com.nisum.foodmanagement.repository;

import java.util.List;

import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.nisum.foodmanagement.model.Location;

/**
 * @author Raunak
 *
 */
@Repository
@Transactional
public interface LocationRepository extends CrudRepository<Location, Integer>{

	public Location findByLocationId(int locationId);
	
	public List<Location> findAll();
}
