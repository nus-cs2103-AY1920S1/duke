package command;

import main.Storage;
import main.TaskList;
import main.Ui;

import java.io.IOException;

/**
 * Represents the command to mark a task as done.
 */
public class DoneCommand extends Command {
    String[] temp;

    /**
     * Creates an DoneCommand object.
     *
     * @param temp Parsed String array derived from input.
     */
    public DoneCommand(String[] temp) {
        this.temp = temp;
    }

    /**
     * Marks a task as done, updates the file in storage.
     *
     * @param task    The working TaskList object.
     * @param ui      The working Ui object.
     * @param storage The working storage object.
     * @throws IOException If file is not found.
     */
    public String execute(TaskList task, Ui ui, Storage storage) throws IOException {
        try {
            int index = Integer.parseInt(temp[1]) - 1;
            task.getList().get(index).markAsDone();
            storage.arrayToFile(task.getList());
            return ui.printDone(task.getList(), index);
        } catch (NullPointerException e) {
            return ui.printError("Please input a valid task number.");
        } catch (IndexOutOfBoundsException e) {
            return ui.printError("Please input a valid task number.");
        }

    }
}
