import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Event extends Task {

    private String time;

    public Event(String taskName, boolean done, String time) {
        super(taskName, done);
        this.time = time;
    }

    public String toString() {
        if (done) {
            return "[E][✓]" + taskName + "(at:" + time + ")";
        } else {
            return "[E][✗]" + taskName + "(at:" + time + ")";
        }
    }


    public Date convertDateTime() {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date converted;
        try {
            converted = formatter.parse(time);
        } catch  (ParseException e) {
            return null;
        }
        return converted;
    }

    public String storageFormat() {
        if (done) {
            return "E/✓/" + taskName + "/" + time;
        } else {
            return "E/✗/" + taskName + "/" + time;
        }
    }
}
