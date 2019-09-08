package duke.command;

import duke.component.DukeDatabase;
import duke.component.TaskList;

/**
 * Encapsulate a command which list all the tasks in the tasks list of Duke.
 */
public class ListCommand extends QueryCommand {

    /**
     * Returns a message which contains the descriptions of all the tasks in the tasks list of Duke.
     *
     * @param database database of Duke.
     * @param tasksList tasks list of Duke.
     * @return
     */
    public String execute(DukeDatabase database, TaskList tasksList) {
        StringBuilder builder = new StringBuilder(250);

        String header = "Here are the tasks in your list:\n";
        String printedList = getPrintedList(tasksList);

        builder.append(header);
        builder.append(printedList);

        return builder.toString();
    }
}
