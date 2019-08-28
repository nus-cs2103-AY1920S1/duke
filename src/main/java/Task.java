import java.util.List;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public abstract String encode();

    public static Task decode(String info, List<Task> tasks) {
        String[] arr = info.split(",");
        switch (arr[0]) {
        case "todo":
            Task todo = new Todo(arr[1]);
            if (arr[2].equals("true")) {
                todo.markAsDone();
            }
            return todo;
        case "deadline":
            Task deadline = new Deadline(arr[1], arr[3]);
            if (arr[2].equals("true")) {
                deadline.markAsDone();
            }
            return deadline;
        case "event":
            Task event = new Event(arr[1], arr[3]);
            if (arr[2].equals("true")) {
                event.markAsDone();
            }
            return event;
        default:
            throw new InvalidArgumentException();
        }
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markAsDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

}
