package main.task;

public class Time {
    protected int time;

    public Time(int time) {
        this.time = time;
    }

    public Time(String time) {
        this.time = Integer.parseInt(time);
    }

    public String toString() {
        int minutes = time % 100;
        if (time > 1200) {
            return ((time - 1200) / 100) + minutes + "pm";
        } else {
            return (time / 100) + minutes + "am";
        }
    }
}
