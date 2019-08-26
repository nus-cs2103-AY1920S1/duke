import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

public class Deadline extends Task{
    protected String timeDesc;
    protected Date date;

    public Deadline(String desc, String timeDesc) {
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
        return "[D]" + super.toString() + "(by: " + timeDesc + ")";
    }

    public Date getDate() {
        return date;
    }
}
