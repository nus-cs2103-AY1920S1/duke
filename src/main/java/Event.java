import java.util.Calendar;

public class Event extends Task {
    private Calendar time;

    public Event(String des, Calendar time) {
        super(des);
        this.time = time;
    }

    @Override
    public String toString() {
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
            case 1 : nameOfMonth = "January";
                break;
            case 2 : nameOfMonth = "February";
                break;
            case 3 : nameOfMonth = "March";
                break;
            case 4 : nameOfMonth = "April";
                break;
            case 5 : nameOfMonth = "May";
                break;
            case 6 : nameOfMonth = "June";
                break;
            case 7 : nameOfMonth = "July";
                break;
            case 8 : nameOfMonth = "August";
                break;
            case 9 : nameOfMonth = "September";
                break;
            case 10 : nameOfMonth = "October";
                break;
            case 11 : nameOfMonth = "November";
                break;
            case 12 : nameOfMonth = "December";
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
        return "[E]" + super.toString() + " (at: " + time.get(Calendar.DAY_OF_MONTH) + jargon
                + nameOfMonth + ", "
                + time.get(Calendar.YEAR) + ". "
                + hoursAndMinutes
                + ")";
    }
}
