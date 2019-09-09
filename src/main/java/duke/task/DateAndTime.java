package duke.task;

import duke.DukeException;

public class DateAndTime {
    protected String dateAndTime;
    protected int date;
    protected int month;
    protected int year;
    protected int timeNumber;
    protected String timeDay;

    protected String[] monthMappings = new String[]{"", "January", "February", "March", "April", "May", "June",
                                                    "July", "August", "September", "October", "November", "December"};

    public DateAndTime(String dateAndTime) throws DukeException {
        this.dateAndTime = dateAndTime;
        try {
            String[] split = dateAndTime.split(" ");
            String date[] = split[0].split("/");
            int time = Integer.parseInt(split[1]) / 100;

            this.date = Integer.parseInt(date[0]);
            this.month = Integer.parseInt(date[1]);
            this.year = Integer.parseInt(date[2]);

            if (time < 12) {
                this.timeNumber = (time == 0) ? 12 : time;
                this.timeDay = "am";
            } else {
                this.timeNumber = time - 12;
                this.timeDay = "pm";
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Date and time not in correct format.");
        }
    }

    public String toSave() {
        return dateAndTime;
    }

    @Override
    public String toString() {
        String extra = (date == 1) ? "st" : (date == 2) ? "nd" : (date == 3) ? "rd" : "th";
        return this.date + extra + " of " + monthMappings[this.month] + " " + this.year + ", " + this.timeNumber + this.timeDay;
    }
}
