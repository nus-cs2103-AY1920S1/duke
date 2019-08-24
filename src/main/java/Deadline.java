import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Deadline extends Task {
    protected SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HHmm");
    protected DateFormat printFormat = new SimpleDateFormat("dd/MM/yyyy HH.mm aa");
    protected Date dateTime;

    public Deadline(String description, Date dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + printFormat.format(dateTime) + ")";
    }

    @Override
    public String writeToFile() {
        return ("D " + super.writeToFile() + " | " + dateFormat.format(dateTime));
    }

}

