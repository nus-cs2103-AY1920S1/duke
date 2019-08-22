public class ToDoTask extends Task {
    public ToDoTask(String task) {
        super(task);
        if (task.trim().length() == 0) {
                throw new EmptyDescriptionDukeException("todo");
        }
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
