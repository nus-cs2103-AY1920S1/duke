package duke.command;

/**
 * Abstract class for data structures which to wrap a command, and the arguments for it, which can be
 * passed to the logic executing these command.
 */
public abstract class Command {
    private final Type type;
    private final String[] parameters;

    /**
     * Constructs the command
     *
     * @param type       The enumerated type of command
     * @param parameters The arguments for the command, if any
     */
    public Command(Type type, String... parameters) {
        this.type = type;
        this.parameters = parameters;
    }

    public static Type getTypeOf(Command command) {
        return command.type;
    }

    public static String[] getParametersUsed(Command command) {
        return command.parameters;
    }
}





