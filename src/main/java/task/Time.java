package task;

public class Time {
    protected int time;

    /**
     * Constructor for Time objects using integer.
     * @param time int the time in 24hr-clock representation.
     */
    public Time(int time) {
        this.time = time;
        assert time >= 0 : "Time cannot be negative";
        assert time < 2400 : "Time cannot be greater or equal to 2400hrs";
    }

    /**
     * Alternate constructor for Time objects using String.
     * @param time String String representation of time in 24hr-clock representation.
     */
    public Time(String time) {
        this.time = Integer.parseInt(time);
    }

    /**
     * Returns the String that represents the time.
     * @return String. (Example: 1800 -> 6pm, 0900 -> 9am).
     */
    public String toString() {
        int minutes = time % 100;
        if (time >= 1200) {
            int t = time - 1200 == 0 ? 12 : (time - 1200)/ 100;
            return t + minutes + "pm";
        } else {
            return (time / 100) + minutes + "am";
        }
    }

    /**
     * Returns the original time that was passed into the constructor. (24hr-clock representation).
     * @return int.
     */
    public int origin() {
        return this.time;
    }
}
