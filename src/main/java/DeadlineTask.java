import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DeadlineTask extends Task {

    protected Date by;

    public DeadlineTask(String description, String by) throws ParseException {
        super(description);
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HHmm");
        this.by = format.parse(by);
    }

    @Override
    public String toFileString() {
        return "D\t" + (this.isDone ? "1\t" : "0\t") + this.description + "\t" + dateFormat.format(this.by) + "\n";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}