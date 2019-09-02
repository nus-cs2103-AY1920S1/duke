package Task;

public class Time {

    private Period period;
    private int hour;
    private int minutes;
    private int time;

    /**
     * Documents whether given time is am or pm.
     */
    public enum Period {
        AM, PM;
    }

    /**
     * Creates time object with hour, minute and period components.
     * @param time in military format.
     */
    private Time(int time) {
        this.time = time;
        hour = time / 100;
        minutes = time % 100;
        if (time < 1200) {
            period = Period.AM;
        } else {
            period = Period.PM;
        }
    }

    /**
     * Processes a given String and converts String to a time object.
     * @param timeString String in form HHMM.
     * @return Time object.
     */
    public static Time processTime(String timeString) {
        return new Time(Integer.parseInt(timeString));
    }

    /**
     * Formats Time object.
     * @return A string in the form HHMM.
     */
    @Override
    public String toString() {
        return time + "";
    }
}
