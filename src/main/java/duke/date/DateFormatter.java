package duke.date;

import duke.exception.DukeException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * To format the Date and Time.
 *
 * @author TeoShyanJie
 *
 */
public class DateFormatter {
    /** input of Date and Time format. */
    private DateFormat df = new SimpleDateFormat("dd/MM/yyyy HHmm");

    /** Output of Date and Time format. */
    private DateFormat outputformat = new SimpleDateFormat("d MMMM yyyy',' h:mm a");

    private String timeline = "";

    /**
     * Constructor of DateFormatter.
     * @param timeline Date and Time.
     */
    public DateFormatter(String timeline) {
        this.timeline = timeline;
    }

    /**
     * Get formatted Date and Time.
     * @return return Formatted Date and Time in string.
     * @throws DukeException Invalid Date and Time Format.
     */
    public String getTime() throws DukeException {
        Date date = null;
        try {
            date = df.parse(this.timeline);
            return outputformat.format(date);
        } catch (ParseException e) {
            throw new DukeException("Invalid Data and Time Format!!!");
        }
    }
}
