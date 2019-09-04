public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    public String taskSavedTextFormat() {
        String done = "0";
        if (super.isDone) {
            done = "1";
        }
        return "T | " + done + " | " + super.description;
    }
}
