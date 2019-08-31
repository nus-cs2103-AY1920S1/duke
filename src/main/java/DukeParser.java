public class DukeParser {

    public DukeParser() {

    }

    public static Command parse(String input) {
        String command = input.split(" ")[0].trim(); //get the first word;
        switch (command) {
        case "bye":
            return new ByeCommand();
        case "list":
            return new ListCommand();
        case "done":
            return new DoneCommand();
        case "delete":
            return new DeleteCommand();
        case "todo":
            return new ToDoCommand();
        case "deadline":
            return new DeadlineCommand();
        case "event":
            return new EventCommand();
        case "help":
            return new HelpCommand();
        case "find":
            return new FindCommand();
        default:
            throw new InvalidCommandDukeException(command);
        }
    }
}
