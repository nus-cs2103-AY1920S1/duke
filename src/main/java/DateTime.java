import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTime {
    private final SimpleDateFormat _formatGiven = new SimpleDateFormat(
            "d/M/yyyy HHmm");
    private final String _formatToShow = " 'of' MMMM yyyy, ha";
    private final SimpleDateFormat _formatter = new SimpleDateFormat(
            _formatToShow);
    private String _dateTime;
    private Date _date;

    public DateTime(String dt) throws ParseException {
        this._dateTime = dt;
        this._date = _formatGiven.parse(dt);
    }

    public String getDateTimeString() {
        String[] splitDate = this._dateTime.split("/");
        String suffix = getSuffix(Integer.parseInt(splitDate[0]));
        String dateTime = suffix + _formatter.format(this._date);
        return dateTime;
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
