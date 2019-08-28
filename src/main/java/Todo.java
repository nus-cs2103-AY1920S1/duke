public class Todo extends Task {
    /**
     * Todo is a form of task with no dates
     * @param description the information of the given task
     */
    public Todo(String description) {
        super(description);
        this.type = "todo";
    }

    @Override
    public String toString() {
        return "[T][" + getStatusIcon() + "]" + description;
    }
}