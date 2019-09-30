package duke.command;

import duke.Duke;
import duke.exception.DukeException;
import duke.exception.InvalidCommandException;
import duke.exception.MissingParameterException;
import duke.task.Task;
import duke.task.Event;
import java.text.ParseException;

/**
 * The class handles methods involving creating an Event task.
 */
public class EventCommand extends Command {

    /**
     * Public constructor for EventCommand.
     * @param parameters Information needed for executing the command (i.e. description and date).
     */
    public EventCommand(String parameters) {
        super(parameters);
    }

    /**
     * Executes the Event command (i.e. creates the Event task object).
     * @return Duke's response during/after creating the Event task object.
     * @throws DukeException if there are missing parameters in the command.
     */
    public String executeCommand() throws DukeException {
        if (parameters.length() <= 0) {
            throw new MissingParameterException(">:( !!! The description and date of a event cannot be empty.");

        } else if (!(parameters.contains("at"))) {
            throw new  InvalidCommandException(">:( !!! Your argument parameters are wrong");

        } else if (!(parameters.contains("/at"))) {
            throw new MissingParameterException(">:( !!! The date of a event cannot be empty.");

        } else {
            try {
                String[] temp = parameters.split("/at");
                if ((temp.length <= 1) || (temp[0].length() < 0) || (temp[1].length() < 0)) {
                    return ">:( !!! The description or date of a event cannot be empty.";
                }

                Task t = new Event(temp[0].trim(), temp[1].trim());
                return Duke.taskList.add(t);

            } catch (ParseException e) {
                return ">:( !!! Your date is not in dd/MM/yyyy HHmm format (e.g. 2/12/2019 1800)";
            }
        }
    }
}
