package duke.command;

import duke.task.Task;
import duke.task.TaskList;

/**
 * A command that involves adding a task to TaskList.
 */
public abstract class CommandAddTask extends Command {
    /**
     * Adds a task to TaskList.
     * @param tasks The TaskList to add the task to.
     * @return A string confirming the adding of the task.
     */
    public abstract String execute(TaskList tasks);

    protected String createTotalNumOfTaskMsg(TaskList tasks) {
        return "Now you have " + tasks.getSizeOfTaskList() + " tasks in the list.";
    }

    protected String createSuccessMsg(Task t) {
        StringBuilder sb = new StringBuilder();
        sb.append("Got it. I've added this task:\n");
        sb.append(t.toString());
        return sb.toString();
    }
}
