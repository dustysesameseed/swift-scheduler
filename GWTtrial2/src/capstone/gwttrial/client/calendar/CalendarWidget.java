package capstone.gwttrial.client.calendar;

import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;
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
	 * FlexTable that composes the Calendar
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
	 * day of the month that current week begins on
	 */
	private int beginWeek;

	/*
	 * day of the month that current week ends on
	 */
	private int endWeek;

	/*
	 * List of days of the week String constants
	 */
	private final String[] daysOfWeek = { "Sunday", "Monday", "Tuesday",
			"Wednesday", "Thursday", "Friday", "Saturday" };

	public CalendarWidget() {
		grid = new FlexTable();
		dateInfo = parseDate();
	}

	public CalendarWidget(String username) {
		grid = new FlexTable();
		dateInfo = parseDate();
	}

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
			rowTemp++;
		}

		rowTemp = 8;
		grid.setText(7, 0, "12:00 pm");
		gridFormatter.setStyleName(7, 0, "daysOfTheWeekCells");

		for (int i = 1; i < 5; i++) {
			grid.setText(rowTemp, 0, i + ":00 pm");
			gridFormatter.setStyleName(rowTemp, 0, "daysOfTheWeekCells");
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

	private String[] parseDate() {
		Date date = new Date();
		String dateString = DateTimeFormat.getFormat("EEEE MMM d yyyy").format(
				date);
		return dateString.split(" ");
	}

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
			event.getDate();
		}
	}

	public FlexTable getCalendar() {
		return grid;
	}
}
