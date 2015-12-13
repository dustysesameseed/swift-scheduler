package capstone.gwttrial.client.calendar;

import java.util.Date;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.StackPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DatePicker;

import capstone.gwttrial.client.user.User;

public class CalendarView extends Composite implements CalendarViewHandler {
	private CalendarWidget userCal;
	private Button prof;
	private Button logout;
	private DatePicker da;
	private Anchor sendEmergencyMsg;
	private Anchor contactAdmin;
	private FlexTable contactsTable;
	private FlexTable contentTable;
	private final DecoratorPanel parentPanel;

	// private final leftParentPanel

	public CalendarView() {
		parentPanel = new DecoratorPanel();
		initWidget(parentPanel);

		userCal = new CalendarWidget();
		contentTable = new FlexTable();
		da = new DatePicker();

		prof = new Button("Profile");
		logout = new Button("Sign Out");
		sendEmergencyMsg = new Anchor("Send Important Message",
				"emergency.html");
		contactAdmin = new Anchor("Contact Admin", "contact.html");

		setLayout();

		// Funtionality of Datepicker Click to Calendar Highlight
		// Highlight specific day in Calendarview while click the corresponding
		// one in Datepicker
		da.addValueChangeHandler(new ValueChangeHandler<Date>() {
			@Override
			public void onValueChange(ValueChangeEvent<Date> event) {
				Date date = da.getHighlightedDate();
				int dayOfWeek = date.getDay(); // Stilling working although
												// deprecated
				// Window.alert(Integer.toString(dayOfWeek));
				for (int i = 1; i < 8; i++) {
					userCal.getCalendar().getColumnFormatter()
							.setStyleName(i, "");
				}
				userCal.getCalendar().getColumnFormatter()
						.setStyleName(dayOfWeek + 1, "selectedColumn");
			}
		});
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
				"calendar-ListContainer");
		contentTable.getCellFormatter().setWidth(0, 0, "100%");
		contentTable.getFlexCellFormatter().setVerticalAlignment(0, 0,
				DockPanel.ALIGN_TOP);
	}

	private void setLeft() {
		VerticalPanel leftParentPanel = new VerticalPanel();
		leftParentPanel.setHeight("100%");
		leftParentPanel.setWidth("100%");
		leftParentPanel.setBorderWidth(20);
		leftParentPanel.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);

		// **************** TOP LEFT PANEL **********************
		// Create top left panel -- contains welcome, permissions label
		VerticalPanel topLeftPanel = new VerticalPanel();
		topLeftPanel.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);
		topLeftPanel.setHeight("20%");
		topLeftPanel.setWidth("100%");

		// Add welcome label
		Label welcome = new Label("Welcome, " + User.getUsername() + "!");
		welcome.setStyleName("welcome");
		topLeftPanel.add(welcome);

		// **************** MIDDLE LEFT PANEL **********************
		// Create middle left panel
		VerticalPanel middleLeftPanel = new VerticalPanel();
		middleLeftPanel.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);
		middleLeftPanel.setHeight("35%");
		middleLeftPanel.setWidth("100%");

		// Add navigation label
		Label navigate = new Label("Navigate to a date:");
		navigate.setStyleName("subHeaderText");
		middleLeftPanel.add(navigate);

		// Add datepicker
		middleLeftPanel.add(da);

		// ****************BOTTOM LEFT PANEL **********************
		// Create bottom left stack panel
		StackPanel bottomLeftPanel = new StackPanel();
		bottomLeftPanel.setHeight("100%");
		bottomLeftPanel.setWidth("100%");

		// Create the My Account stack
		VerticalPanel myAccount = new VerticalPanel();

		// Add permissions label
		Label permissions = new Label("Permissions: " + User.getLevel());
		Anchor profile = new Anchor("My Profile");
		Anchor signOut = new Anchor("Sign Out");
		permissions.setStyleName("subHeaderText");
		profile.setStyleName("subHeaderText");
		signOut.setStyleName("subHeaderText");
		myAccount.add(permissions);
		myAccount.add(profile);
		myAccount.add(signOut);

		bottomLeftPanel.add(myAccount, "My Account");

		// Create the Action Center stack
		VerticalPanel actionCenter = new VerticalPanel();
		sendEmergencyMsg.setStyleName("subHeaderText");
		contactAdmin.setStyleName("subHeaderText");
		actionCenter.add(sendEmergencyMsg);
		actionCenter.add(contactAdmin);

		bottomLeftPanel.add(actionCenter, "Action Center");

		// Create the Team Members stack
		VerticalPanel teamMembers = new VerticalPanel();
		teamMembers.add(new Label("Tom Weston"));
		teamMembers.add(new Label("Katherine Glen"));
		teamMembers.add(new Label("Harry Miller"));
		teamMembers.add(new Label("Clara Evans"));
		teamMembers.add(new Label("John Moray"));
		teamMembers.add(new Label("Denise Lovett"));

		bottomLeftPanel.add(teamMembers, "Team Members");

		// Add all 3 sections to the left parent panel
		leftParentPanel.add(topLeftPanel);
		leftParentPanel.add(middleLeftPanel);
		leftParentPanel.add(bottomLeftPanel);

		contentTable.getCellFormatter().addStyleName(0, 0, "calendar-LeftCell");
		contentTable.setWidget(0, 0, leftParentPanel);
	}

	public FlexTable getCalendar() {
		return userCal.getCalendar();
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

	public Widget asWidget() {
		return this;
	}
}
