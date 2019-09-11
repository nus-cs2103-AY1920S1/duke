package duke.command;

import duke.Storage;
import duke.task.Task;
import duke.TaskList;
import duke.Ui;

import java.util.InputMismatchException;

/**
 * Represents a command which changes duke.task.Task specified in an index in the Tasklist to be done.
 * @see TaskList
 * @see Task
 */

public class DoneCommand extends Command {
    /**
     * Constructor for duke.command.DoneCommand.
     * @param commandSplitBySpaces String representation of the user input
     */
    public DoneCommand(String[] commandSplitBySpaces) {
        super(commandSplitBySpaces);
    }

    /**
     * Executes the command by using the three arguments provided.
     * @param taskList
     * @param ui
     * @param storage
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            String outputString = "";
            int index = Integer.parseInt(super.commandSplitBySpaces[1]);
            outputString = outputString + ui.printDoneMessage();
            taskList.done(index);
            outputString = outputString + ui.printTask(taskList.getTasks().get(index - 1));
            return outputString;
        } catch (IndexOutOfBoundsException e) {
            throw new InputMismatchException("I'm sorry, the task number does not exist?");
        } catch (NumberFormatException e) {
            throw new InputMismatchException("I'm sorry, can you please write a number after done?");
        }
    }
}
