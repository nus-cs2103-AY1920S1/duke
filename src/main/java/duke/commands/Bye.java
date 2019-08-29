package duke.commands;

import duke.*;

public class Bye extends Command {
    public Bye(String[] args) {
        super(args);
    }
    @Override
    public String getName() {
        return "bye";
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.say("Bye. Hope to see you again soon!");
    }
}
