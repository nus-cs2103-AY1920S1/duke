import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Deadline extends Task {

    Date by;
    String deadLine;

    public Deadline(String description, String deadLine){
        super(description);
        try {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HHmm");
            this.by = dateFormat.parse(deadLine);
            this.deadLine = deadLine;
        } catch (Exception e){
            this.deadLine = deadLine;
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadLine + ")";
    }

    @Override
    public String stringForAppend() {
        return "D | " + super.getStatusIcon() + " | " + description + " | " + deadLine;
    }
}