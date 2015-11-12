package capstone.gwttrial.client.login.service;





import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("login")
public interface LoginService extends RemoteService {
	Boolean getLoginSuccess(String un, String pw) throws IllegalArgumentException;
}
