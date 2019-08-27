import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Deadline extends Task {

    Date by;

    public Deadline(String description, String deadLine) throws DukeException {
        super(description);
        try {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HHmm");
            this.by = dateFormat.parse(deadLine);
            System.out.println(this.by);
        } catch (Exception e){
            throw new DukeException("Wrong date and time format");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}