import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;

public class Deadline extends Task {
    protected Date time;

    public Deadline(String description, String time) throws ParseException {
        super(description);
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HHmm");
        this.time = format.parse(time);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + time + ")";
    }

}
