package seedu.duke.logic.command;

import seedu.duke.commons.exceptions.DukeException;
import seedu.duke.logic.validation.Validation;
import seedu.duke.model.task.TaskList;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.util.ArrayList;
import java.util.List;

/**
 * Defines a Command object that marks Tasks in the TaskList as undone.
 * Contains a method that executes commands by marking tasks in the TaskLst as undone.
 * Stores information on the task to be marked as done, so the task can be marked as done when the execute method is
 * called by a client of this class.
 */
public class UndoneCommand extends Command {

    private String taskString;

    /** Creates an instance of UndoneCommand.
     * @param taskString String representation of task description.
     */
    public UndoneCommand(String taskString) {
        this.taskString = taskString.trim();
    }

    /** Executes an UndoneCommand object using information stored in instance attributes.
     * Modifies a given TaskList by marking the task defined by its attributes as undone.
     * Saves the updated TaskList to the given Storage.
     * Displays a message indicating successful marking after marking process has been completed.
     * @param tasks TaskList to which a given task is to be marked as undone by this command.
     * @param ui Ui with methods that are called to display a message upon successful marking of the task.
     * @param storage Storage that contains saved String representation of the given TaskList and is to be modified
     *                upon successful marking of the task.
     * @throws DukeException if execution of the command is unsuccessful.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            ArrayList<ArrayList<Integer>> rangeList;
            if (taskString.equals("all")) {
                ArrayList<Integer> all = new ArrayList<>(List.of(0, tasks.size() - 1));
                rangeList = new ArrayList<>(List.of(all));
            } else {
                rangeList = Validation.getValidatedListRange(tasks, taskString);
            }

            String undoneMessage = tasks.markAsUndone(rangeList);
            ui.showUndoneMessage(undoneMessage);
            assert ui.getOutput().equals(undoneMessage) : "Task was marked as undone "
                    + "but output does not tally with undone message.";
            assert isExit() == false : "An Undone command is exhibiting behaviour that instructs the program to exit.";
            storage.save(tasks, ui);
        } catch (DukeException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new DukeException("Execution of command unsuccessful.");
        }
    }
}
