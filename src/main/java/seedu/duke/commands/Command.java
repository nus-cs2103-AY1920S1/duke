package seedu.duke.commands;

import seedu.duke.commands.exceptions.CommandExecutionException;
import seedu.duke.commands.exceptions.InvalidCommandException;
import seedu.duke.tasks.TaskManager;

public abstract class Command {
    /**
     * Executes the command. The operations that are performed depends on the implementing class.
     *
     * @param taskManager A task manager that handles operations to the list of tasks.
     * @return A <code>CommandResult</code> that contains a feedback to the user in the form of <code>String</code>.
     * @throws InvalidCommandException   An error that occurs due to invalid command parameter
     *                                   (e.g. non-existent task number).
     * @throws CommandExecutionException An error that occurs during command execution
     *                                   (e.g. due to input/output failure when updating database file).
     */
    public abstract CommandResult execute(TaskManager taskManager)
            throws InvalidCommandException, CommandExecutionException;

    /**
     * Convert a string into <code>Command</code>. Calling this function might return different types of
     * class that extends <code>Command</code> depending on the string provided in the parameter.
     *
     * @param s String representation of the command
     * @return A command that is represented by the given string in the parameter.
     * @throws InvalidCommandException An exception that is returned if the string provided in the parameter
     *                                 does not follow a valid command format.
     */
    public static Command parseCommand(String s) throws InvalidCommandException {
        String[] commandParts = s.split(" ", 2);

        if (commandParts.length < 2) {
            if (commandParts[0].matches("^(done|delete)$")) {
                throw new InvalidCommandException("You must provide a task number for '"
                        + commandParts[0] + "' command!");
            } else if (commandParts[0].matches("^(todo|event|deadline|find)$")) {
                throw new InvalidCommandException("You must provide a description for '"
                        + commandParts[0] + "' command!");
            }
        }

        switch (commandParts[0]) {
        case "list":
            if (commandParts.length == 1) {
                return new ListCommand();
            }

            String[] listParams = commandParts[1].split("/orderby ", 2);
            if (listParams.length == 1) {
                return new ListCommand();
            } else {
                return new ListCommand(listParams[1]);
            }
        case "done":
        case "delete":
            try {
                int taskNumber = Integer.parseInt(commandParts[1]);
                if (commandParts[0].matches("done")) {
                    return new DoneCommand(taskNumber);
                } else {
                    return new DeleteCommand(taskNumber);
                }
            } catch (NumberFormatException e) {
                throw new InvalidCommandException("Task number must be an integer!");
            }
        case "todo":
            return new AddToDoCommand(commandParts[1]);
        case "event":
            String[] eventParams = commandParts[1].split(" /at ", 2);
            if (eventParams.length < 2) {
                throw new InvalidCommandException("The description of an event must contain ' /at '.");
            }
            return new AddEventCommand(eventParams[0], eventParams[1]);
        case "deadline":
            String[] deadlineParams = commandParts[1].split(" /by ", 2);
            if (deadlineParams.length < 2) {
                throw new InvalidCommandException("The description of a deadline must contain ' /by '.");
            }
            return new AddDeadlineCommand(deadlineParams[0], deadlineParams[1]);
        case "find":
            return new FindCommand(commandParts[1]);
        case "bye":
            return new ExitCommand();
        default:
            throw new InvalidCommandException("I'm sorry, but I don't know what that means :-(");
        }
    }
}
