public class DateTime {
    private int day, month, year, time;

    public DateTime(String input) throws IndexOutOfBoundsException, DukeException {
        String[] rawInput = input.split(" ");
        String[] date = rawInput[0].split("/");

        this.day = Integer.parseInt(date[0]);
        this.month = Integer.parseInt(date[1]);
        this.year = Integer.parseInt(date[2]);
        this.time = Integer.parseInt(rawInput[1]);

        if (time > 2359 || time < 0) {
            throw new DukeException("Please enter a valid time");
        }
    }

    @Override
    public String toString() {
        return this.day + "/" + this.month + "/" + "/" + this.year + " " + this.time;
    }
}
