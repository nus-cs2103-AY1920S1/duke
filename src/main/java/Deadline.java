import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String dateTime) {
        super(description);
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            this.by = dateFormat.parse(dateTime).toString();
        } catch (ParseException pe) {
            this.by = dateTime;
            System.out.println("    Next time, please specify the deadline in this format: dd/mm/yyy hh:mm");
            System.out.println("    example: 01/12/2019 14:30");
            System.out.println();
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
