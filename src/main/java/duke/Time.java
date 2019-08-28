package duke;

public class Time {

    Period period;
    int hour;
    int minutes;
    int time;

    public enum Period {
        AM, PM;
    }

    public Time(int time) {
        this.time = time;
        hour = time / 100;
        minutes = time % 100;
        if (time < 1200) {
            period = Period.AM;
        } else {
            period = Period.PM;
        }
    }

    public static Time processTime(String timeString) {
        return new Time(Integer.parseInt(timeString));
    }

    @Override
    public String toString() {
        return time + "";
    }
}
