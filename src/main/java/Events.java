public class Events extends Task {
    protected String at;
    protected String event[];
    protected String[] datetime;

    public Events(String description, String at) {
        super(description);
        this.at = at;
        event = at.split(" ", 2);
        this.datetime = event[1].split(" ");
    }

    public String getdate() {
        String splitdates[] = this.datetime[0].split("/");
        String day = splitdates[0];
        String month = splitdates[1];
        String year = splitdates[2];
        String editedday;
        String editedmonth;

        if (day.equals("1") || day.equals("01")) {
            editedday = "1st";
        } else if (day.equals("2") || day.equals("02")) {
            editedday = "2nd";
        } else if (day.equals("3") || day.equals("03")) {
            editedday = "3rd";
        } else if (day.equals("21")) {
            editedday = "21st";
        } else if (day.equals("22")) {
            editedday = "22nd";
        } else if (day.equals("23")) {
            editedday = "23rd";
        } else if (day.equals("31")) {
            editedday = "31st";
        } else {
            editedday = day + "th";
        }

        switch (month) {
            case "1":
            case "01":
                editedmonth = "January";
                break;

            case "2":
            case "02":
                editedmonth = "Febuary";
                break;

            case "3":
            case "03":
                editedmonth = "March";
                break;

            case "4":
            case "04":
                editedmonth = "April";
                break;

            case "5":
            case "05":
                editedmonth = "May";
                break;

            case "6":
            case "06":
                editedmonth = "June";
                break;

            case "7":
            case "07":
                editedmonth = "July";
                break;

            case "8":
            case "08":
                editedmonth = "August";
                break;

            case "9":
            case "09":
                editedmonth = "September";
                break;

            case "10":
                editedmonth = "October";
                break;

            case "11":
                editedmonth = "November";
                break;

            case "12":
                editedmonth = "December";
                break;

            default:
                editedmonth = "Invalid";
                break;

        }

        return editedday + " of " + editedmonth + " " + year;
    }

    public String gettime() {
        String time = this.datetime[1];
        String timestr;
        int timeint = Integer.parseInt(time);
        int hours = timeint/100;
        int minutes = timeint % 100;
        if (hours >= 12) {
            timestr = Integer.toString(hours - 12) + "."
                    + String.format("%02d", minutes) + "pm";
        } else if (hours == 0) {
            timestr = "12."
                    + String.format("%02d", minutes) + "am";
        }
        else {
            timestr = Integer.toString(hours) + "."
                    + String.format("%02d", minutes) + "am";
        }

        return timestr;
    }

    @Override
    public String formatString() {
        return "E-" + super.checkStatus() + "-" + super.getDescription().trim() + "-" + this.event[1];
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() +
                "(by: " + this.getdate() + ", " + this.gettime() + ")";
    }
}
