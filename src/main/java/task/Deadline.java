package task;

import java.text.SimpleDateFormat;
import java.util.Date;

<<<<<<< .merge_file_a14716
public class Deadline extends Task {
    private Date by;

=======
/**
 * Defines the tasks with deadlines and provides it's completion status.
 */
public class Deadline extends Task {
    private Date by;

    /**
     * Constructs the Deadline object with specified completion status.
     * @param description Task description.
     * @param isDone Task completion status.
     * @param by Task's deadline.
     */
>>>>>>> .merge_file_a02068
    public Deadline(String description, boolean isDone, Date by) {
        super(description, isDone);
        this.by = by;
    }

<<<<<<< .merge_file_a14716
=======
    /**
     * Constructs the Deadline object with default completion status of FALSE.
     * @param description Task description.
     * @param by Task's deadline.
     */
>>>>>>> .merge_file_a02068
    public Deadline(String description, Date by) {
        super(description);
        this.by = by;
    }

<<<<<<< .merge_file_a14716
=======

    /**
     * Formats the date for the deadline.
     * @return Formatted date with specified date pattern.
     */
>>>>>>> .merge_file_a02068
    public String stringDate() {
        return new SimpleDateFormat("dd-MM-yyyy HH:mm").format(by);
    }

<<<<<<< .merge_file_a14716
=======
    /**
     * Returns the literal description of the object.
     * @return Understandable description of object.
     */
>>>>>>> .merge_file_a02068
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + stringDate() + ")";
    }

<<<<<<< .merge_file_a14716
=======
    /**
     * Formats the object so that it can be save into file.
     * @return Formatted description of the object.
     */
>>>>>>> .merge_file_a02068
    public String toFile() {
        return "D | " + super.getStatusIcon() + " | " + super.getDescription() + " | " + stringDate();
    }
}