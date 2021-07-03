import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime by;


    /**
     * Constructor for deadline.
     *
     * @param description  description of deadline.
     * @param by string indicating date and time.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = Parser.parseInput(by);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d 'of' MMM yyyy h:mm a");
        return "[D]" + super.toString() + " (by: " + by.format(formatter) + ")";
    }

}