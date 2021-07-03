public class ToDo extends Task {

    /**
     * Constructor for ToDo.
     *
     * @param description  description of todo
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
