/**
 * This class represents ToDo tasks which is a type of Task.
 */
public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
        this.typeOfTask = "[T]";
    }
}
