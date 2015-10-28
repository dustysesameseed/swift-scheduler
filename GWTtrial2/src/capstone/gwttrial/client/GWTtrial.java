package capstone.gwttrial.client;

<<<<<<< HEAD
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class GWTtrial implements EntryPoint {
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		final EventBus eventBus = new SimpleEventBus();
		AppController appViewer = new AppController(eventBus);
		appViewer.go(RootPanel.get());
=======
import capstone.gwttrial.client.calendar.service.CalendarService;
import capstone.gwttrial.client.calendar.service.CalendarServiceAsync;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class GWTtrial implements EntryPoint {
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		final EventBus eventBus = new SimpleEventBus();
		AppController appViewer = new AppController(eventBus);
		appViewer.go(RootPanel.get());

		// Composite loginPage = new Login();
		// RootPanel rootPanel = RootPanel.get();
		// rootPanel.add(loginPage);
>>>>>>> refs/remotes/origin/master
	}
}