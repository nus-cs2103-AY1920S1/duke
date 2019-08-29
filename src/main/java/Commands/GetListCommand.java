package Commands;

import Functionality.*;

public class GetListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printList(tasks.getTaskList());
    }
}
