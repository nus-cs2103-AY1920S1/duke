enum Command {
    BYE     ("bye"),
    DONE    ("done"),
    LIST    ("list"),
    TODO    ("todo"),
    EVENT   ("event"),
    DEADLINE("deadline"),
    DELETE  ("delete");

    private String commandText;

    Command (String commandText) {
        this.commandText = commandText;
    }

    public String toString() {
        return commandText;
    }

    public static Command getFromString(String commandText) throws InvalidCommandException {
        Command[] allCommands = Command.values();

        for (Command cmd : allCommands) {
            if (commandText.equals(cmd.toString())) {
                return cmd;
            }
        }

        throw new InvalidCommandException(Duke.OOPS_STR + Duke.INVALID_COMMAND_STR);
    }
}
