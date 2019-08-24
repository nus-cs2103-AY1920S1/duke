package duke;

import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.AddTodoCommand;
import duke.command.ClearCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;

public class Parser {
    /**
     * Parses the line from a scanner, returns a Command based on the line.
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
            if (line.indexOf("done ") == 0) {
                //Set task to done
                return new DoneCommand(line);
            } else if (line.indexOf("delete ") == 0) {
                return new DeleteCommand(line);
            } else if (line.indexOf("todo ") == 0) {
                return new AddTodoCommand(line);
            } else if (line.indexOf("event ") == 0) {
                return new AddEventCommand(line);
            } else if (line.indexOf("deadline ") == 0) {
                return new AddDeadlineCommand(line);
            } else {
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
            }
        }
    }
}
