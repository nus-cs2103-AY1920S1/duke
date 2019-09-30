public class Parser {

    public static Command parse(String fullCommand) {
        try {
            String command = getCommand(fullCommand);
            String description;
            DateTime dateTime;
            int index;

            switch (command) {
                case "list":
                    return new ListCommand();
                case "done":
                    description = getDescription(fullCommand, command);
                    index = Integer.parseInt(description);
                    return new DoneCommand(index);
                case "todo":
                    description = getDescription(fullCommand, command);
                    return new AddCommand(new Todo(description));
                case "deadline":
                    description = getDescription(fullCommand, command);
                    dateTime = getDateAndTime(fullCommand, command);
                    return new AddCommand(new Deadline(description, dateTime));
                case "event":
                    description = getDescription(fullCommand, command);
                    dateTime = getDateAndTime(fullCommand, command);
                    return new AddCommand(new Event(description, dateTime));
                case "delete":
                    description = getDescription(fullCommand, command);
                    index = Integer.parseInt(description);
                    return new DeleteCommand(index);
                case "find":
                    description = getDescription(fullCommand, command);
                    return new FindCommand(description);
                case "bye":
                    return new ByeCommand();
                case "help":
                    return new HelpCommand();
                default:
                    throw new DukeException("I'm sorry, but I don't know what that means :-(");
            }
        } catch (DukeException e) {
            return new ErrorCommand(e.getMessage());
        }
    }

    public static String getCommand(String input) {
        String command;

        if (input.contains(" ")) {
            int index = input.indexOf(" ");
            command = input.substring(0, index);
        } else {
            command = input;
        }

        return command;
    }

    public static String getDescription(String input, String command) throws DukeException {
        String action = "";

        if (input.contains(" ") && input.contains("/")) {
            int index = input.indexOf(" ") + 1;
            String removeCommand = input.substring(index);
            String[] split = removeCommand.split("/", 2);
            action = split[0];
        } else if (input.contains(" ")) {
            String[] split = input.split(" ", 2);
            action = split[1];
        }

        if (action.equals("")) {
            throw new DukeException("The description of a " + command + " cannot be empty.");
        }

        return action;
    }

    public static DateTime getDateAndTime(String input, String command) throws DukeException {
        String detail = "";
        DateTime dateTime;

        if (input.contains(" ") && input.contains("/")) {
            int index = input.indexOf(" ");
            String removeCommand = input.substring(index);
            String[] split = removeCommand.split("/", 2);
            index = split[1].indexOf(" ") + 1;
            detail = split[1].substring(index);
        }

        if (detail.equals("")) {
            throw new DukeException("The time and date of a " + command + " cannot be empty.");
        } else {
            dateTime = new DateTime(detail);
            dateTime.setDateAndTime();
        }

        return dateTime;
    }
}

