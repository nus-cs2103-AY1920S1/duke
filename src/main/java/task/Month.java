package task;

public class Month {
    protected int monthNumber;

    /**
     * Constructor for Month objects.
     * @param monthNumber int month number.
     */
    public Month(int monthNumber) {
        this.monthNumber = monthNumber;
    }

    /**
     * Returns the corresponding month name from the given month number.
     * @return String month name.
     */
    public String toString() {
        if (this.monthNumber == 1) {
            return "January";
        } else if (this.monthNumber == 2) {
            return "February";
        } else if (this.monthNumber == 3) {
            return "March";
        } else if (this.monthNumber == 4) {
            return "April";
        } else if (this.monthNumber == 5) {
            return "May";
        } else if (this.monthNumber == 6) {
            return "June";
        } else if (this.monthNumber == 7) {
            return "July";
        } else if (this.monthNumber == 8) {
            return "August";
        } else if (this.monthNumber == 9) {
            return "September";
        } else if (this.monthNumber == 10) {
            return "October";
        } else if (this.monthNumber == 11) {
            return "November";
        } else if (this.monthNumber == 12) {
            return "December";
        } else {
            return null;
        }
    }
}
