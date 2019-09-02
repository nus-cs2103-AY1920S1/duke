package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

public class ListCommand extends Command {
    public ListCommand(String[] args) {
        super(args);
    }

    @Override
    public String getName() {
        return "list";
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.size() > 0) {
            ui.say(tasks.toString());
        } else {
            ui.say("No tasks yet.");
        }
    }
}
