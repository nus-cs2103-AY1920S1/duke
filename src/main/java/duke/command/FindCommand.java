package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Encapsulates a command to find tasks in the task list containing keyword.
 */
public class FindCommand extends Command {
    protected final String keyword;

    /**
     * Constructs a FindCommand object.
     *
     * @param keyword  Keyword to be searched for.
     */
    public FindCommand(String keyword) {
        super();
        this.keyword = keyword;
    }

    /**
     * Executes the command, listing all tasks in the task list containing the keyword.
     *
     * @param storage  Data file of chat bot.
     * @param taskList  Task list of chat bot.
     * @param ui  User interface of chat bot.
     */
    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) {
        ui.showFindMessage(taskList, keyword);
    }
}
