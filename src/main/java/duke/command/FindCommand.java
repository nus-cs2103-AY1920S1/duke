package duke.command;

import duke.storage.Storage;

import duke.task.TaskList;

import duke.ui.Ui;

public class FindCommand extends Command {

    String keyword;

    /**
     * Constructor to create a FindCommand object.
     *
     * @param keyword The user's search keyword.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Get Ui to print the matching tasks.
     *
     * @param taskList The TaskList object containing the list of tasks.
     * @param ui The Ui object we are currently using.
     * @param storage The Storage object we are currently using.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        assert ui != null :
                "Ui object cannot be null";

        return ui.printMatchingTasks(taskList.getTasks(), keyword);
    }
}
