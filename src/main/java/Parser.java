public class Parser {

    public static Command parse(String fullCommand) throws DukeException{
        String[] tokens = fullCommand.split(" ");
        String firstWord = tokens[0];
        switch(firstWord){
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
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
            return new AddDeadlineCommand(deadlineDesc, deadlineDate);
        case "event":
            checkValidity("event", fullCommand, tokens);
            String[] eventSplit = fullCommand.split(" /at ");
            String eventDesc = eventSplit[0].substring(6);
            String eventDate = eventSplit[eventSplit.length - 1];
            return new AddEventCommand(eventDesc, eventDate);
        default:
            return new NullCommand();
        }
    }

    public static void checkValidity(String check, String input, String[] tokens) throws DukeException{
        switch(check){
        case "deadline":
            if (tokens.length <= 1){
                throw new DukeException("Oh no! Missing description!", DukeExceptionType.MISSINGDESCRIPTION);
            } else if (!input.contains(" /by ")){
                throw new DukeException("Oh no! Missing date!", DukeExceptionType.MISSINGDATE);
            }
            return;
        case "event":
            if (tokens.length <= 1){
                throw new DukeException("Oh no! Missing description!", DukeExceptionType.MISSINGDESCRIPTION);
            } else if (!input.contains(" /at ")){
                throw new DukeException("Oh no! Missing date!", DukeExceptionType.MISSINGDATE);
            }
            return;
        case "todo":
            if (tokens.length <= 1){
                throw new DukeException("Oh no! Missing description!", DukeExceptionType.MISSINGDESCRIPTION);
            }
            return;
        case "done":
        case "delete":
            if (tokens.length <= 1){
                throw new DukeException("Oh no! No task specified!", DukeExceptionType.MISSINGTASK);
            }
            return;
        default:
        }
    }
}
