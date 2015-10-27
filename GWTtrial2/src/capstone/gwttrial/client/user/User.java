package capstone.gwttrial.client.user;

import java.io.Serializable;

public class User implements Serializable{

	private static String un;
	private static String level;
	private static boolean isNewUser;

	// Default new user constructor
	public User(String username, String level, boolean isNewUser) {
		User.un = username;
		User.level = level;
		User.isNewUser = isNewUser;
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
}