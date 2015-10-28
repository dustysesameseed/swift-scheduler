package capstone.gwttrial.client.calendar;

import capstone.gwttrial.client.event.AddEvent;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.HasWidgets;

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
				eventBus.fireEvent(new AddEvent());
			}
		});

		viewHandler.getLogoutButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				// deleteSelectedContacts();
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
}
