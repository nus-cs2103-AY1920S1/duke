import java.util.HashMap;
import java.util.Map;

/**
 * Command Enum Class. Contains the valid commands for Duke.
 */
public enum Command {
    DONE("done"),
    LIST("list"),
    DELETE("delete"),
    BYE("bye"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event");

    /** Reversed lookup table mapping strings to their valid Command. */
    private static final Map<String, Command> lookupTable = new HashMap<>();

    /** Initializes the lookupTable when enum class is loaded. */
    static {
        for (Command command : Command.values()) {
            lookupTable.put(command.cmd, command);
        }
    }

    /** String representation of command. */
    private String cmd;

    /**
     * Constructor.
     * @param cmd String representation of command.
     */
    Command(String cmd) {
        this.cmd = cmd;
    }

    /**
     * Gets the Command from the given string.
     * @param cmd Command in string form.
     * @return Command that has this string form. Null if cmd not found or if cmd == null.
     */
    public static Command lookup(String cmd) {
        return lookupTable.get(cmd);
    }

    /**
     * String representation of command.
     * @return String representation of command.
     */
    @Override
    public String toString() {
        return cmd;
    }
}
