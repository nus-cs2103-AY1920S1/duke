package core;

import command.AddAliasCommand;
import command.AddDeadlineCommand;
import command.AddEventCommand;
import command.AddTodoCommand;
import command.Command;
import command.CommandType;
import command.DeleteAliasCommand;
import command.DeleteTaskCommand;
import command.DoneTaskCommand;
import command.ExitCommand;
import command.FindTaskCommand;
import command.ListAliasesCommand;
import command.ListTasksCommand;
import exception.DukeIllegalArgumentException;
import exception.EmptyFieldException;
import exception.InvalidCommandFormatException;
import exception.InvalidIndexException;
import exception.UnknownCommandException;
import task.Deadline;
import task.Task;
import task.Todo;
import task.Event;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;

public class Parser {
    /**
     * Parse user input and returns a Command object corresponding to string input.
     *
     * @param command User string input.
     * @return executable Command object.
     * @throws EmptyFieldException           If input does not provide a required field value.
     * @throws DukeIllegalArgumentException  If input has invalid field value.
     * @throws InvalidCommandFormatException If input is not in the right format.
     * @throws UnknownCommandException       If a command cannot be understood.
     * @throws InvalidIndexException         If input provides an out-of-bound index.
     */
    public static Command parse(String command) throws EmptyFieldException, DukeIllegalArgumentException,
            InvalidCommandFormatException, UnknownCommandException, InvalidIndexException {
        CommandType type = parseCommandTypeFromAlias(command);

        if (type == CommandType.LIST_TASKS) {
            return parseListTasksCommand(command);
        } else if (type == CommandType.DONE_TASK) {
            return parseDoneTaskCommand(command);
        } else if (type == CommandType.ADD_TODO) {
            return parseAddTodoCommand(command);
        } else if (type == CommandType.ADD_DEADLINE) {
            return parseAddDeadlineCommand(command);
        } else if (type == CommandType.ADD_EVENT) {
            return parseAddEventCommand(command);
        } else if (type == CommandType.DELETE_TASK) {
            return parseDeleteCommand(command);
        } else if (type == CommandType.FIND_TASK) {
            return parseFindTaskCommand(command);
        } else if (type == CommandType.ADD_ALIAS) {
            return parseAddAliasCommand(command);
        } else if (type == CommandType.DELETE_ALIAS) {
            return parseDeleteAliasCommand(command);
        } else if (type == CommandType.LIST_ALIASES) {
            return parseListAliasesCommand(command);
        } else if (type == CommandType.EXIT) {
            return parseExitCommand(command);
        } else {
            throw new UnknownCommandException("OOPS!!! Sorry mate, I don't geddit.");
        }
    }

    private static CommandType parseCommandTypeFromAlias(String stringToParse) throws UnknownCommandException {
        for (CommandType type : CommandType.values()) {
            ArrayList<String> aliases = Command.getAliases(type);
            for (String alias : aliases) {
                boolean isEqualsIgnoreCase = stringToParse.equalsIgnoreCase(alias);
                boolean isFirstWordMatching = stringToParse.toLowerCase().startsWith(alias + " ");
                if (isEqualsIgnoreCase || isFirstWordMatching) {
                    return type;
                }
            }
        }
        throw new UnknownCommandException("OOPS!!! Sorry mate, I don't geddit.");
    }

    private static Command parseDeleteAliasCommand(String command) throws EmptyFieldException,
            InvalidCommandFormatException {
        try {
            int firstSpaceIndex = command.indexOf(' ');
            int typeLength = "/type".length();
            int typeIndex = command.indexOf("/type");

            String remainingCommand = command.substring(firstSpaceIndex).trim();
            if (remainingCommand.isEmpty()) {
                throw new EmptyFieldException("OOPS!!! Can't delete nothing.");
            }
            String aliasesString = command.substring(firstSpaceIndex, typeIndex).trim();
            String[] aliasesToDelete = aliasesString.split(" ");

            String typeString = command.substring(typeIndex + typeLength).trim();
            CommandType type = parseCommandTypeFromTypeId(typeString);

            return new DeleteAliasCommand(command, type, aliasesToDelete);
        } catch  (DukeIllegalArgumentException | StringIndexOutOfBoundsException e) {
            throw new InvalidCommandFormatException("OOPS!!! Please gimme the right format for adding aliases: \n\t"
                    + "'delete-alias [aliases, separated by spaces] /type [command ID]'");
        }
    }

    private static Command parseAddAliasCommand(String command) throws EmptyFieldException,
            InvalidCommandFormatException {
        try {
            int firstSpaceIndex = command.indexOf(' ');
            int typeLength = "/type".length();
            int typeIndex = command.indexOf("/type");

            String remainingCommand = command.substring(firstSpaceIndex).trim();
            if (remainingCommand.isEmpty()) {
                throw new EmptyFieldException("OOPS!!! Can't add nothing.");
            }
            String aliasesString = command.substring(firstSpaceIndex, typeIndex).trim();
            String[] aliasesToAdd = aliasesString.split(" ");

            String typeString = command.substring(typeIndex + typeLength).trim();
            CommandType type = parseCommandTypeFromTypeId(typeString);

            return new AddAliasCommand(command, type, aliasesToAdd);
        } catch (DukeIllegalArgumentException | StringIndexOutOfBoundsException e) {
            throw new InvalidCommandFormatException("OOPS!!! Please gimme the right format for adding aliases: \n\t"
                    + "'alias [aliases, separated by spaces] /type [command ID]'");
        }
    }

    private static CommandType parseCommandTypeFromTypeId(String stringToParse) throws DukeIllegalArgumentException {
        for (CommandType type : CommandType.values()) {
            boolean isEqualsIgnoreCase = stringToParse.equalsIgnoreCase(type.getId());
            if (isEqualsIgnoreCase) {
                return type;
            }
        }
        throw new DukeIllegalArgumentException("OOPS!!! No such ID for command type.");
    }

    private static Command parseListAliasesCommand(String command) {
        return new ListAliasesCommand(command);
    }

    private static Command parseExitCommand(String command) {
        return new ExitCommand(command);
    }

    private static Command parseFindTaskCommand(String command) throws EmptyFieldException,
            InvalidCommandFormatException {
        try {
            //parse searchString from command string
            int firstSpaceIndex = command.indexOf(' ');
            String searchString = command.substring(firstSpaceIndex).trim();
            if (searchString.isEmpty()) {
                throw new EmptyFieldException("OOPS!!! Can't search for somethin' with nothin' mate.");
            }

            return new FindTaskCommand(command, searchString);
        } catch (StringIndexOutOfBoundsException e) {
            throw new InvalidCommandFormatException("OOPS!!! Please gimme the right format for searching: "
                    + "'find [keyword]'");
        }
    }

    private static Command parseDeleteCommand(String command) throws InvalidIndexException {
        try {
            int taskIndex = Integer.parseInt(command.split(" ")[1]);
            return new DeleteTaskCommand(command, taskIndex);
        } catch (NumberFormatException e) {
            throw new InvalidIndexException("OOPS!!! I can't do that, please gimme a valid task ID mate.");
        }
    }

    private static Command parseAddEventCommand(String command) throws EmptyFieldException,
            DukeIllegalArgumentException, InvalidCommandFormatException {
        try {
            int atLength = "/at".length();
            int firstSpaceIndex = command.indexOf(' ');
            int atIndex = command.indexOf("/at"); // look for '/at' keyword from command

            // parse description from command
            String description = command.substring(firstSpaceIndex, atIndex).trim();
            if (description.isEmpty()) {
                throw new EmptyFieldException("OOPS!!! The description of an event cannot be empty mate.");
            }

            // parse at-datetime from command
            String at = command.substring(atIndex + atLength).trim();
            if (at.isEmpty()) {
                throw new EmptyFieldException("OOPS!!! The start & end dates of an event cannot be empty mate.");
            }
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("d/M/yyyy[ HHmm]");
            DateTimeFormatter fmt2 = DateTimeFormatter.ofPattern("[d/M/yyyy][ ][HHmm]");
            Task task = null;
            int dashIndex = at.indexOf("-");
            if (dashIndex != -1) {
                String atStart = at.substring(0, dashIndex).trim();
                String atEnd = at.substring(dashIndex + 1).trim();
                TemporalAccessor parsedStart = fmt.parseBest(atStart, LocalDateTime::from, LocalDate::from);
                TemporalAccessor parsedEnd = fmt2.parseBest(atEnd,
                        LocalDateTime::from, LocalDate::from, LocalTime::from);
                if ((parsedStart instanceof LocalDateTime) && (parsedEnd instanceof LocalDateTime)) {
                    task = new Event(description, (LocalDateTime) parsedStart,
                            (LocalDateTime) parsedEnd);
                } else if ((parsedStart instanceof LocalDate) && ((parsedEnd instanceof LocalDate))) {
                    task = new Event(description, ((LocalDate) parsedStart).atStartOfDay(),
                            ((LocalDate) parsedEnd).atStartOfDay(), true);
                } else if ((parsedStart instanceof LocalDate) && (parsedEnd instanceof LocalTime)) {
                    task = new Event(description, ((LocalDate) parsedStart).atStartOfDay(),
                            ((LocalTime) parsedEnd).atDate((LocalDate) parsedStart), true);
                } else {
                    throw new InvalidCommandFormatException(
                            "OOPS!!! Please gimme an event with the right format: \n\t\t"
                                    + "'event [description] /at [start datetime] - [end datetime]'\n\t"
                                    + "datetime format: d/M/yyyy HHmm");
                }
            } else {
                TemporalAccessor parsedStart = fmt.parseBest(at, LocalDateTime::from, LocalDate::from);
                if (parsedStart instanceof LocalDateTime) {
                    task = new Event(description, (LocalDateTime) parsedStart);
                } else if (parsedStart instanceof LocalDate) {
                    task = new Event(description, ((LocalDate) parsedStart).atStartOfDay(), true);
                }
            }

            assert task != null : "Task is null";

            return new AddEventCommand(command, task);
        } catch (DateTimeParseException | StringIndexOutOfBoundsException e) {
            throw new InvalidCommandFormatException("OOPS!!! Please gimme an event with the right format: \n\t\t"
                    + "'event [description] /at [start datetime] - [end datetime]'\n\t"
                    + "datetime format: d/M/yyyy HHmm");
        }
    }

    private static Command parseAddDeadlineCommand(String command) throws EmptyFieldException,
            InvalidCommandFormatException {
        try {
            int byLength = "/by".length();
            int firstSpaceIndex = command.indexOf(' ');
            int byIndex = command.indexOf("/by"); // look for '/by' keyword from command

            // parse description from command
            String description = command.substring(firstSpaceIndex, byIndex).trim();
            if (description.isEmpty()) {
                throw new EmptyFieldException("OOPS!!! The description of a deadline cannot be empty mate.");
            }

            // parse by-datetime from command
            String by = command.substring(byIndex + byLength).trim();
            if (by.isEmpty()) {
                throw new EmptyFieldException("OOPS!!! The date of a deadline cannot be empty mate.");
            }
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("d/M/yyyy[ HHmm]");
            TemporalAccessor parsedBy = fmt.parseBest(by, LocalDateTime::from, LocalDate::from);

            Task task = null;

            // create task depending on given whether a by-time is given
            if (parsedBy instanceof LocalDateTime) {
                task = new Deadline(description, (LocalDateTime) parsedBy);
            } else if (parsedBy instanceof LocalDate) {
                task = new Deadline(description, ((LocalDate) parsedBy).atStartOfDay(), true);
            }
          
            assert task != null : "Task is null";

            return new AddDeadlineCommand(command, task);
        } catch (DateTimeParseException | StringIndexOutOfBoundsException e) {
            throw new InvalidCommandFormatException("OOPS!!! Please gimme a deadline with the right format: "
                    + "'deadline [description] /by [datetime]'\n\t"
                    + "datetime format: d/M/yyyy[ HHmm]");
        }
    }

    private static Command parseAddTodoCommand(String command) throws EmptyFieldException,
            InvalidCommandFormatException {
        try {
            //parse description from command string
            int firstSpaceIndex = command.indexOf(' ');
            String description = command.substring(firstSpaceIndex).trim();
            if (description.isEmpty()) {
                throw new EmptyFieldException("OOPS!!! The description of a task cannot be empty mate.");
            }

            Task task = new Todo(description);

            return new AddTodoCommand(command, task);
        } catch (StringIndexOutOfBoundsException e) {
            throw new InvalidCommandFormatException("OOPS!!! Please gimme a todo with the right format: "
                    + "'todo [description]'");
        }
    }

    private static Command parseDoneTaskCommand(String command) throws InvalidIndexException {
        try {
            int taskIndex = Integer.parseInt(command.split(" ")[1]);
            return new DoneTaskCommand(command, taskIndex);
        } catch (NumberFormatException e) {
            throw new InvalidIndexException("OOPS!!! I can't do that, please gimme a valid task ID mate.");
        }
    }

    private static Command parseListTasksCommand(String command) {
        return new ListTasksCommand(command);
    }
}
