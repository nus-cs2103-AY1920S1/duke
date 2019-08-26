import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

public class Events extends Task{
    protected String timeDesc;
    protected Date date;

    public Events(String desc, String timeDesc) throws DukeException {
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
        return "[E]" + super.toString() + "(at: " + date + ")";
    }

    public String toFileFormat() {
        if (isDone) {
            String format = "E | [✓] | " + taskDesc + " | " + timeDesc + "\n";
            return format;
        } else {
            String format = "E | [✗] | " + taskDesc + " | " + timeDesc + "\n";
            return format;
        }
    }

    public Date getDate() {
        return date;

    }
}
