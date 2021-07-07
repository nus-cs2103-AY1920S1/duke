package seedu.duke.logic.command;

import seedu.duke.commons.exceptions.DukeException;
import seedu.duke.logic.validation.Validation;
import seedu.duke.model.task.TaskList;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.util.ArrayList;
import java.util.List;

/**
 * Defines a Command object that removes Tasks from the TaskList.
 * Contains a method that executes commands by removing tasks from the TaskLst.
 * Stores information on the task to be removed, so the task can be removed when the execute method is
 * called by a client of this class.
 */
public class DeleteCommand extends Command {

    private String taskString;

    /** Creates an instance of DeleteCommand.
     * @param taskString String representation of task description.
     */
    public DeleteCommand(String taskString) {
        this.taskString = taskString.trim();
    }

    /** Executes a DeleteCommand object using information stored in instance attributes.
     * Modifies a given TaskList by deleting the task(s) defined by its attributes to it.
     * Saves the updated TaskList to the given Storage.
     * Displays a message indicating successful removal after removal process has been completed.
     * @param tasks TaskList to which a given task is to be removed by this command.
     * @param ui Ui with methods that are called to display a message upon successful removal of the task.
     * @param storage Storage that contains saved String representation of the given TaskList and is to be modified
     *                upon successful removal of the task.
     * @throws DukeException if execution of the command is unsuccessful.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {

            ArrayList<ArrayList<Integer>> rangeList;
            if (taskString.toLowerCase().equals("all")) {
                ArrayList<Integer> all = new ArrayList<>(List.of(0, tasks.size() - 1));
                rangeList = new ArrayList<>(List.of(all));
            } else {
                rangeList = Validation.getValidatedListRange(tasks, taskString);
            }

            String deletedMessage = tasks.removeRanges(rangeList);
            ui.showDeletedMessage(deletedMessage);
            assert ui.getOutput().equals(deletedMessage) : "Task(s) was/ were deleted from a TaskList "
                    + "but output does not tally with deleted message.";
            assert isExit() == false : "A Delete command is exhibiting behaviour that instructs the program to exit.";
            storage.save(tasks, ui);
        } catch (DukeException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new DukeException("Execution of command unsuccessful." + ex.getMessage());
        }
    }
}
