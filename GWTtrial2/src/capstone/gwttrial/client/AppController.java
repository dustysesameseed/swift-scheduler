package capstone.gwttrial.client;

import capstone.gwttrial.client.calendar.CalendarDetails;
import capstone.gwttrial.client.calendar.CalendarPresenter;
import capstone.gwttrial.client.calendar.CalendarView;
import capstone.gwttrial.client.calendar.Constants;
import capstone.gwttrial.client.doevent.CreateEvent;
import capstone.gwttrial.client.doevent.CreateEventHandler;
import capstone.gwttrial.client.doevent.CreateEventPresenter;
import capstone.gwttrial.client.doevent.CreateEventView;
import capstone.gwttrial.client.login.LoginEvent;
import capstone.gwttrial.client.login.LoginEventHandler;
import capstone.gwttrial.client.login.LoginPresenter;
import capstone.gwttrial.client.login.LoginView;
import capstone.gwttrial.client.login.LogoutEvent;
import capstone.gwttrial.client.login.LogoutEventHandler;
import capstone.gwttrial.client.user.User;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;

public class AppController implements Presenter, ValueChangeHandler<String> {
	private final EventBus eventBus;
	private HasWidgets container;
	private String username;
	private int createEventSrcRow;
	private int createEventSrcCol;
	private CalendarDetails calDetails;

	public AppController(EventBus eventBus) {
		this.eventBus = eventBus;
		this.createEventSrcRow = -1;
		this.createEventSrcCol = -1;

		calDetails = new CalendarDetails(username);
		Constants.logger
				.severe("APPCONTROLLER.JAVA: INSTANTIATING CALENDARDETAILS");
		bind();
	}

	private void bind() {
		History.addValueChangeHandler(this);

		// "Sign In" button eventBus handler -- token "login"
		eventBus.addHandler(LoginEvent.TYPE, new LoginEventHandler() {
			public void onLogin(LoginEvent event) {
				Constants.logger
						.severe("APPCONTROLLER.JAVA: SIGN IN EVENT DETECTED");
				createToken(event.getId());
			}
		});

		// "Sign Out" button eventBus handler -- token "home"
		eventBus.addHandler(LogoutEvent.TYPE, new LogoutEventHandler() {
			public void onLogout(LogoutEvent event) {
				Constants.logger
						.severe("APPCONTROLLER.JAVA: SIGN OUT EVENT DETECTED");
				createToken(event.getId());
			}
		});

		// "Create" button eventBus handler (for the CreateEvent view and
		// CalendarView) -- token "createEvent" or "home"
		eventBus.addHandler(CreateEvent.TYPE, new CreateEventHandler() {
			public void onCreateEvent(CreateEvent event) {
				Constants.logger
						.severe("APPCONTROLLER.JAVA: CREATE EVENT DETECTED");
				createEventSrcRow = event.getCellSrcRow();
				createEventSrcCol = event.getCellSrcCol();
				Constants.logger.severe("APPCONTROLLER.JAVA: ROW, COL: "
						+ createEventSrcRow + "," + createEventSrcCol);
				createToken(event.getId());
			}
		});
	}

	public void go(final HasWidgets container) {
		this.container = container;

		if ("".equals(History.getToken())) {
			createToken("login");
		} else {
			History.fireCurrentHistoryState();
		}
	}

	/**
	 * Create tokens for history and page loads
	 * 
	 * @param token
	 */
	private void createToken(String token) {
		History.newItem(token);
	}

	/**
	 * Check token and create appropriate presenters
	 * 
	 * @param ValueChangeEvent
	 *            <String> event
	 */
	public void onValueChange(ValueChangeEvent<String> event) {
		String token = event.getValue();

		if (token != null) {
			Presenter presenter = null;

			if (token.equals("login") || token.equals("logout")) {
				presenter = new LoginPresenter(eventBus, new LoginView(token));
			} else if (token.equals("home")) {
				username = User.getUsername();
				presenter = new CalendarPresenter(eventBus, new CalendarView(),
						username);
			} else if (token.equals("createEvent")) {
				presenter = new CreateEventPresenter(eventBus,
						new CreateEventView(createEventSrcRow,
								createEventSrcCol));
			}

			if (presenter != null) {
				presenter.go(container);
			}
		}
	}

}
