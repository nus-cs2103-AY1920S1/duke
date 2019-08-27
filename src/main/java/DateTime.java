public class DateTime {

    private int year;
    private int month;
    private int day;
    private int time;

    public DateTime(int year, int month, int day, int time) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.time = time;
    }

    public static DateTime create(String dateTimeString) {
        String[] currArray = dateTimeString.split("\\s+", 2);
        String dateString = currArray[0];
        String timeString = currArray[1];
        String[] dateArray = dateString.split("/", 3);
        return new DateTime(Integer.parseInt(dateArray[2]), Integer.parseInt(dateArray[1]), Integer.parseInt(dateArray[0]), Integer.parseInt(timeString));
    }

    private String getDate() {
        if (day % 10 == 1) {
            return day + "st";
        } else if (day % 10 == 2) {
            return day + "nd";
        } else if (day % 10 == 3) {
            return day + "rd";
        } else {
            return day + "th";
        }
    }

    private String getMonth() {
        String monthString = "";
        switch (month) {
        case 1:
            monthString = "Janurary";
            break;
        case 2:
            monthString = "February";
            break;
        case 3:
            monthString = "March";
            break;
        case 4:
            monthString = "April";
            break;
        case 5:
            monthString = "May";
            break;
        case 6:
            monthString = "June";
            break;
        case 7:
            monthString = "July";
            break;
        case 8:
            monthString = "August";
            break;
        case 9:
            monthString = "September";
            break;
        case 10:
            monthString = "October";
            break;
        case 11:
            monthString = "November";
            break;
        case 12:
            monthString = "December";
            break;
        }
        return monthString;
    }

    private String getTime() {
        int hour;
        int minute;
        String timeSuffix;
        if (time <= 59) {
            minute = time % 100;
            hour = 12;
            timeSuffix = "am";
        } else if (time <= 1159) {
            minute = time % 100;
            hour = (time - minute) / 100;
            timeSuffix = "am";
        } else if (time <= 1259) {
            minute = time % 100;
            hour = 12;
            timeSuffix = "pm";
        } else {
            minute = time % 100;
            hour = (time - minute) / 100 - 12;
            timeSuffix = "pm";
        }
        return hour + ":" + String.format("%02d", minute) + timeSuffix;
    }

    @Override
    public String toString() {
        return getDate() + " of " + getMonth() + " " +  year + ", " + getTime();
    }
}
