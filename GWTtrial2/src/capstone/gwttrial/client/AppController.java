package capstone.gwttrial.client;

import capstone.gwttrial.client.calendar.CalendarPresenter;
import capstone.gwttrial.client.calendar.CalendarView;
import capstone.gwttrial.client.calendar.Presenter;
import capstone.gwttrial.client.login.LoginEvent;
import capstone.gwttrial.client.login.LoginEventHandler;
import capstone.gwttrial.client.login.LoginPresenter;
import capstone.gwttrial.client.login.LoginView;
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

	public AppController(EventBus eventBus) {
		this.eventBus = eventBus;
		bind();
	}

	private void bind() {
		History.addValueChangeHandler(this);

		eventBus.addHandler(LoginEvent.TYPE, new LoginEventHandler() {
			public void onLogin(LoginEvent event) {
				createToken("home");
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

			if (token.equals("login")) {
				presenter = new LoginPresenter(eventBus, new LoginView());
			}

			else if (token.equals("home")) {
				username = User.getUsername();
				presenter = new CalendarPresenter(eventBus, new CalendarView(),
						username);
			}

			if (presenter != null) {
				presenter.go(container);
			}
		}
	}
}
