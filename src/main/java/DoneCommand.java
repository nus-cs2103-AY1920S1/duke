/**
 * Simulates a done command of the Duke System.
 * @author Fabian Chia Hup Peng
 */

import java.util.ArrayList;

public class DoneCommand extends Command {

    private int taskNumber;

    /**
     * Creates a DoneCommand instance with the appropriate attributes.
     * @param taskNumber The position number of the task in the task list.
     */
    public DoneCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the done command; marks the task as done from the task list,
     * and prints the done message.
     * @param taskList The task list for the task to be added to.
     * @param ui The ui which prints the added message.
     * @param storage The storage which deals with the hard drive.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ArrayList<Task> arrayList = taskList.getArrayList();
        Task task = arrayList.get(taskNumber - 1);
        taskList.setTaskAsDone(task);

        ui.printDoneMessage(task);
    }

}