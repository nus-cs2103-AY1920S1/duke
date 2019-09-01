package duke.date;

import java.text.DecimalFormat;

public class DukeDateStub extends DukeDate {

    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;

    public DukeDateStub(int year, int month, int day, int hour, int minute) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
    }

    @Override
    public String format() {
        DecimalFormat df = new DecimalFormat("00");
        return String.format("%d/%d/%d, %d:%s",
                this.day,
                this.month,
                this.year,
                this.hour,
                df.format(this.minute));
    }

}
