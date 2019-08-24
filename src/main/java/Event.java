import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Event extends Task {
    protected String at;
    protected Date atDate;
    protected String day;
    protected String month;
    protected String year;
    protected String hour;
    protected String minute;

    public Event(String description, String at) throws InvalidTaskArgumentDukeException {
        super(description);
        this.at = at;

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        try {
            String[] dateTime = at.split(" ");
            String[] date = dateTime[0].split("/");
            this.day = date[0];
            this.month = date[1];
            this.year = date[2];
            this.hour = dateTime[1].substring(0, 2);
            this.minute = dateTime[1].substring(2);

            this.atDate = format.parse(day + "/" + month + "/" + year + " " + hour + ":" + minute);
        } catch (Exception e) {
            throw new InvalidTaskArgumentDukeException("☹ OOPS!!! The format of event timing is invalid.");
        }

    }

    public String dateToString() {
        SimpleDateFormat format;

        if (this.day.equals("1")) {
            format = new SimpleDateFormat("d" + "'st of '" + "MMMM yyyy" + "', '" + "hh:mma");
        } else if (this.day.equals("2")) {
            format = new SimpleDateFormat("d" + "'nd of '" + "MMMM yyyy" + "', '" + "hh:mma");
        } else if (this.day.equals("3")) {
            format = new SimpleDateFormat("d" + "'rd of '" + "MMMM yyyy" + "', '" + "hh:mma");
        } else {
            format = new SimpleDateFormat("d" + "'th of '" + "MMMM yyyy" + "', '" + "hh:mma");
        }

        return format.format(this.atDate);
    }

    public Date getAtDate() {
        return atDate;
    }

    public void setAtDate(Date atDate) {
        this.atDate = atDate;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getMinute() {
        return minute;
    }

    public void setMinute(String minute) {
        this.minute = minute;
    }

    public String getAt() {
        return at;
    }

    public void setAt(String at) {
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + dateToString() + ")";
    }
}
