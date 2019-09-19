package SerSnapsalot.parser;

import SerSnapsalot.exception.DukeException;
import SerSnapsalot.command.AddCommand;
import SerSnapsalot.command.Command;
import SerSnapsalot.command.ExceptionCommand;
import SerSnapsalot.command.ExitCommand;
import SerSnapsalot.command.HelpCommand;
import SerSnapsalot.command.StorageCommand;
import SerSnapsalot.command.TaskListCommand;

/**
 * Represents a Parser.
 * Determines the type of the task to create an appropriate command.
 */

public class Parser {

    /**
     * Splits the full command into keyword and description based on keywords.
     * Creates and returns a command.Command object.
     * The following types are AddCommands: "deadline", "event", "todo".
     * The follow type is a HelpCommand: "help".
     * The following type is an ExitCommand: "exit".
     * The following types are StorageCommands: "archive", "save".
     * The following types are TaskListCommands: "clear", "delete", "done", "find", "list".
     * The following types are Easter Eggs: "snap".
     * The HelpCommand is returned by default if no keyword is detected.
     *
     * @param fullCommand The full command input.
     * @return command A Command Class to be executed.
     * @throws DukeException If fullCommand entered does not adhere to specifications of each type.
     */
    public Command parse(String fullCommand) throws DukeException {
        String type = "";
        String errorMessage = "";
        Command command;
        try {
            if (fullCommand.contains("find")) {
                command = parseFind(fullCommand);
            } else if (fullCommand.equals("clear")) {
                command = parseClear(fullCommand);
            } else if (fullCommand.equals("bye")) {
                command = parseExit(fullCommand);
            } else if (fullCommand.equals("save")) {
                command = parseSave(fullCommand);
            } else if (fullCommand.contains("archive")) {
                command = parseArchive(fullCommand);
            } else if (fullCommand.equals("list")) {
                command = parseList(fullCommand);
            } else if (fullCommand.contains("delete")) {
                command = parseDelete(fullCommand);
            } else if (fullCommand.contains("done")) {
                command = parseMarkDone(fullCommand);
            } else if (fullCommand.contains("event")) {
                command = parseAddCommand(fullCommand);
            } else if (fullCommand.contains("deadline")) {
                command = parseAddCommand(fullCommand);
            } else if (fullCommand.contains("todo")) {
                command = parseAddCommand(fullCommand);
            } else if (fullCommand.equals("snap")){
                command = parseSnapCommand("snap");
            } else {
                command = new HelpCommand("help", fullCommand);
            }
        } catch (DukeException e) {
            command = new ExceptionCommand("DukeException" ,e.toString());
        }
        return command;
    }

    /**
     * Parses commands containing the keyword "find"
     *
     * @param fullCommand The full command input.
     * @return outputCommand A TaskListCommand Class to be executed.
     * @throws DukeException If fullCommand entered does not adhere to specifications.
     */
    public Command parseFind(String fullCommand) throws DukeException {
        Command outputCommand;
        String errorMessage = "";
        fullCommand = fullCommand.substring(4);
        if (fullCommand.isEmpty()) {
            errorMessage += "Parser Exception: Please specify keyword for search in the format:\n";
            errorMessage += "find -YourKeywordHere-";
            throw new DukeException(errorMessage);
        }
        outputCommand = new TaskListCommand("find", fullCommand);
        return outputCommand;
    }

    /**
     * Parses commands containing the keyword "clear"
     *
     * @param fullCommand The full command input.
     * @return outputCommand A TaskListCommand Class to be executed.
     */
    public Command parseClear(String fullCommand) {
        Command outputCommand = new TaskListCommand("clear", fullCommand);
        return outputCommand;
    }

    /**
     * Parses commands containing the keyword "bye"
     *
     * @param fullCommand The full command input.
     * @return outputCommand An ExitCommand Class to be executed.
     */
    public Command parseExit(String fullCommand) {
        Command  outputCommand = new ExitCommand("exit", fullCommand);
        return outputCommand;
    }

    public Command parseSave(String fullCommand) {
        Command outputCommand = new StorageCommand("save", fullCommand);
        return outputCommand;
    }

    /**
     * Parses commands containing the keyword "archive"
     *
     * @param fullCommand The full command input.
     * @return outputCommand A StorageCommand Class to be executed.
     * @throws DukeException If fullCommand entered does not adhere to specifications.
     */
    public Command parseArchive(String fullCommand) throws DukeException {
        fullCommand = fullCommand.substring(7);

        if (fullCommand.isEmpty()) {
            String errorMessage = "Parser Exception: Please specify archive command in the format:\n";
            errorMessage += "archive save: Saves the task list to archive and clears it.\n";
            errorMessage += "archive load: Load the task list from archive";
            throw new DukeException(errorMessage);
        } else {
            fullCommand.substring(1);
            Command outputCommand = new StorageCommand("archive", fullCommand);
            return outputCommand;
        }

    }

    /**
     * Parses commands containing the keyword "archive"
     *
     * @param fullCommand The full command input.
     * @return outputCommand A TaskListCommand Class to be executed.
     */
    public Command parseList(String fullCommand) {
        Command outputCommand = new TaskListCommand("list", fullCommand);
        return outputCommand;
    }

    /**
     * Parses commands containing the keyword "delete"
     *
     * @param fullCommand The full command input.
     * @return outputCommand A TaskListCommand Class to be executed.
     * @throws DukeException If fullCommand entered does not adhere to specifications.
     */
    public Command parseDelete(String fullCommand) throws DukeException {
        String errorMessage = "";
        fullCommand = fullCommand.substring(6);
        if (fullCommand.isEmpty()) {
            errorMessage += "Parser Exception: Please specify keyword for deletion in the format:\n";
            errorMessage += "delete -YourKeywordHere-";
            throw new DukeException(errorMessage);
        }
        fullCommand = fullCommand.substring(1);
        char firstChar = fullCommand.charAt(0);
        if (firstChar < 0 || firstChar > '9') {
            errorMessage += "Parser Exception: Please specify a number to be deleted";
            throw new DukeException(errorMessage);
        }
        Command outputCommand = new TaskListCommand("delete", fullCommand);
        return outputCommand;
    }

    /**
     * Parses commands containing the keyword "done"
     *
     * @param fullCommand The full command input.
     * @return outputCommand A TaskListCommand Class to be executed.
     * @throws DukeException If fullCommand entered does not adhere to specifications.
     */
    public Command parseMarkDone(String fullCommand) throws DukeException {
        fullCommand = fullCommand.substring(4);
        if (fullCommand.isEmpty()) {
            String errorMessage = "Parser Exception: Please specify task to be marked done in the format:";
            errorMessage += "done #\n Where # is the index of the task";
            errorMessage += "Use the keyword (list) to display the task list";
            throw new DukeException(errorMessage);
        }
        fullCommand = fullCommand.substring(1);
        if (fullCommand.charAt(0) < '0' || fullCommand.charAt(0) > '9') {
            throw new DukeException("Parser Exception: Please use numbers 1-9 to specify index");
        }
        Command outputCommand = new TaskListCommand("done", fullCommand);
        return outputCommand;
    }

    /**
     * Parses commands containing the keyword "deadline" or "event" or "todo".
     *
     * @param fullCommand The full command input.
     * @return outputCommand An AddCommand Class to be executed.
     * @throws DukeException If fullCommand entered does not adhere to specifications.
     */
    public static Command parseAddCommand(String fullCommand) throws DukeException {
        String type = "";
        String errorMessage = "";
        if (fullCommand.contains("event")) {
            type = "event";
            if (fullCommand.equals("event")) {
                errorMessage += "Parser Exception: Please specify event according to the format:\n";
                errorMessage += "event -InsertEventHere- /at DD/MM/YYYY HHMM\n";
                errorMessage += "E.g. (event MyEvent /at 11/12/2019 1830)";
                throw new DukeException(errorMessage);
            }
            fullCommand = fullCommand.substring(5);
        } else if (fullCommand.contains("deadline")) {
            type = "deadline";
            if (fullCommand.equals("deadline")) {
                errorMessage += "Parser Exception: Please specify deadline according to the format:\n";
                errorMessage += "deadline -InsertDeadlineHere- /by DD/MM/YYYY HHMM\n";
                errorMessage += "E.g. (deadline MyDeadline /at 11/12/2019 1830)";
                throw new DukeException(errorMessage);
            }
            fullCommand = fullCommand.substring(8);

        } else if (fullCommand.contains("todo")) {
            type = "todo";
            fullCommand = fullCommand.substring(4);
            if (fullCommand.isEmpty()) {
                errorMessage += "Parser Exception: Please specify todo according to the format:\n";
                errorMessage += "todo -InsertToDoHere- \n";
                errorMessage += "E.g. (todo MyToDo)";
                throw new DukeException(errorMessage);
            }
        } else {
            throw new DukeException("Parser Exception: Unable to identify AddCommand");
        }
        Command outputCommand = new AddCommand(type, fullCommand);
        return outputCommand;
    }
    /**
     * Parses commands containing the keyword "snap"
     *
     * @param fullCommand The full command input.
     * @return outputCommand A TaskListCommand Class to be executed.
     * @throws DukeException If fullCommand entered does not adhere to specifications.
     */
    public Command parseSnapCommand(String fullCommand) throws DukeException {
        Command outputCommand;
        outputCommand = new TaskListCommand("snap", fullCommand);
        return outputCommand;
    }

}