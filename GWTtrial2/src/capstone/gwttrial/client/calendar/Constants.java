package capstone.gwttrial.client.calendar;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class Constants {

	/*
	 * Global static logger
	 */
	public static Logger logger = Logger.getLogger("Cal");

	/*
	 * List of days of the week String constants
	 */
	public static final String[] DAYS_OF_WEEK = { "Sunday", "Monday",
			"Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };

	public static final String[] MONTHS = { "Jan", "Feb", "Mar", "Apr", "May",
			"Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };

	/*
	 * Map of months : total days in month
	 */
	public static final Map<String, Integer> MONTHS_TO_TOTALDAYS_MAP;
	static {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("Jan", 31);
		map.put("Feb", 28);
		map.put("Mar", 31);
		map.put("Apr", 30);
		map.put("May", 31);
		map.put("Jun", 30);
		map.put("Jul", 31);
		map.put("Aug", 31);
		map.put("Sep", 30);
		map.put("Oct", 31);
		map.put("Nov", 30);
		map.put("Dec", 31);
		MONTHS_TO_TOTALDAYS_MAP = Collections.unmodifiableMap(map);
	}
}
