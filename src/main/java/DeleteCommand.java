/**
 * Simulates a delete command of the Duke System.
 * @author Fabian Chia Hup Peng
 */

import java.util.ArrayList;

public class DeleteCommand extends Command {

    private int taskNumber;

    /**
     * Creates a DeleteCommand instance with the appropriate attributes.
     * @param taskNumber The position number of the task in the task list.
     */
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the delete command; deletes the task from the task list,
     * and prints the deleted message.
     * @param taskList The task list for the task to be added to.
     * @param ui The ui which prints the added message.
     * @param storage The storage which deals with the hard drive.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ArrayList<Task> arrayList = taskList.getArrayList();
        Task task = arrayList.get(taskNumber - 1);
        taskList.deleteTask(task);

        ui.printDeletedMessage(task, taskList);
    }

}