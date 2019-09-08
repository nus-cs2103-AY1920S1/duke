package command;

import exception.InvalidCommandException;
import task.Task;
import utils.Ui;

import java.util.HashMap;
import java.util.List;

public class CommandCentre {

    /**
     * A collection to store and map command names to their
     * respective Command.
     */
    private HashMap<String, Command> commands;

    /**
     * Manages all commands in the app.
     */
    public CommandCentre() {
        commands = new HashMap<>();
    }

    /**
     * Adds a new Command with its command name into the collection of commands.
     *
     * @param commandName The name of the command to be registered.
     * @param command     The Command object this commandName will map to.
     */
    public void register(String commandName, Command command) {
        commands.put(commandName, command);
    }

    /**
     * Executes the command given by the command name.
     *
     * @param commandName The name of the command to be executed.
     * @return boolean Whether the command is valid or not.
     */
    public void execute(String commandName) {
        Command command = commands.get(commandName);
        command.execute();
    }

    /**
     * Checks whether the Command Centre contains a Command with the specified
     * command name.
     *
     * @param commandName The command name to be checked for.
     * @return True if command is found, false otherwise.
     */
    public boolean contains(String commandName) {
        return commands.containsKey(commandName);
    }

    /**
     * Initialize dummy commands for test.
     */
    public void initializeCommands() {
        register("bye", new Command() {
            @Override
            public void execute() {
            }
        });

        register("help", new Command() {
            @Override
            public void execute() {
            }
        });

        register("list", new Command() {
            @Override
            public void execute() {
            }
        });

        register("done", new Command() {
            @Override
            public void execute() {
            }
        });

        register("delete", new Command() {
            @Override
            public void execute() {
            }
        });
        register("todo", new Command() {
            @Override
            public void execute() {
            }
        });
        register("deadline", new Command() {
            @Override
            public void execute() {
            }
        });
        register("event", new Command() {
            @Override
            public void execute() {
            }
        });
        register("find", new Command() {
            @Override
            public void execute() {
            }
        });
    }
}
