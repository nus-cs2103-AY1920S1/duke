public class DateTime {
    private static final String[] MONTHS_OF_YEAR = {"January", "February", "March", "April", "May",
            "June", "July", "August", "September", "October", "November", "December"};

    private Integer day;
    private Integer month;
    private Integer year;
    private Integer hours;
    private Integer minutes;

    public DateTime(String date) {
        String[] dateComponents = date.split(" ");

        String dayComponent = dateComponents[0];
        String timeComponent = dateComponents[1];

        //Parsing the dayComponent, example: 2/05/2019
        String[] componentsOfDate = dayComponent.split("/");
        this.day = Integer.parseInt(componentsOfDate[0]);
        this.month = Integer.parseInt(componentsOfDate[1]);
        this.year = Integer.parseInt(componentsOfDate[2]);

        //Parsing the timeComponent, example: 1800
        String hoursString = timeComponent.substring(0, 2);
        String minutesString = timeComponent.substring(2);
        this.hours = Integer.parseInt(hoursString);
        this.minutes = Integer.parseInt(minutesString);
    }

    //Adds the "st", "nd", "rd" ordinals based on numerical value of day
    public String appendOrdinal(Integer day) {
        if (day % 10 == 1) {
            return day.toString() + "st";
        } else if (day % 10 == 2) {
            return day.toString() + "nd";
        } else if (day % 10 == 3) {
            return day.toString() + "rd";
        } else {
            return day.toString() + "th";
        }
    }

    //Converts 24H timing to 12H timing
    public String to12HourTime(Integer hours, Integer minutes) {
        String hoursString;
        String minutesString;
        String noonString;

        if (hours <= 12) {
            hoursString = hours.toString();
        } else {
            hoursString = Integer.toString(hours - 12);
        }

        if (minutes == 0) {
            minutesString = "";
        } else {
            minutesString = "." + minutes.toString();
        }

        noonString = hours >= 12 ? "pm" : "am";

        return String.format("%s%s%s", hoursString, minutesString, noonString);
    }
    @Override
    public String toString() {
        return String.format("%s of %s %s, %s",
                appendOrdinal(day),
                MONTHS_OF_YEAR[month - 1],
                year.toString(),
                to12HourTime(hours, minutes));
    }
}
