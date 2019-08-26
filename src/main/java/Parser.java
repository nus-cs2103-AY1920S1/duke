public class Parser {

    public static Command parse(String fullCommand) throws DukeException {
        //Parse the string to check which command it is referring to
        String[] details = fullCommand.trim().split("\\s+");

        switch (details[0]) {
            case "list":
                return new ListCommand();
            case "done":
                return new DoneCommand(Integer.parseInt(details[1]) - 1);
            case "delete":
                return new DeleteCommand(Integer.parseInt(details[1]) - 1);
            case "todo":
            case "deadline":
            case "event":
                return new AddCommand(details[0], fullCommand.split(details[0]));
            case "bye":
                return new ExitCommand();
            default:
                throw new DukeException("I'm sorry, but I don't know what that means :-( ");
        }
    }
}
