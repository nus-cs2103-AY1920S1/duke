package duke;

import java.util.Hashtable;
import java.util.regex.Pattern;

/**
 * Represents a date and time of an event. A <code>Datetime</code> object corresponds
 * to a date and time represented as a string e.g. "01/01/2000 0000"
 */
public class Datetime {
    private String date;
    private String time;

    /**
     * Constructs a Datetime object from a raw string containing date and time.
     * @param datetime string containing date and time.
     */
    public Datetime(String datetime) {
        String[] content = datetime.split(" ");
        this.date = content[0];
        this.time = content[1];
    }

    private static Hashtable<Integer, String> ordinals = new Hashtable<>() {{
            put(0, "0th");
            put(1, "1st");
            put(2, "2nd");
            put(3, "3rd");
            put(4, "4th");
            put(5, "5th");
            put(6, "6th");
            put(7, "7th");
            put(8, "8th");
            put(9, "9th");
        }};

    private static Hashtable<Integer, String> months = new Hashtable<>() {{
            put(1, "January");
            put(2, "February");
            put(3, "March");
            put(4, "April");
            put(5, "May");
            put(6, "June");
            put(7, "July");
            put(8, "August");
            put(9, "September");
            put(10, "October");
            put(11, "November");
            put(12, "December");
        }};

    private static Pattern DATE_PATTERN = Pattern.compile("^\\d{2}/\\d{2}/\\d{4} \\d{4}$");

    /**
     * Returns true if the format of the given string is that of a datetime.
     * @param datetime String to be compared.
     * @return true if format of given string matches DATE_PATTERN.
     */
    public static boolean matches(String datetime) {
        return DATE_PATTERN.matcher(datetime).matches();
    }

    /**
     * Returns string representation of the date attribute of a Datetime.
     * @return string representation of a date.
     */
    public String getDate() {
        String[] dateArr = this.date.split("/");
        int dayAsInt = Integer.parseInt(dateArr[0]);
        String day;
        if (dayAsInt >= 11 && dayAsInt <= 13) {
            day = String.format("%dth", dayAsInt);
        } else if (dayAsInt > 10) {
            day = dateArr[0].substring(0,1) + ordinals.get(dayAsInt % 10);
        } else {
            day = ordinals.get(dayAsInt);
        }
        String month = months.get(Integer.parseInt(dateArr[1]));
        return String.format("%s of %s %s", day, month, dateArr[2]);
    }

    /**
     * Returns string representation of a time attribute of a Datetime.
     * @return string representation of a time.
     */
    public String getTime() {
        int hour = Integer.parseInt(this.time.substring(0,2));
        String min = this.time.substring(2);
        return String.format("%d:%s%s", hour == 12 || hour == 0 ? 12 : hour % 12,
                                        min,
                                        hour > 11 ? "pm" : "am");
    }

    /**
     * Returns the concatenation of the date and time attributes of a Datetime.
     * @return string concatenation of the date and time attributes.
     */
    public String getRawDatetime() {
        return this.date + " " + this.time;
    }

    /**
     * Returns string representation of a Datetime.
     * @return string representation of a Datetime.
     */
    @Override
    public String toString() {
        return String.format("%s, %s", this.getDate(), this.getTime());
    }
}
