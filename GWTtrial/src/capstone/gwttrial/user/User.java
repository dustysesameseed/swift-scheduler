package capstone.gwttrial.user;

public abstract class User {

	String name;
	String level;
	
	public User(String name, String level) {
		this.name = name;
		this.level = level;
	}
	
	public abstract String getName();
	public abstract void setName(String name);
	public abstract String getLevel();
}