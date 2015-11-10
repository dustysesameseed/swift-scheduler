package capstone.gwttrial.client.calendar;

import capstone.gwttrial.client.user.User;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DatePicker;
import com.google.gwt.user.client.ui.Anchor;
import java.util.List;

public class CalendarView extends Composite implements CalendarViewHandler {
	private CalendarWidget userCal;
	private Button prof;
	private Button logout;
	private DatePicker da;
	private Button contact;
	private Anchor anchor1;
	private FlexTable contactsTable;
	private FlexTable contentTable;
	private final DecoratorPanel parentPanel;

	public CalendarView() {
		parentPanel = new DecoratorPanel();
		initWidget(parentPanel);

		userCal = new CalendarWidget();
		contentTable = new FlexTable();
		da = new DatePicker();

		prof = new Button("Profile");
		logout = new Button("Sign Out");
		contact = new Button("Contact Admin");
		anchor1 = new Anchor("Emergency Message", "www.osu.edu");

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
		left.add(prof);
		left.add(da);
		left.add(anchor1);
		left.add(contact);
		left.add(logout);
		contentTable.getCellFormatter().addStyleName(0, 0, "calendar-LeftCell");
		contentTable.setWidget(0, 0, left);
	}

	private void setCalendar() {
		userCal.render();
		userCal.setCalendarContent();
		contentTable.setWidget(0, 1, userCal.getCalendar());
	}

	public HasClickHandlers getProfileButton() {
		return prof;
	}

	public HasClickHandlers getLogoutButton() {
		return logout;
	}

	public FlexTable getCalendar() {
		return userCal.getCalendar();
	}

	public void setData(List<String> data) {
		contactsTable.removeAllRows();

		for (int i = 0; i < data.size(); ++i) {
			contactsTable.setWidget(i, 0, new CheckBox());
			contactsTable.setText(i, 1, data.get(i));
		}
	}

	public void refresh() {

	}

	public Widget asWidget() {
		return this;
	}
}
