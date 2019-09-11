package duke;
public class Time {
    int date;
    int month;
    int year;
    int time;
    public Time(String s) {
        String[] sa = s.split("-");
        this.date = Integer.parseInt(sa[0]);
        this.month = Integer.parseInt(sa[1]);
        this.year = Integer.parseInt(sa[2]);
        this.time = Integer.parseInt(sa[3]);
    }
}
