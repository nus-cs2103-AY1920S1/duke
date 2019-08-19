package duke.commands;

import duke.Command;
import duke.Duke;

public class List extends Command {
    public List(Duke duke) {
        super(duke);
        name = "list";
    }
    public void execute(String[] args) {
        duke.say(duke.getTaskList().toString());
    }
}
