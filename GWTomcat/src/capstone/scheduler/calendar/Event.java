package capstone.scheduler.calendar;

import java.util.ArrayList;
import capstone.scheduler.user.User;

public class Event {
	public ArrayList<User> users;
	public String name;
	public int startTime;
	public int endTime;
	
	public Event (String name, int startTime, int endTime){
		this.name = name;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
}