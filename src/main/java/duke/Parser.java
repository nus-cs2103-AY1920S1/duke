package duke;

import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ShowCommand;
import duke.command.TodoCommand;
import duke.command.UnknownCommand;

import java.lang.ArrayIndexOutOfBoundsException;

/**
 * Handles parsing of user input to commands so that they can be interpreted by the bot.
 */
public class Parser {

    /**
     * Parses the user input and returns a specific command type upon interpretation (on a case-by-case basis of the
     * user input).
     *
     * @param userInput Command given by user.
     * @return Sub-class of Command.
     * @throws DukeException if error in parsing certain Add Command subclasses.
     */
    static Command parse(String userInput) throws DukeException {
        String[] command = userInput.split(" ", 2);
        switch (command[0]) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ShowCommand();
        case "done":
            int i = Integer.parseInt(command[1]) - 1;
            return new DoneCommand(i);
        case "delete":
            int j = Integer.parseInt(command[1]) - 1;
            return new DeleteCommand(j);
        case "todo":
            try {
                String[] detailsTRecurs = command[1].split(" /every ");
                if (detailsTRecurs.length == 1) {
                    return new TodoCommand(detailsTRecurs[0]);
                } else {
                    int freq = Integer.parseInt(detailsTRecurs[1]);
                    return new TodoCommand(detailsTRecurs[0], freq);
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("\u2639 OOPS!!! The description of a todo cannot be empty.");
            }
        case "deadline":
            try {
                String[] detailsD = command[1].split(" /by ");
                String[] detailsDRecurs = detailsD[1].split(" /every ");
                if (detailsDRecurs.length == 1) {
                    return new DeadlineCommand(detailsD[0], detailsDRecurs[0]);
                } else {
                    int freq = Integer.parseInt(detailsDRecurs[1]);
                    return new DeadlineCommand(detailsD[0], detailsDRecurs[0], freq);
                }
            } catch (DukeException e) {
                throw new DukeException(e.getMessage());
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("\u2639 OOPS!!! The description of the deadline is incomplete.");
            }
        case "event":
            try {
                String[] detailsE = command[1].split(" /at ");
                String[] detailsERecurs = detailsE[1].split(" /every ");
                if (detailsERecurs.length == 1) {
                    return new EventCommand(detailsE[0], detailsERecurs[0]);
                } else {
                    int freq = Integer.parseInt(detailsERecurs[1]);
                    return new EventCommand(detailsE[0], detailsERecurs[0], freq);
                }
            } catch (DukeException e) {
                throw new DukeException(e.getMessage());
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("\u2639 OOPS!!! The description of the event is incomplete.");
            }
        case "find":
            return new FindCommand(command[1]);
        default:
            return new UnknownCommand();
        }
    }
}

