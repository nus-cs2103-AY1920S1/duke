import exception.DukeException;
import tasks.Deadline;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

public class DeadlineCommand extends Command {
    public DeadlineCommand(String desc) {
        super(desc);
    }

    public String execute(Storage storage, TaskList taskList, Ui ui) throws IOException, ParseException {
        try {
            if (desc.length() <= 8) {
                throw new DukeException();
            }
            String[] descArr = desc.split("/");
            Date date = getDate(descArr, storage);
            Deadline deadline =
                    new Deadline(getDeadlineDesc(descArr), date);
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
        return descArr[0].substring(9, descArr[0].length() - 1);
    }
}
