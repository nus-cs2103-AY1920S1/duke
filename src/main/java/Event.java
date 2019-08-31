import java.util.ArrayList;
import java.util.Calendar;

public class Event extends Task {
    private Calendar startTime;
    private Calendar endTime;

    public Event(Parser parser) {
        super(parser.getList().get(0));
        startTime = Calendar.getInstance();
        endTime = Calendar.getInstance();
        ArrayList<String> inputArray = parser.getList();
        startTime.set(Integer.parseInt(inputArray.get(3)),
                (Integer.parseInt(inputArray.get(2)) - 1),
                Integer.parseInt(inputArray.get(1)),
                Integer.parseInt(inputArray.get(4)),
                Integer.parseInt(inputArray.get(5)));
        endTime.set(Integer.parseInt(inputArray.get(3)),
                (Integer.parseInt(inputArray.get(2)) - 1),
                Integer.parseInt(inputArray.get(1)),
                Integer.parseInt(inputArray.get(6)),
                Integer.parseInt(inputArray.get(7)));
    }

    public Event(String des, Calendar startTime, Calendar endTime) {
        super(des);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Calendar getStartTime() {
        return startTime;
    }

    public Calendar getEndTime() {
        return endTime;
    }

    public String printStartTime() {
        String jargon = "";
        switch (startTime.get(Calendar.DAY_OF_MONTH) % 10) {
        case 1 : jargon = "st ";
            break;
        case 2 : jargon = "nd ";
            break;
        case 3 : jargon = "rd ";
            break;
        default : jargon = "th ";
        }
        String nameOfMonth = "";
        switch (startTime.get(Calendar.MONTH)) {
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
        default : nameOfMonth = "Invalid Start Month";
        }
        String hoursAndMinutes;
        if (startTime.get(Calendar.HOUR_OF_DAY) <= 12) {
            hoursAndMinutes = startTime.get(Calendar.HOUR_OF_DAY) + ":";
        } else {
            hoursAndMinutes = startTime.get(Calendar.HOUR) + ":";
        }
        if (startTime.get(Calendar.MINUTE) < 10) {
            hoursAndMinutes += "0";
        }
        hoursAndMinutes += startTime.get(Calendar.MINUTE);
        if (startTime.get(Calendar.AM_PM) == 0) {
            hoursAndMinutes += "am";
        } else {
            hoursAndMinutes += "pm";
        }
        return startTime.get(Calendar.DAY_OF_MONTH) + jargon
                + nameOfMonth + ", "
                + startTime.get(Calendar.YEAR) + ". "
                + hoursAndMinutes;
    }

    public String printEndTime() {
        String jargon = "";
        switch (endTime.get(Calendar.DAY_OF_MONTH) % 10) {
        case 1 : jargon = "st ";
            break;
        case 2 : jargon = "nd ";
            break;
        case 3 : jargon = "rd ";
            break;
        default : jargon = "th ";
        }
        String nameOfMonth = "";
        switch (endTime.get(Calendar.MONTH)) {
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
        default : nameOfMonth = "Invalid End Time";
        }
        String hoursAndMinutes;
        if (endTime.get(Calendar.HOUR_OF_DAY) <= 12) {
            hoursAndMinutes = endTime.get(Calendar.HOUR_OF_DAY) + ":";
        } else {
            hoursAndMinutes = endTime.get(Calendar.HOUR) + ":";
        }
        if (endTime.get(Calendar.MINUTE) < 10) {
            hoursAndMinutes += "0";
        }
        hoursAndMinutes += endTime.get(Calendar.MINUTE);
        if (endTime.get(Calendar.AM_PM) == 0) {
            hoursAndMinutes += "am";
        } else {
            hoursAndMinutes += "pm";
        }
        return endTime.get(Calendar.DAY_OF_MONTH) + jargon
                + nameOfMonth + ", "
                + endTime.get(Calendar.YEAR) + ". "
                + hoursAndMinutes;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + this.printStartTime() + " - "
                + this.printEndTime()
                + ")";
    }
}
