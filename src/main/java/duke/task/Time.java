package duke.task;

/**
 * Represents a time. Contains two integers representing hour and minute.
 */
public class Time {
    private int hour;
    private int minute;

    public Time(String format24h) {
        this.hour = Integer.parseInt(format24h.substring(0, 2));
        this.minute = Integer.parseInt(format24h.substring(2));
        assert this.hour <= 23 : "hour value cannot be more than 24";
        assert this.minute <= 59 : "minute value cannot be more than 59";
    }

    /**
     * Returns string description of the time.
     *
     * @return string description of time.
     */
    @Override
    public String toString() {
        StringBuilder timeString = new StringBuilder();
        if (this.hour > 12) {
            timeString.append(this.hour - 12);
        } else {
            timeString.append(this.hour);
        }
        if (this.minute != 0) {
            timeString.append(":");
            timeString.append(String.format("%02d", this.minute));
        }
        if (this.hour >= 12) {
            timeString.append("pm");
        } else {
            timeString.append("am");
        }
        return timeString.toString();
    }
}
