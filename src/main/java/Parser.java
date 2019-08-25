public class Parser {

    /**
     * A method that parses user input lines and converts them into executable Command objects.
     * @param s A String that contains the user input line
     * @return A Command object representing the command as parsed
     */
    static Command parse(String s) throws DukeException {
        String[] arg = s.split(" ", 2);
        switch (arg[0]) {
        case "bye":
            return new ByeCommand();
        case "list":
            return new ListCommand();
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
                return new DoneCommand(idx);
            } else { //Command must be "delete"
                return new DeleteCommand(idx);
            }
        case "todo":
        case "deadline":
        case "event":
            if (arg.length < 2 || arg[1].trim().length() < 1) {
                throw new DukeEmptyDescException(arg[0]);
            }
            Task t;
            if (arg[0].equals("todo")) {
                return new TodoCommand(arg[1]);
            } else if (arg[0].equals("deadline")) {
                String[] temp = arg[1].split(" /by ", 2);
                if (temp.length < 2 || temp[1].trim().length() < 1) {
                    throw new DukeEmptyDateException(arg[0]);
                }
                return new DeadlineCommand(temp[0], temp[1]);
            } else { //Task must be "event"
                String[] temp = arg[1].split(" /at ", 2);
                if (temp.length < 2 || temp[1].trim().length() < 1) {
                    throw new DukeEmptyDateException(arg[0]);
                }
                return new EventCommand(temp[0], temp[1]);
            }
        case "find":
            if (arg.length < 2) {
                throw new DukeEmptyDescException(arg[0]);
            }
            return new FindCommand(arg[1]);
        default:
            throw new DukeException();
        }
    }
}
