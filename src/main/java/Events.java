public class Events extends Task {
    private String at;
    private String event[];
    private String[] datetime;

    protected Events(String description, String at) {
        super(description);
        this.at = at;
        event = at.split(" ", 2);
        this.datetime = event[1].split(" ");
    }

    private String getDate() {
        String[] splitDates = this.datetime[0].split("/");
        String day = splitDates[0];
        String month = splitDates[1];
        String year = splitDates[2];
        String editedDay;
        String editedMonth;

        if (day.equals("1") || day.equals("01")) {
            editedDay = "1st";
        } else if (day.equals("2") || day.equals("02")) {
            editedDay = "2nd";
        } else if (day.equals("3") || day.equals("03")) {
            editedDay = "3rd";
        } else if (day.equals("21")) {
            editedDay = "21st";
        } else if (day.equals("22")) {
            editedDay = "22nd";
        } else if (day.equals("23")) {
            editedDay = "23rd";
        } else if (day.equals("31")) {
            editedDay = "31st";
        } else {
            editedDay = day + "th";
        }

        switch (month) {
            case "1":
            case "01":
                editedMonth = "January";
                break;

            case "2":
            case "02":
                editedMonth = "Febuary";
                break;

            case "3":
            case "03":
                editedMonth = "March";
                break;

            case "4":
            case "04":
                editedMonth = "April";
                break;

            case "5":
            case "05":
                editedMonth = "May";
                break;

            case "6":
            case "06":
                editedMonth = "June";
                break;

            case "7":
            case "07":
                editedMonth = "July";
                break;

            case "8":
            case "08":
                editedMonth = "August";
                break;

            case "9":
            case "09":
                editedMonth = "September";
                break;

            case "10":
                editedMonth = "October";
                break;

            case "11":
                editedMonth = "November";
                break;

            case "12":
                editedMonth = "December";
                break;

            default:
                editedMonth = "Invalid";
                break;

        }

        return editedDay + " of " + editedMonth + " " + year;
    }

    private String getTime() {
        String time = this.datetime[1];
        String timeStr;
        int timeInt = Integer.parseInt(time);
        int hours = timeInt/100;
        int minutes = timeInt % 100;
        if (hours >= 12) {
            timeStr = Integer.toString(hours - 12) + "."
                    + String.format("%02d", minutes) + "pm";
        } else if (hours == 0) {
            timeStr = "12."
                    + String.format("%02d", minutes) + "am";
        }
        else {
            timeStr = Integer.toString(hours) + "."
                    + String.format("%02d", minutes) + "am";
        }

        return timeStr;
    }

    @Override
    public String formatString() {
        return "E-" + super.checkStatus() + "-" + super.getDescription().trim() + "-" + this.event[1];
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() +
                "(at: " + this.getDate() + ", " + this.getTime() + ")";
    }
}
