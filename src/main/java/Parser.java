import java.util.HashMap;
import java.util.Map;

class Parser {
    private Map<String, Command> commands = new HashMap<>();

    void register(String name, Command command) {
        commands.put(name, command);
    }

    Command parse(String[] words) {
        return commands.get(words[0]);
    }
}
