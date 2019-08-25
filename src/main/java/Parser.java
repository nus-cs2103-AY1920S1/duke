import java.util.List;

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
                return new DoneCommand();
            case "delete":
                return new DeleteCommand();
            case "todo":
                return new AddToDoCommand();
            case "deadline":
                return new AddDeadlineCommand();
            case "event":
                return new AddEventCommand();
            default:
                return new NullCommand();
        }
    }
}
