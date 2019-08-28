public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String command) {
        super(command);
        String[] commandArr = command.split(" /", 2);
        this.description = commandArr[0];
        String byString = commandArr[1].split(" ", 2)[1]; //remove the "by"
        this.by = DateTimeHandler.getDateTime(byString);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toStringFile() {
        return "D | " + ((isDone) ? "1" : "0") + " | " + description + " | " + by;
    }
}