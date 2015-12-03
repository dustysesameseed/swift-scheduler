package capstone.gwttrial.client.register;

import capstone.gwttrial.client.Presenter;
import capstone.gwttrial.client.register.service.RegisterService;
import capstone.gwttrial.client.register.service.RegisterServiceAsync;
import capstone.gwttrial.client.user.User;
import capstone.gwttrial.shared.FieldVerifier;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;

public class RegisterPresenter implements Presenter {

	private final EventBus eventBus;
	private final RegisterView display;
	private final RegisterServiceAsync rpcLogin;

	public RegisterPresenter(EventBus eventBus, RegisterView loginView) {
		this.eventBus = eventBus;
		this.display = loginView;
		this.rpcLogin = GWT.create(RegisterService.class);
	}

	private void bind() {
		display.getRegisterButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (validateCredentials()) {
					// TODO: get user level from server...for now, we are all
					// normal
					User.setCurrentUser(display.getUN(), "Team Member");
					rpcLogin.getRegisterSuccess(display.getUN(), display.getPW(),
							new AsyncCallback<Boolean>() {

								public void onSuccess(Boolean registered) {
									if (registered) {
									eventBus.fireEvent(new RegisterEvent("home"));
									} else {
										Window.alert("Invalid username or password.");
									}
									
								}

								public void onFailure(Throwable caught) {
									Window.alert("Failure on register attempt.");
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
