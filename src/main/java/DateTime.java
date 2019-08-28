import java.util.Arrays;

public class DateTime {
    protected int day;
    protected int month;
    protected int year;
    protected int time;


    public DateTime(String raw) {
        String[] rawArr = raw.split("/");
        this.day = Integer.parseInt(rawArr[0]);
        this.month = Integer.parseInt(rawArr[1]);
        String[] splitt = rawArr[2].split(" ");
        this.year = Integer.parseInt(splitt[0]);
        this.time = Integer.parseInt(splitt[1]);
    }

    String[] months = {"January", "February", "March", "April", "May", "June",
                        "July", "August", "September", "October", "November", "December"};

    @Override
    public String toString() {
        String day;
        if(this.day == 1) {
            day = "1st";
        } else if (this.day == 2) {
            day = "2nd";
        } else if (this.day == 3) {
            day = "3rd";
        } else if (this.day % 10 == 1) {
            day = this.day + "st";
        } else if (this.day % 10 == 2) {
            day = this.day + "nd";
        } else if (this.day % 10 == 3) {
            day = this.day + "rd";
        } else {
            day = this.day + "th";
        }

        return day + " of " + months[this.month - 1] + ", " + this.time + "hrs";

    }

}
