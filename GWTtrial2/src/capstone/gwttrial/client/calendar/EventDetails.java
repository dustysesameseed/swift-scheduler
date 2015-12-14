package capstone.gwttrial.client.calendar;


import java.util.ArrayList;
import java.util.Date;

import capstone.gwttrial.client.user.User;

import java.io.Serializable;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.TextBox;

public class EventDetails implements Serializable {
	private ArrayList<User> users;
	private int eventID;
	private String name;
	private String location;
	private String startTime;
	private String endTime;
	private String date;
	private String creator;
	private String description;
	private String startDatetime;
	private String endDatetime;

	public EventDetails() {
		new EventDetails("", "", "", "", "", "", -1);
	}

	public EventDetails(String name, String location, String date,
			String startTime, String endTime, String description, int eventID) {
		this.name = name;
		this.location = location;
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
		this.description = description;
		this.eventID = eventID;
		this.startDatetime = "";
		this.endDatetime= "";
	}
	
	public EventDetails(int eventID, String name, String location, String start,
			String end, String creator, String description) {
		// These are the variables stored in the database for an event
		this.eventID = eventID;
		this.name = name;
		this.location = location;
		this.startTime = start;
		this.endTime = end;
		this.creator = creator;
		this.description = description;
	}

	public void parseDetails(ArrayList<TextBox> details) {
		this.name = details.get(0).getText();
		this.location = details.get(1).getText();
		this.date = details.get(2).getText();
		this.startTime = details.get(3).getText();
		this.endTime = details.get(4).getText();
		this.description = details.get(5).getText();
		this.setStartDatetime();
		this.setEndDatetime();
	}

	public String getName() {
		return name;
	}

	public String getLocation() {
		return location;
	}

	public String getDate() {
		return date;
	}

	public String getStartTime() {
		return startTime;
	}

	public String getDescription() {
		return description;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public void setStartTimeFormatted(String startTime) {
		Date readTime = DateTimeFormat.getFormat("hh:mma").parse(startTime);
		this.startTime = DateTimeFormat.getFormat("HH:mm:ss").format(readTime);
	}
	
	public void setEndTimeFormatted(String endTime) {
		Date readTime = DateTimeFormat.getFormat("hh:mma").parse(endTime);
		this.endTime = DateTimeFormat.getFormat("HH:mm:ss").format(readTime);
	}
	
	public void setEventID(int eventID){
		this.eventID = eventID;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setStartDatetime() {
		this.startDatetime = this.getDateFormatted() + " " + this.getStartTimeFormatted();
	}
	
	public void setEndDatetime() {
		this.endDatetime = this.getDateFormatted() + " " + this.getEndTimeFormatted();
	}
	
	public int getEventID() {
		return eventID;
	}

	public String getEndTime() {
		return endTime;
	}
	
	public String getStartDatetime() {
		return startDatetime;
	}
	
	public String getEndDatetime() {
		return endDatetime;
	}
	
	public String getPercentOfHour() {

		Date earlyTime = DateTimeFormat.getFormat("hh:mma").parse(this.startTime);
		Date laterTime = DateTimeFormat.getFormat("hh:mma").parse(this.endTime);
		//in milliseconds
		double diff = laterTime.getTime() - earlyTime.getTime();

		double diffMinutes = diff / (60 * 1000);
		double diffpercent = (diffMinutes / 60.0)*100.0;
		int diffperint = (int) diffpercent;
		String diffperstr = ""+diffperint;
		
		return diffperstr;
	}
	
	public String getMinutesOffset() {
		Date earlyTime = DateTimeFormat.getFormat("hh:mma").parse(this.startTime);
		return DateTimeFormat.getFormat("mm").format(earlyTime);
	}
	
	public void unformatTime() {
		this.setDate(this.startTime);
		this.setDate(this.getDateUnformatted());
		this.setStartTime(this.getStartTimeUnformatted());
		this.setEndTime(this.getEndTimeUnformatted());
	}
	
	public String getEndTimeFormatted() {
		Date readTime = DateTimeFormat.getFormat("hh:mma").parse(this.endTime);
		return DateTimeFormat.getFormat("HH:mm:ss").format(readTime);
	}
	
	public String getStartTimeFormatted() {
		Date readTime = DateTimeFormat.getFormat("hh:mma").parse(this.startTime);
		return DateTimeFormat.getFormat("HH:mm:ss").format(readTime);
	}
	
	public String getDateFormatted() {
		Date readDate = DateTimeFormat.getFormat("MM/dd/yy").parse(this.date);
		return DateTimeFormat.getFormat("yyyy-MM-dd").format(readDate);
	}
	
	public String getEndTimeUnformatted() {
		Date readTime = DateTimeFormat.getFormat("yyyy-MM-dd HH:mm:ss'.0'").parse(this.endTime);
		return DateTimeFormat.getFormat("hh:mma").format(readTime);
	}
	
	public String getStartTimeUnformatted() {
		Date readTime = DateTimeFormat.getFormat("yyyy-MM-dd HH:mm:ss'.0'").parse(this.startTime);
		return DateTimeFormat.getFormat("hh:mma").format(readTime);
	}
	
	public String getDateUnformatted() {
		Date readDate = DateTimeFormat.getFormat("yyyy-MM-dd HH:mm:ss'.0'").parse(this.date);
		return DateTimeFormat.getFormat("MM/dd/yy").format(readDate);
	}
	
}