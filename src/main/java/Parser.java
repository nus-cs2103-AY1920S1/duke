import java.text.ParseException;

public class Parser {
    public static Command parseForCommands(String input) throws DukeException {
        String[] parameters = input.split(" ", 2);
        String command = parameters[0];

        switch (command) {
            case "list":
                return new ListCommand();
            case "bye":
                return new ByeCommand();
            case "done":
                return Parser.parseDoneCommand(parameters);
            case "delete":
                return Parser.parseDeleteCommand(parameters);
            case "todo":
                return Parser.parseTodoCommand(parameters);
            case "event":
                return Parser.parseEventCommand(parameters);
            case "deadline":
                return Parser.parseDeadlineCommand(parameters);
            default:
                throw new DukeException("☹ OOPS!!! I'm sorry, "
                        + "but I don't know what that means :-(");
        }
    }

    private static DoneCommand parseDoneCommand(String[] parameters) throws DukeException {
        try {
            int taskIndex = Integer.parseInt(parameters[1]);
            return new DoneCommand(taskIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("I'm not psychic! You need"
                    + " to tell me the id of the task\n"
                    + "you're done with!");
        } catch (NumberFormatException e) {
            throw new DukeException("You need to provide me "
                    + "with a valid task index!\n"
                    + "(That means integer numbers only!)");
        }
    }

    private static DeleteCommand parseDeleteCommand(String[] parameters) throws DukeException {
        try {
            int taskIndex = Integer.parseInt(parameters[1]);
            return new DeleteCommand(taskIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("I'm not psychic! You need"
                    + " to tell me the id of the task\n"
                    + "you want to delete!");
        } catch (NumberFormatException e) {
            throw new DukeException("You need to provide me "
                    + "with a valid task index!\n"
                    + "(That means integer numbers only!)");
        }
    }

    private static TodoCommand parseTodoCommand(String[] parameters) throws DukeException {
        try {
            return new TodoCommand(parameters[1]);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("☹ OOPS!!! The description of a todo task"
                    + " cannot be empty.");
        }
    }

    private static EventCommand parseEventCommand(String[] parameters) throws DukeException {
        try {
            String[] deadlineParams = parameters[1].split(" /at ", 2);
            DateTime at = DateTime.parseString(deadlineParams[1]);
            return new EventCommand(deadlineParams[0], at);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("☹ OOPS!!! You need to give me both description\nAND time"
                    + " to create an event task.");
        } catch (ParseException e) {
            throw new DukeException("I couldn't decipher the date and time"
                    + " that you gave me...\n"
                    + "Please write it in <dd/mm/yyyy HHmm> format for me to"
                    + "\nunderstand!");
        }
    }

    private static DeadlineCommand parseDeadlineCommand(String[] parameters) throws DukeException {
        try {
            String[] deadlineParams = parameters[1].split(" /by ", 2);
            DateTime by = DateTime.parseString(deadlineParams[1]);
            return new DeadlineCommand(deadlineParams[0], by);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("☹ OOPS!!! You need to give me both description\nAND time"
                    + " to create a deadline task.");
        } catch (ParseException e) {
            throw new DukeException("I couldn't decipher the date and time"
                    + " that you gave me...\n"
                    + "Please write it in <dd/mm/yyyy HHmm> format for me to"
                    + "\nunderstand!");
        }
    }
}
