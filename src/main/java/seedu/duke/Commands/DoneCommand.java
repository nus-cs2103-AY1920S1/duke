package seedu.duke.Commands;

import seedu.duke.Task;

import java.util.List;

public class DoneCommand extends Command {

    private int taskId;

    public DoneCommand(int taskId) {
        this.taskId = taskId - 1;
    }

    @Override
    public void execute(List<Task> tasks) {
        tasks.get(taskId).markAsDone();
        echo(new String[]{"Nice! I've marked this task as done:",
                tasks.get(taskId).getDescriptionWithStatus()});
    }
}
