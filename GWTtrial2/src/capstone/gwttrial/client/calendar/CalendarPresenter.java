package capstone.gwttrial.client.calendar;

import capstone.gwttrial.client.Presenter;
import capstone.gwttrial.client.calendar.service.CalendarService;
import capstone.gwttrial.client.calendar.service.CalendarServiceAsync;
import capstone.gwttrial.client.user.User;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.HasWidgets;

public class CalendarPresenter implements Presenter {

	private final EventBus eventBus;
	private final CalendarView display;
	private final CalendarDetails userCal;
	private final String username;
	private CalendarServiceAsync rpcService;

	public CalendarPresenter(EventBus eventBus, CalendarView view, String username) {
		this.eventBus = eventBus;
		this.display = view;
		this.username = username;
		this.rpcService = GWT.create(CalendarService.class);
		
		// TODO Customize this based on user
		this.userCal = new CalendarDetails(username);
	}

	public void bind() {
		display.getAddButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				// eventBus.fireEvent(new AddEvent());
				// Add event
			}
		});

		display.getDeleteButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				// deleteSelectedContacts();
				// Delete event
			}
		});

		
	}

	public void go(final HasWidgets container) {
		bind();
		container.clear();
		container.add(display.asWidget());
		
		
	}

	public String getCurrentUser() {
		return username;
	}
}
