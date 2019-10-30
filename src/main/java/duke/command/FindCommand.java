package duke.command;

import duke.Ui;
import duke.data.DukeData;
import duke.data.TaskList;

/**
 * The FindCommand helps the user search for tasks in the list which matches
 * the given keyword by the user.
 */
public class FindCommand implements Command {
    private String findKeyword;

    /**
     * Creates a FindCommand with the given keyword.
     * @param keyword the word(s) the user wants Duke to search for
     *                in the Duke's task list
     */
    public FindCommand(String keyword) {
        this.findKeyword = keyword;
    }

    /**
     * Execute method which calls the method upon initialisation of the object.
     * @param dukeData the storage object of the program
     * @param ui       ui object which handles output of user interaction
     * @param taskList the list of tasks that is stored in the Duke program
     * @return a string representation of Duke's findings in its task list
     */
    @Override
    public String execute(DukeData dukeData, Ui ui, TaskList taskList) {
        return ui.showFind(this.findKeyword, taskList);
    }
}
