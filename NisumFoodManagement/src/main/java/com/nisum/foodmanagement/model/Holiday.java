/**
 * 
 */
package com.nisum.foodmanagement.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Raunak
 *
 */

@Entity
@Table(name = "HOLIDAY")
public class Holiday {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int holidayId;
	private String year;
	private String month;
	private String date;
	private String holidayName;
	public int getHolidayId() {
		return holidayId;
	}
	public void setHolidayId(int holidayId) {
		this.holidayId = holidayId;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getHolidayName() {
		return holidayName;
	}
	public void setHolidayName(String holidayName) {
		this.holidayName = holidayName;
	}
	@Override
	public String toString() {
		return "Holiday [holidayId=" + holidayId + ", year=" + year + ", month=" + month + ", date=" + date
				+ ", holidayName=" + holidayName + "]";
	}

}
