import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Deadline extends Task {

    private String deadline;

    public Deadline(String taskName, boolean done, String deadline) {
        super(taskName, done);
        this.deadline  = deadline;
    }

    public String toString() {
        if (done) {
            return "[D][✓]" + taskName + "(by:" + deadline + ")";
        } else {
            return "[D][✗]" + taskName + "(by:" + deadline + ")";
        }
    }


    public Date convertDateTime() {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date converted;
        try {
            converted = formatter.parse(deadline);
        } catch  (ParseException e) {
            return null;
        }
        return converted;
    }

    public String storageFormat() {
        if (done) {
            return "D/✓/" + taskName + "/" + deadline;
        } else {
            return "D/✗/" + taskName + "/" + deadline;
        }
    }
}
