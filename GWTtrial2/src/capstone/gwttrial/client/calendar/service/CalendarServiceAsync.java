package capstone.gwttrial.client.calendar.service;

import java.util.ArrayList;

import capstone.gwttrial.client.calendar.CalendarDetails;
import capstone.gwttrial.client.calendar.EventDetails;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>CalendarService</code>.
 */
public interface CalendarServiceAsync {
	void getCalendarEvents(String un, AsyncCallback<ArrayList<EventDetails>> callback)
			throws IllegalArgumentException;

	void addCalendarEvent(EventDetails event, AsyncCallback<Integer> callback)
			throws IllegalArgumentException;

	void removeCalendarEvent(Integer eventID, AsyncCallback<Boolean> callback)
			throws IllegalArgumentException;
}
