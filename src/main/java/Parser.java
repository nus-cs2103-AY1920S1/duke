/**
 * Deals with making sense of user input.
 */
public class Parser {

    /**
     * Creates a Parser object.
     */
    public Parser() {
    }

    /**
     * Returns differnt types of Command objects depending on user input.
     * @param command represents the input by the user
     * @return Command object according to what the user inputs
     * @throws DukeException when the input is missing information or
     *                       in the wrong format
     */
    public static Command parse(String command) throws DukeException {
        String[] commandSplit = command.split(" ");
        String type = commandSplit[0];

        Type enumType;
        String timeDesc;

        switch (type) {
            case "list":
                return new ListCommand();

            case "done":
                if (commandSplit.length == 1) {
                    throw new DukeException("☹ OOPS!!! Please indicate which " +
                                            "task you have completed.");
                }

                return new DoneCommand(commandSplit[1]);

            case "delete":
                if (commandSplit.length == 1) {
                    throw new DukeException("☹ OOPS!!! Please indicate which task " +
                                            "you would like to delete.");
                }

                return new DeleteCommand(commandSplit[1]);

            case "bye":
                return new ExitCommand();

            case "find":
                if (commandSplit.length == 1) {
                    throw new DukeException("☹ OOPS!!! Please indicate the keyword " +
                                            "you are looking for!");
                }

                return new KeyCommand(commandSplit[1]);

            case "todo":
            case "deadline":
            case "event":
                if (commandSplit.length == 1) {
                    throw new DukeException("☹ OOPS!!! The description of a " +
                                            command + " cannot be empty.");
                }

                switch (type) {
                    case "todo":
                        enumType = Type.valueOf(type.toUpperCase());
                        return new AddCommand(enumType, command.substring(5), "");

                    case "deadline": {
                        String[] timeSplit = command.split("/by");

                        if (timeSplit.length == 1) {
                            throw new DukeException(" ☹ OOPS!!! Please enter a " +
                                                    "deadline for your task.");
                        }

                        timeDesc = timeSplit[1];
                        enumType = Type.valueOf(type.toUpperCase());
                        return new AddCommand(enumType, timeSplit[0].substring(9), timeDesc);
                    }

                    case "event":
                        String[] timeSplit = command.split("/at");
                        timeDesc = timeSplit[1];
                        enumType = Type.valueOf(type.toUpperCase());

                        if (timeSplit.length == 1) {
                            throw new DukeException(" ☹ OOPS!!! Please enter a time " +
                                                    "for your task.");
                        }

                        timeDesc = timeSplit[1];
                        enumType = Type.valueOf(type.toUpperCase());

                        return new AddCommand(enumType, timeSplit[0].substring(6), timeDesc);
                }

            default:
                throw new DukeException(" ☹ OOPS!!! I'm sorry, but I don't " +
                                        "know what that means :-(");
        }

    }

}
