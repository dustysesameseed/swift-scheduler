package capstone.gwttrial.client.doevent;

import com.google.gwt.event.shared.GwtEvent;

public class CreateEvent extends GwtEvent<CreateEventHandler> {
	public static Type<CreateEventHandler> TYPE = new Type<CreateEventHandler>();
	private final String id;
	private final int row;
	private final int col;

	public CreateEvent(int row, int col, String id) {
		this.row = row;
		this.col = col;
		this.id = id;
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

	@Override
	public Type<CreateEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(CreateEventHandler handler) {
		handler.onCreateEvent(this);
	}
}