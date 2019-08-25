package duke;

import duke.command.*;
import duke.exception.DukeException;

public class Parser {
    public static Command parse(String fullCommand) throws DukeException {
        String userInput = fullCommand.replaceAll("\\s+", " ");
        if (userInput.equals("bye")) {
            return new ExitCommand();
        } else if (userInput.equals("list")) {
            return new ListCommand();
        } else if (userInput.startsWith("todo")) {
            String details = userInput.replaceFirst("todo", "");
            return new AddTodoCommand(details);
        } else if (userInput.startsWith("deadline")) {
            String details = userInput.replaceFirst("deadline", "");
            return new AddDeadlineCommand(details);
        } else if (userInput.startsWith("event")) {
            String details = userInput.replaceFirst("event", "");
            return new AddEventCommand(details);
        } else if (userInput.contains("done")) {
            String[] doneDetails = userInput.split(" ");
            if (doneDetails.length < 2) {
                throw new DukeException("\u2639 OOPS!!! The description of done cannot be empty.");
            }
            String listActionIndex = doneDetails[1];
            int arrayActionIndex = Integer.parseInt(listActionIndex) - 1;
            return new DoneCommand(arrayActionIndex);
        } else if (userInput.contains("delete")) {
            String[] deleteDetails = userInput.split(" ");
            if (deleteDetails.length < 2) {
                throw new DukeException("\u2639 OOPS!!! The description of delete cannot be empty.");
            }
            String listActionIndex = deleteDetails[1];
            int arrayActionIndex = Integer.parseInt(listActionIndex) - 1;
            return new DeleteCommand(arrayActionIndex);
        } else {
            throw new DukeException("\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
