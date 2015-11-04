package capstone.gwttrial.client.doevent;

import java.util.ArrayList;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTMLTable.Cell;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class CreateEventView extends Composite {
	private ArrayList<TextBox> boxes;
	private Button createButton;
	private Button cancelButton;
	private VerticalPanel parentPanel;
	private Cell addEventSrc;

	public CreateEventView(Cell addEventSrc) {
		parentPanel = new VerticalPanel();
		initWidget(parentPanel);

		// Initialize boxes and buttons
		this.addEventSrc = addEventSrc;

		createButton = new Button("Add");
		cancelButton = new Button("Cancel");

		configure();
	}

	private void configure() {
		// Configure parentPanel
		parentPanel.setBorderWidth(50);
		parentPanel.setSize("100%", "100%");
		parentPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		parentPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);

		// int time = addEventSrc.getRowIndex();

		// Initialize Labels, Boxes, and Buttons
		Label header = new Label("Create an Event");
		Label nameLabel = new Label("Event Name");
		Label locLabel = new Label("Location");
		Label dateTimeLabel = new Label("Date/Time");
		Label descLabel = new Label("Description");
		configureTextBoxes();

		// Add style and text
		header.setStyleName("header");

		// Everything goes into a FlexTable
		FlexTable flexTable = new FlexTable();
		flexTable.setCellPadding(25);
		flexTable.setCellSpacing(25);

		flexTable.setWidget(0, 0, nameLabel);
		flexTable.setWidget(1, 0, locLabel);
		flexTable.setWidget(2, 0, dateTimeLabel);
		flexTable.setWidget(3, 0, descLabel);

		flexTable.setWidget(0, 1, boxes.get(0));
		flexTable.setWidget(1, 1, boxes.get(1));
		flexTable.setWidget(2, 1, boxes.get(2));
		flexTable.setWidget(2, 2, boxes.get(3));
		flexTable.setWidget(3, 1, boxes.get(4));

		flexTable.setWidget(4, 3, cancelButton);
		flexTable.setWidget(4, 4, createButton);

		// Add the FlexTable to the ParentPanel
		parentPanel.add(header);
		parentPanel.add(flexTable);
	}

	private void configureTextBoxes() {
		TextBox nameTBox = new TextBox();
		TextBox locTBox = new TextBox();
		TextBox dateTBox = new TextBox();
		TextBox timeTBox = new TextBox();
		TextBox descTBox = new TextBox();

		nameTBox.setText("Add the name of your event");
		locTBox.setText("Enter a location");
		dateTBox.setText("Enter a date");
		timeTBox.setText("Enter a time");
		descTBox.setText("Enter a description");
		nameTBox.setFocus(true);
		nameTBox.selectAll();

		boxes = new ArrayList<TextBox>();
		boxes.add(nameTBox);
		boxes.add(locTBox);
		boxes.add(dateTBox);
		boxes.add(timeTBox);
		boxes.add(descTBox);
	}

	public Button getCreateButton() {
		return createButton;
	}

	public Button getCancelButton() {
		return cancelButton;
	}

	public ArrayList<TextBox> getEventDetails() {
		return boxes;
	}

	public Widget asWidget() {
		return this;
	}
}
