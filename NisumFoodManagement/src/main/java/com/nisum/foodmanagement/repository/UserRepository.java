package com.nisum.foodmanagement.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nisum.foodmanagement.model.User;


@Repository
@Transactional
public interface UserRepository extends CrudRepository<User, Integer> {
	
	public List<User> findAll();
	
	public User findByName(String name);
	public User findByEmailId(String emailId);
}
