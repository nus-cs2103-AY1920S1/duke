package duke;

public class Command {

    String command;
    CommandType type;
    Parser parser;

    public Command() {};

    public Command(CommandType type) {
        this.type = type;
    }

    public Command(CommandType type, String command) {
        this.command = command;
        this.type = type;
    }

}
