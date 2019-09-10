package duke.command;

import duke.ui.MessageHandler;
import duke.utilities.Storage;
import duke.task.TaskList;
import duke.task.Task;

import java.util.List;

/**
 * Inherits from abstract Command class.
 * Handles execution of commands in this format:
 * <code>find</code>
 */
public class FindTaskCommand extends Command {
    public FindTaskCommand(String commandInformation) {
        super(commandInformation);
    }

    /**
     * Executes commands in this format:
     * <code>find</code> and searches the list for tasks matching the search term.
     *
     * @param tasks          <code>TaskList</code> object which holds the taskList
     *                       and various methods to operate on the taskList
     * @param messageHandler <code>UI</code> object which handles console output
     * @param storage        <code>Storage</code> object which allows for reading
     *                       result of executed command into preset task.txt file
     * @return <code>String</code> of all search results
     */
    @Override
    public String execute(TaskList tasks, MessageHandler messageHandler, Storage storage) {
        String searchTerm = this.commandInformation;
        List<Task> searchResultsList = tasks.findMatchingTasks(searchTerm);
        return messageHandler.searchResults(searchResultsList);
    }

}
