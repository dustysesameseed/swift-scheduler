package capstone.gwttrial.client.calendar;

import capstone.gwttrial.client.user.User;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import java.util.Date;
import java.util.List;

public class CalendarView extends Composite implements CalendarViewHandler {
	private Button prof;
	private Button logout;
	private FlexTable contactsTable;
	private final FlexTable contentTable;
	private final DecoratorPanel parentPanel;
	private final String[] dateInfo;
	private final String[] daysOfWeek = { "Sunday", "Monday", "Tuesday",
			"Wednesday", "Thursday", "Friday", "Saturday" };

	public CalendarView() {
		parentPanel = new DecoratorPanel();
		initWidget(parentPanel);
		parentPanel.setWidth(Window.getClientWidth() + "px");
		parentPanel.setHeight(Window.getClientHeight() + "px");

		contentTable = new FlexTable();
		contentTable.setWidth(Window.getClientWidth() - 10 + "px");
		contentTable.setHeight(Window.getClientHeight() - 10 + "px");
		contentTable.getCellFormatter().addStyleName(0, 0,
				"contacts-ListContainer");
		contentTable.getCellFormatter().setWidth(0, 0, "100%");
		contentTable.getFlexCellFormatter().setVerticalAlignment(0, 0,
				DockPanel.ALIGN_TOP);

		dateInfo = parseDate();
		// Create the menu
		setLeft();
		setGrid();

		parentPanel.add(contentTable);
	}

	// TODO: Possibly we want to make a separate Grid widget and refresh it when
	// necessary
	private void setGrid() {
		FlexTable grid = new FlexTable();
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
		grid.setText(2, 0, "8:00 am");
		grid.setText(3, 0, "9:00 am");
		grid.setText(4, 0, "10:00 am");
		grid.setText(5, 0, "11:00 am");
		grid.setText(6, 0, "12:00 pm");
		grid.setText(7, 0, "1:00 pm");
		grid.setText(8, 0, "2:00 pm");
		grid.setText(9, 0, "3:00 pm");
		grid.setText(10, 0, "4:00 pm");
		grid.setText(11, 0, "5:00 pm");

		for (int i = 1; i < 8; i++) {
			grid.setText(1, i, daysOfWeek[i - 1]);
		}

		for (int i = 2; i < 12; i++) {
			for (int j = 1; j < 8; j++) {
				grid.setText(i, j, "I AM AN EVENT");
			}
		}
		contentTable.setWidget(0, 1, grid);
	}

	private void setLeft() {
		VerticalPanel left = new VerticalPanel();
		left.setHeight("100%");
		left.setWidth("100%");
		left.setBorderWidth(20);
		left.setSpacing(10);
		left.setHorizontalAlignment(VerticalPanel.ALIGN_LEFT);

		Label welcome = new Label("Welcome, " + User.getUsername() + "!");
		welcome.setStyleName("welcome");
		Label permissions = new Label("Permissions: " + User.getLevel());
		left.add(welcome);
		left.add(permissions);

		prof = new Button("Profile");
		logout = new Button("Sign Out");
		left.add(prof);
		left.add(logout);
		contentTable.getCellFormatter().addStyleName(0, 0, "calendar-LeftCell");
		contentTable.setWidget(0, 0, left);
	}

	public HasClickHandlers getProfileButton() {
		return prof;
	}

	public HasClickHandlers getLogoutButton() {
		return logout;
	}

	public HasClickHandlers getList() {
		return contactsTable;
	}

	public void setData(List<String> data) {
		contactsTable.removeAllRows();

		for (int i = 0; i < data.size(); ++i) {
			contactsTable.setWidget(i, 0, new CheckBox());
			contactsTable.setText(i, 1, data.get(i));
		}
	}

	public Widget asWidget() {
		return this;
	}

	private String[] parseDate() {
		Date date = new Date();
		String dateString = DateTimeFormat
				.getFormat("EEEE MMM d yyyy HH mm ss").format(date);
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

		int beginWeek = dayOfMonth - numDaysFromSunday;
		int endWeek = dayOfMonth + (6 - numDaysFromSunday);
		str = str.concat(beginWeek + ", " + dateInfo[3] + " - " + dateInfo[1]
				+ " " + endWeek + ", " + dateInfo[3]);
		return str;
	}
}
