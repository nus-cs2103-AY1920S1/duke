/**
 * Represents a TodoTask. A TodoTask does not have a time, it only has a name.
 */
public class ToDoTask extends Task {
    /**
     * Constructs a TOdoTask with the given name.
     * @param task The name to be assigned to the Task.
     */
    public ToDoTask(String task) {
        super(task);
        if (task.trim().length() == 0) {
                throw new EmptyDescriptionDukeException("todo");
        }
    }

    /**
     * Constructs a TodoTask with the given name and completion state.
     * @param isCompleted The completion state of the task.
     * @param task The name of the task.
     */
    public ToDoTask(String isCompleted, String task) {
        super(task, Boolean.parseBoolean(isCompleted));
        if (task.trim().length() == 0) {
            throw new EmptyDescriptionDukeException("todo");
        }
    }

    /**
     * Returns a string which represents this task.
     * @return a String detailing the task's name.
     */
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

    /**
     * returns a string that is used to store the task in the save file.
     * @return a string specifically formatted for storage.
     */
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
