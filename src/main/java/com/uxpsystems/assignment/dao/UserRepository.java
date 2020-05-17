package com.uxpsystems.assignment.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.uxpsystems.assignment.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>{
	
	Optional<User> findByUsername(String username);

}
