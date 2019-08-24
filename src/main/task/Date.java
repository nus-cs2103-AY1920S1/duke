package main.task;

public class Date {
    protected int year;
    protected Month month;
    protected int day;

    public Date(int year, int mth, int day) {
        this.year = year;
        this.day = day;
        this.month = new Month(mth);
    }

    public Date(String date) {
        String[] dateComponents = date.split("/");
        this.day = Integer.parseInt(dateComponents[0]);
        this.month = new Month(Integer.parseInt(dateComponents[1]));
        this.year = Integer.parseInt(dateComponents[2]);
    }

    public String toString() {
        if (day % 10 == 1) {
            return this.day + "st " + this.month.toString() + " " + this.year;
        } else if (day % 10 == 2) {
            return this.day + "nd " + this.month.toString() + " " + this.year;
        } else if (day % 10 == 3) {
            return this.day + "rd " + this.month.toString() + " " + this.year;
        } else {
            return this.day + "th " + this.month.toString() + " " + this.year;
        }
    }
}
