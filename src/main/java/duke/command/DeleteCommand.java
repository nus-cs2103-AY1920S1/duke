package duke.command;

import duke.exception.DukeException;
import duke.exception.TaskNotExistException;
import duke.task.Task;
import duke.ui.Storage;
import duke.ui.TaskList;
import duke.ui.Ui;

/**
 * Deletes a task.
 */
public class DeleteCommand extends Command {
    private String[] oneLine;

    public DeleteCommand(String[] oneLine) {
        this.oneLine = oneLine;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        int tasksSize = tasks.size();
        String deleteMessage1 = " Noted. I've removed this task: \n";
        String deleteMessage2 = " Now you have " + (tasksSize - 1) + " tasks in the list.\n";
        int i = Integer.parseInt(oneLine[1].trim());
        if (i <= tasksSize && i > 0) {
            Task deleteTask = tasks.getTaskList().get(i - 1);
            tasks.getTaskList().remove(i - 1);
            try {
                storage.save(tasks);
            } catch (Exception e) {
                Ui.printOutput(" duke.txt has problem");
            }
            return String.format(
                    Ui.frontSpace + deleteMessage1 + Ui.frontSpace + "   %s\n" + Ui.frontSpace + deleteMessage2,
                    deleteTask);
        } else {
            throw new TaskNotExistException("task does not exist");
        }
    }
}
