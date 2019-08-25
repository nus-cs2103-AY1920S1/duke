import java.time.DateTimeException;
import java.time.Month;
import java.util.function.DoubleUnaryOperator;

public class Date {
    protected String unprocessedDate;
    protected int day;
    protected Month month;
    protected int year;

    public Date(String unprocessedDate) throws DukeException {
        this.unprocessedDate = unprocessedDate;
        processDate();
    }

    public void processDate() throws DukeException {
        if (unprocessedDate.equals("")) {
            return;
        }
        String[] dateSplit = unprocessedDate.split("/");
        if (dateSplit.length < 3) {
            throw new DukeException("\u2639 OOPS!!! Please specify the date" +
                    " in the format date/month/year e.g. 2/12/2019.");
        }
        int monthNumber = Integer.parseInt(dateSplit[1]);
        if (isValidMonth(monthNumber)) {
            this.month = Month.of(monthNumber);
        } else {
            throw new DukeException("\u2639 OOPS!!! Please input a valid month.");
        }
        this.year = Integer.parseInt(dateSplit[2]);
        int inputDay = Integer.parseInt(dateSplit[0]);
        if (isValidDay(inputDay, monthNumber, year)) {
            this.day = inputDay;
        } else {
            throw new DukeException("\u2639 OOPS!!! Please input a valid day.");
        }
    }

    public boolean isValidMonth(int monthNumber) {
        if (monthNumber >= 1 && monthNumber <= 12) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isValidDay(int day, int monthNumber, int year) {
        switch(monthNumber) {
        case 1:
        case 3:
        case 5:
        case 7:
        case 8:
        case 10:
        case 12:
            if (day >= 1 && day <= 31) {
                return true;
            } else {
                return false;
            }
        case 4:
        case 6:
        case 9:
        case 11:
            if (day >= 1 && day <= 30) {
                return true;
            } else {
                return false;
            }
        case 2:
            if (isLeapYear(year) && day >= 1 && day <= 29) {
                return true;
            } else if (day >= 1 && day <= 28) {
                return true;
            } else {
                return false;
            }
        default:
            return false;
        }
    }

    public boolean isLeapYear(int year) {
        if (year % 400 == 0) {
            return true;
        } else if (year % 100 == 0) {
            return false;
        } else if (year % 4 == 0) {
            return true;
        } else {
            return false;
        }
    }

    public int getDay() {
        return day;
    }

    public Month getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        if (month == null) {
            return "";
        }
        if (day % 10 == 1 && day != 11) {
            return day + "st of " + month + " " + year;
        } else if (day % 10 == 2 && day != 12) {
            return day + "nd of " + month + " " + year;
        } else {
            return day + "th of " + month + " " + year;
        }
    }
    public String getUnprocessedDate() {
        return unprocessedDate;
    }

}
