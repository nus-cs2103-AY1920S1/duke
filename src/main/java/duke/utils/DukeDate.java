package duke.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/** Convenience class to convert between String and Date object. */
public class DukeDate {
    private final String dateFormatString = "dd/MM/yyyy HH:mm";
    private SimpleDateFormat df;
    private Date time;

    /**
     * Constructor.
     *
     * @param dateStr String representing a certain date and time (format as specified
     *                in DukeDate.dateFormatString)
     * @throws ParseException Exception thrown when error encountered parsing dateStr
     */
    public DukeDate(String dateStr) throws ParseException {
        this.df = new SimpleDateFormat(this.dateFormatString);
        this.time = this.df.parse(dateStr);
    }

    /**
     * String representation of the date and time in the format specified in
     * DukeDate.dateFormatString
     *
     * @return String representation of the date and time
     */
    public String toString() {
        return this.df.format(this.time);
    }
}
