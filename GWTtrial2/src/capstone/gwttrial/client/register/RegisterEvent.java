package capstone.gwttrial.client.register;

import com.google.gwt.event.shared.GwtEvent;

public class RegisterEvent extends GwtEvent<RegisterEventHandler> {
	public static Type<RegisterEventHandler> TYPE = new Type<RegisterEventHandler>();
	private final String id;

	public RegisterEvent(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	@Override
	public Type<RegisterEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(RegisterEventHandler handler) {
		handler.onRegister(this);
	}
}
