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

/**
 * @author Raunak
 *
 */

@Entity
@Table(name = "MEETING_ROOM")
public class MeetingRoom {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int meetingRoomId;
	private int locationId;
	private String name;
	private String description;
	public int getMeetingRoomId() {
		return meetingRoomId;
	}
	public void setMeetingRoomId(int meetingRoomId) {
		this.meetingRoomId = meetingRoomId;
	}
	public int getLocationId() {
		return locationId;
	}
	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "MeetingRoom [meetingRoomId=" + meetingRoomId + ", LocationId=" + locationId + ", name=" + name
				+ ", description=" + description + "]";
	}
	
}
