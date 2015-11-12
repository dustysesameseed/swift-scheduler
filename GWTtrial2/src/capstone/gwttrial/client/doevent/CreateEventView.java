package capstone.gwttrial.client.doevent;

import java.util.ArrayList;

import capstone.gwttrial.client.calendar.CalendarWidget;
import capstone.gwttrial.client.calendar.Constants;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
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
	private int srcRow;
	private int srcCol;

	public CreateEventView() {
	}

	public CreateEventView(int createEventSrcRow, int createEventSrcCol) {
		parentPanel = new VerticalPanel();
		initWidget(parentPanel);

		// Initialize boxes and buttons
		this.srcRow = createEventSrcRow;
		this.srcCol = createEventSrcCol;

		createButton = new Button("Create");
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

		if (srcRow != -1 && srcCol != -1) {
			// Use the srcRow to identify which hour was selected
			Constants.logger
					.severe("CREATEEVENTVIEW.JAVA: RETRIEVING HOUR FROM CALENDARWIDGET");
			Constants.logger.severe("HOUR: "
					+ CalendarWidget.getHourFromRow(srcRow));
			String hr = CalendarWidget.getHourFromRow(srcRow).toString();
			String amPm = CalendarWidget.getAmPm();
			timeTBox.setText(hr + ":00" + amPm);

			// Use the srcCol to identify which day was selected
			Constants.logger
					.severe("CREATEEVENTVIEW.JAVA: RETREIVING DAY FROM CALENDARWIDGET");
			Constants.logger.severe("DAY: "
					+ CalendarWidget.getDayFromCol(srcCol));
			String month = CalendarWidget.getCurrentMonthNum().toString();
			String day = CalendarWidget.getDayFromCol(srcCol).toString();
			String dateStr = CalendarWidget.getCurrentMonthNum() + "/" + day
					+ "/" + CalendarWidget.getCurrentYear2();
			Constants.logger.severe("Date set to: " + dateStr);
			dateTBox.setText(dateStr);

		} else {
			Constants.logger
					.severe("CREATEEVENTVIEW: CELL SOURCE FOR CREATE EVENT HAS ROW, COL: "
							+ srcRow + "," + srcCol);
			dateTBox.setText("Enter a date (eg. 11/15/15)");
			timeTBox.setText("Enter a time (eg. 5:00 pm)");
		}

		nameTBox.setText("Enter an event name (eg. Team Meeting)");
		locTBox.setText("Enter a location (eg. Ohio Union)");
		descTBox.setText("Enter a description");

		nameTBox.setWidth("180%");
		locTBox.setWidth("180%");
		dateTBox.setWidth("180%");
		timeTBox.setWidth("180%");
		descTBox.setWidth("180%");
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
		// TODO: parse input to make sure they are legitimate strings
		return boxes;
	}

	public Widget asWidget() {
		return this;
	}
}
