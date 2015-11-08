package capstone.gwttrial.client.login;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class LoginView extends Composite {
	private VerticalPanel vertPanel;
	private Label header;
	private Label loginMsg;
	private Label errorLabel;
	private TextBox unField;
	private PasswordTextBox pwField;
	private Button signInButton;
	private String token;
	private Button registerButton;

	public LoginView(String token) {
		vertPanel = new VerticalPanel();
		initWidget(vertPanel);

		this.token = token;

		// Initialize Labels, Boxes, and Buttons
		header = new Label("Swift Scheduler");
		loginMsg = new Label("Sign in to your account:");
		errorLabel = new Label();
		unField = new TextBox();
		pwField = new PasswordTextBox();
		signInButton = new Button("Sign In");
		registerButton = new Button("Need an account? Register here!");

		// Configure Labels, Boxes, and Buttons
		configure();
	}

	private void configure() {
		// Add style and text
		header.setStyleName("header");
		signInButton.addStyleName("signInButton");
		registerButton.addStyleName("registerButton");
		errorLabel.setStyleName("serverResponseLabelError");
		unField.setText("Username");
		pwField.setText("Password");

		// Focus the cursor on the name field when the app loads
		unField.setFocus(true);
		unField.selectAll();

		// Put everything together in a FlexTable
		FlexTable flexTable = new FlexTable();
		flexTable.setWidget(0, 0, loginMsg);
		flexTable.setWidget(1, 0, unField);
		flexTable.setWidget(2, 0, pwField);
		flexTable.setWidget(2, 2, signInButton);
		flexTable.setWidget(3, 0, errorLabel);
		flexTable.setWidget(5, 0, registerButton);

		if (token.equals("logout")) {
			Label logoutLabel = new Label("You have been logged out");
			logoutLabel.setStyleName("serverResponseLabelError");
			flexTable.setWidget(4, 0, logoutLabel);
		}

		// Configure verticalPanel
		vertPanel.getParent().setHeight("100%");
		vertPanel.setSize("100%", "100%");
		vertPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		vertPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);

		// Add header and table to verticalPanel
		vertPanel.add(header);
		vertPanel.add(flexTable);
	}

	public Button getSignInButton() {
		return signInButton;
	}
	public Button getregisterButton() {
		return registerButton;
	}

	public Label getErrorLabel() {
		return errorLabel;
	}

	public String getUN() {
		return unField.getText();
	}

	public String getPW() {
		return pwField.getText();
	}
}
