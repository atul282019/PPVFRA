package com.ppvfra.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.ppvfra.entity.Nationality;

public interface NationalityRepository extends JpaRepository<Nationality, Integer> {
	

}
