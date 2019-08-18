package duke.commands;

import duke.Command;
import duke.Duke;

public class Bye extends Command {
    public Bye(Duke duke) {
        super(duke);
        name = "bye";
    }
    public void execute(String[] args) {
        duke.say("Bye. Hope to see you again soon!");
        duke.quit();
    }
}
