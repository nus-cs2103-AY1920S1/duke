package duke.task;

/**
 * Represents Date and Time of an Event or Deadline.
 */
public class DateTime {

    /**
     * Represents day of DateTime.
     */
    private int day;

    /**
     * Represents month of DateTime.
     */
    private int month;

    /**
     * Represents year of DateTime.
     */
    private int year;

    /**
     * Represents hour of DateTime.
     */
    private int hour;

    /**
     * Represents minute of DateTime.
     */
    private int minute;

    /**
     * Creates DateTime object with input.
     *
     * @param day    Day of DateTime.
     * @param month  Month of DateTime.
     * @param year   Year of DateTime.
     * @param hour   Hour of DateTime.
     * @param minute Minute of DateTime
     */
    public DateTime(int day, int month, int year, int hour, int minute) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.hour = hour;
        this.minute = minute;
    }

    /**
     * Static method that creates a DateTime object with DateTime string.
     *
     * @param dateTimeString Follows input of DD/MM/YYYY HHMM.
     * @return DateTime object of specified date and time.
     */
    public static DateTime create(String dateTimeString) {
        String[] dateTime = dateTimeString.split(" ");
        String[] date = dateTime[0].split("/");
        int day = Integer.parseInt(date[0]);
        int month = Integer.parseInt(date[1]);
        int year = Integer.parseInt(date[2]);

        int time = Integer.parseInt(dateTime[1]);
        int hour = time / 100;
        int minute = time % 100;
        return new DateTime(day, month, year, hour, minute);
    }

    /**
     * Returns string representation of DateTime.
     *
     * @return String representation of DateTime in DDth of MMMM, HH.MM am/pm.
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.day);
        if (this.day >= 10 && this.day <= 20) {
            stringBuilder.append("th");
        } else if ((this.day % 10) == 1) {
            stringBuilder.append("st");
        } else if ((this.day % 10) == 2) {
            stringBuilder.append("nd");
        } else if ((this.day % 10) == 3) {
            stringBuilder.append("rd");
        } else {
            stringBuilder.append("th");
        }
        stringBuilder.append(" of ");

        switch (this.month) {
        case 1:
            stringBuilder.append("January");
            break;
        case 2:
            stringBuilder.append("February");
            break;
        case 3:
            stringBuilder.append("March");
            break;
        case 4:
            stringBuilder.append("April");
            break;
        case 5:
            stringBuilder.append("May");
            break;
        case 6:
            stringBuilder.append("June");
            break;
        case 7:
            stringBuilder.append("July");
            break;
        case 8:
            stringBuilder.append("August");
            break;
        case 9:
            stringBuilder.append("September");
            break;
        case 10:
            stringBuilder.append("October");
            break;
        case 11:
            stringBuilder.append("November");
            break;
        case 12:
            stringBuilder.append("December");
            break;
        default:
            break;
        }

        stringBuilder.append(String.format(" %d, ", year));

        if ((hour == 0) || (hour == 12)) {
            stringBuilder.append("12");
        } else {
            int hourMod = hour % 12;
            stringBuilder.append(hourMod);
        }
        stringBuilder.append(String.format(".%02d", minute));

        if (hour >= 12) {
            stringBuilder.append("pm");
        } else {
            stringBuilder.append("am");
        }

        return stringBuilder.toString();
    }
}
