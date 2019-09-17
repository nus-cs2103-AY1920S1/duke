package duke.parser;

import duke.command.*;
import duke.exception.DukeException;

import java.text.SimpleDateFormat;

public class Parser {

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy HHmm");

    /**
     * Reads the first word of user input as command and recognises what type of command
     * the user is looking for
     * @param command first word of user input
     * @return new Command object based on what the user input is
     * @throws DukeException if the user's input is not recognised
     */
    public static Command parse(String command) throws DukeException {
        assert command!=null : "command should be valid input string";
            switch (command) {
            case ("list"):
                return new ListCommand();

            case ("bye"):
                return new ExitCommand();

            case ("done"):
                return new DoneCommand();

            case ("delete"):
                return new DeleteCommand();

            case ("deadline"):
                return new DeadlineCommand();

            case ("todo"):
                return new TodoCommand();

            case ("event"):
                return new EventCommand();

            case ("find") :
                return new FindCommand();

            default:
                throw new DukeException("☹OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
}
