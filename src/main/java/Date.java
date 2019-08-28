public class Date {
    int day;
    Month month;
    int monthNum;
    int year;

    public enum Month {
        JANUARY, FEBRUARY, MARCH, APRIL, MAY, JUNE, JULY, AUGUST, SEPTEMBER, OCTOBER, NOVEMBER, DECEMBER;
    }

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

    public static Date processDate(String dateString) {
        String[] date = dateString.split("/");
        return new Date(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]));
    }

    public String toString() {
        return String.format("%02d/%02d/%d", day, monthNum, year);
    }

}
