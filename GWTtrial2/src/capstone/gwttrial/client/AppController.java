package capstone.gwttrial.client;

import capstone.gwttrial.client.calendar.CalendarPresenter;
import capstone.gwttrial.client.calendar.CalendarView;
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

import com.google.gwt.user.client.ui.HTMLTable.Cell;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;

public class AppController implements Presenter, ValueChangeHandler<String> {
	private final EventBus eventBus;
	private HasWidgets container;
	private String username;
	private Cell addEventSrc;

	public AppController(EventBus eventBus) {
		this.eventBus = eventBus;
		this.addEventSrc = null;
		bind();
	}

	private void bind() {
		History.addValueChangeHandler(this);

		eventBus.addHandler(LoginEvent.TYPE, new LoginEventHandler() {
			public void onLogin(LoginEvent event) {
				createToken("home");
			}
		});

		eventBus.addHandler(LogoutEvent.TYPE, new LogoutEventHandler() {
			public void onLogout(LogoutEvent event) {
				createToken("logout");
			}
		});

		eventBus.addHandler(CreateEvent.TYPE, new CreateEventHandler() {
			public void onCreateEvent(CreateEvent event) {
				createToken("addEvent");
				addEventSrc = event.getEventCell();
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

	// Create tokens for history and page loads
	private void createToken(String token) {
		History.newItem(token);
	}

	// Check token and create appropriate presenters
	public void onValueChange(ValueChangeEvent<String> event) {
		String token = event.getValue();

		if (token != null) {
			Presenter presenter = null;

			if (token.equals("login") || token.equals("logout")) {
				presenter = new LoginPresenter(eventBus, new LoginView(token));
			}

			else if (token.equals("home")) {
				username = User.getUsername();
				presenter = new CalendarPresenter(eventBus, new CalendarView(),
						username);
			}

			else if (token.equals("addEvent")) {
				presenter = new CreateEventPresenter(eventBus,
						new CreateEventView(addEventSrc));
			}

			if (presenter != null) {
				presenter.go(container);
			}
		}
	}
}
