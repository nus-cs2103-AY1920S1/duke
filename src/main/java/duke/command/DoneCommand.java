package duke.command;

import duke.app.Duke;
import duke.command.Command;
import duke.exception.DukeException;
import duke.task.Task;
import duke.tasklist.Tasklist;
import duke.ui.Ui;

public class DoneCommand extends Command {
    private String index;

    public DoneCommand(String index) {
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

            Task task = list.get(entry);
            task.markAsDone();

            ui.showDone(task.toString());

        } catch (IllegalArgumentException e) {
            throw new DukeException("You need to specify the task you want to complete!");
        } // End of try-catch.

    } // End of method.
}
