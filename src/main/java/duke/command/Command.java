package duke.command;

/**
 * An abstract class representing an instruction for Duke.
 *
 * A Command is a data structure that wraps a valid instruction type for Duke, and the arguments that should be used 
 * when executing an instruction of that type.
 */
public abstract class Command {
    // the type of instruction to be executed
    private Type type;
    // the arguments to be used when executing
    private String[] arguments;

    /**
     * Constructs the Command
     *
     * @param type       The enumerated type of Command
     * @param arguments The arguments for the Command, if any
     */
    Command(Type type, String... arguments) {
        this.type = type;
        this.arguments = arguments;
    }

    /**
     * Returns the enumerated type of the Command
     *
     * @param command The Command of which the enumerated type is needed from.
     * @return The enumerated Type of the command.
     */
    public static Type getTypeOf(Command command) {
        return command.type;
    }

    /**
     * Returns the arguments provided with the Command
     *
     * @param command The Command from which the arguments used are needed.
     * @return The arguments used in the Command, in a String[].
     */
    public static String[] getArgumentsUsed(Command command) {
        return command.arguments;
    }
}





