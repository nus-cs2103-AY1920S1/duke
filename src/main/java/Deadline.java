import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class Deadline extends Task {
    protected Date by;

    public Deadline(String description, String by) {
        super(description);
        try {
            this.by = dateFormatter(by);
        } catch (DukeException e) {
            System.err.println("Something went wrong: " + e);
        }
    }

    private Date dateFormatter(String date) throws DukeException {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HHmm");
            Date parseDate = formatter.parse(date);
            System.out.println(parseDate.toString());
            return parseDate;
        } catch (ParseException e) {
            throw new DukeException("Something went wrong: " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        SimpleDateFormat properFormat = new SimpleDateFormat("dd 'of' MMMM yyyy, hh:mm a");
        return "[D]" + "[" + super.getStatusIcon() + "] " + super.toString() + " (by: " + properFormat.format(this.by)
                + ")";
    }
}
