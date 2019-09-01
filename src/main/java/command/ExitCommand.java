package command;

import duke.Storage;
import duke.TaskList;

/**
 * Represents a Command which signals the end of the program.
 */
public class ExitCommand extends Command {

    /**
     * Prompts Ui to print a concluding message.
     * @param tasks TaskList which stores the list of tasks.
     * @param storage Storage which saves the task into the text file.
     */
    public String execute(TaskList tasks, Storage storage) {
        return "Bye. Hope to see you again soon!";
    }

    public boolean isExit() {
        return true;
    }
}

