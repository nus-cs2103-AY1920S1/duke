package duke.command;

// container class for commands and parameters given
public abstract class Command {
    private final Type type;
    private final String[] parameters;

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





