package duke.command;

import duke.Storage;
import duke.task.Task;
import duke.TaskList;
import duke.Ui;

import java.util.InputMismatchException;

/**
 * Represents a command which deletes duke.task.Task specified in an index in the Tasklist.
 * @see TaskList
 * @see Task
 */

public class DeleteCommand extends Command {
    /**
     * Constructor for duke.command.DeleteCommand.
     * @param commandSplitBySpaces
     */
    public DeleteCommand(String[] commandSplitBySpaces) {
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
            int index = Integer.parseInt(commandSplitBySpaces[1]);
            outputString = outputString + ui.printDeletedMessage();
            outputString = outputString + ui.printTask(taskList.getTasks().get(index-1));
            taskList.delete(index-1);
            outputString = outputString + ui.printNumberOfTasks(taskList);
            outputString = outputString + ui.printTaskList(taskList);
            return outputString;
        } catch (IndexOutOfBoundsException e) {
            throw new InputMismatchException("I'm sorry, the task number does not exist?");
        } catch (NumberFormatException e) {
            throw new InputMismatchException("I'm sorry, can you please write a number after delete?");
        }
    }
}
