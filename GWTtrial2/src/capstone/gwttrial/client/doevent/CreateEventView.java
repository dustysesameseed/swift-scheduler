package capstone.gwttrial.client.doevent;

import java.util.ArrayList;

import capstone.gwttrial.client.calendar.CalendarWidget;
import capstone.gwttrial.client.calendar.Constants;
import capstone.gwttrial.client.calendar.EventDetails;
import capstone.gwttrial.client.user.User;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

public class CreateEventView extends Composite {
	private ArrayList<TextBox> boxes;
	private ArrayList<TextArea> areas;
	private TextArea userList;
	private ArrayList<String> chosenUserList;
	private CellList<String> userCellList;
	private Button createButton;
	private Button cancelButton;
	private Button deleteButton;
	private Button eventToEdit;
	private EventDetails eventToEditDetails;
	private VerticalPanel parentPanel;
	private int srcRow;
	private int srcCol;
	private Button saveButton;
	private boolean isEdit;

	public CreateEventView() {
	}

	public CreateEventView(int createEventSrcRow, int createEventSrcCol) {
		parentPanel = new VerticalPanel();
		initWidget(parentPanel);

		// Store row and column of clickEvent for later population
		this.srcRow = createEventSrcRow;
		this.srcCol = createEventSrcCol;

		// Initialize buttons and text area
		createButton = new Button("Create");
		cancelButton = new Button("Cancel");
		chosenUserList = new ArrayList<String>();
		userList = new TextArea();

		isEdit = false;

		configure();
	}

	public CreateEventView(Button eventToEdit, EventDetails eventDetails) {
		parentPanel = new VerticalPanel();
		initWidget(parentPanel);

		// Store Button and EventDetails belonging to the event to be edited
		this.eventToEdit = eventToEdit;
		this.eventToEditDetails = eventDetails;
		Constants.logger
				.severe("CREATEEVENTVIEW.JAVA: EventToEditDetailsID set to: "
						+ this.eventToEditDetails.getEventID());

		// Initialize buttons
		saveButton = new Button("Save");
		cancelButton = new Button("Cancel");
		deleteButton = new Button("Delete");
		chosenUserList = new ArrayList<String>();
		userList = new TextArea();

		isEdit = true;

		configure();
	}

	private void configure() {
		// Configure parentPanel
		parentPanel.setBorderWidth(50);
		parentPanel.setSize("100%", "100%");
		parentPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		parentPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);

		// Initialize Labels, Boxes, and Buttons
		Label header;
		if (!isEdit) {
			header = new Label("Create an Event");
		} else {
			header = new Label("Edit Event");
		}
		header.setStyleName("subHeaderText");
		Label nameLabel = new Label("Event Name ");
		Label locLabel = new Label("Location");
		Label dateLabel = new Label("Date");
		Label startTimeLabel = new Label("Start Time");
		Label endTimeLabel = new Label("End Time");
		Label descLabel = new Label("Description");
		Label chosenUsersLabel = new Label("Attendees");
		Label chooseUsersLabel = new Label("Add Attendees\t");
		configureInputFields();

		// Add style and text
		header.setStyleName("header");

		// Everything goes into a FlexTable
		FlexTable flexTable = new FlexTable();
		flexTable.setCellPadding(100);
		flexTable.setCellSpacing(100);

		flexTable.setWidget(0, 0, nameLabel);
		flexTable.setWidget(1, 0, locLabel);
		flexTable.setWidget(2, 0, dateLabel);
		flexTable.setWidget(3, 0, startTimeLabel);
		flexTable.setWidget(4, 0, endTimeLabel);
		flexTable.setWidget(5, 0, descLabel);
		flexTable.setWidget(6, 0, chosenUsersLabel);
		flexTable.setWidget(7, 0, chooseUsersLabel);

		flexTable.setWidget(0, 1, boxes.get(0));
		flexTable.setWidget(1, 1, boxes.get(1));
		flexTable.setWidget(2, 1, boxes.get(2));
		flexTable.setWidget(3, 1, boxes.get(3));
		flexTable.setWidget(4, 1, boxes.get(4));
		flexTable.setWidget(5, 1, areas.get(0));
		flexTable.setWidget(6, 1, areas.get(1));
		flexTable.setWidget(7, 1, userCellList);

		if (createButton != null) {
			flexTable.setWidget(8, 3, cancelButton);
			flexTable.setWidget(8, 4, createButton);
		} else {
			flexTable.setWidget(8, 3, saveButton);
			flexTable.setWidget(8, 4, cancelButton);
			flexTable.setWidget(8, 5, deleteButton);
		}

		// Add the FlexTable to the ParentPanel
		parentPanel.add(header);
		parentPanel.add(flexTable);
		Constants.logger.severe("CREATEEVENTVIEW.JAVA: END OF CONFIGURE().");
	}

	private void configureInputFields() {
		TextBox nameTBox = new TextBox();
		TextBox locTBox = new TextBox();
		TextBox dateTBox = new TextBox();
		TextBox startTimeTBox = new TextBox();
		TextBox endTimeTBox = new TextBox();
		TextArea descTBox = new TextArea();
		userCellList = configureUserList();

		if (!isEdit) {
			// Add default name, location, and date text for new Event
			nameTBox.setText("Enter an event name (eg. Team Meeting)");
			locTBox.setText("Enter a location (eg. Ohio Union)");
			descTBox.setText("Enter a description");

			Integer hr = CalendarWidget.getHourFromRow(srcRow);
			// Use the srcRow to identify which hour was selected
			Constants.logger.severe("CREATEEVENTVIEW.JAVA: HOUR: " + hr);
			String amPm = CalendarWidget.getAmPm(hr);
			startTimeTBox.setText(hr + ":00" + amPm);

			// Make sure am/pm and 12-1 cases are being set correctly
			if (hr == 11) {
				endTimeTBox.setText("12:00pm");
			} else if (hr == 12) {
				endTimeTBox.setText("1:00pm");
			} else {
				endTimeTBox.setText((hr + 1) + ":00" + amPm);
			}

			// Use the srcCol to identify which day was selected
			Constants.logger.severe("CREATEEVENTVIEW.JAVA: DAY: "
					+ CalendarWidget.getDayFromCol(srcCol));
			String month = CalendarWidget.getCurrentMonthNum().toString();
			String day = CalendarWidget.getDayFromCol(srcCol).toString();
			String dateStr = CalendarWidget.getCurrentMonthNum() + "/" + day
					+ "/" + CalendarWidget.getCurrentYear2();
			Constants.logger.severe("CREATEEVENTVIEW.JAVA: Date set to: "
					+ dateStr);
			dateTBox.setText(dateStr);
		} else {
			Constants.logger
					.severe("CREATEEVENTVIEW: CELL SOURCE FOR CREATE EVENT HAS ROW, COL: "
							+ srcRow + "," + srcCol);
			nameTBox.setText(eventToEditDetails.getName());
			dateTBox.setText(eventToEditDetails.getDate());
			startTimeTBox.setText(eventToEditDetails.getStartTime());
			endTimeTBox.setText(eventToEditDetails.getEndTime());
			locTBox.setText(eventToEditDetails.getLocation());
			descTBox.setText(eventToEditDetails.getDescription());
			userList.setText(eventToEditDetails.getUserList());
		}

		nameTBox.setWidth("180%");
		locTBox.setWidth("180%");
		dateTBox.setWidth("180%");
		startTimeTBox.setWidth("180%");
		endTimeTBox.setWidth("180%");
		descTBox.setWidth("180%");
		userList.setWidth("180%");
		nameTBox.setFocus(true);
		nameTBox.selectAll();

		boxes = new ArrayList<TextBox>();
		boxes.add(nameTBox);
		boxes.add(locTBox);
		boxes.add(dateTBox);
		boxes.add(startTimeTBox);
		boxes.add(endTimeTBox);
		areas = new ArrayList<TextArea>();
		areas.add(descTBox);
		areas.add(userList);
	}

	private CellList<String> configureUserList() {
		ArrayList<String> team = User.getStrTeam();
		userList.setText("");

		// Create a cell to render each value.
		TextCell textCell = new TextCell();

		// Create a CellList that uses the cell.
		CellList<String> cellList = new CellList<String>(textCell);
		cellList.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);

		// Add a selection model to handle user selection.
		final SingleSelectionModel<String> selectionModel = new SingleSelectionModel<String>();
		cellList.setSelectionModel(selectionModel);
		selectionModel
				.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
					public void onSelectionChange(SelectionChangeEvent event) {
						String selected = selectionModel.getSelectedObject();
						if (selected != null) {
							String str = userList.getText();
							if (str.contains(selected)) {
								// Remove the user that is already in the
								// textarea
								String strArray[] = str.split(",");
								String temp = "";
								for (int i = 0; i < strArray.length; i++) {
									if (!strArray[i].equals(selected)) {
										if (i != 0) {
											temp = temp.concat(",");
										}
										temp = temp.concat(strArray[i]);
									}
								}
								userList.setText(temp);
							} else {
								// Add the user to the textarea
								if (str.isEmpty()) {
									userList.setText(selected);
								} else {
									userList.setText(str + ", " + selected);
								}
							}
						}
						selectionModel.setSelected(selected, false);
						selectionModel.clear();
					}
				});

		// Set the total row count. This isn't strictly necessary, but it
		// affects paging calculations, so its good habit to keep the row count
		// up to date.
		cellList.setRowCount(team.size(), true);

		// Push the data into the widget.
		cellList.setRowData(0, User.getStrTeam());

		return cellList;
	}

	public Button getCreateButton() {
		return createButton;
	}

	public Button getCancelButton() {
		return cancelButton;
	}

	public Button getSaveButton() {
		return saveButton;
	}

	public Button getDeleteButton() {
		return deleteButton;
	}

	public Button getEventToEdit() {
		return eventToEdit;
	}

	public EventDetails getEventToEditDetails() {
		return eventToEditDetails;
	}

	public ArrayList<TextBox> getEventBoxes() {
		// TODO: parse input to make sure they are legitimate strings
		return boxes;
	}

	public ArrayList<TextArea> getEventAreas() {
		// TODO: parse input to make sure they are legitimate strings
		return areas;
	}

	public boolean isEdit() {
		return isEdit;
	}

	public Widget asWidget() {
		return this;
	}

	public ArrayList<String> getUserList() {
		return chosenUserList;
	}
}
