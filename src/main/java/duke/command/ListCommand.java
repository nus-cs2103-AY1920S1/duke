package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ListCommand extends Command {
    /**
     * Executes a list command using the given task list, UI and file storage.
     *
     * @param tasks the task list supplied.
     * @param ui the UI supplied.
     * @param storage the file storage supplied.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder myBuilder = new StringBuilder();
        myBuilder.append("Here are the tasks in your list:\n");
        myBuilder.append(tasks.toString());
        ui.printMessage(myBuilder.toString());
    }
}
