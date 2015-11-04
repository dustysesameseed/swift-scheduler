package capstone.gwttrial.client.login;

import com.google.gwt.event.shared.GwtEvent;

public class LogoutEvent extends GwtEvent<LogoutEventHandler> {
	public static Type<LogoutEventHandler> TYPE = new Type<LogoutEventHandler>();
	private final String id;

	public LogoutEvent(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	@Override
	public Type<LogoutEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(LogoutEventHandler handler) {
		handler.onLogout(this);
	}
}
