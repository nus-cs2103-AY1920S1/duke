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

    public String getDescription() {
        return this.description;
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

    /**
     * the method reads the strings from the saved txt file and converts them to Task Object form.
     * @param s string in the txt file.
     * @return Task created from the string.
     * @throws UnknownInputException If the string does not follow the format of a Task.
     */
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

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * formats the Task into savable format.
     * @return String that can be saved into a txt file.
     */
    public String toFormattedString() {
        return this.getSimpleStatusIcon() + "~" + this.description;
    }
}