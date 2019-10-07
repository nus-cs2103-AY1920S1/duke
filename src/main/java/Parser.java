import java.util.Stack;

public class Parser {
    private static Stack<UndoableCommand> commandHistory = new Stack<>();

    /**
     * A method that parses user input lines and converts them into executable Command objects.
     * @param s A String that contains the user input line
     * @return A Command object representing the command as parsed
     */
    static Command parse(String s) throws DukeException {
        String[] arg = s.split(" ", 2);
        assert arg.length <= 2;
        Command command;
        switch (arg[0]) {
        case "bye":
            command = new ByeCommand();
            break;
        case "list":
            command = new ListCommand();
            break;
        case "undo":
            command = new UndoCommand(commandHistory);
            break;
        case "done":
        case "delete":
            if (arg.length < 2 || arg[1].trim().length() < 1) {
                throw new DukeEmptyDescException(arg[0]);
            }
            int idx;
            try {
                idx = Integer.parseInt(arg[1]);
            } catch (NumberFormatException e) {
                throw new DukeInvalidTaskException(arg[1]);
            }
            if (arg[0].equals("done")) {
                command = new DoneCommand(idx);
            } else { //Command must be "delete"
                command = new DeleteCommand(idx);
            }
            break;
        case "todo":
        case "deadline":
        case "event":
            if (arg.length < 2 || arg[1].trim().length() < 1) {
                throw new DukeEmptyDescException(arg[0]);
            }
            Task t;
            if (arg[0].equals("todo")) {
                command = new TodoCommand(arg[1]);
            } else if (arg[0].equals("deadline")) {
                String[] temp = arg[1].split(" /by ", 2);
                if (temp.length < 2 || temp[1].trim().length() < 1) {
                    throw new DukeEmptyDateException(arg[0]);
                }
                command = new DeadlineCommand(temp[0], temp[1]);
            } else { //Task must be "event"
                String[] temp = arg[1].split(" /at ", 2);
                if (temp.length < 2 || temp[1].trim().length() < 1) {
                    throw new DukeEmptyDateException(arg[0]);
                }
                command = new EventCommand(temp[0], temp[1]);
            }
            break;
        case "find":
            if (arg.length < 2) {
                throw new DukeEmptyDescException(arg[0]);
            }
            command = new FindCommand(arg[1]);
            break;
        default:
            throw new DukeException();
        }
        if (command instanceof UndoableCommand) {
            commandHistory.add((UndoableCommand) command);
        }
        assert command != null;
        return command;
    }
}
