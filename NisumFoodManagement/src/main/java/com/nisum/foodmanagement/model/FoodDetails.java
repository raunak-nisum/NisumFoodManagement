package com.nisum.foodmanagement.model;

import java.sql.Date;

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
@Table(name = "FoodDetails")
public class FoodDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long FoodDetailsId;
	private int userId;
	private String year;
	private String month;
	private String date;
	private String breakFast;
	private String lunch;
	private String dinner;
	private String completeDate;
	private Date createdDate;
	private Date updatedDate;
	public long getFoodDetailsId() {
		return FoodDetailsId;
	}
	public void setFoodDetailsId(long foodDetailsId) {
		FoodDetailsId = foodDetailsId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
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
	public String getBreakFast() {
		return breakFast;
	}
	public void setBreakFast(String breakFast) {
		this.breakFast = breakFast;
	}
	public String getLunch() {
		return lunch;
	}
	public void setLunch(String lunch) {
		this.lunch = lunch;
	}
	public String getDinner() {
		return dinner;
	}
	public void setDinner(String dinner) {
		this.dinner = dinner;
	}
	public String getCompleteDate() {
		return completeDate;
	}
	public void setCompleteDate(String completeDate) {
		this.completeDate = completeDate;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	@Override
	public String toString() {
		return "FoodDetails [FoodDetailsId=" + FoodDetailsId + ", userId=" + userId + ", year=" + year + ", month="
				+ month + ", date=" + date + ", breakFast=" + breakFast + ", lunch=" + lunch + ", dinner=" + dinner
				+ ", completeDate=" + completeDate + "]";
	}

}
