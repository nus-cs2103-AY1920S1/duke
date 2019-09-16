package duke.task;

public class Todo extends Task {

    // the Todo subclass has one constructor
    public Todo(String item) {
        super(item);
    }

    @Override
    // overrides toString method in Task
    public String toString() {
        if (isDone) {
            return "[T][" + "/" + "] " + super.toString();
        } else {
            return "[T][" + "x" + "] " + super.toString();
        }
    }

    @Override
    // overrides saveTask method in Task
    public String saveTask() {
        if (isDone) {
            return "T" + " | " + "1" + " | " + super.toString() + "\n";
        } else {
            return "T" + " | " + "0" + " | " + super.toString() + "\n";
        }
    }

}
