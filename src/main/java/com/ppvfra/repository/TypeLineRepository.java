package com.ppvfra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ppvfra.entity.TypeLine;

public interface TypeLineRepository extends JpaRepository<TypeLine, Integer> {
	
	@Query(value="select m.id,m.code from m_TypeLine m where m.code not in ('Candidate')",nativeQuery=true)
	List<Object[]> gettypeline_data();
	
	@Query(value="select m.id,m.code from m_TypeLine m where m.code in ('Candidate')",nativeQuery=true)
	List<Object[]> gettypeline_datasecond();
	
	@Query("select m.id,m.code from TypeLine m where m.code not in ('Candidate')")
	List<Object[]> gettypeline_data_class();
	
	@Query("select m.id,m.code from TypeLine m where m.code in ('Candidate')")
	List<Object[]> gettypeline_datasecond_class();
	
	@Query("select a from TypeLine a order by a.code")
	List<TypeLine> getTypleLineOrderByCode();
}
