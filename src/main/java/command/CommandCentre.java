package command;

import utils.Ui;

import java.util.HashMap;
import java.util.Stack;

public class CommandCentre {

    /**
     * A collection to store and map command names to their
     * respective Command.
     */
    private HashMap<String, Command> commands;
    /**
     * A stack to store all the opposite commands of previously executed commands. The opposite of
     * the most recently executed command will be at the top of the stack.
     */
    private Stack<Command> commandHistory;
    private Ui ui;

    /**
     * Manages all commands in the app.
     */
    public CommandCentre() {
        commands = new HashMap<>();
        commandHistory = new Stack<>();
    }

    public void setUi(Ui ui) {
        this.ui = ui;
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

    public void addToHistory(Command command) {
        commandHistory.push(command);
    }

    /**
     * Undoes the latest command that has been executed.
     * If there are no more latest commands, ui responds with a warning message.
     */
    public void undo() {
        if (!commandHistory.isEmpty()) {
            commandHistory.pop().execute();
        } else {
            ui.printUndoNotAllowedMessage();
        }
    }

    /**
     * Initialize dummy commands for Junit test.
     */
    public void initializeDummyCommands() {
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
