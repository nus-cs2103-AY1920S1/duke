package myduke.task.parameters;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A Wrapper class to represent a DateTime.
 */
public class DukeDateTime {
    //Accepted Date Time format
    private static final String DATETIME_FORMAT = "dd/M/yy HHmm";
    private static final String PRINT_DATETIME_FORMAT = "dd MMM yyyy hh:mm a";

    //Class variable
    private String dateTimeString;
    private Date localDateTime;
    private SimpleDateFormat localDateFormatter;
    private SimpleDateFormat localDatePrintFormatter;

    /**
     * A Wrapper class for DateTime Object.
     *
     * @param dateTimeString the date time object to parse
     */
    public DukeDateTime(String dateTimeString) {
        this.dateTimeString = dateTimeString;
        this.localDateFormatter = new SimpleDateFormat(DATETIME_FORMAT);
        this.localDatePrintFormatter = new SimpleDateFormat(PRINT_DATETIME_FORMAT);
        this.localDateTime = null;

        try {
            this.localDateTime = this.localDateFormatter.parse(dateTimeString);
        } catch (ParseException ex2) {
            try {
                this.localDateTime = this.localDatePrintFormatter.parse(dateTimeString);
            } catch (ParseException ex1) {
                this.localDateTime = null;
            }
        }
    }

    /**
     * Determines whether the object is a valid DateTime Object.
     *
     * @return A boolean determining whether the object is a valid DateTime Object
     */
    public boolean isValidDateTimeObject() {
        return this.localDateTime != null;
    }

    /**
     * Get the DateTime Object.
     *
     * @return a DateTime Object.
     */
    public Date getLocalDateTime() {
        return this.localDateTime;
    }

    @Override
    public String toString() {
        if (isValidDateTimeObject()) {
            return localDatePrintFormatter.format(localDateTime);
        } else {
            return this.dateTimeString;
        }

    }

}
