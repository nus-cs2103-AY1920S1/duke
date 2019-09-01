import java.util.HashMap;
import java.util.Map;

class Parser {
    private Map<String, Command> commands = new HashMap<>();

    /**
     * Registers a command to be returned later.
     *
     * @param name Name that will trigger the command.
     * @param command Command to return later.
     */
    void register(String name, Command command) {
        commands.put(name, command);
    }

    /**
     * Returns the appropriate command given the line that the user input.
     *
     * @param words Line of input.
     * @return Command to use on the input.
     * @throws DukeException If there is no such command.
     */
    Command parse(String[] words) throws DukeException {
        Command command = commands.get(words[0]);
        if (command == null) {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
        return command;
    }
}
