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
        return months[this.month - 1] + " " + this.day + ", " + this.time + "hrs";

    }

}
