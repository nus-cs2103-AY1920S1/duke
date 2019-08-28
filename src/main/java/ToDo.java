/** Task to represent a To-do. */
class ToDo extends Task {

    public ToDo(String name) {
        super(name);
    }

    // get task type
    public TaskType getType() {
        return TaskType.TODO;
    }

    // a to-do event has no date
    public String getDate() {
        return null;
    }

    @Override
    public String toString() {
        String doneStr = this.done ? "✓" : "✗";
        return String.format("[T][%s] %s", doneStr, this.name);
    }
}