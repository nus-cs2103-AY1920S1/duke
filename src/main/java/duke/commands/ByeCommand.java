package duke.commands;

import duke.Duke;
import duke.DukeException;

public class ByeCommand extends Command{

    private static final String TOO_MANY_ARGUMENTS = "☹ OOPS!!! The bye command takes no arguments.";

    public static ByeCommand create(Duke duke, String input, String[] args) throws DukeException {
        int numArgs = args.length;
        if (numArgs > 1) {
            throw new DukeException(TOO_MANY_ARGUMENTS);
        }
        return new ByeCommand(duke, input);
    }

    private ByeCommand(Duke duke, String input) {
        super(duke, input);
    }

    public void execute() {
        this.duke.exit();
    }
}
