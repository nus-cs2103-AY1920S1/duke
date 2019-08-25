import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

class Deadline extends Task {

    protected String by;
    protected Date dateTime;
    // List of acceptable date formats (for this case, just choosing two common patterns)
    protected List<String> dateFormats = Arrays.asList("dd/MM/yyyy HHmm", "dd-MM-yyyy HHmm");

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        for (String pattern : dateFormats) {
            SimpleDateFormat dateTime = new SimpleDateFormat(pattern);
            try {
                this.dateTime = dateTime.parse(by);
            } catch (ParseException e) {}
        }
    }

    @Override
    public String toString() {
        if (dateTime != null)
            return "[D]" + super.toString() + " (by: " + dateTime + ")";
        else
            return "[D]" + super.toString() + " (by: " + by + ")";
    }

    public String toSave() { return "D | " + (isDone ? "1" : "0") + " | " + this.description + " | " + this.by; }
}
