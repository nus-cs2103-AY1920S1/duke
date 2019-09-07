package duke;

import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.AddTodoCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.TagCommand;
import duke.exception.DukeException;
import duke.exception.InvalidInputException;
import duke.exception.MissingDescriptionException;

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
            return exit();
        } else if (userInput.equals("list")) {
            return list();
        } else if (userInput.startsWith("todo")) {
            return todo(userInput);
        } else if (userInput.startsWith("deadline")) {
            return deadline(userInput);
        } else if (userInput.startsWith("event")) {
            return event(userInput);
        } else if (userInput.startsWith("done")) {
            return done(userInput);
        } else if (userInput.startsWith("delete")) {
            return delete(userInput);
        } else if (userInput.startsWith("find")) {
            return find(userInput);
        } else if (userInput.startsWith("tag")) {
            return tag(userInput);
        } else {
            throw new InvalidInputException();
        }
    }

    private static Command tag(String userInput) throws MissingDescriptionException {
        String details = userInput.replaceFirst("tag", "").trim();
        if (details.charAt(0) == '#') {
            throw new MissingDescriptionException("tag");
        }
        int index = Integer.parseInt("" + details.charAt(0)) - 1;
        String hashTags = details.substring(1).trim();
        String[] tags = hashTags.split("#");
        return new TagCommand(index, tags);
    }

    private static Command find(String userInput) {
        String details = userInput.replaceFirst("find", "");
        return new FindCommand(details.trim());
    }

    private static Command delete(String userInput) throws MissingDescriptionException {
        String[] deleteDetails = userInput.split(" ");
        boolean descriptionIsEmpty = deleteDetails.length < 2;
        if (descriptionIsEmpty) {
            throw new MissingDescriptionException("delete");
        }
        String listActionIndex = deleteDetails[1].trim();
        int arrayActionIndex = Integer.parseInt(listActionIndex) - 1;
        return new DeleteCommand(arrayActionIndex);
    }

    private static Command done(String userInput) throws MissingDescriptionException {
        String[] doneDetails = userInput.split(" ");
        boolean descriptionIsEmpty = doneDetails.length < 2;
        if (descriptionIsEmpty) {
            throw new MissingDescriptionException("done");
        }
        String listActionIndex = doneDetails[1].trim();
        int arrayActionIndex = Integer.parseInt(listActionIndex) - 1;
        return new DoneCommand(arrayActionIndex);
    }

    private static Command event(String userInput) {
        String details = userInput.replaceFirst("event", "");
        return new AddEventCommand(details.trim());
    }

    private static Command deadline(String userInput) {
        String details = userInput.replaceFirst("deadline", "");
        return new AddDeadlineCommand(details.trim());
    }

    private static Command todo(String userInput) {
        String details = userInput.replaceFirst("todo", "");
        return new AddTodoCommand(details.trim());
    }

    private static Command list() {
        return new ListCommand();
    }

    private static Command exit() {
        return new ExitCommand();
    }
}
