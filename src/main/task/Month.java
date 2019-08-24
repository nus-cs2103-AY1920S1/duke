package main.task;

public class Month {
    protected int monthNumber;

    public Month(int monthNumber) {
        this.monthNumber = monthNumber;
    }

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
