package duke.task;

/**
 * Represents a super-class Task that is extended to ToDo, Deadline and Event.
 */
public abstract class Task {
    private String name;
    private boolean isDone;

    Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    Task(String name) {
        this(name, false);
    }

    public String getName() {
        return this.name;
    }

    public boolean getDone() {
        return isDone;
    }

    /**
     * Sets isDone to true.
     */
    public String setDone() {
        this.isDone = true;
        return ("Nice! I've marked this task as done: \n  " + this.toString());
    }

    public abstract String storageString();

    @Override
    public String toString() {
        return ("[" + (this.isDone ? "done" : "not done") + "]" + " " + this.name);
    }

    /**
     * Static method that returns a Task depending on the given String arr[].
     * @param arr String arr that contains the specifications of the task.
     * @return Task that corresponds to the given arr.
     */
    public static Task taskMaker(String[] arr) {
        String type = arr[0];
        boolean done = arr[1].equals("1");
        String name = arr[2];
        Task task = null;
        switch (type) {
        case "T":
            task = new ToDo(name, done);
            break;
        case "D":
            task = new Deadlines(name, arr[3], done);
            break;
        case "E":
            task = new Event(name, arr[3], done);
            break;
        default:
            break;
        }
        return task;
    }
}
