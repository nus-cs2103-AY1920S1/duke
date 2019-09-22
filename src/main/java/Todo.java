public class Todo extends Task {

    /**
     * Constructor for Todoinstance
     *
     * @param description is a string of the description of the Task
     *
     */
    public Todo (String description) {
        super(description);
    }

    @Override
    public String date() {
        return "";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}