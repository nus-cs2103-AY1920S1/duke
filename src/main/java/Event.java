import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

class Event extends Task {

    protected String at;
    protected Date dateTime;
    // List of acceptable date formats (for this case, just choosing two common patterns)
    protected List<String> dateFormats = Arrays.asList("dd/MM/yyyy HHmm", "dd-MM-yyyy HHmm");

    public Event(String description, String at) {
        super(description);
        this.at = at;
        for (String pattern: dateFormats) {
            SimpleDateFormat dateTime = new SimpleDateFormat(pattern);
            try {
                this.dateTime = dateTime.parse(at);
            } catch (ParseException e) {}
        }
    }

    @Override
    public String toString() {
        if (dateTime != null)
            return "[E]" + super.toString() + " (at: " + dateTime + ")";
        else
            return "[E]" + super.toString() + " (at: " + at + ")";
    }

    public String toSave() { return "E | " + (isDone ? "1" : "0") + " | " + this.description + " | " + this.at; }
}
