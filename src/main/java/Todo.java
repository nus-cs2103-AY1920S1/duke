public class Todo extends Task {

    /**
     * constructor.
     * @param description of task.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    String getStoredForm() {
        String name = this.getDescription();
        int status = this.isDone()? 1 : 0;
        return "T " + status + " " + name + "\n";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
