package commands;

import logic.*;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.bye();
        System.exit(0);
    }
}
