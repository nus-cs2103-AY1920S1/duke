/**
 * Enumerator class for all recognized command types.
 */

enum Command {
    BYE("bye"),
    DONE("done"),
    LIST("list"),
    TODO("todo"),
    EVENT("event"),
    DEADLINE("deadline"),
    AFTERTASK("aftertask"),
    DELETE("delete"),
    FIND("find");

    private String commandText;

    Command(String commandText) {
        this.commandText = commandText;
    }

    /**
     * Returns a string representation of the command.
     * 
     * @return A string representing the command
     */
    public String toString() {
        return commandText;
    }

    /**
     * Attempts to match an input string to a valid command, which is returned.
     * 
     * @param commandText String representation of the command
     * @return Command that matches the string, if exists
     * @throws InvalidCommandException If there is no matching Command
     */
    public static Command getFromString(String commandText) throws InvalidCommandException {
        Command[] allCommands = Command.values();

        for (Command cmd : allCommands) {
            if (commandText.equals(cmd.toString())) {
                return cmd;
            }
        }

        throw new InvalidCommandException(Duke.PRINTED_OOPS + Duke.PRINTED_INVALID_COMMAND);
    }
}
