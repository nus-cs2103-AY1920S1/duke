package seedu.duke.logic.command;

import seedu.duke.commons.exceptions.DukeException;
import seedu.duke.model.task.Task;
import seedu.duke.model.task.TaskList;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

/**
 * Defines a Command object that lists out all Tasks in the TaskList to the user.
 */
public class RandomCommand extends Command {

    int size;

    /**
     * Creates an instance of RandomCommand.
     * Stores to attributes of RandomCommand relevant search information required to create a RandomCommand.
     * @param size Size of the TaskList to be randomly generated, specified by a client of this class.
     */
    public RandomCommand(int size) {
        this.size = size;
    }
    
    /** Executes a ListCommand object.
     * Displays a message that shows the String representation of the given TaskList when called.
     * @param tasks TaskList that was being used by a client of this command.
     * @param ui Ui with methods that are called to display a message showing the String representation of the
     *           given tasklist.
     * @param storage Storage that contains saved String representation of the given TaskList.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        (new DeleteCommand("all")).execute(tasks, ui, storage);
        for (int i = 0; i < size; i++) {
            tasks.add(Task.generateRandomTask());
        }
        assert tasks != null : "TaskList that is to be displayed is null.";
        ui.showRandomTaskList(tasks);
        storage.save(tasks, ui);
    }
    
}
