public class Parser {

    public static Command parse(String userCmd) throws DukeException {
        switch (userCmd) {
        case "list":
            return new ListCommand();
        case "done":
            return new DoneCommand();
        case "delete":
            return new DeleteCommand();
        case "exit":
            return new ExitCommand();
        case "todo":
        case "event":
        case "deadline":
            return new AddCommand(userCmd);
        default:
            throw new DukeException("Invalid Command");
        }
    }
}
