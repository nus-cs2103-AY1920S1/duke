import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class Deadline extends Task {
    private Date by;

    public Deadline(String description, String by) {
        super(description);
        try {
            this.by = new SimpleDateFormat("dd/MM/yyyy HHmm").parse(by);
        } catch (ParseException ex) {
            System.out.print(ex);
        }
    }

    public String encode() {
        return "deadline," + super.description + "," + super.isDone + "," + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + by + ")";
    }

}
