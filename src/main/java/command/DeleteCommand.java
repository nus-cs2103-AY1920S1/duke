package command;

import main.Storage;
import main.TaskList;
import main.Ui;
import task.Task;

import java.io.IOException;

/**
 * Represents the command to delete a task.
 */
public class DeleteCommand extends Command {

    private String[] temp;

    /**
     * Creates an DeleteCommand object.
     *
     * @param temp Parsed String array derived from input.
     */
    public DeleteCommand(String[] temp) {
        this.temp = temp;
    }

    /**
     * Deletes the task associated with the number from task.
     * adding it to the array within task, writing the updated list
     * to storage and printing the current list size.
     *
     * @param task    The working TaskList object.
     * @param ui      The working Ui object.
     * @param storage The working storage object.
     * @throws IOException          If file is not found.
     * @throws NullPointerException If an invalid task number is received.
     */
    public String execute(TaskList task, Ui ui, Storage storage) throws NullPointerException, IOException {
        try {
            Task toRemove = task.getList().remove(Integer.parseInt(temp[1]) - 1);
            storage.arrayToFile(task.getList());
            return ui.printRemove(task.getList(), toRemove);
        } catch (NullPointerException e) {
            return ui.printError("Please input a valid task number to delete.");
        } catch (IndexOutOfBoundsException e) {
            return ui.printError("Please input a valid task number to delete.");
        }
    }
}
