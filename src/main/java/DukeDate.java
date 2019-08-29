import java.text.DecimalFormat;

class DukeDate {

    private static final String ERROR_ILLEGAL_YEAR = "☹ OOPS!!! Year should be a number greater than 0";
    private static final String ERROR_ILLEGAL_MONTH = "☹ OOPS!!! Month should be a number between 1 and 12.";
    private static final String ERROR_ILLEGAL_DAY = "☹ OOPS!!! Date must be greater than 0 (and less than 31).";
    private static final String ERROR_DAY_MONTH_MISMATCH = "☹ OOPS!!! Given date does not exist in given month.";
    private static final String ERROR_ILLEGAL_TIME = "☹ OOPS!!! Hour must be between 0 and 23,\n"
            + "              and minute must be between 0 and 59.";

    private int year;
    private String month;
    private int day;
    private int hour;
    private int minute;
    private String amPmMarker;

    DukeDate(int year, int month, int day, int hour, int minute)
            throws DukeDateFormatException {
        if (year < 0) {
            throw new DukeDateFormatException(ERROR_ILLEGAL_YEAR);
        }
        this.year = year;
        this.month = this.getMonth(month);
        if (!dayIsValid(month, day)) {
            throw new DukeDateFormatException(ERROR_DAY_MONTH_MISMATCH);
        }
        this.day = day;
        this.setTime(hour, minute);
    }

    private String getMonth(int monthInt) throws DukeDateFormatException {
        switch (monthInt) {
        case 1:
            return "January";
        case 2:
            return "February";
        case 3:
            return "March";
        case 4:
            return "April";
        case 5:
            return "May";
        case 6:
            return "June";
        case 7:
            return "July";
        case 8:
            return "August";
        case 9:
            return "September";
        case 10:
            return "October";
        case 11:
            return "November";
        case 12:
            return "December";
        default:
            throw new DukeDateFormatException(ERROR_ILLEGAL_MONTH);
        }
    }

    private boolean dayIsValid(int month, int day) throws DukeDateFormatException {
        if (day < 1) {
            throw new DukeDateFormatException(ERROR_ILLEGAL_DAY);
        }
        switch (month) {
        case 2:
            return day <= 29;
        case 1:
        case 3:
        case 5:
        case 7:
        case 8:
        case 10:
        case 12:
            return day <= 31;
        case 4:
        case 6:
        case 9:
        case 11:
            return day <= 30;
        default:
            throw new DukeDateFormatException(ERROR_ILLEGAL_MONTH);
        }
    }

    private void setTime(int hour, int minute) throws DukeDateFormatException {
        if (hour > 23 || hour < 0 || minute > 59 || minute < 0) {
            throw new DukeDateFormatException(ERROR_ILLEGAL_TIME);
        }
        if (hour >= 12) {
            this.amPmMarker = "PM";
            this.hour = hour - (hour / 13) * 12;
        } else {
            this.amPmMarker = "AM";
            this.hour = hour;
        }
        this.minute = minute;
    }

    String format() {
        DecimalFormat df = new DecimalFormat("00");
        return String.format("%d %s, %d, %d:%s %s",
                             this.day,
                             this.month,
                             this.year,
                             this.hour,
                             df.format(this.minute),
                             this.amPmMarker);
    }

    @Override
    public String toString() {
        return this.format();
    }

}
