/**
 * Represents different types of commands.
 */
public enum CommandType {
    TODO,
    EVENT,
    DEADLINE,
    LIST,
    DELETE,
    DONE,
    BYE,
    FIND,
    HELP,
    ERROR;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
