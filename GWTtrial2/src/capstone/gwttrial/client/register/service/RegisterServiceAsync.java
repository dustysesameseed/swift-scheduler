package capstone.gwttrial.client.register.service;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>RegisterService</code>.
 */
public interface RegisterServiceAsync {
	void getRegisterSuccess(String un, String pw, AsyncCallback<Boolean> callback)
			throws IllegalArgumentException;
}
