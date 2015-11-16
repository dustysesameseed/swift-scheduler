package capstone.gwttrial.client.calendar;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Calendar Widget contains a Sun-Sat, 8am-4pm calendar widget with a header
 * that displays the current week. Events are populated using the data in the
 * CalendarDetails static class.
 * 
 */

public class CalendarWidget extends VerticalPanel {

	/*
	 * FlexTable that displays the Calendar
	 */
	private FlexTable grid;

	/*
	 * Contains date information (see following list of index date contents):
	 * *****idx 0: Current Day (Str) -- Sunday, Monday, Tuesday, Wednesday...
	 * *****idx 1: Current Month (Str) -- Jan, Feb, Mar, Apr, May, Jun, Jul...
	 * *****idx 2: Current Day (Num) -- 1, 2, 3, 4, 5, 6, 7, 8, 9, 10...
	 * *****idx 3: Current Year (4-digit) -- 2014, 2015, 2016, 2017, 2018...
	 * *****idx 4: Current Year (2-digit) -- 15, 16, 17, 26, 09, 45, 68, 56
	 * *****idx 5: Current Month (Num) -- 1, 2, 3, 4, 5, 6, 7, 8, 9, 10...
	 */
	private static String[] dateInfo;

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
	private static Map<Integer, Integer> rowToHrMap;

	/*
	 * Relates col in table to day of month
	 */
	private static Map<Integer, Integer> colToDayMap;

	/*
	 * Relates event buttons in calendar to their respective events
	 */
	private static Map<Button, EventDetails> eventButtonMap;
	/*
	 * Formatter for grid as a whole plus individual cells
	 */
	private FlexCellFormatter gridFormatter;

	private int cellHeight;
	private int cellWidth;

	/**
	 * Default constructor for new users
	 * 
	 */
	public CalendarWidget() {
		grid = new FlexTable();
		dateInfo = parseDate();
		rowToHrMap = new HashMap<Integer, Integer>();
		colToDayMap = new HashMap<Integer, Integer>();
		eventButtonMap = new HashMap<Button, EventDetails>();
		gridFormatter = grid.getFlexCellFormatter();
		cellHeight = 200;
		cellWidth = 200;
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
		rowToHrMap = new HashMap<Integer, Integer>();
		colToDayMap = new HashMap<Integer, Integer>();
		eventButtonMap = new HashMap<Button, EventDetails>();
		gridFormatter = grid.getFlexCellFormatter();
		cellHeight = 200;
		cellWidth = 200;
	}

	/**
	 * Create the default calendar widget view
	 */
	public void render() {
		grid.insertRow(0);
		grid.insertRow(0);
		grid.insertRow(0);

		grid.setWidth("100%");
		grid.setHeight("100%");
		grid.setCellPadding(5);
		grid.setCellSpacing(5);
		grid.setBorderWidth(2);
		grid.setTitle("Create Event");

		// Set the Days of the Week cells (row 1)
		setHeaderCells();

		// set text column 0
		setHoursTextColumn0();

		// Filler cells
		cellWidth = Window.getClientWidth() / 10;
		cellHeight = Window.getClientHeight() / 25;
		Constants.logger
				.severe("CALENDARWIDGET.JAVA: CELL WIDTH, CELL HEIGHT: "
						+ cellWidth + "," + cellHeight);

		for (int i = 2; i < 12; i++) {
			// grid.getRowFormatter().setStyleName(i, "");
			for (int j = 1; j < 8; j++) {
				grid.getColumnFormatter().setWidth(j, cellWidth + "px");
				grid.setText(i, j, " ");
			}
		}
	}

	/**
	 * Populate the Table Header Cell with the current week and the cells in row
	 * 1 with the Days of the Week
	 */
	private void setHeaderCells() {
		// Populate Table Header Cell with current week
		gridFormatter.setColSpan(0, 0, 8);
		grid.setText(0, 0, getCurrentWeek());
		gridFormatter.setStyleName(0, 0, "currentMonthCell");

		// Populate header row 1 with days of the week
		// Populate column-to-day map also
		String day = dateInfo[0];
		for (int i = 1; i < 8; i++) {
			gridFormatter.setStyleName(1, i, "daysOfTheWeekCells");
			colToDayMap.put(i, beginWeek + i - 1);
		}

		// Add style to highlight the current day of the week
		for (int i = 1; i < 8; i++) {
			grid.setText(1, i, Constants.daysOfWeek[i - 1]);
			if (Constants.daysOfWeek[i - 1].equals(day)) {
				gridFormatter.addStyleName(1, i, "serverResponseLabelError");
				grid.getColumnFormatter().addStyleName(i,
						"serverResponseLabelError");
			}
		}

	}

	/**
	 * Populate Column 0 with text correlating to the hours displayed in the
	 * calendar
	 */
	private void setHoursTextColumn0() {
		// TODO: Fix am/pm
		int rowTemp = 2;
		for (int i = 8; i < 12; i++) {
			grid.setText(rowTemp, 0, i + ":00 am");
			gridFormatter.setStyleName(rowTemp, 0, "daysOfTheWeekCells");
			rowToHrMap.put(rowTemp, i);
			rowTemp++;
		}

		rowTemp = 8;
		grid.setText(7, 0, "12:00 pm");
		gridFormatter.setStyleName(7, 0, "daysOfTheWeekCells");

		for (int i = 1; i < 5; i++) {
			grid.setText(rowTemp, 0, i + ":00 pm");
			gridFormatter.setStyleName(rowTemp, 0, "daysOfTheWeekCells");
			rowToHrMap.put(rowTemp, i);
			rowTemp++;
		}

	}

	/**
	 * Populate the calendar with the list of events from the CalendarDetails
	 * events list
	 * 
	 */
	public void setCalendarContent() {
		int eventCounter = 1;
		if (CalendarDetails.getEventList() == null) {
			Constants.logger
					.severe("CALENDARWIDGET.JAVA: CALENDAR EVENT LIST IS NULL");
		} else
			for (EventDetails event : CalendarDetails.getEventList()) {
				Constants.logger.severe("EVENT: " + eventCounter);
				String eventName = event.getName();
				String date = event.getDate();

				// Get the hour of the event, which corresponds to the row in
				// the table
				String startTime = event.getStartTime();
				int idx = startTime.indexOf(":");
				startTime = startTime.substring(0, idx);

				int eventCol = 0;
				String[] dateArray = event.getDate().split("/");
				Integer dayOfMonth = Integer.parseInt(dateArray[1]);
				Constants.logger
						.severe("CALENDARWIDGET.JAVA: DAY OF EVENT ENTERED WAS: "
								+ dayOfMonth);
				if (dayOfMonth <= endWeek && dayOfMonth >= beginWeek) {
					// Calculate how many days from the beginning of the week,
					// which should correlate to columns
					eventCol = dayOfMonth - beginWeek + 1;

					// Create a new button for the event to be displayed
					Button newEvent = new Button(eventName + "<br/>"
							+ event.getLocation());
					newEvent.setWidth("100%");
					newEvent.setHeight("100%");
					eventButtonMap.put(newEvent, event);
					grid.setWidget(getRowFromHour(startTime), eventCol,
							newEvent);
					eventCounter++;
				} else {
					Constants.logger
							.severe("CALENDARWIDGET.JAVA: DAY OF EVENT IS NOT DISPLAYED WITHIN "
									+ beginWeek + " - " + endWeek);
				}
			}
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

		// Find the number of days from the start of the week (Sunday) to the
		// current date
		int numDaysFromSunday = 0;
		for (int i = 0; i < 7; i++) {
			if (dayOfWeek.equals(Constants.daysOfWeek[i])) {
				numDaysFromSunday = i;
				break;
			}
		}

		// Construct the week string for the header cell
		beginWeek = dayOfMonth - numDaysFromSunday;
		endWeek = dayOfMonth + (6 - numDaysFromSunday);
		str = str.concat(beginWeek + ", " + dateInfo[3] + " - " + dateInfo[1]
				+ " " + endWeek + ", " + dateInfo[3]);
		return str;
	}

	/**
	 * Get the current date and store it into a String array
	 * 
	 * @return String[] of current date information in "EEEE MMM d yyyy yy MM"
	 *         format
	 */
	private String[] parseDate() {
		Date date = new Date();
		String dateString = DateTimeFormat.getFormat("EEEE MMM d yyyy yy MM")
				.format(date);
		return dateString.split(" ");
	}

	public static Integer getRowFromHour(String hr) {
		Integer row = -1;
		for (Integer rowFromMap : rowToHrMap.keySet()) {
			if (rowToHrMap.get(rowFromMap).equals(Integer.valueOf(hr))) {
				row = rowFromMap;
			}
		}
		return row;
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
			rowToHrMap.put(counter, flag);
			counter++;
		}
		if (flag == 12 && endTime != 12) {
			for (flag = 1; flag <= endTime && counter < 10; flag++) {
				rowToHrMap.put(counter, flag);
			}
		}
	}

	public FlexTable getCalendar() {
		return grid;
	}

	public static String getAmPm() {
		return "pm";
	}

	public static Integer getHourFromRow(int row) {
		return rowToHrMap.get(row);
	}

	public static Integer getDayFromCol(int srcCol) {
		return colToDayMap.get(srcCol);
	}

	public static Object getCurrentMonthNum() {
		return dateInfo[5];
	}

	public static Object getCurrentYear2() {
		return dateInfo[4];
	}

	public static Map<Button, EventDetails> getEventButtonMap() {
		return eventButtonMap;
	}
}