package duke.command;

import duke.exception.DukeException;
import duke.exception.WrongTaskFormatException;
import duke.task.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * UpdateCommands represents user commands to update a task.
 */

public class UpdateCommand extends Command {
    private String taskContents;

    public UpdateCommand(String taskContents) {
        super();
        this.taskContents = taskContents;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            String[] taskContentsList = taskContents.split(" ", 2);

            String taskNumber = taskContentsList[0];
            int indexOfTask = Integer.parseInt(taskNumber) - 1;
            Task taskToUpdate = tasks.getTask(indexOfTask);

            String updatedContents = taskContentsList[1];
            taskToUpdate.update(updatedContents);

            message += ui.showUpdateMessage();
            message += ui.showTask(taskToUpdate);

            storage.writeTaskToFile(tasks);
            return message;
        } catch (IndexOutOfBoundsException ex) {
        throw new WrongTaskFormatException("To update a task, you should follow this format:\n"
                + "update <task number> <task description> [optional]<time in DD/MM/YYYY HHMM>");
        }
    }
}
