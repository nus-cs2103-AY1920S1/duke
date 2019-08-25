package duck.task;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Todo) {
            Todo another = (Todo) obj;
            return this.description.equals(another.description) && this.isDone == another.isDone;
        } else {
            return false;
        }
    }
}
