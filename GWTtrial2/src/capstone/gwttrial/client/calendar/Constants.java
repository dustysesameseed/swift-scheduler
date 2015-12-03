package capstone.gwttrial.client.calendar;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import com.google.gwt.thirdparty.guava.common.collect.ImmutableMap;

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

	/*
	 * List of days of the week String constants
	 */
	public static final String[] MONTHS = { "Jan", "Feb", "Mar", "Apr", "May",
			"Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };

	/*
	 * Map of months to their total days of month
	 */
	public static final Map<String, Integer> MONTHS_TO_TOTALDAYS_MAP = new HashMap<String, Integer>();
	static {
		MONTHS_TO_TOTALDAYS_MAP.put("Jan", new Integer(31));
		MONTHS_TO_TOTALDAYS_MAP.put("Feb", new Integer(28));
		MONTHS_TO_TOTALDAYS_MAP.put("Mar", new Integer(31));
		MONTHS_TO_TOTALDAYS_MAP.put("Apr", new Integer(30));
		MONTHS_TO_TOTALDAYS_MAP.put("May", new Integer(31));
		MONTHS_TO_TOTALDAYS_MAP.put("Jun", new Integer(30));
		MONTHS_TO_TOTALDAYS_MAP.put("Jul", new Integer(31));
		MONTHS_TO_TOTALDAYS_MAP.put("Aug", new Integer(31));
		MONTHS_TO_TOTALDAYS_MAP.put("Sep", new Integer(30));
		MONTHS_TO_TOTALDAYS_MAP.put("Oct", new Integer(31));
		MONTHS_TO_TOTALDAYS_MAP.put("Nov", new Integer(30));
		MONTHS_TO_TOTALDAYS_MAP.put("Dec", new Integer(31));
	}
}
