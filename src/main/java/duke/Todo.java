package duke;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
        this.type = "T";
    }

    @Override
    public String toString() {
        return "[" + this.type + "][" + this.getStatusIcon() + "] " + this.description;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Todo) {
            Todo todo = (Todo) o;
            return this.description.equals(todo.description);
        }
        return false;
    }
}
