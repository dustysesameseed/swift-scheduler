package capstone.gwttrial.client.user;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {

	private static ArrayList<String> teamStr;
	private static String un;
	private static String level;
	private static boolean isNewUser;

	// Default new user constructor
	public User(String username, String level, boolean isNewUser) {
		User.un = username;
		User.level = level;
		User.isNewUser = isNewUser;
		populateTeam();
	}

	private void populateTeam() {
		teamStr = new ArrayList<String>(7);
		teamStr.add("Peter Dudley");
		teamStr.add("Tom Weston");
		teamStr.add("Katherine Glen");
		teamStr.add("Harry Miller");
		teamStr.add("Clara Evans");
		teamStr.add("John Moray");
		teamStr.add("Denise Lovett");
	}

	public static String getUsername() {
		return un;
	}

	public static void setUsername(String username) {
		un = username;
	};

	public static String getLevel() {
		return level;
	}

	public static boolean isNewUser() {
		return isNewUser;
	}

	// For existing users
	public static void setCurrentUser(String un, String level) {
		new User(un, level, false);
	}

	public static ArrayList<String> getStrTeam() {
		return teamStr;
	}
}