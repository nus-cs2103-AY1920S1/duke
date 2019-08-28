import java.time.LocalDateTime;

public class Event extends Task {
    private LocalDateTime dateTime;

    public Event(String description, String date) {
        super(description);
        String[] dateSplit = date.split("\\s");
        String[] d = dateSplit[0].split("/");
        int day = Integer.parseInt(d[0]);
        int month = Integer.parseInt(d[1]);
        int year = Integer.parseInt(d[2]);
        int hour = Integer.parseInt(dateSplit[1].substring(0, 2));
        int min = Integer.parseInt(dateSplit[1].substring(2));
        dateTime = LocalDateTime.of(year, month, day, hour, min);
    }

    private String getDate() {
        return dateTime.getDayOfMonth() + "/" + dateTime.getMonthValue() + "/" + dateTime.getYear() +
                " " + String.format("%02d", dateTime.getHour()) + String.format("%02d", dateTime.getMinute());
    }

    @Override
    public String toString() {
        return "[E][" + getStatusIcon() + "] " + description + "(at: " + getDate() + ")";
    }
}
