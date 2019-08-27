public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * formats the Task into savable format
     * @return String that can be saved into a txt file
     */
    public String toFormattedString() {
        return "T~" + super.toFormattedString();
    }
}