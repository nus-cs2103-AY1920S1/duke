package slave.elements;

import slave.command.*;

import slave.exception.DukeException;
import slave.exception.MissingDateException;
import slave.exception.MissingDescriptionException;
import slave.exception.MissingTaskException;

/**
 * A parser to take in input by user and returns the appropriate command,
 */
public class Parser {

    /**
     * Static method which parses input by user and returns the corresponding command object.
     *
     * @param fullCommand Input by user.
     * @return Corresponding command based on user input.
     * @throws DukeException For invalid input.
     */
    public static Command parse(String fullCommand) throws DukeException {
        String[] tokens = fullCommand.split(" ");
        String firstWord = tokens[0];
        switch(firstWord){
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "help":
            return new HelpCommand();
        case "clear":
            return new ClearCommand();
        case "find":
            return new FindCommand(fullCommand.substring(5));
        case "done":
            checkValidity("done", fullCommand, tokens);
            return new DoneCommand(Integer.parseInt(tokens[1]));
        case "delete":
            checkValidity("delete", fullCommand, tokens);
            return new DeleteCommand(Integer.parseInt(tokens[1]));
        case "todo":
            checkValidity("todo", fullCommand, tokens);
            return new AddToDoCommand(fullCommand.substring(5));
        case "deadline":
            checkValidity("deadline", fullCommand, tokens);
            String[] deadlineSplit = fullCommand.split(" /by ");
            String deadlineDesc = deadlineSplit[0].substring(9);
            String deadlineDate = deadlineSplit[deadlineSplit.length - 1];

            if (isDate(deadlineDate)) {
                String[] dateSplitter = deadlineDate.split(" ");
                Date validDeadlineDate = new Date(dateSplitter[0], dateSplitter[1]);
                return new AddDeadlineCommand(deadlineDesc, validDeadlineDate);
            }

            return new AddDeadlineCommand(deadlineDesc, deadlineDate);
        case "event":
            checkValidity("event", fullCommand, tokens);
            String[] eventSplit = fullCommand.split(" /at ");
            String eventDesc = eventSplit[0].substring(6);
            String eventDate = eventSplit[eventSplit.length - 1];
            if (isDate(eventDate)) {
                String[] eventSplitter = eventDate.split(" ");
                Date validEventDate = new Date(eventSplitter[0], eventSplitter[1]);
                return new AddEventCommand(eventDesc, validEventDate);
            }
            return new AddEventCommand(eventDesc, eventDate);
        default:
            return new NullCommand(firstWord);
        }
    }

    /**
     * Checks if it is a proper date in the form DD/MM/YYYY HHMM.
     *
     * @param dateDescription Date description.
     * @return True or false depending on the validity of the date description in that form.
     */
    private static boolean isDate(String dateDescription) {
        String[] dateSplit = dateDescription.split(" ");
        if (dateSplit.length != 2) {
            return false;
        } else {
            return dateSplit[0].contains("/") &&
                    dateSplit[0].chars().filter(ch -> ch == '/').count() == 2;
        }
    }


    /**
     * Check validity of the deadline/event commands.
     *
     * @param check What command to check.
     * @param input Input from user.
     * @param tokens Input split by space.
     * @throws DukeException Throws exception if date or description is missing.
     */
    private static void checkValidity(String check, String input, String[] tokens) throws DukeException {
        switch(check) {
        case "deadline":
            if (tokens.length <= 1) {
                throw new MissingDescriptionException();
            } else if (!input.contains(" /by ")) {
                throw new MissingDateException();
            }
            return;
        case "event":
            if (tokens.length <= 1) {
                throw new MissingDescriptionException();
            } else if (!input.contains(" /at ")) {
                throw new MissingDateException();
            }
            return;
        case "todo":
            if (tokens.length <= 1) {
                throw new MissingDescriptionException();
            }
            return;
        case "done":
        case "delete":
            if (tokens.length <= 1) {
                throw new MissingTaskException();
            }
            return;
        default:
        }
    }
}
