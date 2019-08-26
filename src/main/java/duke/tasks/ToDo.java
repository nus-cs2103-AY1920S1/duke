package duke.tasks;
public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String format() {
        String formatted = "T | ";
        String binary = "0";
        if (super.isDone == true) {
            binary = "1";
        }
        formatted += binary + " | " + super.description;
        return formatted;
    }
}
