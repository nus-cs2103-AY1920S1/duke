package task;

public class Date {
    protected int year;
    protected Month month;
    protected int day;
    protected String origin;

    /**
     * Constructor for Date object.
     * @param year int. The year.
     * @param mth int, The month.
     * @param day int, The day.
     */
    public Date(int year, int mth, int day) {
        this.year = year;
        this.day = day;
        this.month = new Month(mth);
        this.origin = day + "/" + mth + "/" + year;
    }

    /**
     * Alternative constructor for Date object.
     * @param date String input must have the format (dd/mm/yyyy);
     */
    public Date(String date) {
        String[] dateComponents = date.split("/");
        this.day = Integer.parseInt(dateComponents[0]);
        this.month = new Month(Integer.parseInt(dateComponents[1]));
        this.year = Integer.parseInt(dateComponents[2]);
        this.origin = date;
    }

    /**
     * Returns the String that corresponds to the date object. (Example:
     * 18/08/2019 -> 18th August 2019).
     * @return String.
     */
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

    /**
     * String returns the original date format (dd/mm/yyyy).
     * @return String.
     */
    public String origin() {
        return this.origin;
    }
}
