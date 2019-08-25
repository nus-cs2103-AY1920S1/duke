public class Parser {

    public static Command parse(String fullCommand){
        String[] tokens = fullCommand.split(" ");
        String firstWord = tokens[0];
        switch(firstWord){
            case "bye":
                return new ExitCommand();
            case "list":
                return new ListCommand();
            case "done":
                return new DoneCommand(Integer.parseInt(tokens[1]));
            case "delete":
                return new DeleteCommand(Integer.parseInt(tokens[1]));
            case "todo":
                return new AddToDoCommand(fullCommand.substring(5));
            case "deadline":
                String[] deadlineSplit = fullCommand.split(" /by ");
                String deadlineDesc = deadlineSplit[0].substring(9);
                String deadlineDate = deadlineSplit[deadlineSplit.length - 1];
                return new AddDeadlineCommand(deadlineDesc, deadlineDate);
            case "event":
                String[] eventSplit = fullCommand.split(" /at ");
                String eventDesc = eventSplit[0].substring(6);
                String eventDate = eventSplit[eventSplit.length - 1];
                return new AddEventCommand(eventDesc, eventDate);
            default:
                return new NullCommand();
        }
    }
}
