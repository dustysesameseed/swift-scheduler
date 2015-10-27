package capstone.gwttrial.server;

import capstone.gwttrial.client.login.service.LoginService;
import capstone.gwttrial.shared.FieldVerifier;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class LoginServiceImpl extends RemoteServiceServlet implements
		LoginService {

	@Override
	public Boolean getLoginSuccess(String un, String pw) throws IllegalArgumentException {
		
		// TODO Make sure username/password combo is in database, create session instance server-side
		Boolean loggedIn = FieldVerifier.isValidName(un, pw);
		
		return loggedIn;
	}

}
