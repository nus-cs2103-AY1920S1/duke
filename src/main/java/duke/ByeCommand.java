package duke;

public class ByeCommand extends Command{

    public ByeCommand(Duke duke, String input) {
        super(duke, input);
    }

    public void execute() {
        this.duke.exit();
    }
}
