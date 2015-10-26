package capstone.gwttrial.client.calendar.service;

import capstone.gwttrial.client.calendar.CalendarDetails;
import capstone.gwttrial.client.event.EventDetails;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("getcalendar")
public interface CalendarService extends RemoteService {
	CalendarDetails getCalendarEvents(String un) throws IllegalArgumentException;
	Boolean addCalendarEvent(EventDetails event) throws IllegalArgumentException;
	Boolean removeCalendarEvent(EventDetails event) throws IllegalArgumentException;
}
