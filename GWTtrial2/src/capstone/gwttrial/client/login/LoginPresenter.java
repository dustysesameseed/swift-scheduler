package capstone.gwttrial.client.login;

import capstone.gwttrial.client.Presenter;
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

	public LoginPresenter(EventBus eventBus, LoginView loginView) {
		this.eventBus = eventBus;
		this.display = loginView;
		this.rpcLogin = GWT.create(LoginService.class);
	}

	private void bind() {
		display.getSignInButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (validateCredentials()) {
					// TODO: get user level from server...for now, we are all
					// normal
					User.setCurrentUser(display.getUN(), "normal");
					//display.getErrorLabel().setText("Login successful");
					//eventBus.fireEvent(new LoginEvent("home"));
					
					rpcLogin.getLoginSuccess(display.getUN(), display.getPW(), new AsyncCallback<Boolean>() {
						
						public void onSuccess(Boolean loggedIn){
							eventBus.fireEvent(new LoginEvent("home"));
						}
						public void onFailure(Throwable caught){
							Window.alert("Failure on login attempt.");
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
