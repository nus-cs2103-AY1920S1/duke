package duke.command;

import duke.exception.DukeException;
import duke.task.Task;
import duke.tasklist.Tasklist;
import duke.ui.Ui;

public class DeleteCommand extends Command {
    private String index;

    public DeleteCommand(String index) {
        this.index = index;
    }

    @Override
    public void execute(Tasklist list, Ui ui) throws DukeException {
        try {
            String inputEntry = index.trim();
            int entry = Integer.parseInt(inputEntry) - 1;

            if (inputEntry.isEmpty() || entry < 0) {
                throw new IllegalArgumentException();
            }

            Task removedTask = list.remove(entry);

            ui.showDeleted(removedTask.toString(), list.size());

        } catch (IllegalArgumentException e) {
            throw new DukeException("You need to specify the task you want to delete!");
        } // End of try-catch.
    } // End method
}
