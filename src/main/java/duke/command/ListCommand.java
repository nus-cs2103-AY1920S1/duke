package duke.command;

import duke.module.Storage;
import duke.module.TaskList;
import duke.module.Ui;

/**
 * Represents the "list" command supported by Duke.
 */
public class ListCommand extends Command {

    /**
     * Lists all <code>Task</code>s in the <code>TaskList</code> as Strings.
     *
     * @param taskList List of tasks to manage.
     * @param ui UI to show result to user.
     * @param storage Storage to save any changes if necessary.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printToUser(taskList.listAll());
    }

    /**
     * Returns the response to this ListCommand.
     *
     * @param taskList List of tasks to manage.
     * @param storage Storage to save any changes if necessary.
     */
    @Override
    public String getResponse(TaskList taskList, Storage storage) {
        String[] lines = taskList.listAll();
        StringBuilder sb = new StringBuilder();
        for (String line : lines) {
            sb.append(line);
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Returns false.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
