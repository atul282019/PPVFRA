package com.ppvfra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ppvfra.entity.States;

public interface StateRepository extends JpaRepository<States, Long>
{
	@Query("select s from States s  order by s.statename_inenglish")
	List<States> getstates();
}