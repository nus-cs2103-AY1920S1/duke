package duke.date;

import java.text.DecimalFormat;

/**
 * DukeDate stub to be used for testing.
 */
public class DukeDateStub extends DukeDate {

    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;

    /**
     * Constructs a DukeDateStub.
     *
     * @param year Year of this stub.
     * @param month Month of this stub.
     * @param day Day of this stub.
     * @param hour Hour of this stub.
     * @param minute Minute of this stub.
     */
    public DukeDateStub(int year, int month, int day, int hour, int minute) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
    }

    /**
     * Formats this stub to string form.
     *
     * @return String form of this stub.
     */
    @Override
    public String format(boolean shouldTrim) {
        DecimalFormat df = new DecimalFormat("00");
        return String.format("%d/%d/%d, %d:%s",
                this.day,
                this.month,
                this.year,
                this.hour,
                df.format(this.minute));
    }

}
