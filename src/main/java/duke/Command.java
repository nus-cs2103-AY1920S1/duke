package duke;

public abstract class Command {
    protected Duke duke;
    protected String input;

    public Command(Duke duke, String input) {
        this.duke = duke;
        this.input = input;
    }

    public abstract void execute();

    public static Command create(Duke duke, String input) {
        String[] args = input.split(" ");
        switch(args[0]) {
            case "bye":
                return new ByeCommand(duke, input);
            case "list":
                return new ListCommand(duke, input);
            case "done":
                return new DoneCommand(duke, input);
            case "todo":
            case "event":
            case "deadline":
                return new AddCommand(duke, input);
            default:
                return null;
        }
    }
}
