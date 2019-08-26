package duke.command;

import duke.exception.IllegalIndexOfTaskException;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class DeleteCommand extends Command{
    public static final String COMMAND_WORD = "delete";
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) throws IllegalIndexOfTaskException {
        try {
            Task task = tasks.removeTaskAtIndex(index);
            ui.showRemovedTask(task, tasks.getSize());
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new IllegalIndexOfTaskException("Please provide an valid index of the task.");
        }
    }
}