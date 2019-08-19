package duke.task;

public class ToDo extends Task {
    /**
     * Constructor.
     */
    public ToDo(String name) {
        super(name);
    }

    @Override
    /**
     * Overrides toString method.
     * @return String
     */
    public String toString() {
        String status;
        if (this.isDone) {
            status = "[✓]";
        } else {
            status = "[✗]";
        }
        return String.format("[T]%s %s", status, this.name);
    }
}
