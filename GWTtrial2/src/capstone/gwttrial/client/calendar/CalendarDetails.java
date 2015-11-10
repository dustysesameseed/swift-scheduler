package capstone.gwttrial.client.calendar;

import java.io.Serializable;
import java.util.ArrayList;

import capstone.gwttrial.client.user.User;

public class CalendarDetails implements Serializable {
	public static ArrayList<EventDetails> events;
	public String username;
	public User user;

	public CalendarDetails() {
		events = new ArrayList<EventDetails>();
	}

	public CalendarDetails(String username) {
		this.username = username;
		events = new ArrayList<EventDetails>();
	}

	public static void addEvent(EventDetails newEvent) {
		if (events == null) {
			Constants.logger
					.severe("CALENDARDETAILS.JAVA: EVENTS LIST IS NULL");
		} else {
			events.add(newEvent);
			Constants.logger
					.severe("CALENDARDETAILS.JAVA: EVENT HAS BEEN ADDED");
		}
	}

	public static void deleteEvent(String eventName) {
		events.remove(getEventIdx(eventName));
	}

	public static ArrayList<EventDetails> getEventList() {
		return events;
	}

	public static int getEventIdx(String eventName) {
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