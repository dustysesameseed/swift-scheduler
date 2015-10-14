package capstone.gwttrial.user;

import capstone.gwttrial.calendar.Event;

public class Administrator extends User {

	public Administrator(String name, String level) {
		super(name, level);
	}
	
	public void denyRequest(Event request){
		
	}
	
	public void grantRequest(Event request){
		
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getLevel(){
		return level;
	}
	
	public void setLevel(String level){
		this.level = level;
	}
}
