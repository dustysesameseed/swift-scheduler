package capstone.gwttrial.client.login.service;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>LoginService</code>.
 */
public interface LoginServiceAsync {
	void getLoginSuccess(String un, String pw, AsyncCallback<Boolean> callback)
			throws IllegalArgumentException;
}
