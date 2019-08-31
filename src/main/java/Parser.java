public class Parser {

    public static Command parse(String rawString) throws DukeException{
        if (rawString.equals("bye")) {
            return new ByeCommand();
        } else if (rawString.equals("list")) {
            return new ListCommand();
        } else if (rawString.startsWith("done ")) {
            return new DoneCommand(rawString);
        } else if (rawString.startsWith("todo")) {
            return new ToDoCommand(rawString);
        } else if (rawString.startsWith("deadline ")) {
            return new DeadlineCommand(rawString);
        } else if (rawString.startsWith("event ")) {
            return new EventCommand(rawString);
        } else if (rawString.startsWith("delete ")) {
            return new DeleteCommand(rawString);
        } else {
            throw new InvalidInputException("Invalid command! Try the commands: bye, list, done, todo, deadline, " +
                    "event or delete and their respective formats!");
        }
    }
}