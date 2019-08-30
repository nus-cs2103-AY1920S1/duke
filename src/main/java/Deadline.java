import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class Deadline extends Task {
    protected Date deadline;


    public Deadline(String description, Date deadline) {
        super(description);
        this.isDone = false;
        this.deadline = deadline;

    }

    public Deadline(String isDone, String description, String deadline){
        super(isDone, description);
        try {
            this.deadline = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy").parse(deadline.trim());


        } catch (ParseException e) {
            System.out.println(e.getMessage());

        }

    }

    public String toString() {
        return "[D][" + getStatusIcon() + "] " + getDescription() + " (by: " + this.deadline + ")\n";
    }
}

