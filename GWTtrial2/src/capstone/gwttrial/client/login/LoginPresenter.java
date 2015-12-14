package capstone.gwttrial.client.login;

import java.util.ArrayList;

import capstone.gwttrial.client.Presenter;
import capstone.gwttrial.client.calendar.CalendarDetails;
import capstone.gwttrial.client.calendar.Constants;
import capstone.gwttrial.client.calendar.EventDetails;
import capstone.gwttrial.client.calendar.service.CalendarService;
import capstone.gwttrial.client.calendar.service.CalendarServiceAsync;
import capstone.gwttrial.client.login.service.LoginService;
import capstone.gwttrial.client.login.service.LoginServiceAsync;
import capstone.gwttrial.client.user.User;
import capstone.gwttrial.shared.FieldVerifier;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;

public class LoginPresenter implements Presenter {

	private final EventBus eventBus;
	private final LoginView display;
	private final LoginServiceAsync rpcLogin;
	private CalendarServiceAsync rpcCalendar;

	public LoginPresenter(EventBus eventBus, LoginView loginView) {
		this.eventBus = eventBus;
		this.display = loginView;
		this.rpcLogin = GWT.create(LoginService.class);
		this.rpcCalendar = GWT.create(CalendarService.class);
	}

	private void bind() {
		display.getregisterButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new LoginEvent("register"));
			}
		});

		display.getSignInButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (validateCredentials()) {
					User.setCurrentUser(display.getUN(), "Team Member");
					rpcLogin.getLoginSuccess(display.getUN(), display.getPW(),
							new AsyncCallback<Boolean>() {

								public void onSuccess(Boolean loggedIn) {
									if (loggedIn) {
										
										rpcCalendar.getCalendarEvents(display.getUN(),
												new AsyncCallback<ArrayList<EventDetails>>() {

													@Override
													public void onSuccess(
															ArrayList<EventDetails> result) {
														for (int i = 0; i<result.size();i++) {
															CalendarDetails.addEvent(result.get(i));
															CalendarDetails.events.get(CalendarDetails.events.size()-1).unformatTime();
														}
														
														
														eventBus.fireEvent(new LoginEvent(
																"home"));
													}
													
													@Override
													public void onFailure(
															Throwable caught) {
														Window.alert("Failed to load calendar.");
														Constants.logger
																.severe("LOGINPRESENTER.JAVA: CALENDAR LOAD FAILURE");
														
													}
										});						
										
									} else {
										Window.alert("Incorrect username or password.");
										Constants.logger
												.severe("LOGINPRESENTER.JAVA: INCORRECT UN/PW");
									}

								}

								public void onFailure(Throwable caught) {
									Window.alert("Failure on login attempt.");
									Constants.logger
											.severe("LOGINPRESENTER.JAVA: LOGIN FAILURE");
								}
							});

				} else {
					display.getErrorLabel().setText(
							"Invalid Username/password!");
				}
			}

			private boolean validateCredentials() {
				return FieldVerifier.isValidName(display.getUN(),
						display.getPW());
			}
		});
	}

	@Override
	public void go(HasWidgets container) {
		bind();
		container.clear();
		container.add(display.asWidget());
	}
}
