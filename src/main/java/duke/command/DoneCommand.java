package duke.command;

import duke.exception.DukeException;
import duke.exception.TaskNotExistException;
import duke.task.Task;
import duke.ui.Storage;
import duke.ui.TaskList;
import duke.ui.Ui;

/**
 * DoneCommand used to mark a task.
 */
public class DoneCommand extends Command {
    private String[] oneLine;

    public DoneCommand(String[] oneLine) {
        this.oneLine = oneLine;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        int i = Integer.parseInt(oneLine[1].trim());
        int tasksSize = tasks.size();

        if (i <= tasksSize && i > 0) {
            Task current = tasks.getTaskList().get(i - 1);
            current.markAsDone();
            try {
                storage.save(tasks);
            } catch (Exception e) {
                Ui.printOutput(" duke.txt has problem");
            }
            return Ui.frontSpace + "Nice! I've marked this task as done: \n" +
                    Ui.frontSpace + "   " + current.toString();
        } else {
            throw new TaskNotExistException("task does not exist");
        }
    }
}