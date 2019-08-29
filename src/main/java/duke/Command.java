package duke;

public class Command {

    String command;
    CommandType type;

    /**
     * Constructs a Command.
     */
    public Command() {};

    /**
     * Constructor a Command.
     * @param type type of command.
     */
    public Command(CommandType type) {
        this.type = type;
    }

    /**
     * Constructor a Command.
     * @param type type of command.
     * @param description description for task related to given command.
     */
    public Command(CommandType type, String description) {
        this.command = description;
        this.type = type;
    }

    /**
     * Retrieves description of task given in command.
     * @return String with task description.
     */
    public String getDescription() {
        return this.command;
    }

}
