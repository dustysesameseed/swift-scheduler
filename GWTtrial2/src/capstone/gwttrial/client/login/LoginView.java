package capstone.gwttrial.client.login;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Label;
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

	public LoginView() {
		vertPanel = new VerticalPanel();
		initWidget(vertPanel);

		// Initialize Labels, Boxes, and Buttons
		header = new Label("Swift Scheduler");
		loginMsg = new Label("Sign in to your account:");
		errorLabel = new Label();
		unField = new TextBox();
		pwField = new PasswordTextBox();
		signInButton = new Button("Sign In");

		// Configure Labels, Boxes, and Buttons
		configure();
	}

	private void configure() {
		// Add style and text
		header.setStyleName("header");
		signInButton.addStyleName("signInButton");
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

		// Configure verticalPanel
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
