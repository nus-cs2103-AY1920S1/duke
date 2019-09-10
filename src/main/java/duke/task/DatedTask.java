package duke.task;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class DatedTask extends Task {
    private String dateTime;

    DatedTask(String icon, String description, String dateTime) {
        super(icon, description);
        this.dateTime = dateTimeConvert(dateTime.trim());
        assert dateTime.length() != 0;
    }

    private String dateTimeConvert(String s) {
        try {
            Date date = new SimpleDateFormat("dd/MM/yyyy HHmm").parse(s);
            DateFormat df = new SimpleDateFormat("d 'of' MMMMMMMMM yyyy, h:mm a");
            return df.format(date);
        } catch (ParseException e) {
            return s;
        }
    }

    String getDateTime() {
        return this.dateTime;
    }

    @Override
    public String toSaveFormat() {
        return super.toSaveFormat().concat(String.format("| %s", this.getDateTime()));
    }
}
