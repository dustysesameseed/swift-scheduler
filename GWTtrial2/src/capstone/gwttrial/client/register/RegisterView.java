package capstone.gwttrial.client.register;

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

public class RegisterView extends Composite {
	private VerticalPanel vertPanel;
	private Label header;
	private Label registerMsg;
	private Label errorLabel;
	private TextBox unField;
	private PasswordTextBox pwField;
	private Button registerButton;
	private Button loginButton;

	public RegisterView(String token) {
		vertPanel = new VerticalPanel();
		initWidget(vertPanel);

		// Initialize Labels, Boxes, and Buttons
		header = new Label("Swift Scheduler");
		registerMsg = new Label("Choose your username and password:");
		errorLabel = new Label();
		unField = new TextBox();
		pwField = new PasswordTextBox();
		registerButton = new Button("Register");
		loginButton = new Button("Already have an account? Login.");

		// Configure Labels, Boxes, and Buttons
		configure();
	}

	private void configure() {
		// Add style and text
		header.setStyleName("header");
		registerButton.addStyleName("registerButton");
		loginButton.setStyleName("loginButton");
		errorLabel.setStyleName("serverResponseLabelError");
		unField.setText("Username");
		pwField.setText("Password");

		// Focus the cursor on the name field when the app loads
		unField.setFocus(true);
		unField.selectAll();

		// Put everything together in a FlexTable
		FlexTable flexTable = new FlexTable();
		flexTable.setWidget(0, 0, registerMsg);
		flexTable.setWidget(1, 0, unField);
		flexTable.setWidget(2, 0, pwField);
		flexTable.setWidget(2, 2, registerButton);
		flexTable.setWidget(3, 0, errorLabel);
		flexTable.setWidget(5, 0, loginButton);

		// Configure verticalPanel
		vertPanel.getParent().setHeight("100%");
		vertPanel.setSize("100%", "100%");
		vertPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		vertPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);

		// Add header and table to verticalPanel
		vertPanel.add(header);
		vertPanel.add(flexTable);
	}

	public Button getRegisterButton() {
		return registerButton;
	}
	public Button getLoginButton() {
		return loginButton;
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
