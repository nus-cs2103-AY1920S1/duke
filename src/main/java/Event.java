import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Event extends Task {
    Date date1;
    Date date2;

    public Event(String taskName, String at) throws ParseException {
        super(taskName);
        this.taskType = TypeOfTask.EVENT;
        String[] parts = at.split(" - ");
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HHmm");
        this.date1 = formatter.parse(parts[0]);
        this.date2 = formatter.parse(parts[1]);
        this.details = "(at: " + at + ")";
    }

    public Event(String taskName, String at, boolean isCompleted) throws ParseException {
        super(taskName, isCompleted);
        this.taskType = TypeOfTask.EVENT;
        String[] parts = at.split(" - ");
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HHmm");
        this.date1 = formatter.parse(parts[0]);
        this.date2 = formatter.parse(parts[1]);
        this.details = "(at: " + at;

    }


}
