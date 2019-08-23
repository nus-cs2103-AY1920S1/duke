package duke.commands;

import duke.Duke;
import duke.DukeException;

public class ListCommand extends Command {

    private static final String TOO_MANY_ARGUMENTS = "☹ OOPS!!! The list command takes no arguments.";

    public static ListCommand create(Duke duke, String input, String[] args) throws DukeException {
        int numArgs = args.length;
        if (numArgs > 1) {
            throw new DukeException(TOO_MANY_ARGUMENTS);
        }
        return new ListCommand(duke, input);
    }

    private ListCommand(Duke duke, String input) {
        super(duke, input);
    }

    public void execute() {
        duke.say(duke.getTasks().toString());
    }

}
