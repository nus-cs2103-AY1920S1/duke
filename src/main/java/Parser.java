/**
 * Represents a Parser.
 * Determines the type of the task to create an appropriate command.
 */

public class Parser {

    /**
     * Splits the full command into keyword and description.
     * Creates and returns a Command object.
     * The following types are AddCommands: "deadline", "event", "todo".
     * The follow type is a HelpCommand: "help".
     * The following type is an ExitCommand: "exit".
     * The following types are StorageCommands: "save".
     * The following types are TaskListCommands: "clear", "delete", "done", "find", "list".
     * The HelpCommand is returned by default if no keyword is detected.
     *
     * @param fullCommand The full command input.
     * @return command A Command Class to be executed.
     * @throws DukeException If fullCommand entered does not adhere to specifications of each type.
     */
    public static Command parse(String fullCommand) throws DukeException {
        String type = "";
        String errorMessage = "";
        if (fullCommand.contains("find")) {
            type = "find";
            fullCommand = fullCommand.substring(4);
            if (fullCommand.isEmpty()) {
                errorMessage += "Parser Exception: Please specify keyword for search in the format:\n";
                errorMessage += "find -YourKeywordHere-"
                throw new DukeException(errorMessage);
            }
        } else if (fullCommand.equals("clear")) {
            type = "clear";
        } else if (fullCommand.equals("bye")) {
            type = "exit";
        }else if (fullCommand.equals("save")) {
            type = "save";
        } else if (fullCommand.equals("list")) {
            type = "list";
        } else if (fullCommand.contains("delete")) {
            type = "delete";
            fullCommand = fullCommand.substring(6);
            if (fullCommand.isEmpty()) {
                throw new DukeException("Parser Exception: OOPS!! Please specify which task to delete");
            }
            fullCommand = fullCommand.substring(1);
            if (fullCommand.charAt(0) < '0' || fullCommand.charAt(0) > '9') {
                throw new DukeException("Parser Exception: OOPS!! Please enter a number for task number to be deleted");
            }
        } else if (fullCommand.contains("done")) {
            type = "done";
            fullCommand = fullCommand.substring(4);
            if (fullCommand.isEmpty()) {
                throw new DukeException("Parser Exception: OOPS!! Please specify which task is done");
            }
            fullCommand = fullCommand.substring(1);
            if (fullCommand.charAt(0) < '0' || fullCommand.charAt(0) > '9') {
                throw new DukeException("Parser Exception: OOPS!! Please enter a number for task number that is done");
            }
        } else if (fullCommand.contains("event")) {
            if (fullCommand.contains("/at")) {
                type = "event";
                fullCommand = fullCommand.substring(5);
            } else {
                throw new DukeException("Parser Exception: OOPS!! The event must include a time after the keyword /at");
            }
        } else if (fullCommand.contains("deadline")) {
            if (fullCommand.contains("/by")) {
                type = "deadline";
                fullCommand = fullCommand.substring(8);
            } else {
                throw new DukeException("Parser Exception: OOPS!! The deadline must include a time after the keyword /by");
            }
        } else if (fullCommand.contains("todo")) {
            type = "todo";
            fullCommand = fullCommand.substring(4);
            if (fullCommand.isEmpty()) {
                throw new DukeException("Parser Exception: OOPS!! The description of a todo cannot be empty.");
            }
        } else {
            type = "help";
        }

        if (fullCommand.equals(" ")) {
            throw new DukeException("Parser Exception: No command received, please re-enter command.");
        }

        Command command;
        if (type.equals("event") || type.equals("deadline") || type.equals("todo")) {
            command = new AddCommand(type, fullCommand);
        } else if (type.equals("exit")) {
            command = new ExitCommand(type, fullCommand);
        } else if (type.equals("save")) {
            command = new StorageCommand(type, fullCommand);
        } else if (type.equals("help")) {
            command = new HelpCommand(type, fullCommand);
        } else {
            command = new TaskListCommand(type, fullCommand);
        }
        return command;
    }
}
