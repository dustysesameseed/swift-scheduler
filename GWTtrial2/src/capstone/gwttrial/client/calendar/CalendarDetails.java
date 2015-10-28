package capstone.gwttrial.client.calendar;

import java.io.Serializable;
import java.util.ArrayList;
import capstone.gwttrial.client.user.User;

public class CalendarDetails implements Serializable {
	public ArrayList<EventDetails> events;
	public String username;
	public User user;

	public CalendarDetails() {
		events = new ArrayList<EventDetails>();
	}

	public CalendarDetails(String username) {
		this.username = username;
		events = new ArrayList<EventDetails>();
	}

	public void addEvent(EventDetails newEvent) {
		events.add(newEvent);
	}

	public void deleteEvent(String eventName) {
		events.remove(getEventIdx(eventName));
	}

	public int getEventIdx(String eventName) {
		int index = 0;
		boolean found = false;

		for (EventDetails e : events) {
			if (e.getName().equals(eventName)) {
				found = true;
				break;
			}
			index++;
		}

		if (!found) {
			System.out.println("Throw getEventIdx() error");
		}

		return index;
	}
}