package capstone.gwttrial.client.calendar;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HTMLTable;
import com.google.gwt.user.client.ui.Widget;

public interface CalendarViewHandler {
	HasClickHandlers getProfileLink();

	HasClickHandlers getLogoutLink();

	Widget asWidget();

	HTMLTable getCalendar();
}
