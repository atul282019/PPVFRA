package com.ppvfra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ppvfra.entity.Districts;
import com.ppvfra.entity.DusCharacteristicsStates;

public interface DusCharacteristicsStatesRepository extends  JpaRepository<DusCharacteristicsStates,Integer> {
	
	@Query("select d.id,ds.duscharacteristicid,ds.states,ds.note,"
			+ "ds.example_variety,ds.documentpath,ds.documentname,ds.id "
			+ "from DusCharacteristics d join DusCharacteristicsStates ds on d.id=ds.duscharacteristicid and ds.duscharacteristicid=:duscharacteristicid")
	List<Object[]> findAllEntries(@Param("duscharacteristicid") Integer duscharacteristicid);
	
	@Query("select m.id,m.states from DusCharacteristicsStates m where duscharacteristicid=:characteristicsid")
	public List<DusCharacteristicsStates> getAllStatesByDusCharacteristicId(@Param("characteristicsid") Integer characteristicsid);

	@Query(value="select m.id,m.states,a.applicationid "
	+ "from application_dusfeatures a left join "
	+ "m_duscharacteristics_states m on "
	+ "a.duscharacteristicsstatesid = m.id where "
	+ "a.applicationid=:appid and "
	+ "a.dustvarietyid =:dusvarid",nativeQuery=true)
	public List<Object[]> getremarks_saved(@Param("appid") int appid,@Param("dusvarid") int dusvarid);
	
}
