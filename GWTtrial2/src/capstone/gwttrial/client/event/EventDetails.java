package capstone.gwttrial.client.event;

import java.util.ArrayList;
import capstone.gwttrial.client.user.User;
import java.io.Serializable;

public class EventDetails implements Serializable {
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