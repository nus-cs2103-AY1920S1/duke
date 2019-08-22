public class ToDoTask extends Task {
    public ToDoTask(String task) {
        super(task);
    }

    @Override
    public String toString() {
        String output = "[T]";
        if (super.completed) {
            output += "[✓]";
        } else {
            output += "[✗]";
        }
        output += " " + super.task;
        return output;
    }
}
