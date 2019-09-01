package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.Storage;

public class ListCommand extends Command {
    public ListCommand(String[] description) {
        super(description);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.showLine();
        int totalNumber = tasks.numberOfTasks();
        for (int i = 0; i < totalNumber; i++) {
            int index = i + 1;
            System.out.println("     " + index + ". " + tasks.getList().get(i));
        }
    }
}
