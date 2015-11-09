package capstone.gwttrial.client.doevent;

import java.util.ArrayList;

import capstone.gwttrial.client.calendar.CalendarWidget;

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
	private Cell src;

	public CreateEventView(Cell source) {
		parentPanel = new VerticalPanel();
		initWidget(parentPanel);

		// Initialize boxes and buttons
		this.src = source;

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

		// Initialize Labels, Boxes, and Buttons
		Label header = new Label("Create an Event");
		Label nameLabel = new Label("Event Name");
		Label locLabel = new Label("Location");
		Label dateLabel = new Label("Date");
		Label timeLabel = new Label("Time");
		Label descLabel = new Label("Description");
		configureTextBoxes();

		// Add style and text
		header.setStyleName("header");

		// Everything goes into a FlexTable
		FlexTable flexTable = new FlexTable();
		flexTable.setCellPadding(100);
		flexTable.setCellSpacing(100);

		flexTable.setWidget(0, 0, nameLabel);
		flexTable.setWidget(1, 0, locLabel);
		flexTable.setWidget(2, 0, dateLabel);
		flexTable.setWidget(3, 0, timeLabel);
		flexTable.setWidget(4, 0, descLabel);

		flexTable.setWidget(0, 1, boxes.get(0));
		flexTable.setWidget(1, 1, boxes.get(1));
		flexTable.setWidget(2, 1, boxes.get(2));
		flexTable.setWidget(3, 1, boxes.get(3));
		flexTable.setWidget(4, 1, boxes.get(4));

		flexTable.setWidget(5, 3, cancelButton);
		flexTable.setWidget(5, 4, createButton);

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

		if (src != null) {
			int row = src.getRowIndex();
			String hr = CalendarWidget.getHourFromRow(row).toString();
			String amPm = CalendarWidget.getAmPm();
			timeTBox.setText(hr + ":00" + amPm);
		} else {
			timeTBox.setText("Enter a time (eg. 5:00 pm)");
		}

		nameTBox.setText("Add the name of your event (eg. Team Meeting)");
		nameTBox.setWidth(parentPanel.getOffsetWidth() / 3 + "%");
		locTBox.setText("Enter a location (eg. Ohio Union)");
		dateTBox.setText("Enter a date (eg. 11/15/15)");
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
