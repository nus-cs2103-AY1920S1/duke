package command;

import run.Storage;
import run.TaskList;
import run.Ui;

/**
 * Extends the command class and used to search through tasks to find tasks with names that contain
 * the search parameter.
 */
public class FindCommand extends Command {
    protected String rawString;
    protected String searchString;

    /**
     * Constructor for find command.
     * @param rawString complete unparsed user input of find creation request
     */
    public FindCommand(String rawString) {
        this.rawString = rawString;
        this.searchString = rawString.replace("find ", "");
    }

    /**
     * Searches through list of current tasks from TaskList, filters these tasks into a seperate
     * arraylist and passes to ui to print.
     * @param tasks current TaskList with all current tasks
     * @param ui current user interface
     * @param storage current storage state
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.find(this.searchString);
    }

    /**
     * Checks if this command is an exit ("bye") command.
     * @return false boolean since command is not exit ("bye") command
     */
    public boolean isExit() {
        return false;
    }
}