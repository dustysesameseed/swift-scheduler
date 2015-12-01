package capstone.gwttrial.client.login;

import capstone.gwttrial.client.Presenter;
import capstone.gwttrial.client.calendar.Constants;
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

	public LoginPresenter(EventBus eventBus, LoginView loginView) {
		this.eventBus = eventBus;
		this.display = loginView;
		this.rpcLogin = GWT.create(LoginService.class);
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
										eventBus.fireEvent(new LoginEvent(
												"home"));
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
