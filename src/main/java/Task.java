import java.lang.String;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); // return tick or X symbols
    }

    public String getStatus() {
        return (isDone ? "1" : "0"); // return 1 or 0
    }

    public void markAsDone() {
        this.isDone = true;
    }

    // [Level-7] Converts task in String format to Task object
    public static Task convertStringToTask(String line) {
        String[] parameters = line.split("\\|");
        // To-Do
        if (line.charAt(0) == 'T') {
            Task task = new ToDo(parameters[2].trim());
            if (parameters[1].trim().equals("1"))
                task.markAsDone();
            return task;
        } // Deadline
        else if (line.charAt(0) == 'D') {
            Task task = new Deadline(parameters[2].trim(), parameters[3].trim());
            if (parameters[1].trim().equals("1"))
                task.markAsDone();
            return task;
        } // Event
        else if (line.charAt(0) == 'E') {
            Task task = new Event(parameters[2].trim(), parameters[3].trim());
            if (parameters[1].trim().equals("1"))
                task.markAsDone();
            return task;
        }
        return null;
    }

    // [Level-7] Converts task to String format to write to hard disk
    public String convertTaskToString() {
        return String.format("- | %s | %s", this.getStatus(), this.description);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}
