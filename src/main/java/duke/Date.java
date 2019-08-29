package duke;

public class Date {
    int day;
    Month month;
    int monthNum;
    int year;

    /**
     * Documents months of a year.
     */
    public enum Month {
        JANUARY, FEBRUARY, MARCH, APRIL, MAY, JUNE, JULY, AUGUST, SEPTEMBER, OCTOBER, NOVEMBER, DECEMBER;
    }

    /**
     * Creates Date object containing integers for dates and years and enum for month.
     * Converts month in integer form to enum.
     * @param day day of the given date in integer form
     * @param monthNum month of given date in integer form
     * @param year year of the given date in integer form
     */
    public Date(int day, int monthNum, int year) {
        this.day = day;
        this.year = year;
        switch (monthNum) {
            case 1:
                this.month = Month.JANUARY;
                break;
            case 2:
                this.month = Month.FEBRUARY;
                break;
            case 3:
                this.month = Month.MARCH;
                break;
            case 4:
                this.month = Month.APRIL;
                break;
            case 5:
                this.month = Month.MAY;
                break;
            case 6:
                this.month = Month.JUNE;
                break;
            case 7:
                this.month = Month.JULY;
                break;
            case 8:
                this.month = Month.AUGUST;
                break;
            case 9:
                this.month = Month.SEPTEMBER;
                break;
            case 10:
                this.month = Month.OCTOBER;
                break;
            case 11:
                this.month = Month.NOVEMBER;
                break;
            case 12:
                this.month = Month.DECEMBER;
                break;
        }
    }

    /**
     * Processes a given String and converts String to a date object.
     * @param dateString String in form DD/MM/YYYY.
     * @return Date object.
     */
    public static Date processDate(String dateString) {
        String[] date = dateString.split("/");
        return new Date(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]));
    }

    /**
     * Formats Date object.
     * @return A string in the form DD/MM/YYYY.
     */
    public String toString() {
        return String.format("%02d/%02d/%d", day, monthNum, year);
    }

}
