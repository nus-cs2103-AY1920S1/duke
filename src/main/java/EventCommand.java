import exception.DukeException;
import tasks.Event;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

public class EventCommand extends Command {
    public EventCommand(String desc) {
        super(desc);
    }

    public String execute(Storage storage, TaskList taskList, Ui ui) throws IOException, ParseException {
        try {
            if (desc.length() <= 5) {
                throw new DukeException();
            }
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
        return descArr[0].substring(6, descArr[0].length() - 1);
    }
}
