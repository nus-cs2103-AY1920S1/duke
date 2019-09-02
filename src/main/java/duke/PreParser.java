package duke;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.CommandMap;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.TodoCommand;

/**
 * Parser that converts Strings into {@link Command} objects using a {@link CommandMap}
 * as a String-{@link Class} map.
 */
public class PreParser {
    private CommandMap commandMap;

    /**
     * Constructs a PreParser using the default {@link CommandMap} mapping.
     */
    public PreParser() {
        String[] dummyArgs = new String[0];
        commandMap = new CommandMap();
        commandMap.register(new ByeCommand(dummyArgs));
        commandMap.register(new ListCommand(dummyArgs));
        commandMap.register(new DoneCommand(dummyArgs));
        commandMap.register(new TodoCommand(dummyArgs));
        commandMap.register(new DeadlineCommand(dummyArgs));
        commandMap.register(new EventCommand(dummyArgs));
        commandMap.register(new DeleteCommand(dummyArgs));
        commandMap.register(new FindCommand(dummyArgs));
    }

    /**
     * Constructs a PreParser using the specified {@link CommandMap}.
     *
     * @param commandMap  a CommandMap that maps command name Strings to {@link Command}s.
     */
    public PreParser(CommandMap commandMap) {
        this.commandMap = commandMap;
    }

    /**
     * Returns a {@link Command} object parsed from the specified String. The Command contains all arguments specified
     * in the String, ignoring extraneous spaces.
     * <p>
     * Specifically, the Command arguments are parsed into a String[] by calling {@link String#split} on the input with
     * <code>" "</code> as the delimiter.
     *
     * @param fullCommand
     * @return                the parsed Command
     * @throws DukeException  if the command name is not a key in the CommandMap
     */
    public Command parse(String fullCommand) throws DukeException {
        String[] args = fullCommand.split(" ");
        try {
            Class<? extends Command> cls = commandMap.get(args[0]);
            Command command = cls.getConstructor(String[].class).newInstance(new Object[]{args});
            return command;
        } catch (Exception e) {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }
}
