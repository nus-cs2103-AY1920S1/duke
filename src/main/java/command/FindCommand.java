package command;

import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

/**
 * Represents a find command.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Initializes FindCommand with words to be searched.
     *
     * @param keyword is the word user wants to search
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Shows all task with description that matches keyword.
     *
     * @param tasks contains task list
     * @param ui deals with interaction with users
     * @param storage deals with loading and saving of task list
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showFindCommand(tasks, keyword);
    }
}
