package duke.command;

public abstract class InputCommand extends Command {
    private String string;

    public InputCommand(String string) {
        this.string = string.trim();
    }

    public String getString() {
        return this.string;
    }
}
