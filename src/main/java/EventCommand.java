import exception.DukeException;
import tasks.Event;
import java.util.Date;
import java.io.IOException;
import java.text.ParseException;

/**
 * Represents a event command.
 *
 * @author Michelle Yong
 */
public class EventCommand extends Command {
    /**
     * Creates an event command with the description.
     *
     * @param desc The description for the event command.
     */
    public EventCommand(String desc) {
        super(desc);
    }

    /**
     * Executes the event command and shows that the event has been added to the list.
     *
     * @param storage The storage for the file with all the tasks.
     * @param taskList The taskList used.
     * @param ui The User Interface used.
     * @return The message telling user that the event has been added.
     * @throws IOException If an input or output exception occurred.
     * @throws ParseException If a parse exception occurred.
     */
    public String execute(Storage storage, TaskList taskList, Ui ui) throws IOException, ParseException {
        try {
            if (desc.length() <= 5) {
                throw new DukeException();
            }
            assert (desc.length() > 5);
            String[] descArr = desc.split("/");;
            Date date = getDate(descArr, storage);
            Event event = new Event(getEventDesc(descArr), date);
            taskList.addTask(event);
            storage.appendTaskToFile(event);
            return ui.showTaskAdded(event, taskList.getSize());
        } catch (DukeException e) {
            return ui.showEventError();
        }
    }

    /**
     * Gets the description of the event.
     *
     * @param descArr The string array with command and description separated.
     * @return The description of the event.
     */
    public String getEventDesc(String[] descArr) {
        assert (descArr[0].length() >= 6);
        return descArr[0].substring(6, descArr[0].length() - 1);
    }
}