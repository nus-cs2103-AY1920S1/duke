import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Deadline extends Task {

    protected String by, hour;
    protected int day, month, year;

    public Deadline(String description, String by) {
        // by = 2/12/2019 1800
        // deadline cs /by 21/12/2019 0800
        super(description);
        this.by = by;

        if (!by.contains("of")) { // If string by contains the word "of", by string is already formatted
                                 // For use when reading from saved file

            taskType = possibleTaskTypes.DEADLINE;

            String[] words = by.split("/");
            String[] years = by.split(" ");

            this.day = Integer.parseInt(words[0]);
            this.month = Integer.parseInt(words[1]);
            this.hour = years[1];
            this.year = Integer.parseInt(words[2].split(" ")[0]);

            String dayString;
            if ((this.day == 1) || (this.day == 21) || (this.day == 31)) {
                dayString = "st";
            } else if ((this.day == 2) || (this.day == 22)) {
                dayString = "nd";
            } else if ((this.day == 3) || (this.day == 23)) {
                dayString = "rd";
            } else {
                dayString = "th";
            }

            String[] possibleMonths = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November",
                    "December"};


            String monthString = possibleMonths[this.month - 1];

            String hoursString = this.hour;
            String amOrpm = "";

            if (Integer.parseInt(hoursString.substring(0, 2)) < 12) {
                amOrpm = "am";
            } else if (Integer.parseInt(hoursString.substring(0, 2)) < 24) {
                amOrpm = "pm";
            }

            String minuteString = "";

            if (Integer.parseInt(hoursString.substring(2)) == 0) {
                minuteString = ""
                ;
            } else {
                minuteString = "." + hoursString.substring(2);
            }

            int hourString = -1;

            if (Integer.parseInt(hoursString.substring(0, 2)) > 12) {
                hourString = (Integer.parseInt(hoursString.substring(0, 2)) - 12);
            } else {
                hourString = Integer.parseInt(hoursString.substring(0, 2));
            }


            this.by = this.day + dayString + " of " + monthString + " " + year + ", " + hourString + minuteString + amOrpm;
        }

    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toSaveString(){
        return ("D" + super.toSaveString() + " | " + this.by);
    }
}
