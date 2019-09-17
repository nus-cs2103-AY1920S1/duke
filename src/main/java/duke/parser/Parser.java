package duke.parser;

import duke.command.Command;
import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.AddTodoCommand;
import duke.command.ByeCommand;
import duke.command.ClearAllCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ErrorCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;
import duke.command.MassDeleteCommand;
import duke.command.TutorialCommand;


/**
 * Class that takes in a String and parse it to return a valid command.
 */
public class Parser {

    /**
     * Used to take in a string input and returns a command if the input is valid.
     * @param input Input made by the user.
     * @return The command corresponding to the user input.
     * @throws Exception Used to handle any exception that occurs.
     */
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
        case "find" :
            return new FindCommand(constructedString);
        case "help" :
            return new HelpCommand(constructedString);
        case "massdelete" :
            return new MassDeleteCommand(constructedString);
        case "clearall" :
            return new ClearAllCommand(constructedString);
        case "tutorial" :
            return new TutorialCommand(constructedString);
        default:
            return new ErrorCommand("OOPS!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
