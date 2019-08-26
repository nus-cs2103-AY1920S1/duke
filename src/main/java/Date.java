public class Date {
    protected int day;
    protected int month;
    protected int year;
    protected String dayOutput;
    protected String monthOutput;
    protected String yearOutput;
    protected boolean validFormat = true;
    public Date(int day, int month, int year) throws DukeException {
        String dayString = String.valueOf(day);
        String monthString = String.valueOf(month);
        String yearString = String.valueOf(year);
        if (dayString.length() > 2 || monthString.length() > 2 || yearString.length() != 4) {
            validFormat = false;
        }
        this.day = day;
        this.month = month;
        this.year = year;
        switch (day) {
            case 1:
                dayOutput = "1st";
                break;
            case 2:
                dayOutput = "2nd";
                break;
            case 3 :
                dayOutput = "3rd";
                break;
            default :
                dayOutput = dayString + "th";
        }
        switch (month) {
            case 1 :
                monthOutput = "January";
                break;
            case 2 :
                monthOutput = "February";
                break;
            case 3 :
                monthOutput = "March";
                break;
            case 4 :
                monthOutput = "April";
                break;
            case 5 :
                monthOutput = "May";
                break;
            case 6 :
                monthOutput = "June";
                break;
            case 7 :
                monthOutput = "July";
                break;
            case 8 :
                monthOutput = "August";
                break;
            case 9 :
                monthOutput = "September";
                break;
            case 10 :
                monthOutput = "October";
                break;
            case 11 :
                monthOutput = "November";
                break;
            case 12 :
                monthOutput = "December";
                break;
            default :
                throw new DukeException("     Invalid month entered!");
        }
        yearOutput = yearString;
        if (day < 0) {
            throw new DukeException("     Negative date does not exist!");
        }
        if (monthOutput.equals("February")) {
            if (day > 28) {
                throw new DukeException("     February only has 28 days!");
            }
        } else if (monthOutput.equals("April") || monthOutput.equals("June") || monthOutput.equals("September") ||
                   monthOutput.equals("November")) {
            if (day > 30) {
                throw new DukeException("     That month only has 30 days!");
            }
        } else {
            if (day > 31) {
                throw new DukeException("     That month only has 31 days!");
            }
        }
    }

    public boolean isValid() {
        return validFormat;
    }
    @Override
    public String toString() {
        return this.dayOutput + " of " + monthOutput + " " + yearOutput;
    }
}
