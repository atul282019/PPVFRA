package com.ppvfra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ppvfra.entity.Country;

public interface CountryRepository extends JpaRepository<Country, Long> {

	@Query("select s from Country s  order by s.Country")
	List<Country> getConutry();
}
