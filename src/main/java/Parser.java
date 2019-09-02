import java.util.HashMap;
import java.util.Map;

class Parser {
    private Map<String, Command> commands = new HashMap<>();

    void register(String name, Command command) {
        commands.put(name, command);
    }

    Command parse(String[] words) throws DukeException {
        Command command = commands.get(words[0]);
        if (command == null) {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
        return command;
    }
}
