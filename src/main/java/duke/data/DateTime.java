package duke.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTime {
    private final SimpleDateFormat DATETIME_FORMATGIVEN = new SimpleDateFormat(
            "d/M/yyyy HHmm");
    private final String FORMAT_TOSHOW = " 'of' MMMM yyyy, ha";
    private final SimpleDateFormat DATETIME_FORMATTER = new SimpleDateFormat(
            FORMAT_TOSHOW);
    private String _dateTime;
    private Date _date;

    public DateTime(String dateTime) throws ParseException {
        this._dateTime = dateTime;
        this._date = DATETIME_FORMATGIVEN.parse(dateTime);
    }

    public String getDateTimeString() {
        String[] splitDate = this._dateTime.split("/");
        String suffix = getSuffix(Integer.parseInt(splitDate[0]));
        String dateTimeString = suffix + DATETIME_FORMATTER.format(this._date);
        return dateTimeString;
    }

    private String getSuffix(int d) {
        switch (d % 10) {
        case 1:
            return d + "st";
        case 2:
            return d + "nd";
        case 3:
            return d + "rd";
        default:
            return d + "th";
        }
    }
}
