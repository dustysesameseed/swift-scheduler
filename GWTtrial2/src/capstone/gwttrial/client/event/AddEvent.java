package capstone.gwttrial.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class AddEvent extends GwtEvent<AddEventHandler> {
  public static Type<AddEventHandler> TYPE = new Type<AddEventHandler>();
  
  @Override
  public Type<AddEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(AddEventHandler handler) {
    handler.onAddContact(this);
  }
}
