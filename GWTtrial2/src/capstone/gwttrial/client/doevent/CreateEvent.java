package capstone.gwttrial.client.doevent;

import capstone.gwttrial.client.calendar.EventDetails;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.user.client.ui.Button;

public class CreateEvent extends GwtEvent<CreateEventHandler> {
	public static Type<CreateEventHandler> TYPE = new Type<CreateEventHandler>();
	private final String id;
	private final int row;
	private final int col;
	private final Button eventToEdit;
	private final EventDetails eventDetails;

	public CreateEvent(int row, int col, String id) {
		this.row = row;
		this.col = col;
		this.id = id;

		this.eventToEdit = null;
		this.eventDetails = null;
	}

	public CreateEvent(Button eventToEdit, EventDetails eventDetails, String id) {
		this.row = -1;
		this.col = -1;
		this.id = id;

		this.eventToEdit = eventToEdit;
		this.eventDetails = eventDetails;
	}

	public String getId() {
		return id;
	}

	public int getCellSrcRow() {
		return row;
	}

	public int getCellSrcCol() {
		return col;
	}

	public Button getEventToEdit() {
		return eventToEdit;
	}

	public EventDetails getEventDetails() {
		return eventDetails;
	}

	@Override
	public Type<CreateEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(CreateEventHandler handler) {
		handler.onCreateEvent(this);
	}
}