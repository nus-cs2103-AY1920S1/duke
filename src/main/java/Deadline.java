import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

public class Deadline extends Task{
    protected String timeDesc;
    protected Date date;

    public Deadline(String desc, String timeDesc) throws DukeException {
        super(desc);
        this.timeDesc = timeDesc;

        try {
            SimpleDateFormat deadlineFormatter = new SimpleDateFormat("dd/MM/yyyy hhmm");
            this.date = deadlineFormatter.parse(timeDesc);
        } catch (ParseException e) {
            throw new DukeException("Please enter deadline in format dd/mm/yyyy hhmm");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date + ")";
    }

    public String toFileFormat() {
        if(isDone) {
            String format = "D | [✓] | " + taskDesc + " | " + timeDesc + "\n";
            return format;
        } else {
            String format = "D | [✗] | " + taskDesc + " | " + timeDesc + "\n";
            return format;
        }
    }

    public Date getDate() {
        return date;
    }
}
