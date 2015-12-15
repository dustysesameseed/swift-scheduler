package capstone.gwttrial.client.doevent;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;

import capstone.gwttrial.client.Presenter;
import capstone.gwttrial.client.calendar.CalendarDetails;
import capstone.gwttrial.client.calendar.CalendarWidget;
import capstone.gwttrial.client.calendar.Constants;
import capstone.gwttrial.client.calendar.EventDetails;
import capstone.gwttrial.client.calendar.service.CalendarService;
import capstone.gwttrial.client.calendar.service.CalendarServiceAsync;

public class CreateEventPresenter implements Presenter {

	private CreateEventView view;
	private EventBus eventBus;
	private EventDetails eventDeets;
	private CalendarServiceAsync rpcCalendar;

	public CreateEventPresenter(EventBus eventBus, CreateEventView view) {
		this.eventBus = eventBus;
		this.view = view;
		this.eventDeets = new EventDetails();
		this.rpcCalendar = GWT.create(CalendarService.class);
	}

	private void bind() {
		if (!view.isEdit()) {
			view.getCreateButton().addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					eventDeets.parseDetails(view.getEventBoxes(),
							view.getEventAreas());
					rpcCalendar.addCalendarEvent(eventDeets,
							new AsyncCallback<Integer>() {

								public void onSuccess(Integer eventID) {
									eventDeets.setEventID(eventID.intValue());
									CalendarDetails.addEvent(eventDeets);
									CalendarDetails.events.get(
											CalendarDetails.events.size() - 1)
											.setEventID(eventID.intValue());
									Constants.logger
											.severe("EVENT DETAILS ADDED TO CALENDAR DETAILS LIST");
									eventBus.fireEvent(new CreateEvent(-1, -1,
											"home"));
								}

								public void onFailure(Throwable caught) {
									Window.alert("Failure on add event attempt.");
									Constants.logger
											.severe("CREATEVENTPRESENTER.JAVA: ADD EVENT FAILURE");
								}
							});

				}
			});
		} else {
			view.getSaveButton().addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {

					// Add a new version using updated EventDetails
					eventDeets.parseDetails(view.getEventBoxes(),
							view.getEventAreas());
					Constants.logger
							.severe("CREATEEVENTPRESENTER.JAVA: EventDetailsID set to: "
									+ view.getEventToEditDetails().getEventID());
					rpcCalendar.removeCalendarEvent(view
							.getEventToEditDetails().getEventID(),
							new AsyncCallback<Boolean>() {
								@Override
								public void onSuccess(Boolean result) {
									// Remove the EventDetails from the
									// CalendarDetails
									CalendarDetails.deleteEvent(view
											.getEventToEditDetails());

									// Remove the button event from the static
									// map
									CalendarWidget.getEventButtonMap().remove(
											view.getEventToEdit());

									rpcCalendar.addCalendarEvent(eventDeets,
											new AsyncCallback<Integer>() {

												public void onSuccess(
														Integer eventID) {
													eventDeets.setEventID(eventID
															.intValue());
													CalendarDetails
															.addEvent(eventDeets);
													CalendarDetails.events
															.get(CalendarDetails.events
																	.size() - 1)
															.setEventID(
																	eventID.intValue());
													Constants.logger
															.severe("EVENT DETAILS UPDATED TO CALENDAR DETAILS LIST");
													Constants.logger
															.severe("EVENT DETAILS UPDATED");
													eventBus.fireEvent(new CreateEvent(
															-1, -1, "home"));
												}

												public void onFailure(
														Throwable caught) {
													Window.alert("Failure on update-add event attempt.");
													Constants.logger
															.severe("CREATEVENTPRESENTER.JAVA: UPDATE-ADD EVENT FAILURE");
												}
											});
								}

								@Override
								public void onFailure(Throwable caught) {
									Window.alert("Failure on update-remove event attempt.");
									Constants.logger
											.severe("CREATEVENTPRESENTER.JAVA: UPDATE-REMOVE EVENT FAILURE");
								}
							});
				}
			});

			view.getDeleteButton().addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					Constants.logger.severe("EVENT DELETED");
					rpcCalendar.removeCalendarEvent(view
							.getEventToEditDetails().getEventID(),
							new AsyncCallback<Boolean>() {
								@Override
								public void onSuccess(Boolean result) {
									// Remove the EventDetails from the
									// CalendarDetails
									CalendarDetails.deleteEvent(view
											.getEventToEditDetails());

									// Remove the button event from the static
									// map
									CalendarWidget.getEventButtonMap().remove(
											view.getEventToEdit());

									eventBus.fireEvent(new CreateEvent(-1, -1,
											"home"));
								}

								@Override
								public void onFailure(Throwable caught) {
									Window.alert("Failure on remove event attempt.");
									Constants.logger
											.severe("CREATEVENTPRESENTER.JAVA: REMOVE EVENT FAILURE");
								}
							});

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
