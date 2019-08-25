import java.text.SimpleDateFormat;
import java.util.Date;

public class Deadline extends Task{
    protected String by;
    protected Date byDate;
    protected String day;
    protected String month;
    protected String year;
    protected String hour;
    protected String minute;

    public Deadline(String description, String by) throws InvalidTaskArgumentDukeException {
        super(description);
        this.by = by;

        String[] dateTime = by.split(" ");
        String[] date = dateTime[0].split("/");

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        try {
            this.day = date[0];
            this.month = date[1];
            this.year = date[2];
            this.hour = dateTime[1].substring(0, 2);
            this.minute = dateTime[1].substring(2);

            this.byDate = format.parse(day + "/" + month + "/" + year + " " + hour + ":" + minute);
        } catch (Exception e) { throw new InvalidTaskArgumentDukeException("☹ OOPS!!! The format of deadline timing is invalid.");}
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

        return format.format(this.byDate);
    }

    public Date getByDate() {
        return byDate;
    }

    public void setByDate(Date byDate) {
        this.byDate = byDate;
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

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dateToString() + ")";
    }
}
