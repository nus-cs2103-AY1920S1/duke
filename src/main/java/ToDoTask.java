public class ToDoTask implements Task {
    public final String description;
    public final boolean isDone;

    public ToDoTask(String description) {
        this.description = description;
        this.isDone = false;
    }

    public ToDoTask(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    @Override
    //Returns a copy of this task but with its completion status marked as done
    public Task getTaskMarkedAsDone() {
        return new ToDoTask(description, true);
    }

    @Override
    //Returns a copy of this task but with its completion status marked as undone
    public Task getTaskMarkedUndone() {
        return new ToDoTask(description, false);
    }

    @Override
    //Returns a string representation of the Task, including its type, completion status and description
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[T][");
        sb.append(Task.getStatusIcon(isDone));
        sb.append("] ");
        sb.append(this.description);
        return sb.toString();
    }
}
