package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a Command which signals the end of the program.
 */
public class ExitCommand extends Command {

    /**
     * Prompts Ui to print a concluding message.
     * @param tasks TaskList which stores the list of tasks.
     * @param ui Ui which feedbacks to user about success of command.
     * @param storage Storage which saves the task into the text file.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.print("Bye. Hope to see you again soon!");
    }

    public boolean isExit() {
        return true;
    }
}

