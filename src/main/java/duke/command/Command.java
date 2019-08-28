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

    /**
     * Returns the enumerated type of the command
     *
     * @param command The command of which the enumerated type is needed from
     * @return The enumerated Type of the command
     */
    public static Type getTypeOf(Command command) {
        return command.type;
    }

    /**
     * Returns the arguments provided with the command
     *
     * @param command The command of which the arguments provided is needed from
     * @return The arguments provided to the command
     */
    public static String[] getArgumentsUsed(Command command) {
        return command.parameters;
    }
}





