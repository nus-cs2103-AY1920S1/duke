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
    public Task getTaskMarkedAsDone() {
        return new ToDoTask(description, true);
    }

    @Override
    public Task getTaskMarkedUndone() {
        return new ToDoTask(description, false);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[T][");
        sb.append(Task.getStatusIcon(isDone));
        sb.append("] ");
        sb.append(this.description);
        return sb.toString();
    }
}
