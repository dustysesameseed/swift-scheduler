package capstone.gwttrial.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import capstone.gwttrial.client.calendar.CalendarDetails;
import capstone.gwttrial.client.calendar.Constants;
import capstone.gwttrial.client.calendar.EventDetails;
import capstone.gwttrial.client.calendar.service.CalendarService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class CalendarServiceImpl extends RemoteServiceServlet implements
		CalendarService {

	@Override
	public ArrayList<EventDetails> getCalendarEvents(String un)
			throws IllegalArgumentException {

		// Pull events from database, serialize and send to user
		// Dates are returned in 'yyyy-mm-dd hh:mm:ss' form
		Connection connect = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<EventDetails> events = new ArrayList<EventDetails>();
		Boolean noDatabaseMode = true;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://localhost/swiftdb?" + "user=root&password=pass");
		
			
			preparedStatement = connect.prepareStatement("SELECT * FROM Events");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int eventID = resultSet.getInt(1);
				String name = resultSet.getString(2);
				String location = resultSet.getString(3);
				String start = resultSet.getTimestamp(4).toString();
				String end = resultSet.getTimestamp(5).toString();
				String creator = resultSet.getString(6);
				String description = resultSet.getString(7);
				EventDetails event = new EventDetails(eventID,name,location,start,end,creator,description);
				events.add(event);
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		if (noDatabaseMode){
			EventDetails event1 = new EventDetails(1,"Event1","Place1,"
					,"2015-11-21 12:00:00.0", "2015-11-21 13:00:00.0",un,"First event");
			EventDetails event2 = new EventDetails(2,"Event2","Place2,"
					,"2015-11-22 14:00:00.0", "2015-11-22 15:00:00.0",un,"Second event");
			events.add(event1); events.add(event2);
		}
		return events;
	}

	@Override
	public Integer addCalendarEvent(EventDetails event)
			throws IllegalArgumentException {

		// Put user event in database, return eventID
		// Dates are to be entered in 'yyyy-mm-dd hh:mm:ss' form
		Connection connect = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int eventID = -1;
		Boolean noDatabaseMode = true;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://localhost/swiftdb?" + "user=root&password=pass");
		
			
			preparedStatement = connect.prepareStatement("INSERT INTO Events VALUES (default"
		 		+ ",'"+event.getName()
		 		+ "','"+event.getLocation()
		 		+ "','"+event.getStartDatetime()
		 		+ "','"+event.getEndDatetime()
		 		+ "','username"
		 		+ "','"+event.getDescription()+"');");
			preparedStatement.executeUpdate();
		 
			preparedStatement = connect.prepareStatement("SELECT MAX(EVENTID) FROM EVENTS;");
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			eventID = resultSet.getInt(1);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		if (noDatabaseMode) eventID = event.getEventID();
		return eventID;
	}

	@Override
	public Boolean removeCalendarEvent(Integer eventID)
			throws IllegalArgumentException {

		// Remove event from database, return success
		Connection connect = null;
		PreparedStatement preparedStatement = null;
		Boolean removed = false;
		Boolean noDatabaseMode = true;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://localhost/swiftdb?" + "user=root&password=pass");
		
			
			preparedStatement = connect.prepareStatement("DELETE FROM Events WHERE eventID='"+eventID.intValue()+"';");
			preparedStatement.executeUpdate();
			removed = true;
				 
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		if (noDatabaseMode) removed = true;
		return removed;
	}

}
