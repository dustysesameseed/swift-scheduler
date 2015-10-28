package capstone.gwttrial.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface CalendarServiceAsync {
	void greetServer(String input, String pw, AsyncCallback<String> callback)
			throws IllegalArgumentException;
}
