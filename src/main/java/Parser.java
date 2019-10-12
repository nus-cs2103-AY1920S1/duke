public class Parser {

    /**
     * Returns the command given by the user.
     * Accepts the string command given and create a new Comment object
     * depending on the command type.
     * @param command refers to the input by the user
     * @return a new Command object
     */
    public static Command parse(String command) throws DukeException {
        String commandType = command.split(" ")[0];
        String instruction = "";
        String date = "";

        switch (commandType) {

        case "bye":
            return new ByeCommand();

        case "list":
            return new ListCommand();

        case "delete":
            instruction = command.substring(command.indexOf(" ") + 1);
            return new DeleteCommand(instruction);

        case "done":
            instruction = command.substring(command.indexOf(" ") + 1);
            return new DoneCommand(instruction);

        case "find":
            instruction = command.substring(command.indexOf(" ") + 1);
            return new FindCommand(instruction);

        case "todo":
            instruction = command.substring(command.indexOf(" ") + 1);
            return new AddTodoCommand(instruction);

        case "deadline":
            if (command.contains("/")) {
                instruction = command.substring(command.indexOf(" ") + 1, command.indexOf("/") - 1);
                assert command.length() > command.indexOf("/") + 4 : "Incomplete date";
                date = command.substring(command.indexOf("/") + 4);
            }

            return new AddDeadlineCommand(instruction, date);

        case "event":
            if (command.contains("/")) {
                instruction = command.substring(command.indexOf(" ") + 1, command.indexOf("/") - 1);
                assert command.length() > command.indexOf("/") + 4 : "Incomplete date";
                date = command.substring(command.indexOf("/") + 4);
            }

            return new AddEventCommand(instruction, date);

        default:
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

    }
}
