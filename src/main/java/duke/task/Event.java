package duke.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Event extends Task {
    protected String dateTime;

    public Event(String name, String dateTime, boolean isDone) {
        super(name, isDone);
        this.dateTime = formatDateTime(dateTime);
    }

    private String formatDateTime(String dateTime) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HHmm");
        SimpleDateFormat newFormat = new SimpleDateFormat("d 'of' MMMMMMMMM yyyy, h:mm a");
        try {
            Date date = format.parse(dateTime);
            return newFormat.format(date);
        } catch (ParseException e) {
            return dateTime;
        }
    }

    public String getTime() {
        return this.dateTime;
    }

    @Override
    public String toString() {
        return String.format("[E] | %s | at: %s", super.toString(), this.dateTime);
    }
}
