package capstone.gwttrial.client.register.service;





import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("register")
public interface RegisterService extends RemoteService {
	Boolean getRegisterSuccess(String un, String pw) throws IllegalArgumentException;
}
