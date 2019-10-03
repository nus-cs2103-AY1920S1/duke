package duke.util;

import duke.exception.DukeException;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

/**
 * A class wrapper around java.util.Date and java.text.SimpleDataFormat
 * to enable Duke to freely parse input strings and switch between formats
 * for standardized screen output.
 */
public class DateTime implements Serializable {
    private Date dateTime;

    /**
     * Returns a DateTime object whose parameters are initialized by parsing through
     * an input String with format "dd/MM/yy HHmm". Parsing is done through
     * java.text.SimpleDateFormat. Upon success, returns a DateTime
     * object. More templates will be added in the future to increase
     * the versatility of the parsing.
     *
     * @param input String to be parsed into date and time
     * @return java.util.Date object
     * @throws DukeException if this method fails to parse the input String
     */
    public static DateTime parseString(String input) throws DukeException {
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HHmm");
            Date dateTime = format.parse(input);
            return new DateTime(dateTime);
        } catch (ParseException e) {
            throw new DukeException("I couldn't decipher the date and time"
                    + " that you gave me...\n"
                    + "Please write it in <dd/mm/yyyy HHmm> format for me to"
                    + "\nunderstand!", e);
        }
    }

    // private constructor called by the static parser method DateTime.parseString()
    // returns a DateTime object.
    private DateTime(Date dateTime) {
        assert (dateTime != null); // static method always return a valid datetime
        this.dateTime = dateTime;
    }

    /**
     * Returns the date and time in a standardized format
     * of "dd MMM yyyy", eg. 17 FEB 1996.
     *
     * @return string representation of DateTime object
     */
    @Override
    public String toString() {
        SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy, hh:mm a");
        return format.format(this.dateTime);
    }
}
