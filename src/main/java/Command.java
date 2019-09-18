import java.util.Date;
import java.io.IOException;
import java.text.ParseException;

/**
 * Abstract class that represents a command.
 *
 * @author Michelle Yong
 */
public abstract class Command {
    String desc;

    /**
     * Creates a command.
     */
    public Command() {}

    /**
     * Creates a command with description.
     *
     * @param desc The description for the command.
     */
    public Command(String desc) {
        this.desc = desc;
    }

    /**
     * Abstract method that executes the command and returns the message.
     *
     * @param storage The storage for the file with all the tasks.
     * @param taskList The taskList used.
     * @param ui The User Interface used.
     * @return The message shown to the user.
     * @throws IOException If an input or output exception occurred.
     * @throws ParseException If a parse exception occurred.
     * @throws AssertionError If an assertion error occurred.
     */
    public abstract String execute(Storage storage, TaskList taskList, Ui ui) throws
            IOException, ParseException, AssertionError;

    /**
     * Gets the date.
     *
     * @param descArr The command line with description and date separated.
     * @param storage The storage for the task manager.
     * @return The date.
     * @throws ParseException If a parse exception occurred.
     */
    public Date getDate(String[] descArr, Storage storage) throws ParseException {
        return storage.convertToDate(descArr[1].substring(3));
    }

    /**
     * Gets the task number.
     *
     * @param arr The array with command and description separated.
     * @return The task number.
     */
    public int getTaskNum(String[] arr) {
        return Integer.parseInt(arr[1]) - 1;
    }
}