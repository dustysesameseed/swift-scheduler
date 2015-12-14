package capstone.gwttrial.client.calendar.service;

import java.util.ArrayList;

import capstone.gwttrial.client.calendar.CalendarDetails;
import capstone.gwttrial.client.calendar.EventDetails;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("calendar")
public interface CalendarService extends RemoteService {
	ArrayList<EventDetails> getCalendarEvents(String un)
			throws IllegalArgumentException;

	Integer addCalendarEvent(EventDetails event)
			throws IllegalArgumentException;

	Boolean removeCalendarEvent(Integer eventID)
			throws IllegalArgumentException;
}
