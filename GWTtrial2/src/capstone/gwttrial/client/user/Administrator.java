package capstone.gwttrial.client.user;

import capstone.gwttrial.client.event.EventDetails;

public class Administrator extends User {
	public Administrator(){
		super("user", "admin", true);
	}
	
	public Administrator(String un, String level, boolean isNewUser) {
		super(un, "admin", isNewUser);
	}

	public void denyRequest(EventDetails request) {

	}

	public void grantRequest(EventDetails request) {

	}
}
