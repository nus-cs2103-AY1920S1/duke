package duke;

import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.AddTodoCommand;
import duke.command.ClearCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;

import static duke.ui.ErrorMsg.UNKNOWN_COMMAND;

public class Parser {
    /**
     * Parses the line from a scanner, returns a Command based on the line.
     *
     * @param line line from scanner (see ui.readLine())
     * @return Command subclass based on line
     * @throws DukeException generic exception that contains error message
     */
    public Command parse(String line) throws DukeException {
        switch (line) {
        case "help":
            return new HelpCommand();
        case "clear":
            return new ClearCommand();
        case "exit":
        case "bye"://exit
            return new ExitCommand();
        case "list"://list
            return new ListCommand();
        default:
            //Observe that line.equals and startsWith are used in tandem.
            //line.equals is used for proper error handling (if command called without args)
            if (line.equals("done") || line.startsWith("done ")) {
                //Set task to done
                return new DoneCommand(line);
            } else if (line.equals("delete") || line.startsWith("delete ")) {
                return new DeleteCommand(line);
            } else if (line.equals("todo") || line.startsWith("todo ")) {
                return new AddTodoCommand(line);
            } else if (line.equals("event") || line.startsWith("event ")) {
                return new AddEventCommand(line);
            } else if (line.equals("deadline") || line.startsWith("deadline ")) {
                return new AddDeadlineCommand(line);
            } else if (line.equals("find") || line.startsWith("find ")) {
                return new FindCommand(line);
            } else {
                throw new DukeException(UNKNOWN_COMMAND);
            }
        }
    }
}
