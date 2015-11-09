package capstone.gwttrial.client.calendar;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Calendar Widget contains a Sun-Sat, 8am-4pm calendar widget with a header
 * that displays the current week. Events are populated using the data in the
 * CalendarDetails static class.
 * 
 * @author Sharon
 * 
 */

public class CalendarWidget extends VerticalPanel {

	/*
	 * FlexTable that displays the Calendar
	 */
	private FlexTable grid;

	/*
	 * Contains date information (see following list of index date contents):
	 * *****idx 0: Current Day of Week -- Sunday, Monday, Tuesday, Wednesday...
	 * *****idx 1: Current Month -- Jan, Feb, Mar, Apr, May, Jun, Jul...
	 * *****idx 2: Current Day of the Month -- 1, 2, 3, 4, 5, 6, 7, 8, 9, 10...
	 * *****idx 3: Current Year -- 2014, 2015, 2016...
	 */
	private String[] dateInfo;

	/*
	 * Day of the month that current week begins on
	 */
	private int beginWeek;

	/*
	 * Day of the month that current week ends on
	 */
	private int endWeek;

	/*
	 * Relates row in table to hours
	 */
	private static Map<Integer, Integer> rowHourKey;

	/*
	 * List of days of the week String constants
	 */
	private final String[] daysOfWeek = { "Sunday", "Monday", "Tuesday",
			"Wednesday", "Thursday", "Friday", "Saturday" };

	/**
	 * Default constructor for new users
	 * 
	 */
	public CalendarWidget() {
		grid = new FlexTable();
		dateInfo = parseDate();
		rowHourKey = new HashMap<Integer, Integer>();
	}

	/**
	 * Constructor that is personalized by user
	 * 
	 * @param String
	 *            username
	 */
	public CalendarWidget(String username) {
		grid = new FlexTable();
		dateInfo = parseDate();
		rowHourKey = new HashMap<Integer, Integer>();
	}

	/**
	 * Create the default calendar widget view
	 */
	public void render() {
		grid.insertRow(0);
		grid.insertRow(0);
		grid.insertRow(0);
		FlexCellFormatter gridFormatter = grid.getFlexCellFormatter();

		// Set the header cells
		gridFormatter.setColSpan(0, 0, 8);
		grid.setText(0, 0, getCurrentWeek());
		gridFormatter.setStyleName(0, 0, "currentMonthCell");

		for (int i = 0; i < 8; i++) {
			gridFormatter.setStyleName(1, i, "daysOfTheWeekCells");
		}

		grid.setWidth("100%");
		grid.setHeight("100%");
		grid.setCellPadding(10);
		grid.setCellSpacing(10);
		grid.setBorderWidth(5);
		grid.setTitle("Add Event");

		// set text column 0
		int rowTemp = 2;
		for (int i = 8; i < 12; i++) {
			grid.setText(rowTemp, 0, i + ":00 am");
			gridFormatter.setStyleName(rowTemp, 0, "daysOfTheWeekCells");
			rowHourKey.put(rowTemp, i);
			rowTemp++;
		}

		rowTemp = 8;
		grid.setText(7, 0, "12:00 pm");
		gridFormatter.setStyleName(7, 0, "daysOfTheWeekCells");

		for (int i = 1; i < 5; i++) {
			grid.setText(rowTemp, 0, i + ":00 pm");
			gridFormatter.setStyleName(rowTemp, 0, "daysOfTheWeekCells");
			rowHourKey.put(rowTemp, i);
			rowTemp++;
		}

		// Filler cells
		for (int i = 1; i < 8; i++) {
			grid.setText(1, i, daysOfWeek[i - 1]);
		}

		for (int i = 2; i < 12; i++) {
			for (int j = 1; j < 8; j++) {
				grid.setText(i, j, " ");
			}
		}
	}

	/**
	 * Populates the map that relates row numbers in the calendar to the hour
	 * displayed in each row's column 0
	 * 
	 * @param startTime
	 * @param endTime
	 */
	private void populateRowHrMap(int startTime, int endTime) {
		int flag; // flag if i = 12
		int counter = 2; // keeps track of rows
		for (flag = startTime; flag <= 12 && counter < 10; flag++) {
			rowHourKey.put(counter, flag);
			counter++;
		}
		if (flag == 12 && endTime != 12) {
			for (flag = 1; flag <= endTime && counter < 10; flag++) {
				rowHourKey.put(counter, flag);
			}
		}
	}

	/**
	 * Get the current date and store it into a String array
	 * 
	 * @return String[] of current date information in "EEEE MMM d yyyy" format
	 */
	private String[] parseDate() {
		Date date = new Date();
		String dateString = DateTimeFormat.getFormat("EEEE MMM d yyyy").format(
				date);
		return dateString.split(" ");
	}

	/**
	 * Get the current week to be displayed in the header cell
	 * 
	 * @return String current week in format "Nov 6, 2015 - Nov 13, 2015"
	 */
	private String getCurrentWeek() {
		String dayOfWeek = dateInfo[0]; // "Monday", "Wednesday"...etc.
		int dayOfMonth = Integer.parseInt(dateInfo[2]); // 0-31
		String str = dateInfo[1] + " "; // "Nov", "Dec"...etc.

		int numDaysFromSunday = 0;
		for (int i = 0; i < 7; i++) {
			if (dayOfWeek.equals(daysOfWeek[i])) {
				numDaysFromSunday = i;
				break;
			}
		}

		beginWeek = dayOfMonth - numDaysFromSunday;
		endWeek = dayOfMonth + (6 - numDaysFromSunday);
		str = str.concat(beginWeek + ", " + dateInfo[3] + " - " + dateInfo[1]
				+ " " + endWeek + ", " + dateInfo[3]);
		return str;
	}

	public void setCalendarContent() {
		for (EventDetails event : CalendarDetails.getEventList()) {
			String eventName = event.getName();
			String date = event.getDate();
			Button newEvent = new Button("eventName");
			grid.setWidget(3, 3, newEvent);
		}
	}

	public FlexTable getCalendar() {
		return grid;
	}

	public static Integer getHourFromRow(int row) {
		return rowHourKey.get(row);
	}

	public static String getAmPm() {
		return "pm";
	}
}
