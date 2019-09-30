package duke.command;

import duke.Duke;
import duke.exception.InvalidCommandException;
import duke.exception.MissingParameterException;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Task;
import java.text.ParseException;

/**
 * The class handles methods involving creating a Deadline task.
 */
public class DeadlineCommand extends Command {
    /**
     * Public constructor for DeadlineCommand.
     * @param parameters Information needed to execute the command (i.e. date and description).
     */
    public DeadlineCommand(String parameters) {
        super(parameters);
    }

    /**
     * Executes the Deadline command (i.e. creates the Deadline task object).
     * @return Duke's response during/after creating the Deadline task object.
     * @throws MissingParameterException if there are missing parameters in the command.
     */
    public String executeCommand() throws DukeException {
        if (parameters.length() <= 0) {
            throw new MissingParameterException(">:( !!! The description and date of a"
                    + "deadline cannot be empty.");

        } else if (!(parameters.contains("by"))) {
            throw new InvalidCommandException(">:( !!! Your argument parameters are wrong");

        } else if (!(parameters.contains("/by"))) {
            throw new MissingParameterException(">:( !!! The date of a deadline cannot be empty.");

        } else {
            try {
                String[] temp = parameters.split("/by");
                if ((temp.length <= 1) || (temp[0].length() < 0) || (temp[1].length() < 0)) {
                    return ">:( !!! The description or date of a deadline cannot be empty.";
                }

                Task t = new Deadline(temp[0].trim(), temp[1].trim());
                return Duke.taskList.add(t);

            } catch (ParseException e) {
                return ">:( !!! Your date is not in dd/MM/yyyy HHmm format";
            }
        }
    }
}
