package duke.command;

import duke.gui.Ui;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Represents an instruction to find all tasks with specified keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword, boolean isExit) {
        super(isExit);
        this.keyword = keyword;
    }

    /**
     * Finds all tasks containing the keyword and lists them.
     *
     * @param taskList the TaskList instance Duke is referencing.
     * @param ui the Ui instance handling user-facing interaction.
     * @param storage the Storage instance dealing with hard disk reading/writing.
     * @return the response containing the response and boolean flag to exit the program.
     */
    @Override
    public CommandResponse execute(TaskList taskList, Ui ui, Storage storage) {
        // create a new task list with results containing the keyword.
        TaskList searchList = taskList.find(keyword);
        // inform the user of matching queries (if any)
        String response = ui.showSearchList(searchList);
        return new CommandResponse(response, super.isExit());
    }
}
