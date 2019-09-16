package duke.command;

import duke.exception.FailedToSaveIOException;
import duke.exception.InvalidDateTimeException;
import duke.exception.InvalidParameterException;
import duke.parser.DateParser;
import duke.storage.Storage;
import duke.task.TaskManager;
import duke.ui.UserInterface;

import java.io.IOException;
import java.util.Date;

/**
 *  The <code>DoneCommand</code> is created when the user enters <code>"done"</code>. The done command will mark a
 *  specified task entered by the user as done. The user interface will display the updated information if it is
 *  successful.
 */
public class RemindCommand implements Command {

    /**
     * The index of the task to be mark as done in the list of tasks.
     */
    int index;

    Date date;

    /**
     * Constructs a new done command with the specified index of the task to be marked as done in the list of tasks.
     * @param index the index of the task to be mark as done in the list of tasks
     * @throws InvalidParameterException if the index of the task specified is not a number
     */
    public RemindCommand(String line) throws InvalidParameterException {
        String[] arr = line.split(" ");
        try {
            this.index = getIndex(arr);
            this.date = getDate(arr);
        } catch (NumberFormatException nfe) {
            throw new InvalidParameterException("" + index);
        } catch(ArrayIndexOutOfBoundsException aioube) {
            throw new InvalidParameterException(line);
        } catch (InvalidDateTimeException idte) {
            throw new InvalidParameterException(idte.getInvalidDateTime());
        }
    }

    /**
     * Executes the command. This will mark the task specified by the user as done and display the updated information
     * on the user interface.
     * @param taskManager the list of tasks
     * @param ui the user interface
     * @param storage the storage for the tasks
     * @throws InvalidParameterException if the index is out of range
     */
    public String execute(TaskManager taskManager, UserInterface ui, Storage storage) throws InvalidParameterException {
        try {
            String task = taskManager.remind(index, date);
            storage.save(taskManager.getCurrentTaskListToSave());
            return ui.showSetReminder(task, date.toString());
        } catch (FailedToSaveIOException ftsioe) {
            return ui.showSaveError();
        } catch (IndexOutOfBoundsException aioube) {
            throw new InvalidParameterException("" + index);
        }  catch (IOException ioe) {
            return ui.showSaveError();
        }
    }

    private int getIndex(String[] arr) throws ArrayIndexOutOfBoundsException, NumberFormatException {
        return Integer.parseInt(arr[0]);
    }

    private Date getDate(String[] arr) throws ArrayIndexOutOfBoundsException, InvalidDateTimeException {
        return DateParser.parse(String.join(" ", arr[1], arr[2]));
    }

}
