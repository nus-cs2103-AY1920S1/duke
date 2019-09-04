package duke.parser;

import duke.command.Command;
import duke.command.AddTodoCommand;
import duke.command.AddEventCommand;
import duke.command.AddDeadlineCommand;
import duke.command.ByeCommand;
import duke.command.ListCommand;
import duke.command.DoneCommand;
import duke.command.DeleteCommand;
import duke.command.ErrorCommand;
import duke.exception.DukeException;

public class Parser {
    public static Command parse(String input) throws Exception {
        String[] inputArray = input.split(" ");
        String constructedString = "";
        for (int i = 1; i < inputArray.length; i++) {
            if (i == inputArray.length - 1) {
                constructedString += inputArray[i];
            } else {
                constructedString += inputArray[i];
                constructedString += " ";
            }
        }
        String command = inputArray[0].toLowerCase();
        switch (command) {
        case "todo":
            return new AddTodoCommand(constructedString);
        case "event":
            return new AddEventCommand(constructedString);
        case "deadline":
            return new AddDeadlineCommand(constructedString);
        case "bye":
            return new ByeCommand(constructedString);
        case "list":
            return new ListCommand(constructedString);
        case "done":
            return new DoneCommand(constructedString);
        case "delete":
            return new DeleteCommand(constructedString);
        default:
            return new ErrorCommand("     OOPS!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
