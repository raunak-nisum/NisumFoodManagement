/**
 * 
 */
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
@Table(name = "SUGGESTION")
public class Suggestion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long suggestionId;
	private int userId;
	private String title;
	private String comment;
	private Date createdDate;
	private Date updatedDate;
	public long getSuggestionId() {
		return suggestionId;
	}
	public void setSuggestionId(long suggestionId) {
		this.suggestionId = suggestionId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
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
		return "Suggestion [suggestionId=" + suggestionId + ", userId=" + userId + ", title=" + title + ", comment="
				+ comment + ", createdDate=" + createdDate + ", updatedDate=" + updatedDate + "]";
	}

}
