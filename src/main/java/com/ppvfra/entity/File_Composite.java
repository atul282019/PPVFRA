package com.ppvfra.entity;

import java.io.Serializable;

public class File_Composite implements Serializable 
{
	private String botanical_initial;
	private String variety_type;
	private Integer f_year;
	 
	
	 
	 
	public String getBotanical_initial() {
		return botanical_initial;
	}
	public void setBotanical_initial(String botanical_initial) {
		this.botanical_initial = botanical_initial;
	}
	public String getVariety_type() {
		return variety_type;
	}
	public void setVariety_type(String variety_type) {
		this.variety_type = variety_type;
	}
	public Integer getF_year() {
		return f_year;
	}
	public void setF_year(Integer f_year) {
		this.f_year = f_year;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((botanical_initial == null) ? 0 : botanical_initial.hashCode());
		result = prime * result + ((f_year == null) ? 0 : f_year.hashCode());
		result = prime * result + ((variety_type == null) ? 0 : variety_type.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		File_Composite other = (File_Composite) obj;
		if (botanical_initial == null) {
			if (other.botanical_initial != null)
				return false;
		} else if (!botanical_initial.equals(other.botanical_initial))
			return false;
		if (f_year == null) {
			if (other.f_year != null)
				return false;
		} else if (!f_year.equals(other.f_year))
			return false;
		if (variety_type == null) {
			if (other.variety_type != null)
				return false;
		} else if (!variety_type.equals(other.variety_type))
			return false;
		return true;
	}
	 
	 
	 
	
	  	
}