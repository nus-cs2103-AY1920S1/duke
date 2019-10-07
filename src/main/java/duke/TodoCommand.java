package duke;

public class TodoCommand extends Command {
    private String description;

    public TodoCommand(String description) {
        super(CommandType.TODO);
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
