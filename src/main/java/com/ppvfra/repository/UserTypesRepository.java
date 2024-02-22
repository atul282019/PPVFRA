package com.ppvfra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ppvfra.entity.UserTypes;

public interface UserTypesRepository extends JpaRepository<UserTypes, Integer> {
	
	@Query("Select u.id from UserTypes u where type=:type")
	int getIdByType(@Param("type")  String type);
	
}
