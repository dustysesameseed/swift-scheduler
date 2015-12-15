package capstone.gwttrial.client.calendar;

import java.util.Date;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
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
	private DatePicker da;
	private Anchor requestEvents;
	private Anchor showApprovedEvents;
	private Anchor approveEvents;
	private Anchor profile;
	private Anchor logout;
	private Anchor schedulingTools;
	private FlexTable contentTable;
	private final DecoratorPanel parentPanel;

	public CalendarView() {
		parentPanel = new DecoratorPanel();
		initWidget(parentPanel);

		userCal = new CalendarWidget();
		contentTable = new FlexTable();
		da = new DatePicker();

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
				"calendar-ListContainer");
		contentTable.getCellFormatter().setWidth(0, 0, "100%");
		contentTable.getFlexCellFormatter().setVerticalAlignment(0, 0,
				DockPanel.ALIGN_TOP);
	}

	private void setLeft() {
		VerticalPanel leftParentPanel = new VerticalPanel();
		int height = (Window.getClientHeight() - 48) / 2;
		leftParentPanel.setHeight(height + "px");
		leftParentPanel.setWidth("100%");
		leftParentPanel.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);

		// **************** TOP LEFT PANEL **********************
		// Create top left panel -- contains welcome, permissions label
		VerticalPanel topLeftPanel = configureTopLeft();

		// **************** MIDDLE LEFT PANEL **********************
		// Create middle left panel
		VerticalPanel middleLeftPanel = configureMiddleLeft();

		// **************** BOTTOM LEFT PANEL **********************
		// Create bottom left stack panel
		StackPanel bottomLeftPanel = configureBottomLeft();

		// Combine top and middle sections to the left parent panel
		leftParentPanel.add(topLeftPanel);
		leftParentPanel.add(middleLeftPanel);

		// Add both left parent panel and bottom panel to the super parent
		VerticalPanel leftSuperParent = new VerticalPanel();
		leftSuperParent.setHeight("100%");
		leftSuperParent.setWidth("100%");
		leftSuperParent.setBorderWidth(15);
		leftSuperParent.add(leftParentPanel);
		leftSuperParent.add(bottomLeftPanel);

		bottomLeftPanel.setHeight(height + "px");
		bottomLeftPanel.setWidth("100%");

		contentTable.getCellFormatter().addStyleName(0, 0, "calendar-LeftCell");
		contentTable.setWidget(0, 0, leftSuperParent);
	}

	private VerticalPanel configureTopLeft() {
		VerticalPanel topLeftPanel = new VerticalPanel();
		topLeftPanel.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);
		topLeftPanel.setHeight("100%");
		topLeftPanel.setWidth("100%");

		// Add welcome label
		Label welcome = new Label("Welcome, " + User.getUsername() + "!");
		welcome.setStyleName("welcome");
		topLeftPanel.add(welcome);

		return topLeftPanel;
	}

	private VerticalPanel configureMiddleLeft() {
		VerticalPanel middleLeftPanel = new VerticalPanel();
		middleLeftPanel.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);
		middleLeftPanel.setHeight("100%");
		middleLeftPanel.setWidth("100%");

		// Add navigation label
		Label navigate = new Label("Navigate to a date:");
		navigate.setStyleName("subHeaderText");
		middleLeftPanel.add(navigate);

		// Add datepicker
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
		middleLeftPanel.add(da);

		return middleLeftPanel;
	}

	/**
	 * Set up the bottom stack panel of the left sidebar
	 * 
	 * @return StackPanel
	 */
	private StackPanel configureBottomLeft() {
		StackPanel bottomLeftPanel = new StackPanel();
		// -------------------------------------------
		// Create the My Account stack
		VerticalPanel myAccount = new VerticalPanel();

		// Add permissions label
		Label permissions = new Label("Permissions: " + User.getLevel());
		Label admin = new Label("Administrator: Peter Dudley"); // TODO: make
																// this
																// User.getAdmin();
		Label myLinks = new Label("My Links:");
		profile = new Anchor("My Profile");
		schedulingTools = new Anchor("My Scheduling Tools");
		logout = new Anchor("Sign Out");

		permissions.setStyleName("subHeaderText");
		admin.setStyleName("subHeaderText");
		myLinks.setStyleName("subHeaderText");
		schedulingTools.setStyleName("subHeaderText");
		profile.setStyleName("subHeaderText");
		logout.setStyleName("subHeaderText");

		myAccount.add(admin);
		myAccount.add(permissions);
		myAccount.add(myLinks);
		myAccount.add(schedulingTools);
		myAccount.add(profile);
		myAccount.add(logout);
		bottomLeftPanel.add(myAccount, "My Account");

		// -------------------------------------------
		// Create the Action Center stack
		VerticalPanel actionCenter = new VerticalPanel();

		// Configure approval for events panel
		Label eventActions = new Label("Event Actions:");
		eventActions.setStyleName("subHeaderText");
		actionCenter.add(eventActions);

		// Configure according to User level
		if (User.getLevel().equalsIgnoreCase("Team Member")) {
			requestEvents = new Anchor("Submit An Event For Approval");
			requestEvents.setStyleName("subHeaderText");
			actionCenter.add(requestEvents);

		} else {
			approveEvents = new Anchor("Approve Suggested Events");
			approveEvents.setStyleName("subHeaderText");
			actionCenter.add(approveEvents);
		}

		showApprovedEvents = new Anchor("See Approved Events");
		showApprovedEvents.setStyleName("subHeaderText");
		actionCenter.add(showApprovedEvents);

		// Configure messaging center section labels and anchors
		Label messagingSectHeader = new Label("Messaging:");
		Anchor sendEmergencyMsg = new Anchor("Send Important Message",
				"emergency.html");
		Anchor contactAdmin = new Anchor("Contact Administrator",
				"contact.html");

		messagingSectHeader.setStyleName("subHeaderText");
		sendEmergencyMsg.setStyleName("subHeaderText");
		contactAdmin.setStyleName("subHeaderText");

		actionCenter.add(messagingSectHeader);
		actionCenter.add(sendEmergencyMsg);
		if (User.getLevel().equalsIgnoreCase("Team Member")) {
			actionCenter.add(contactAdmin);
		}

		bottomLeftPanel.add(actionCenter, "Action Center");

		// -------------------------------------------
		// Create the Team Members stack
		VerticalPanel teamMembers = new VerticalPanel();
		teamMembers.add(new Label("Tom Weston"));
		teamMembers.add(new Label("Katherine Glen"));
		teamMembers.add(new Label("Harry Miller"));
		teamMembers.add(new Label("Clara Evans"));
		teamMembers.add(new Label("John Moray"));
		teamMembers.add(new Label("Denise Lovett"));
		bottomLeftPanel.add(teamMembers, "Team Members");

		return bottomLeftPanel;
	}

	public FlexTable getCalendar() {
		return userCal.getCalendar();
	}

	private void setCalendar() {
		userCal.render();
		userCal.setCalendarContent();
		contentTable.setWidget(0, 1, userCal.getCalendar());
	}

	public HasClickHandlers getProfileLink() {
		return profile;
	}

	public HasClickHandlers getLogoutLink() {
		return logout;
	}

	public Widget asWidget() {
		return this;
	}

	@Override
	public Anchor getReqEventsLink() {
		return requestEvents;
	}

	@Override
	public Anchor getShowApprovedEventsLink() {
		return showApprovedEvents;
	}

	@Override
	public Anchor getApproveEventsLink() {
		return approveEvents;
	}
}
