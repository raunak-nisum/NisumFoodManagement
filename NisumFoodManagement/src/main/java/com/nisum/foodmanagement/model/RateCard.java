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
@Table(name = "RATECARD")
public class RateCard {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int rateCardId;
	private int breakFastRate;
	private int lunchRate;
	private int dinnerRate;
	public int getRateCardId() {
		return rateCardId;
	}
	public void setRateCardId(int rateCardId) {
		this.rateCardId = rateCardId;
	}
	public int getBreakFastRate() {
		return breakFastRate;
	}
	public void setBreakFastRate(int breakFastRate) {
		this.breakFastRate = breakFastRate;
	}
	public int getLunchRate() {
		return lunchRate;
	}
	public void setLunchRate(int lunchRate) {
		this.lunchRate = lunchRate;
	}
	public int getDinnerRate() {
		return dinnerRate;
	}
	public void setDinnerRate(int dinnerRate) {
		this.dinnerRate = dinnerRate;
	}
	@Override
	public String toString() {
		return "RateCard [rateCardId=" + rateCardId + ", breakFastRate=" + breakFastRate + ", lunchRate=" + lunchRate
				+ ", dinnerRate=" + dinnerRate + "]";
	}
	
	

}
