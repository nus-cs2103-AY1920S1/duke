import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Deadline extends Task {
    private Date date;

    Deadline(String description) {
        super(description);
        this.type = "D";
    }

    void parseTime(String time) throws ParseException {
        this.date = new SimpleDateFormat("dd/MM/yyyy hhmm").parse(time);
    }
    @Override
    public String toString() {
        return "[" + this.type + "][" + this.getStatusIcon() + "] " + this.description + this.date;
    }
}
