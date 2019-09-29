/**
 * Creates a ToDos object which extends from the Task class.
 * A <code>description</code> is passed into this class to
 * instantiate a todos task.
 */
public class ToDos extends Task {

    /**
     * Instantiate a ToDos object by passing a String of description.
     * @param description Description of the todos task.
     */
    protected ToDos(String description) {
        super(description);
    }

    @Override
    public String getNumericalDate() {
        return "";
    }

    @Override
    public String formatString() {
        return "T-" + super.checkStatus().trim() + "-" + super.getDescription();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
