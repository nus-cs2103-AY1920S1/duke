package duke;

import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.AddTodoCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.exception.DukeException;

/**
 * Represents a parser to make sense of inputs.
 */
public class Parser {

    /**
     * Processes the user input and creates the corresponding commands.
     * @param fullCommand Command input by user.
     * @return Corresponding command that is input by user.
     * @throws DukeException If a new command cannot be created due to invalid input parameters.
     */
    public static Command parse(String fullCommand) throws DukeException {
        String userInput = fullCommand.replaceAll("\\s+", " ");
        if (userInput.equals("bye")) {
            return new ExitCommand();
        } else if (userInput.equals("list")) {
            return new ListCommand();
        } else if (userInput.startsWith("todo")) {
            String details = userInput.replaceFirst("todo", "");
            return new AddTodoCommand(details.trim());
        } else if (userInput.startsWith("deadline")) {
            String details = userInput.replaceFirst("deadline", "");
            return new AddDeadlineCommand(details.trim());
        } else if (userInput.startsWith("event")) {
            String details = userInput.replaceFirst("event", "");
            return new AddEventCommand(details.trim());
        } else if (userInput.contains("done")) {
            String[] doneDetails = userInput.split(" ");
            if (doneDetails.length < 2) {
                throw new DukeException("\u2639 OOPS!!! The description of done cannot be empty.");
            }
            String listActionIndex = doneDetails[1].trim();
            int arrayActionIndex = Integer.parseInt(listActionIndex) - 1;
            return new DoneCommand(arrayActionIndex);
        } else if (userInput.contains("delete")) {
            String[] deleteDetails = userInput.split(" ");
            if (deleteDetails.length < 2) {
                throw new DukeException("\u2639 OOPS!!! The description of delete cannot be empty.");
            }
            String listActionIndex = deleteDetails[1].trim();
            int arrayActionIndex = Integer.parseInt(listActionIndex) - 1;
            return new DeleteCommand(arrayActionIndex);
        } else {
            throw new DukeException("\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
