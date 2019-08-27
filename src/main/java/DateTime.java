public class DateTime {

    String dateTime;
    String day;
    String month;
    String year;
    String time;

    public DateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public void processDate(String d) {
        String[] splitDate = d.split("/");
        processDay(splitDate[0]);
        processMonth(splitDate[1]);
        year = splitDate[2];
    }

    public void processDay(String d) {
        int dayNum = Integer.parseInt(d);
        if (dayNum % 10 == 1) {
            day = d + "st";
        } else if (dayNum % 10 == 2) {
            day = d + "nd";
        } else if (dayNum % 10 == 3) {
            day = d + "rd";
        } else {
            day = d + "th";
        }
    }

    public void processMonth(String m) {
        switch (Integer.parseInt(m)) {
        case 1:
            month = "January";
            break;
        case 2:
            month = "February";
            break;
        case 3:
            month = "March";
            break;
        case 4:
            month = "April";
            break;
        case 5:
            month = "May";
            break;
        case 6:
            month = "June";
            break;
        case 7:
            month = "July";
            break;
        case 8:
            month = "August";
            break;
        case 9:
            month = "September";
            break;
        case 10:
            month = "October";
            break;
        case 11:
            month = "November";
            break;
        case 12:
            month = "December";
            break;
        }
    }

    public void processTime(String t) {
        int timeNum = Integer.parseInt(t);
        int minutes = timeNum % 100;
        if (minutes > 0) {
            if (timeNum < 59) {
                int hour = 12;
                time = String.valueOf(hour) + ":" + String.format("%02d", minutes) + "am";
            } else if (timeNum < 1300) {
                // time before 1pm
                int hour = (timeNum - minutes) / 100;
                if (hour < 12) {
                    time = String.valueOf(hour) + ":" + String.format("%02d", minutes) + "am";
                } else {
                    time = String.valueOf(hour) + ":" + String.format("%02d", minutes) + "pm";
                }
            } else {
                // 1pm and after until 2359
                int hour = ((timeNum - minutes) / 100) - 12;
                time = String.valueOf(hour) + ":" + String.format("%02d", minutes) + "pm";
            }
        } else {
            if (timeNum < 59) {
                int hour = 12;
                time = String.valueOf(hour) + "am";
            } else if (timeNum < 1300) {
                // time before 1pm
                int hour = (timeNum - minutes) / 100;
                if (hour < 12) {
                    time = String.valueOf(hour) + "am";
                } else {
                    time = String.valueOf(hour) + "pm";
                }
            } else {
                // 1pm and after until 2359
                int hour = ((timeNum - minutes) / 100) - 12;
                time = String.valueOf(hour) + "pm";
            }
        }
    }
    @Override
    public String toString() {
        String[] splitDateTime = dateTime.split(" ");
        processDate(splitDateTime[0]);
        processTime(splitDateTime[1]);
        String result = day + " of " + month + " " + year + ", " + time;
        return result;
    }
}
