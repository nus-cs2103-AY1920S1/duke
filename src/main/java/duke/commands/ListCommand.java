package duke.commands;

import duke.Duke;

public class ListCommand extends Command {

    public ListCommand(Duke duke, String input) {
        super(duke, input);
    }

    public void execute() {
        duke.say(duke.getTasks().toString());
    }

}
