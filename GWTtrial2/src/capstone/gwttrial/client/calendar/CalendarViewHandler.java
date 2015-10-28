package capstone.gwttrial.client.calendar;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Widget;

public interface CalendarViewHandler {
	HasClickHandlers getProfileButton();

	HasClickHandlers getLogoutButton();

	Widget asWidget();
}
