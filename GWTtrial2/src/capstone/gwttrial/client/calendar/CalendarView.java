package capstone.gwttrial.client.calendar;

<<<<<<< HEAD
import capstone.gwttrial.client.user.User;

import com.google.gwt.event.dom.client.HasClickHandlers;
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

import java.util.List;

public class CalendarView extends Composite implements CalendarViewHandler {
	private Button prof;
	private Button logout;
	private FlexTable contactsTable;
	private final FlexTable contentTable;
	private final DecoratorPanel parentPanel;

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

		// Create the menu
		setLeft();
		setGrid();

		parentPanel.add(contentTable);
	}

	private void setGrid() {
		Label temp = new Label("THIS IS WHERE AN EVENT SHOULD GO");
		FlexTable grid = new FlexTable();
		grid.insertRow(0);
		grid.insertRow(0);
		grid.insertRow(0);
		int leftPos = contentTable.getAbsoluteLeft();
		FlexCellFormatter gridFormatter = grid.getFlexCellFormatter();

		// Set the header cells
		gridFormatter.setColSpan(0, 0, 7);
		grid.setText(0, 0, "CURRENT MONTH");
		gridFormatter.setStyleName(0, 0, "currentMonthCell");

		for (int i = 0; i < 7; i++) {
			gridFormatter.setStyleName(1, i, "daysOfTheWeekCells");
			// gridFormatter.setHeight(0, 0, "10%");
			// gridFormatter.setAlignment(0, 0, DockPanel.ALIGN_CENTER,
			// DockPanel.ALIGN_MIDDLE);
		}

		grid.setWidth("100%");
		grid.setHeight("100%");
		grid.setCellPadding(10);
		grid.setCellSpacing(10);
		grid.setBorderWidth(5);
		grid.setTitle("Add Event");

		grid.setText(1, 0, "Sunday");
		grid.setText(1, 1, "Monday");
		grid.setText(1, 2, "Tuesday");
		grid.setText(1, 3, "Wednesday");
		grid.setText(1, 4, "Thursday");
		grid.setText(1, 5, "Friday");
		grid.setText(1, 6, "Saturday");
		grid.setText(2, 0, "THIS IS WHERE AN EVENT SHOULD GO");
		grid.setText(2, 1, "THIS IS WHERE AN EVENT SHOULD GO");
		grid.setText(2, 2, "THIS IS WHERE AN EVENT SHOULD GO");
		grid.setText(2, 3, "THIS IS WHERE AN EVENT SHOULD GO");
		grid.setText(2, 4, "THIS IS WHERE AN EVENT SHOULD GO");
		grid.setText(2, 5, "THIS IS WHERE AN EVENT SHOULD GO");
		grid.setText(2, 6, "THIS IS WHERE AN EVENT SHOULD GO");
		grid.setText(3, 0, "THIS IS WHERE AN EVENT SHOULD GO");
		grid.setText(3, 1, "THIS IS WHERE AN EVENT SHOULD GO");
		grid.setText(3, 2, "THIS IS WHERE AN EVENT SHOULD GO");
		grid.setText(3, 3, "THIS IS WHERE AN EVENT SHOULD GO");
		grid.setText(3, 4, "THIS IS WHERE AN EVENT SHOULD GO");
		grid.setText(3, 5, "THIS IS WHERE AN EVENT SHOULD GO");
		grid.setText(3, 6, "THIS IS WHERE AN EVENT SHOULD GO");
		contentTable.setWidget(0, 1, grid);
	}

	private void setLeft() {
		VerticalPanel left = new VerticalPanel();
		left.setHeight("100%");
		left.setWidth(parentPanel.getOffsetWidth() / 4 + "px");
		left.setBorderWidth(50);
		left.setSpacing(20);
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
		contentTable.getCellFormatter().addStyleName(0, 0, "contacts-ListMenu");
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
=======
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.HTMLTable;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.CalendarModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CalendarView extends Composite {
	private final Button addButton;
	private final Button deleteButton;
	private final FlexTable contentTable;

	public CalendarView() {
		DecoratorPanel contentTableDecorator = new DecoratorPanel();
		initWidget(contentTableDecorator);
		contentTableDecorator.setWidth("100%");
		contentTableDecorator.setWidth("18em");

		contentTable = new FlexTable();
		contentTable.setWidth("100%");
		contentTable.getCellFormatter().addStyleName(0, 0,
				"contacts-ListContainer");
		contentTable.getCellFormatter().setWidth(0, 0, "100%");
		contentTable.getFlexCellFormatter().setVerticalAlignment(0, 0,
				DockPanel.ALIGN_TOP);

		// Create the menu
		//
		HorizontalPanel hPanel = new HorizontalPanel();
		hPanel.setBorderWidth(0);
		hPanel.setSpacing(0);
		hPanel.setHorizontalAlignment(HorizontalPanel.ALIGN_LEFT);
		addButton = new Button("Add");
		hPanel.add(addButton);
		deleteButton = new Button("Delete");
		hPanel.add(deleteButton);
		contentTable.getCellFormatter().addStyleName(0, 0, "contacts-ListMenu");
		contentTable.setWidget(0, 0, hPanel);
		
		contentTableDecorator.add(contentTable);
	}

	public HasClickHandlers getAddButton() {
		return addButton;
	}

	public HasClickHandlers getDeleteButton() {
		return deleteButton;
>>>>>>> refs/remotes/origin/master
	}

	public Widget asWidget() {
		return this;
	}
}
