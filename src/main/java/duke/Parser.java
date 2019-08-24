package duke;

import duke.command.*;

public class Parser {
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
            if (line.indexOf("done ") == 0) {//Set task to done
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
