package Tasks;

/**
 * An object of a Todo task which is also a child of a Task.
 */
public class Todo extends Task {

    protected String by;

    /**
     * Creates an instance of a Todo task
     *
     * @param description is the description of the task
     * @param by is the date/time of the task. This
     * will be blank for a Todo task
     */
    public Todo(String description, String by) {
        super(description);
        assert(description.length()!=0):"Todo description cannot be empty";
        this.by = by;
    }

    /**
     * overrides toString method
     *
     * @return formatted Todo object (i.e. [D][O] task1 (by: tonight)
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
