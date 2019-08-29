import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Deadline extends Task {

    protected  String by;
    protected Date date;

    public Deadline(String description, String by) throws DukeDeadlineException {
        super(description);
        this.by = by;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HHmm");
        try {
            this.date = formatter.parse(by);
        } catch (ParseException pe){
            throw new DukeDeadlineException("The date of a Deadline must be in the format \"dd/MM/yyyy HHmm\"");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }

    @Override
    public String saveString() {
        return "D" + super.saveString() + " | " + this.by + "\n";
    }
}
