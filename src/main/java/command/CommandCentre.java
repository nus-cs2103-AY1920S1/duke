package command;

import exception.InvalidCommandException;

import java.util.HashMap;

public class CommandCentre {

    /**
     * <p>
     *     A collection to store and map command names to their
     *     respective Command.
     * </p>
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
     * @param command The Command object this commandName will map to.
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
    public boolean execute(String commandName) {
        try {
            Command command = commands.get(commandName);
            if (command == null) {
                throw new InvalidCommandException();
            }
            commands.get(commandName).execute();
            return true;
        } catch (InvalidCommandException e) {
            System.out.print("     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n");
            return false;
        }
    }
}
