package Commands;

import Functionality.*;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.bye();
        System.exit(0);
    }
}
