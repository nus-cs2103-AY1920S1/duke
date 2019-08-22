public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public static Task createTask(String [] tokens) {
        if (tokens[0].equals("todo")) {
            return ToDo.createToDo(tokens);
        } else if (tokens[0].equals("deadline")) {
            return Deadline.createDeadline(tokens);
        } else if (tokens[0].equals("event")) {
            return Event.createEvent(tokens);
        }
        return null;
    }

    public String getStatusIcon() {
        //return (isDone ? "✓" : "✗" );
        return (isDone ? "\u2713" : "\u2718");
    }

    public String getDescription() {
        return description;
    }
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), getDescription());
    }

    public void doTask() {
        this.isDone = true;
    }
}