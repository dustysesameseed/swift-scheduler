package capstone.gwttrial.client.doevent;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.user.client.ui.HTMLTable.Cell;

public class CreateEvent extends GwtEvent<CreateEventHandler> {
	public static Type<CreateEventHandler> TYPE = new Type<CreateEventHandler>();
	private final String id;
	private final Cell eventCell;

	public CreateEvent(Cell src, String string) {
		id = string;
		eventCell = src;
	}

	public String getId() {
		return id;
	}

	public Cell getEventCell() {
		return eventCell;
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
