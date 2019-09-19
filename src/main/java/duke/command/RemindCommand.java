package duke.command;

import duke.exception.FailedToSaveIoException;
import duke.exception.InvalidDateTimeException;
import duke.exception.InvalidParameterException;
import duke.parser.DateParser;
import duke.storage.Storage;
import duke.task.TaskManager;
import duke.ui.UserInterface;

import java.io.IOException;
import java.util.Date;

/**
 *  The <code>RemindCommand</code> is created when the user enters <code>"remind"</code>. The remind command will set a
 *  reminder for the specified task entered by the user. The user interface will display the updated information if it \
 *  is successful.
 */
public class RemindCommand implements Command {

    /**
     * The index of the task to set the reminder for.
     */
    private int index;

    /**
     * The date of the reminder to be set.
     */
    private Date date;

    /**
     * Constructs a new schedule command with the specified index of the task to be reminded in the list of tasks with
     * the specified date.
     * @param line the line of user input that contain the index and date information
     * @throws InvalidParameterException if the information does not exist in the user input line
     */
    public RemindCommand(String line) throws InvalidParameterException {
        String[] arr = line.split(" ");
        try {
            this.index = getIndex(arr);
            this.date = getDate(arr);
        } catch (NumberFormatException nfe) {
            throw new InvalidParameterException("" + index);
        } catch (ArrayIndexOutOfBoundsException aioube) {
            throw new InvalidParameterException(line);
        } catch (InvalidDateTimeException idte) {
            throw new InvalidParameterException(idte.getInvalidDateTime());
        }
    }

    /**
     * Executes the command. This will set a reminder for the task specified by the user as done and display the
     * updated information on the user interface.
     * @param taskManager the task manager for the tasks
     * @param ui the user interface
     * @param storage the storage for the tasks
     * @throws InvalidParameterException if the index is out of range
     */
    public String execute(TaskManager taskManager, UserInterface ui, Storage storage) throws InvalidParameterException {
        try {
            assert (date != null);
            String task = taskManager.remind(index, date);
            storage.save(taskManager.getCurrentTaskListToSave());
            return ui.showSetReminder(task, date.toString());
        } catch (FailedToSaveIoException ftsioe) {
            return ui.showSaveError();
        } catch (IndexOutOfBoundsException aioube) {
            throw new InvalidParameterException("" + index);
        }  catch (IOException ioe) {
            return ui.showSaveError();
        }
    }

    /**
     * Gets the index of the task from the task list to set reminder on from the array.
     * @param arr the array which contains the information
     * @return a string representation of the index of the task
     * @throws ArrayIndexOutOfBoundsException if the information does not exist in the array
     */
    private int getIndex(String[] arr) throws ArrayIndexOutOfBoundsException, NumberFormatException {
        return Integer.parseInt(arr[0]);
    }

    /**
     * Gets the date field of the task from the array.
     * @param arr the array which contains the information
     * @return a string representation of the date field
     * @throws ArrayIndexOutOfBoundsException if the information does not exist in the array
     */
    private Date getDate(String[] arr) throws ArrayIndexOutOfBoundsException, InvalidDateTimeException {
        return DateParser.parse(String.join(" ", arr[1], arr[2]));
    }

}
