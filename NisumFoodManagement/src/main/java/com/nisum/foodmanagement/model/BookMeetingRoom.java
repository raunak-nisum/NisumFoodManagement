/**
 * 
 */
package com.nisum.foodmanagement.model;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author Raunak
 *
 */

@Entity
@Table(name = "BOOK_MEETING_ROOM")
public class BookMeetingRoom {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int bookMeetingRoomId;
	private String meetingTitle;
	private int userId;
	private int meetingRoomId;
	private Timestamp beginTime;
	private Timestamp endTime;
	@Transient
	private String beginTimeString;
	@Transient
	private String endTimeString;
	private int headCount;
	private Timestamp createdTime;
	private Timestamp updatedTime;
	public int getBookMeetingRoomId() {
		return bookMeetingRoomId;
	}
	public void setBookMeetingRoomId(int bookMeetingRoomId) {
		this.bookMeetingRoomId = bookMeetingRoomId;
	}
	public String getMeetingTitle() {
		return meetingTitle;
	}
	public void setMeetingTitle(String meetingTitle) {
		this.meetingTitle = meetingTitle;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getMeetingRoomId() {
		return meetingRoomId;
	}
	public void setMeetingRoomId(int meetingRoomId) {
		this.meetingRoomId = meetingRoomId;
	}
	public Timestamp getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Timestamp beginTime) {
		this.beginTime = beginTime;
	}
	public Timestamp getEndTime() {
		return endTime;
	}
	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}
	
	public String getBeginTimeString() {
		return beginTimeString;
	}
	public void setBeginTimeString(String beginTimeString) {
		this.beginTimeString = beginTimeString;
	}
	public String getEndTimeString() {
		return endTimeString;
	}
	public void setEndTimeString(String endTimeString) {
		this.endTimeString = endTimeString;
	}
	public int getHeadCount() {
		return headCount;
	}
	public void setHeadCount(int headCount) {
		this.headCount = headCount;
	}
	public Timestamp getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}
	public Timestamp getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Timestamp updatedTime) {
		this.updatedTime = updatedTime;
	}
	@Override
	public String toString() {
		return "BookMeetingRoom [bookMeetingRoomId=" + bookMeetingRoomId + ", userId=" + userId + ", meetingRoomId="
				+ meetingRoomId + ", beginTime=" + beginTime + ", endTime=" + endTime + ", headCount=" + headCount
				+ ", createdTime=" + createdTime + ", updatedTime=" + updatedTime + "]";
	}
	
}
