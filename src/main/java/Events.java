import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

public class Events extends Task{
    protected String timeDesc;
    protected Date date;

    public Events(String desc, String timeDesc) {
        super(desc);
        this.timeDesc = timeDesc;

        try {
            SimpleDateFormat deadlineFormatter = new SimpleDateFormat("dd/MM/yyyy hhmm");
            this.date = deadlineFormatter.parse(timeDesc);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + timeDesc + ")";
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
