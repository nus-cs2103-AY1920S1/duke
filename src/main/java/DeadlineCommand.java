import exception.DukeException;
import tasks.Deadline;
import java.util.Date;
import java.io.IOException;
import java.text.ParseException;

/**
 * Represents a deadline command.
 *
 * @author Michelle Yong
 */
public class DeadlineCommand extends Command {
    /**
     * Creates a deadline command with the description.
     *
     * @param description The description for the deadline command.
     */
    public DeadlineCommand(String description) {
        super(description);
    }

    /**
     * Executes the deadline command and shows the deadline has been added to the list.
     *
     * @param storage The storage for the file with all the tasks.
     * @param taskList The taskList used.
     * @param ui The User Interface used.
     * @return The message telling user that the deadline has been added.
     * @throws IOException If an input or output exception occurred.
     * @throws ParseException If a parse exception occurred.
     */
    public String execute(Storage storage, TaskList taskList, Ui ui) throws
            IOException, ParseException {
        try {
            if (description.length() <= 8) {
                throw new DukeException();
            }
            assert (description.length() > 8);
            String[] descArr = description.split("/");
            Date date = getDate(descArr, storage);
            Deadline deadline = new Deadline(getDeadlineDesc(descArr), date);
            taskList.addTask(deadline);
            storage.appendTaskToFile(deadline);
            return ui.showTaskAdded(deadline, taskList.getSize());
        } catch (DukeException e) {
            return ui.showDeadlineError();
        }
    }

    /**
     * Gets the description of the deadline.
     *
     * @param descArr The string array with command and description separated.
     * @return The description of the deadline.
     */
    public String getDeadlineDesc(String[] descArr) {
        assert (descArr[0].length() >= 9);
        return descArr[0].substring(9, descArr[0].length() - 1);
    }
}