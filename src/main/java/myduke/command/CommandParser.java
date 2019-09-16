package myduke.command;

import myduke.exception.DukeException;
import myduke.exception.DukeInvalidCommandException;
import myduke.type.TaskType;
import myduke.util.CommandFactoryHashMap;
import myduke.util.ThrowableFunction;

import java.util.HashMap;

/**
 * Parses a query into its respective command.
 */
public class CommandParser {

    //Class Variable
    private final HashMap<String, String> commandAliasMap;
    private final CommandFactoryHashMap commandConstructorMap;

    /**
     * Constructor for Command Parser.
     */
    public CommandParser() {
        commandConstructorMap = new CommandFactoryHashMap();

        //Add basic command mappings
        commandConstructorMap.put("todo", args -> new AddTaskCommand(TaskType.TASK_TODO, args));
        commandConstructorMap.put("deadline", args -> new AddTaskCommand(TaskType.TASK_DEADLINE, args));
        commandConstructorMap.put("event", args -> new AddTaskCommand(TaskType.TASK_EVENT, args));
        commandConstructorMap.put("doafter", args -> new AddTaskCommand(TaskType.TASK_DO_AFTER, args));
        commandConstructorMap.put("done",      MarkCompletedTaskCommand::new);
        commandConstructorMap.put("delete",    DeleteCommand::new);
        commandConstructorMap.put("find",      FilterTasksCommand::new);
        commandConstructorMap.put("list", args -> {
            if (!args.isEmpty()) {
                throw new DukeInvalidCommandException("I'm sorry, but I don't know what that means :-(");
            }
            return new ListCommand();
        });
        commandConstructorMap.put("bye", args -> {
            if (!args.isEmpty()) {
                throw new DukeInvalidCommandException("I'm sorry, but I don't know what that means :-(");
            }
            return new TerminateSessionCommand();
        });
        commandConstructorMap.put("reinitialise", args -> {
            if (!args.isEmpty()) {
                throw new DukeInvalidCommandException("I'm sorry, but I don't know what that means :-(");
            }
            return new InitialiseDukeCommand();
        });
        commandConstructorMap.put("alias", args -> {
            if (args.isEmpty()) {
                throw new DukeInvalidCommandException("I'm sorry, but I don't know what that means :-(");
            }
            return new UpdateAliasCommand(this, args);
        });

        //Add Alias
        commandAliasMap = new HashMap<>();
        commandAliasMap.put("t", "todo");
        commandAliasMap.put("d", "deadline");
        commandAliasMap.put("due", "deadline");
        commandAliasMap.put("e", "event");
        commandAliasMap.put("a", "doafter");
        commandAliasMap.put("after", "doafter");
        commandAliasMap.put("m", "done");
        commandAliasMap.put("mark", "done");
        commandAliasMap.put("r", "delete");
        commandAliasMap.put("rm", "delete");
        commandAliasMap.put("del", "delete");
        commandAliasMap.put("f", "find");
        commandAliasMap.put("l", "list");
        commandAliasMap.put("ls", "list");
        commandAliasMap.put("shutdown", "bye");
        commandAliasMap.put("reload", "reinitialise");
        commandAliasMap.put("init", "reinitialise");
    }

    /**
     * Unregisters an alias.
     *
     * @param alias the alias to be unregistered.
     *
     * @throws DukeInvalidCommandException if the alias was never assigned or is a reserved command.
     */
    public void removeAlias(String alias) throws DukeInvalidCommandException {
        //user should not be able to remove a reserved command, regardless of case sensitivity.
        if (commandConstructorMap.containsKey(alias.toLowerCase())) {
            throw new DukeInvalidCommandException("Cannot remove '" + alias + "' as it is a reserved command");
        }

        if (commandAliasMap.remove(alias.toLowerCase()) == null) {
            throw new DukeInvalidCommandException(alias + " was never assigned as an alias");
        }
    }

    /**
     * Updates the reference meaning of an alias.
     *
     * @param alias         an alias for a reserved command.
     * @param actualCommand the implied command.
     *
     * @return the previous reserved command which the alias represents if any, otherwise, returns an empty string.
     *
     * @throws DukeInvalidCommandException if alias is a reserved command or the implied command is not recognised.
     */
    public String updateAlias(String alias, String actualCommand) throws DukeInvalidCommandException {
        /* user should not add alias similar to the reserved commands, regardless of case sensitivity. */
        if (commandConstructorMap.containsKey(alias.toLowerCase())) {
            throw new DukeInvalidCommandException(alias + " is a reserved command");
        }

        if (!commandConstructorMap.containsKey(actualCommand.toLowerCase())) {
            throw new DukeInvalidCommandException(actualCommand + " is not an officially recognised command");
        }

        String previousAlias = commandAliasMap.remove(alias.toLowerCase());
        commandAliasMap.put(alias.toLowerCase(), actualCommand.toLowerCase());
        return previousAlias;
    }


    /**
     * Creates the relevant command based on the user's query.
     *
     * @param query The query of the user.
     *
     * @return The appropriate Command.
     *
     * @throws DukeException if no commands matches the query.
     */
    public Command create(String query) throws DukeException {

        String[] parts = query.trim().split(" ", 2);
        if (parts.length == 0 || parts[0].length() == 0) {
            throw new DukeInvalidCommandException("Query should not be empty");
        }

        String aliasName = parts[0].toLowerCase();
        String commandName = commandAliasMap.getOrDefault(aliasName, aliasName);
        ThrowableFunction<String, Command, DukeException> constructorFunction = commandConstructorMap.get(commandName);

        if (constructorFunction == null) {
            throw new DukeInvalidCommandException("I'm sorry, but I don't know what that means :-(");
        }

        String arguments = (parts.length == 2) ? parts[1] : "";
        return constructorFunction.accept(arguments);
    }
}
