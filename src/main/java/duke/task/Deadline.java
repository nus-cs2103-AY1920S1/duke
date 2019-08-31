package duke.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Deadline extends Task {
    protected String dateTime;

    public Deadline(String name, String dateTime, boolean isDone) {
        super(name, isDone);
        this.dateTime = formatDateTime(dateTime);
    }

    private String formatDateTime(String dateTime) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HHmm");
        SimpleDateFormat newFormat = new SimpleDateFormat("d 'of' MMMMMMMMM yyyy, h:mm a");
        try {
            Date date = format.parse(dateTime);
            System.out.println(date);
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
        return String.format("[D] | %s | by: %s", super.toString(), this.dateTime);
    }
}
