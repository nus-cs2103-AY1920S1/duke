package storage;

public class Command {

    private String command;
    public CommandType type;

    /**
     * Constructor a Command.
     *
     * @param type type of command.
     */
    Command(CommandType type) {
        this.type = type;
    }

    /**
     * Constructor a Command.
     *
     * @param type type of command.
     * @param description description for task related to given command.
     */
    Command(CommandType type, String description) {
        this.command = description;
        this.type = type;
    }

    /**
     * Retrieves description of task given in command.
     *
     * @return String with task description.
     */
    String getDescription() {
        return this.command;
    }

}
