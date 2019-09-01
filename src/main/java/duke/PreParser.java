package duke;

import duke.command.Command;
import duke.command.CommandMap;

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
        commandMap.register(new duke.command.Bye(dummyArgs));
        commandMap.register(new duke.command.List(dummyArgs));
        commandMap.register(new duke.command.Done(dummyArgs));
        commandMap.register(new duke.command.Todo(dummyArgs));
        commandMap.register(new duke.command.Deadline(dummyArgs));
        commandMap.register(new duke.command.Event(dummyArgs));
        commandMap.register(new duke.command.Delete(dummyArgs));
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
