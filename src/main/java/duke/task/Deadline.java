package duke.task;

public class Deadline extends Task {

    protected String by; // hour;
    // protected int day, month, year;

    public Deadline(String description, String by) { //sets idDone to the default value, false
        // by = 2/12/2019 1800
        // deadline cs /by 21/12/2019 0800
        super(description);
        this.by = by;

        if (!by.contains("of")) { // If string by contains the word "of", by string is already formatted
                                 // For use when reading from saved file
            this.by = parseBy(by);
        }

    }

    public Deadline(String description, String by, Boolean isDone) {
        // by = 2/12/2019 1800
        // deadline cs /by 21/12/2019 0800
        super(description, isDone);
        this.by = by;

        if (!by.contains("of")) { // If string by contains the word "of", by string is already formatted
            // For use when reading from saved file
            this.by = parseBy(by);
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

    public String parseBy(String by){
        taskType = possibleTaskTypes.DEADLINE;

        String[] words = by.split("/");
        String[] years = by.split(" ");

        int day = Integer.parseInt(words[0]);
        int month = Integer.parseInt(words[1]);
        String hour = years[1];
        int year = Integer.parseInt(words[2].split(" ")[0]);

        String dayString;
        if ((day == 1) || (day == 21) || (day == 31)) {
            dayString = "st";
        } else if ((day == 2) || (day == 22)) {
            dayString = "nd";
        } else if ((day == 3) || (day == 23)) {
            dayString = "rd";
        } else {
            dayString = "th";
        }

        String[] possibleMonths = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November",
                "December"};


        String monthString = possibleMonths[month - 1];

        String hoursString = hour;
        String amOrpm = "";

        if (Integer.parseInt(hoursString.substring(0, 2)) < 12) {
            amOrpm = "am";
        } else if (Integer.parseInt(hoursString.substring(0, 2)) < 24) {
            amOrpm = "pm";
        }

        String minuteString = "";

        if (Integer.parseInt(hoursString.substring(2)) == 0) {
            minuteString = "";
        } else {
            minuteString = "." + hoursString.substring(2);
        }

        int hourString = -1;

        if (Integer.parseInt(hoursString.substring(0, 2)) > 12) {
            hourString = (Integer.parseInt(hoursString.substring(0, 2)) - 12);
        } else {
            hourString = Integer.parseInt(hoursString.substring(0, 2));
        }

        return ( day + dayString + " of " + monthString + " " + year + ", " + hourString + minuteString + amOrpm );
    }
}
