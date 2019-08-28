package duke.command;

/**
 * Data structure to wrap a command, and the arguments for it, which can be passed to the logic executing the command
 */
public class Command {
    public Type type;
    public String[] parameters;

    /**
     * Constructs the command
     * @param type The enumerated type of command
     * @param parameters The arguments for the command, if any
     */
    public Command(Type type, String... parameters) {
        this.type = type;
        this.parameters = parameters;
    }
}





