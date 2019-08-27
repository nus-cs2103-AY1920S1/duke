public class toDo extends Task{
    public toDo(String description) {
        super(description);
    }
    public toDo(String description, boolean isDone) {
        super(description, isDone);
    }
    @Override
    public String toString() {
        return "[T]["+ this.getStatusIcon() +"] " + super.toString();
    }

    @Override
    public String toSave() {
        String done = isDone ? "1 | " : "0 | ";
        return "T | " + done + description;
    }
}
