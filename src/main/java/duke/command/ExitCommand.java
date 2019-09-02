package duke.command;

import duke.component.Storage;
import duke.component.TaskList;
import duke.component.Ui;

/**
 * Represents user's exit commmand to chatbot.
 * The 'ExitCommand' class supports operators (i) executing the command
 * and (ii) checking if the bot has exited its conversation with the user.
 */
public class ExitCommand extends Command {

    /**
     * Closes scanner and exits from program.
     * Prints response in console.
     *
     * @param taskList List of the things user needs to do
     * @param ui       Interface that interacts with the user
     * @param storage  Stores the user input in a file
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) {

        ui.printText("Bye. Hope to see you again soon!");
        ui.closeUi();

    }

    /**
     * Returns true to indicate program has exited.
     *
     * @return true Program has exited
     */
    public boolean isExit() {
        return true;
    }
}