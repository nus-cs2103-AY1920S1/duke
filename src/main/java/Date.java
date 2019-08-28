import java.util.Scanner;

public class Date {
    private String day;
    private String month;
    private String year;
    private String time;

    public Date(String info) {
        Scanner sc = new Scanner(info);
        String[] info1 = sc.next().split("[/]");
        this.day = getDate(info1[0]);
        this.month = getMonth(info1[1]);
        this.year = info1[2];
        this.time = getTime(sc.nextLine().trim());
        sc.close();
    }

    private String getDate(String date) {
        if (date.startsWith("1") && date.length() == 2) {
            return date + "th";
        } else if (date.endsWith("1")) {
            return date + "st";
        } else if (date.endsWith("2")) {
            return date + "nd";
        } else if (date.endsWith("3")) {
            return date + "rd";
        } else {
            return date + "th";
        }
    }

    private String getMonth(String month) {
        String[] arrayOfMonths = {"January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"};
        return arrayOfMonths[Integer.parseInt(month) - 1];
    }

    private String getTime(int time) {
        if (time > 12 ) {
            return (time%12) + "pm";
        } else if (time == 0){
            return 12 + "am";
        } else {
            return time + "am";
        }
    }

    private String getTime(String time) {
        String timing = "";
        if (time.startsWith("0")) {
            timing = time.substring(1, 2);
        } else {
            timing = time.substring(0, 2);
        }

        int t = Integer.parseInt(timing);
        if (t > 12) {
            return (t % 12) + "pm";
        } else if (t == 0) {
            return 12 + "am";
        } else {
            return t + "am";
        }
    }
    @Override
    public String toString() {
        return String.format("%s of %s %s, %s", day, month, year, time);
    }
}
