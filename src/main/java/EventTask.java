import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EventTask extends Task {

    protected Date at;

    public EventTask(String description, String at) throws DukeInvalidDateException {
        super(description);
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HHmm");
        try {
            this.at = format.parse(at);
        } catch (ParseException e) {
            throw new DukeInvalidDateException(at);
        }
    }

    @Override
    public String toFileString() {
        return "E\t" + (this.isDone ? "1\t" : "0\t") + this.description + "\t" + dateFormat.format(this.at) + "\n";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}