package capstone.gwttrial.client.doevent;

import java.util.Map;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasWidgets;

import capstone.gwttrial.client.Presenter;
import capstone.gwttrial.client.calendar.CalendarDetails;
import capstone.gwttrial.client.calendar.CalendarWidget;
import capstone.gwttrial.client.calendar.Constants;
import capstone.gwttrial.client.calendar.EventDetails;

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
		if (!view.isEdit()) {
			view.getCreateButton().addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					eventDeets.parseDetails(view.getEventDetails());
					CalendarDetails.addEvent(eventDeets);
					Constants.logger
							.severe("EVENT DETAILS ADDED TO CALENDAR DETAILS LIST");
					eventBus.fireEvent(new CreateEvent(-1, -1, "home"));
				}
			});
		} else {
			view.getSaveButton().addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {

					// Remove the EventDetails from the CalendarDetails
					CalendarDetails.deleteEvent(view.getEventToEditDetails());

					// Remove the button event from the static map
					CalendarWidget.getEventButtonMap().remove(
							view.getEventToEdit());

					// Add a new version using updated EventDetails
					eventDeets.parseDetails(view.getEventDetails());
					CalendarDetails.addEvent(eventDeets);

					Constants.logger.severe("EVENT DETAILS UPDATED");
					eventBus.fireEvent(new CreateEvent(-1, -1, "home"));
				}
			});

			view.getDeleteButton().addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					Constants.logger.severe("EVENT DELETED");

					// Remove the event from the CalendarDetails
					CalendarDetails.deleteEvent(view.getEventToEditDetails());

					// Remove the event from the static map
					CalendarWidget.getEventButtonMap().remove(
							view.getEventToEdit());

					eventBus.fireEvent(new CreateEvent(-1, -1, "home"));
				}
			});
		}

		view.getCancelButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				Constants.logger.severe("EVENT CREATION CANCELLED");
				eventBus.fireEvent(new CreateEvent(-1, -1, "home"));
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
