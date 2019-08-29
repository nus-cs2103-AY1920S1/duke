package duke.commands;

import duke.*;

public class List extends Command {
    public List(String[] args) {
        super(args);
    }
    @Override
    public String getName() {
        return "list";
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if(tasks.size() > 0) {
            ui.say(tasks.toString());
        }
        else {
            ui.say("No tasks yet.");
        }
    }
}
