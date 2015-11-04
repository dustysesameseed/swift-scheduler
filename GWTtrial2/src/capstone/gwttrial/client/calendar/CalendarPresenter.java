package capstone.gwttrial.client.calendar;

import capstone.gwttrial.client.Presenter;
import capstone.gwttrial.client.doevent.CreateEvent;
import capstone.gwttrial.client.login.LogoutEvent;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTMLTable;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.HTMLTable.Cell;

public class CalendarPresenter implements Presenter {

	private final EventBus eventBus;
	private final CalendarViewHandler viewHandler;
	private final CalendarDetails userCal;
	private final String username;

	public CalendarPresenter(EventBus eventBus, CalendarViewHandler view,
			String username) {
		this.eventBus = eventBus;
		this.viewHandler = view;
		this.username = username;

		// TODO Customize this based on user
		this.userCal = new CalendarDetails(username);
	}

	public void bind() {
		viewHandler.getProfileButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				// eventBus.fireEvent(new AddEvent());
			}
		});

		viewHandler.getLogoutButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new LogoutEvent("logout"));
			}
		});

		final HTMLTable grid = viewHandler.getCalendar();
		grid.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				Cell src = grid.getCellForEvent(event);
				eventBus.fireEvent(new CreateEvent(src, "home"));
			}
		});
	}

	public void go(final HasWidgets container) {
		bind();
		container.clear();
		container.add(viewHandler.asWidget());
	}

	public String getCurrentUser() {
		return username;
	}

	public EventBus getEventBus() {
		return eventBus;
	}
}
