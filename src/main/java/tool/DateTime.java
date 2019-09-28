package tool;

public class DateTime {
    protected int day;
    protected String month;
    protected int year;
    protected int time;

    String[] months = {"January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"};


    /**
     * Constructor for DateTime object for Deadline and Event tasks.
     * @param raw: Raw user input string
     */
    public DateTime(String raw) {
        String[] rawArr = raw.split("/");
        if (rawArr.length < 2) {
            String[] rawTwo = raw.split(" ");
            this.day = Integer.parseInt(rawTwo[0]);
            this.month = rawTwo[1];
            this.year = Integer.parseInt(rawTwo[2]);
            this.time = Integer.parseInt(rawTwo[3].split("hrs")[0]);
        } else {
            this.day = Integer.parseInt(rawArr[0]);
            this.month = months[Integer.parseInt(rawArr[1]) - 1];
            String[] splitt = rawArr[2].split(" ");
            this.year = Integer.parseInt(splitt[0]);
            this.time = Integer.parseInt(splitt[1]);
        }
    }

    @Override
    public String toString() {
        return this.day + " " + this.month + " " + this.year + " " + this.time + "hrs";

    }

}
