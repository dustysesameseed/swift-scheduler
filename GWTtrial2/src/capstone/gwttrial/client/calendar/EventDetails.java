package capstone.gwttrial.client.calendar;

import java.util.ArrayList;

import capstone.gwttrial.client.user.User;

public class EventDetails {
	public ArrayList<User> users;
	public String name;
	public int startTime;
	public int endTime;

	public EventDetails() {
		new EventDetails("", 0, 0);
	}

	public EventDetails(String name, int startTime, int endTime) {
		this.name = name;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}