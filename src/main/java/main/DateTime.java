package main;

/**
 * Represents the date and time of a task in the DD/MM/YYYY TTTT format.
 */
public class DateTime {
    private int day, month, year, time;

    /**
     * Constructs the DateTime object.
     *
     * @param input the date and time in DD/MM/YYYY TTTT format.
     */
    public DateTime(String input) throws IndexOutOfBoundsException, DukeException, NumberFormatException {
        String[] rawInput = input.split(" ");
        String[] date = rawInput[0].split("/");

        this.day = Integer.parseInt(date[0]);
        this.month = Integer.parseInt(date[1]);
        this.year = Integer.parseInt(date[2]);
        this.time = Integer.parseInt(rawInput[1]);

        if (time > 2359 || time < 0) {
            throw new DukeException("Please enter a valid time");
        }

        if (day < 1 || day > 31) {
            throw new DukeException("Please enter a valid day");
        }

        if (month < 1 || month > 12) {
            throw new DukeException("Please enter a valid month");
        }
    }

    @Override
    public String toString() {
        return this.day + "/" + this.month + "/" + this.year + " " + this.time;
    }
}
