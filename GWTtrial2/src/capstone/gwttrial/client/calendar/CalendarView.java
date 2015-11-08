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
import com.google.gwt.user.datepicker.client.DatePicker;
import com.google.gwt.user.client.ui.Anchor;
import java.util.Date;
import java.util.List;

public class CalendarView extends Composite implements CalendarViewHandler {
	private Button prof;
	private Button logout;
	private DatePicker da;
	private Button contact;
	private Anchor anchor1;
	private FlexTable contactsTable;
	private FlexTable contentTable;
	private final DecoratorPanel parentPanel;
	private int beginWeek;
	private int endWeek;
	private String[] dateInfo;
	private FlexTable grid;
	private final String[] daysOfWeek = { "Sunday", "Monday", "Tuesday",
			"Wednesday", "Thursday", "Friday", "Saturday" };

	public CalendarView() {
		parentPanel = new DecoratorPanel();
		initWidget(parentPanel);

		dateInfo = parseDate();
		contentTable = new FlexTable();
		grid = new FlexTable();

		setLayout();
	}

	private void setLayout() {
		parentPanel.setWidth(Window.getClientWidth() + "px");
		parentPanel.setHeight(Window.getClientHeight() + "px");

		setContentTable();
		setLeft();
		setCalendar();

		parentPanel.add(contentTable);
	}

	private void setContentTable() {
		contentTable.setWidth(Window.getClientWidth() - 10 + "px");
		contentTable.setHeight(Window.getClientHeight() - 10 + "px");
		contentTable.getCellFormatter().addStyleName(0, 0,
				"contacts-ListContainer");
		contentTable.getCellFormatter().setWidth(0, 0, "100%");
		contentTable.getFlexCellFormatter().setVerticalAlignment(0, 0,
				DockPanel.ALIGN_TOP);
	}

	// TODO: Possibly we want to make a separate Grid widget and refresh it when
	// necessary
	private void setCalendar() {
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

		for (int i = 1; i < 8; i++) {
			grid.setText(1, i, daysOfWeek[i - 1]);
		}

		for (int i = 2; i < 12; i++) {
			for (int j = 1; j < 8; j++) {
				grid.setText(i, j, " ");
			}
		}

		contentTable.setWidget(0, 1, grid);
	}

	private void setLeft() {
		VerticalPanel left = new VerticalPanel();
		left.setHeight("100%");
		left.setWidth("100%");
		left.setBorderWidth(20);
		left.setHorizontalAlignment(VerticalPanel.ALIGN_LEFT);

		Label welcome = new Label("Welcome, " + User.getUsername() + "!");
		welcome.setStyleName("welcome");
		Label permissions = new Label("Permissions: " + User.getLevel());
		left.add(welcome);
		left.add(permissions);

		prof = new Button("Profile");
		logout = new Button("Sign Out");
		da = new DatePicker();
		contact = new Button("Contact Admin");
		anchor1 = new Anchor("Emergency Message", "www.osu.edu");
		left.add(prof);
		left.add(da);
		left.add(anchor1);
		left.add(contact);
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

	public FlexTable getCalendar() {
		return grid;
	}

	public void setData(List<String> data) {
		contactsTable.removeAllRows();

		for (int i = 0; i < data.size(); ++i) {
			contactsTable.setWidget(i, 0, new CheckBox());
			contactsTable.setText(i, 1, data.get(i));
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

	public void refresh() {

	}

	public Widget asWidget() {
		return this;
	}
}
