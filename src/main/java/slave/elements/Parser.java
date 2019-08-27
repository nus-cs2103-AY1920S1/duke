package slave.elements;

import slave.command.*;
import slave.exception.*;

public class Parser {

    public static Command parse(String fullCommand) throws DukeException{
        String[] tokens = fullCommand.split(" ");
        String firstWord = tokens[0];
        switch(firstWord){
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "help":
            return new HelpCommand();
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
            if (isDate(deadlineDate)){
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
            if (isDate(eventDate)){
                String[] eventSplitter = eventDate.split(" ");
                Date validEventDate = new Date(eventSplitter[0], eventSplitter[1]);
                return new AddEventCommand(eventDesc, validEventDate);
            }
            return new AddEventCommand(eventDesc, eventDate);
        default:
            return new NullCommand(firstWord);
        }
    }

    public static boolean isDate(String dateDescription){
        String[] dateSplit = dateDescription.split(" ");
        if (dateSplit.length != 2){
            return false;
        } else if (!dateSplit[0].contains("/")){
            return false;
        }
        return true;
    }

    public static void checkValidity(String check, String input, String[] tokens) throws DukeException{
        switch(check){
        case "deadline":
            if (tokens.length <= 1){
                throw new MissingDescriptionException();
            } else if (!input.contains(" /by ")){
                throw new MissingDateException();
            }
            return;
        case "event":
            if (tokens.length <= 1){
                throw new MissingDescriptionException();
            } else if (!input.contains(" /at ")){
                throw new MissingDateException();
            }
            return;
        case "todo":
            if (tokens.length <= 1){
                throw new MissingDescriptionException();
            }
            return;
        case "done":
        case "delete":
            if (tokens.length <= 1){
                throw new MissingTaskException();
            }
            return;
        default:
        }
    }
}
