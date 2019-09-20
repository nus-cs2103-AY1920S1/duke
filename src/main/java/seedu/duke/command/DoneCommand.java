package seedu.duke.command;

import java.io.IOException;

import seedu.duke.DukeException;
import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.task.Task;
import seedu.duke.task.TaskList;

public class DoneCommand extends Command {
    int taskNumber;

    public DoneCommand(int i) {
        taskNumber = i;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task taskDone = tasks.markAsDone(taskNumber);
            ui.showSuccessMessage("marked as done", taskDone);
            storage.store(tasks);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.err.println("Whoops, cannot be saved.");
        }
    }
}
