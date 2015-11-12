package capstone.gwttrial.client.calendar;

import java.util.ArrayList;

import capstone.gwttrial.client.user.User;

import java.io.Serializable;

import com.google.gwt.user.client.ui.TextBox;

public class EventDetails implements Serializable {
	private ArrayList<User> users;
	private int eventID;
	private String name;
	private String location;
	private String startTime;
	private String endTime;
	private String date;
	private String creator;
	private String description;

	public EventDetails() {
		new EventDetails("", "", "", "", "", "");
	}

	public EventDetails(String name, String location, String date,
			String startTime, String endTime, String description) {
		this.name = name;
		this.location = location;
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
		this.description = description;
	}
	
	public EventDetails(int eventID, String name, String location, String start,
			String end, String creator, String description) {
		// These are the variables stored in the database for an event
		this.eventID = eventID;
		this.name = name;
		this.location = location;
		this.startTime = start;
		this.endTime = end;
		this.creator = creator;
		this.description = description;
	}

	public void parseDetails(ArrayList<TextBox> details) {
		this.name = details.get(0).getText();
		this.location = details.get(1).getText();
		this.date = details.get(2).getText();
		this.startTime = details.get(3).getText();
		this.description = details.get(4).getText();
	}

	public String getName() {
		return name;
	}

	public String getLocation() {
		return location;
	}

	public String getDate() {
		return date;
	}

	public String getStartTime() {
		return startTime;
	}

	public String getDescription() {
		return description;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getEventID() {
		return eventID;
	}
}