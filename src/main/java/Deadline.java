import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Deadline extends Task {
    protected Date by;

    public Deadline(String taskName, String by) throws ParseException {
        super(taskName);
        this.prefix = "[D]";
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HHmm");
        this.by = formatter.parse(by);
        this.details = "(by: " + this.by + ")";
    }



    @Override
    public String toString() {
        char symbol = this.isCompleted ? '✓' : '✗';
        return prefix + "[" + symbol + "] " + taskName + " " + details;
    }
}
