package seedu.duke.commands;

import seedu.duke.trackables.Deadline;
import seedu.duke.trackables.Task;

import java.util.List;

public class DeleteCommand extends Command {

    private int taskId;

    public DeleteCommand(int taskId) {
        this.taskId = taskId-1;
    }

    @Override
    public void execute(List<Task> tasks) {
        Task taskToRemove = tasks.get(taskId);
        tasks.remove(taskId);
        echo(
                new String[]{
                        "Noted. I've removed this task:",
                        "  " + taskToRemove.toString(),
                        "Now you have " + tasks.size() + " tasks in the list."
                });
    }
}
