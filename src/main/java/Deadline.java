import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Deadline extends Task {

    protected Date by;

    /**
     * Constructor for Deadline.
     *
     * @param description title of Deadline
     * @param by datetime for deadline
     */
    public Deadline(String description, Date by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return "[D]" + super.toString() + "(by: " + formatter.format(by) + ")";
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.description, this.by);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Deadline test = (Deadline) o;
        return this.description.equals(test.description) &&
                this.by.equals(test.by);
    }
}