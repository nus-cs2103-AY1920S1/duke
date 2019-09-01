package duke.command;

import duke.Storage;
import duke.task.Task;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a command which deletes duke.task.Task specified in an index in the Tasklist.
 * @see TaskList
 * @see Task
 */

public class DeleteCommand extends Command {
    String[] commandSplit = super.stringCommand.split(" ");

    /**
     * Constructor for duke.command.DeleteCommand.
     * @param stringCommand
     */
    public DeleteCommand(String stringCommand) {
        super(stringCommand);
    }

    /**
     * Executes the command by using the three arguments provided.
     * @param taskList
     * @param ui
     * @param storage
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        int index = Integer.parseInt(commandSplit[1]);
        ui.printDeletedMessage();
        taskList.delete(index);
        ui.printNumberOfTasks(taskList);
        ui.printTask(taskList.getTasks().get(index-1));
    }

    /**
     * Checks if duke.Duke will end.
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
