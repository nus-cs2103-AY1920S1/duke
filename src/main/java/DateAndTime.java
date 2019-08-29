public class DateAndTime {
    protected int date;
    protected int month;
    protected int year;
    protected int timeNumber;
    protected String timeDay;

    protected String[] monthMappings = new String[]{"", "January", "February", "March", "April", "May", "June",
                                                    "July", "August", "September", "October", "November", "December"};

    public DateAndTime(String dateAndTime) {
        String[] split = dateAndTime.split(" ");
        String date[] = split[0].split("/");
        int time = Integer.parseInt(split[1]) / 100;

        this.date = Integer.parseInt(date[0]);
        this.month = Integer.parseInt(date[1]);
        this.year = Integer.parseInt(date[2]);

        if (time < 12) {
            this.timeNumber = (time == 0) ? 12 : time;
            this.timeDay = "am";
        } else {
            this.timeNumber = time - 12;
            this.timeDay = "pm";
        }
    }

    @Override
    public String toString() {
        String extra = (date == 1) ? "st" : (date == 2) ? "nd" : (date == 3) ? "rd" : "th";
        return this.date + extra + " of " + monthMappings[this.month] + " " + this.year + ", " + this.timeNumber + this.timeDay;
    }
}
