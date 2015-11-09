package capstone.gwttrial.client.doevent;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.HasWidgets;

import capstone.gwttrial.client.Presenter;
import capstone.gwttrial.client.calendar.CalendarDetails;
import capstone.gwttrial.client.calendar.EventDetails;
import capstone.gwttrial.client.login.LoginEvent;

public class CreateEventPresenter implements Presenter {

	private CreateEventView view;
	private EventBus eventBus;
	private EventDetails eventDeets;

	public CreateEventPresenter(EventBus eventBus, CreateEventView view) {
		this.eventBus = eventBus;
		this.view = view;
		this.eventDeets = new EventDetails();
	}

	private void bind() {
		view.getCreateButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				eventDeets.parseDetails(view.getEventDetails());
				CalendarDetails.addEvent(eventDeets);
				eventBus.fireEvent(new CreateEvent(null, "home"));
			}
		});

		view.getCancelButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new CreateEvent(null, "home"));
			}
		});
	}

	@Override
	public void go(HasWidgets container) {
		bind();
		container.clear();
		container.add(view.asWidget());
	}
}
