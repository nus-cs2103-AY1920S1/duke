package duke.command;

import duke.gui.Ui;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskFactory;
import duke.task.TaskList;
import duke.task.TaskType;

/**
 * Represents an instruction to add a new Task to Duke.
 */
public class AddCommand extends Command {
    private TaskType type;
    private String[] args;

    /**
     * Creates the command to add a task.
     *
     * @param type the type of task: deadline, event or to-do.
     * @param args the arguments for creation of the task.
     * @param isExit whether to exit the application after execution.
     */
    public AddCommand(TaskType type, String[] args, boolean isExit) {
        super(isExit);
        this.type = type;
        this.args = args;
    }

    /**
     * Adds a task to the TaskList, informs the user and updates the hard disk.
     *
     * @param taskList the TaskList instance Duke is referencing.
     * @param ui the Ui instance handling user-facing interaction.
     * @param storage the Storage instance dealing with hard disk reading/writing.
     * @return the response containing the response and boolean flag to exit the program.
     */
    @Override
    public CommandResponse execute(TaskList taskList, Ui ui, Storage storage) {
        // create the appropriate task, add to the list and write to disk
        Task task = taskList.add(TaskFactory.getTask(type, args));
        // inform the user the task has been added
        String response = ui.showAddMessage(task, taskList.count());
        // update hard disk
        storage.writeToDisk(taskList);
        return new CommandResponse(response, super.isExit());
    }

    public String[] getArgs() {
        return this.args;
    }
}
