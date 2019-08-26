import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class Event extends Task {
    protected Date at;

    public Event(String description, String at) {
        super(description);
        try {
            this.at = dateFormatter(at);
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
        return "[E]" + "[" + super.getStatusIcon() + "] " + super.toString() + " (at: " + properFormat.format(this.at)
                + ")";
    }
}
