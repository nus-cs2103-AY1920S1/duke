public class DateTime {
    private String date;
    private String time;

    public DateTime(String dateTime) {
        String[] splitDateAndTime = dateTime.split(" ");
        String[] splitDate = splitDateAndTime[0].split("/");
        String day = splitDate[0];
        String month = splitDate[1];
        String year = splitDate[2];

        if (day.equals("1") || day.equals("21") || day.equals("31")) {
            day = day + "st";
        } else if (day.equals("2") || day.equals("22")) {
            day = day + "nd";
        } else if (day.equals("3") || day.equals("23")) {
            day = day + "rd";
        } else {
            day = day + "th";
        }

        switch (month) {
        case "1":
            month = "January";
            break;
        case "2":
            month = "February";
            break;
        case "3":
            month = "March";
            break;
        case "4":
            month = "April";
            break;
        case "5":
            month = "May";
            break;
        case "6":
            month = "June";
            break;
        case "7":
            month = "July";
            break;
        case "8":
            month = "August";
            break;
        case "9":
            month = "September";
            break;
        case "10":
            month = "October";
            break;
        case "11":
            month = "November";
            break;
        default:
            month = "December";
        }

        this.date = day + " " + month + " " + year;

        if (splitDateAndTime.length == 1) {
            time = null;
        } else {
            String[] timings = splitDateAndTime[1].split("-");
            if (timings.length == 1) {
                String hour = splitDateAndTime[1].substring(0, 2);
                String mins = splitDateAndTime[1].substring(2);
                String period = "";

                if (hour.equals("00")) {
                    hour = "12";
                    period = "am";
                } else if (hour.equals("12")) {
                    period = "pm";
                } else {
                    if (Integer.parseInt(hour) > 12) {
                        period = "pm";
                        hour = (Integer.parseInt(hour) % 12) + "";
                    } else {
                        period = "am";
                    }
                }
                this.time = hour + ":" + mins + period;
            } else if (timings.length == 2) {
                String startHour = timings[0].substring(0, 2);
                String startMins = timings[0].substring(2);
                String endHour = timings[1].substring(0, 2);
                String endMins = timings[1].substring(2);
                String startPeriod = "";
                String endPeriod = "";

                if (startHour.equals("00")) {
                    startHour = "12";
                    startPeriod = "am";
                } else if (startHour.equals("12")) {
                    startPeriod = "pm";
                } else {
                    if (Integer.parseInt(startHour) > 12) {
                        startPeriod = "pm";
                        startHour = (Integer.parseInt(startHour) % 12) + "";
                    } else {
                        startPeriod = "am";
                    }
                }

                if (endHour.equals("00")) {
                    endHour = "12";
                    endPeriod = "am";
                } else if (endHour.equals("12")) {
                    endPeriod = "pm";
                } else {
                    if (Integer.parseInt(endHour) > 12) {
                        endPeriod = "pm";
                        endHour = (Integer.parseInt(endHour) % 12) + "";
                    } else {
                        endPeriod = "am";
                    }
                }
                this.time = startHour + ":" + startMins + startPeriod + " - " + endHour + ":" + endMins + endPeriod;
            }
        }
    }

    @Override
    public String toString() {
        if (time == null) {
            return date;
        } else {
            return date + ", " + time;
        }
    }
}
