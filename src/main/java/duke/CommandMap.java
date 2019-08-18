package duke;

import java.util.HashMap;
import java.util.Map;

public class CommandMap{
    Map<String, Command> commands = new HashMap<>();
    public void register(Command command) {
        commands.put(command.getName(), command);
    }
    public Command get(String name) {
        return commands.get(name);
    }
}
