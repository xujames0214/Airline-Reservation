package time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**This class provides a routine to transform a date in String to a date in Date-class.
 * @author Jishen
 *
 */
public class TimeConversion {

	/**Transform a date in String to a date in Date-class.
	 * @param dateString The date in String
	 * @return The date in Date-class
	 */
	public static Date parseDate(String dateString){
		String[] dateStrings = dateString.split(" ");
		String newDateString = dateStrings[0] + "-" + convertMonth(dateStrings[1]) + "-" + dateStrings[2] + " " + dateStrings[3];
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));  
		Date date;
		try {
			date = sdf.parse(newDateString);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		return date;
	}
	
	/**Transform a month in abbreviation to a numeric value.
	 * @param month The month in abbreviation
	 * @return The month in numeric value
	 */
	private static String convertMonth(String month){
		if(month.equals("Jan")) return "01";
		if(month.equals("Feb")) return "02";
		if(month.equals("Mar")) return "03";
		if(month.equals("Apr")) return "04";
		if(month.equals("May")) return "05";
		if(month.equals("June")) return "06";
		if(month.equals("July")) return "07";
		if(month.equals("Aug")) return "08";
		if(month.equals("Sept")) return "09";
		if(month.equals("Oct")) return "10";
		if(month.equals("Nov")) return "11";
		if(month.equals("Dec")) return "12";
		return null;
	}
}
