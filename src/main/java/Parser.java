import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Deals with making sense of the user command.
 */
public class Parser {

    /**
     * Attempts to find within the user input, a Command that takes in one parameter.
     *
     * @param action The action keyword from the user input.
     * @return An Optional Command that contains a Command if a command was found; otherwise it is empty.
     * @throws DukeException If the action keyword is invalid.
     */
    private static Optional<Command> findOneParameterCommand(String action) throws DukeException {
        Optional<Command> command;
        List<String> validActions = Arrays.asList(
                new String[]{"bye", "list", "done", "delete", "find", "todo", "deadline", "event"});

        if (!validActions.contains(action)) {
            throw new InvalidInputException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

        switch (action) {
        case "bye":
            command = Optional.of(new ExitCommand());
            break;
        case "list":
            command = Optional.of(new PrintListCommand());
            break;
        default:
            command = Optional.empty();
        }
        return command;
    }

    /**
     * Attempts to find within the user input, a Command that takes in two parameters.
     *
     * @param parameters The description of the action from the user input.
     * @return An Optional Command that contains a Command if a command was found; otherwise it is empty.
     * @throws DukeException If the parameters are invalid.
     */
    private static Optional<Command> findTwoParameterCommand(String[] parameters) throws DukeException {
        String action = parameters[0];

        if (parameters.length == 1) {
            throw new EmptyDescriptionException("☹ OOPS!!! The description of a " +
                    action + " command cannot be empty.");
        }

        Optional<Command> command;
        switch (action) {
        case "done":
            command = Optional.of(new MarkAsDoneCommand(Integer.parseInt(parameters[1]) - 1));
            break;
        case "delete":
            command = Optional.of(new DeleteTaskCommand(Integer.parseInt(parameters[1]) - 1));
            break;
        case "find":
            command = Optional.of(new FindTaskCommand(parameters[1]));
            break;
        case "todo":
            command = Optional.of(new AddTaskCommand(new Todo(parameters[1])));
            break;
        default:
            command = Optional.empty();
        }
        return command;
    }

    /**
     * Attempts to find within the user input, a Command that takes in three parameters.
     *
     * @param parameters The description of the action from the user input.
     * @return A Command that takes in three parameters.
     * @throws DukeException If the parameters are invalid.
     */
    private static Command findThreeParameterCommand(String[] parameters) throws DukeException {
        String action = parameters[0];
        Command command;

        switch (action) {
        case "deadline":
            String[] description;
            description = parameters[1].split(" /by ");
            if (description.length == 1) {
                throw new EmptyDescriptionException("☹ OOPS!!! You missed out the time of the deadline task.");
            }
            command = new AddTaskCommand(new Deadline(description[0], description[1]));
            break;
        case "event":
            description = parameters[1].split(" /at ");
            if (description.length == 1) {
                throw new EmptyDescriptionException("☹ OOPS!!! You missed out the time of the event task.");
            }
            command = new AddTaskCommand(new Event(description[0], description[1]));
            break;
        default:
            throw new InvalidInputException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        return command;
    }

    /**
     * Parses the user command.
     *
     * @param fullCommand The raw input given by the user.
     * @return A Command that varies based on the user input.
     * @throws DukeException If the user input is invalid.
     */
    public static Command parse(String fullCommand) throws DukeException {
        String[] parameters = fullCommand.split(" ", 2);
        Optional<Command> command = Parser.findOneParameterCommand(parameters[0]);
        if (command.isEmpty()) {
            command = Parser.findTwoParameterCommand(parameters);
            if (command.isEmpty()) {
                return Parser.findThreeParameterCommand(parameters);
            } else {
                return command.get();
            }
        } else {
            return command.get();
        }
    }
}