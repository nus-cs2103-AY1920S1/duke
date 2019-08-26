package duke.command;

import duke.exception.IllegalIndexOfTaskException;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class DoneCommand extends Command{
    public static final String COMMAND_WORD = "done";
    private int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) throws IllegalIndexOfTaskException {
        try {
            Task task = tasks.setTaskAtIndexDone(index);
            ui.showDoneTask(task);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new IllegalIndexOfTaskException("Please provide an valid index of the task.");
        }
    }
}