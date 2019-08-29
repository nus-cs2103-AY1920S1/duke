public class ToDoTask extends Task {
    public ToDoTask(String task) {
        super(task);
        if (task.trim().length() == 0) {
                throw new EmptyDescriptionDukeException("todo");
        }
    }
    public ToDoTask(String isCompleted, String task) {
        super(task, Boolean.parseBoolean(isCompleted));
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

    @Override
    public String toFileFormat() {
        String output = "todo | ";
        if (completed) {
            output += "true";
        } else {
            output += "false";
        }
        output += " | " + super.task;
        return output;
    }
}
