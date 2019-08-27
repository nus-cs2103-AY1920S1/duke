package duke.command;


import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Encapsulates a command to list all tasks in the task list.
 */
public class ListCommand extends Command {
    /**
     * Constructs a ListCommand object.
     */
    public ListCommand() {
        super();
    }

    /**
     * Executes the command, listing all tasks in the task list.
     *
     * @param storage  Data file of chat bot.
     * @param taskList  Task list of chat bot.
     * @param ui  User interface of chat bot.
     */
    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) {
        ui.showTaskList(taskList);
    }
}
