package duke.command;

import duke.command.exceptions.InvalidFindDukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Represents a command that instructs Duke to search for a particular task description.
 */
public class FindCommand extends Command {

    private String cleanedInput;

    /**
     * Constructor for a find command.
     * @param cleanedInput Cleaned input representing a find command.
     */
    public FindCommand(String cleanedInput) {
        this.cleanedInput = cleanedInput;
    }

    /**
     * Executes the find command and searches for tasks with matching descriptions.
     * @param tasks List of tasks
     * @param ui User-Interface
     * @param storage Storage object
     * @return Duke's response to the find command.
     * @throws InvalidFindDukeException If the find command is invalid.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws InvalidFindDukeException {
        if (isInvalidFindCommand(cleanedInput)) {
            throw new InvalidFindDukeException("Invalid find command! Please enter a description after \"find\"");
        } else {
            String matchedDescriptions = getMatchingDescriptions(cleanedInput, tasks);
            if (matchedDescriptions.isBlank()) {
                return "No matching tasks found!";
            } else {
                return matchedDescriptions;
            }
        }
    }

    private String getMatchingDescriptions(String cleanedInput, TaskList tasks) {
        String descriptionToMatch = cleanedInput.substring(cleanedInput.indexOf("find") + 4)
                .toLowerCase().strip();
        String matchedDescriptions = tasks.findTasks(descriptionToMatch);
        return matchedDescriptions;
    }

    private boolean isInvalidFindCommand(String input) {
        return cleanedInput.equalsIgnoreCase("find")
                || cleanedInput.split("\\s+").length == 1;
    }

    /**
     * Checks if this is an exit command.
     * @return False.
     */
    public boolean checkExit() {
        return false;
    }

}
