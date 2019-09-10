import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Deadline extends Task {

    private SimpleDateFormat deadline;
    protected String by;
    private Date date;

    public Deadline(String description, String by) throws ParseException {
        super(description);
        this.by = by;
        this.type = "deadline";

        deadline = new SimpleDateFormat("dd/MM/yyyy HHmm");
        date = deadline.parse(this.by);
    }

    @Override
    public String toString() {
        return "  [D]" + super.toString() + "(by: " + by + ")";
    }
}
