package duke.command;

import duke.exception.DukeEmptyDescriptionException;
import duke.exception.DukeMissingDescriptionException;
import duke.exception.DukeUpdateTodoTimeException;
import duke.exception.DukeWrongTimeFormatException;

import duke.storagedata.StorageData;

import duke.tasklist.TaskList;

import duke.ui.DukeUi;

/**
 * Represents an UpdateCommand to update certain tasks in Duke.
 */
public class UpdateCommand extends Command {

    /**
     * Creates a UpdateCommand object with the details that need to be updated.
     * @param details is the details regarding what to update and what to replace it with.
     */
    public UpdateCommand(String details) {
        super(details);
    }

    @Override
    public String execute(TaskList tasks, DukeUi ui, StorageData storage) {
        String reply = "Got it. Here is the updated task:";
        try {
            return reply + "\n" + storage.updateTasksInData(tasks, this.getDetails());
        } catch (DukeMissingDescriptionException e) {
            return e.getMessage();
        } catch (DukeEmptyDescriptionException ex) {
            return ex.getMessage();
        } catch (DukeUpdateTodoTimeException exx) {
            return exx.getMessage();
        } catch (DukeWrongTimeFormatException exxx) {
            return exxx.getMessage();
        } catch (IndexOutOfBoundsException exxxx) {
            return exxxx.getMessage();
        }
    }
}
