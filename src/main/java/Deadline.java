import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Deadline extends Task {
    protected Date byDate;

    public Deadline(String taskName, String by) throws ParseException {
        super(taskName);
        this.taskType = TypeOfTask.DEADLINE;

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HHmm");
        this.byDate = formatter.parse(by);
        this.details = "(by: " + by + ")";
    }

    public Deadline(String taskName, String by, boolean isCompleted) throws ParseException {
        super(taskName, isCompleted);
        this.taskType = TypeOfTask.DEADLINE;

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HHmm");
        this.byDate = formatter.parse(by);
        this.details = "(by: " + by;
    }



}
