package duke.command;

import duke.command.Command;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class DeleteCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (ui.getRemainingWords().isEmpty()) {
            throw new DukeException("☹OOPS!!! Wrong format");
        }
        int position = Integer.parseInt(ui.getRemainingWords().trim());
        System.out.println("Noted. I've removed this task.");
        System.out.println(tasks.getTaskArrayList().get(position-1));
        tasks.delete(position-1);
        storage.writeData();
        System.out.println("Now you have " + tasks.getTaskArrayList().size() + " tasks in the list");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
