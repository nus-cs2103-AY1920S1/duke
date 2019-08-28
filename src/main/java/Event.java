import java.util.Calendar;

public class Event extends Task {
    private Calendar time;

    public Event(String des, Calendar time) {
        super(des);
        this.time = time;
    }

    public Calendar getTime() {
        return time;
    }

    public String printTime() {
        String jargon = "";
        switch (time.get(Calendar.DAY_OF_MONTH) % 10) {
        case 1 : jargon = "st ";
            break;
        case 2 : jargon = "nd ";
            break;
        case 3 : jargon = "rd ";
            break;
        default : jargon = "th ";
        }
        String nameOfMonth = "";
        switch (time.get(Calendar.MONTH)) {
        case 0 : nameOfMonth = "January";
            break;
        case 1 : nameOfMonth = "February";
            break;
        case 2 : nameOfMonth = "March";
            break;
        case 3 : nameOfMonth = "April";
            break;
        case 4 : nameOfMonth = "May";
            break;
        case 5 : nameOfMonth = "June";
            break;
        case 6 : nameOfMonth = "July";
            break;
        case 7 : nameOfMonth = "August";
            break;
        case 8 : nameOfMonth = "September";
            break;
        case 9 : nameOfMonth = "October";
            break;
        case 10 : nameOfMonth = "November";
            break;
        case 11 : nameOfMonth = "December";
            break;
        }
        String hoursAndMinutes = "" + time.get(Calendar.HOUR) + ":";
        if (time.get(Calendar.MINUTE) < 10) {
            hoursAndMinutes += "0";
        }
        hoursAndMinutes += time.get(Calendar.MINUTE);
        if (time.get(Calendar.AM_PM) == 0) {
            hoursAndMinutes += "am";
        } else {
            hoursAndMinutes += "pm";
        }
        return time.get(Calendar.DAY_OF_MONTH) + jargon
                + nameOfMonth + ", "
                + time.get(Calendar.YEAR) + ". "
                + hoursAndMinutes;
    }
    @Override
    public String toString() {

        return "[E]" + super.toString() + " (at: "
                + this.printTime()
                + ")";
    }
}
