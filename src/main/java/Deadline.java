import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents a deadline-type task which inherits from Task.
 * @author Ang Kai Qi
 * @version 0.1.3
 */
public class Deadline extends Task {

    protected  String by;
    protected Date date;

    /**
     * Creates a deadline task with description and deadline.
     * @param description Description of deadline task.
     * @param by Deadline of the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        assert by.length()  >= 6 || by.length() <= 15: "Parser failed to process deadline correctly.";
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HHmm");
        try {
            this.date = formatter.parse(by);
        } catch (ParseException pe){
            System.out.println("Deadline: Something serious happened here...");
        }
    }

    /**
     * Returns a string representation of the deadline object for UI.
     * @return A string representation of the deadline object for UI.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }

    /**
     * Returns a string representation of the deadline object for Storage.
     * @return A string representation of the deadline object for Storage.
     */
    @Override
    public String saveString() {
        return "D" + super.saveString() + " | " + this.by + "\n";
    }
}
