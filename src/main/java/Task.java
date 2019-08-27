public class Task {
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

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String getSimpleStatusIcon() {
        return (isDone ? "1" : "0"); //return 1 or 0 symbols
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public static Task readString(String s) throws UnknownInputException {
        String[] arr = s.split("~");
        boolean isDone = arr[1].equals("1");
        String description = arr[2];

        if (arr[0].equals("T")) {
            return new ToDo(description, isDone);
        } else if (arr[0].equals("E")) {
            return new Event(description, isDone, arr[3]);
        } else if (arr[0].equals("D")) {
            return new Deadline(description, isDone, arr[3]);
        } else {
            throw new UnknownInputException("Unknown task found.");
        }
    }

    public static int initialiseNumOfTasks(Task[] arr) {
        int no = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null) {
                no++;
            } else {
                break;
            }
        }
        return no;
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public String toFormattedString() {
        return this.getSimpleStatusIcon() + "~" + this.description;
    }
}