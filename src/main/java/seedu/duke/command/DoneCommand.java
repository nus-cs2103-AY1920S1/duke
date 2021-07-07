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
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task taskDone = tasks.markAsDone(taskNumber);
            String s = ui.getSuccessMessage("marked as done", taskDone);
            storage.store(tasks);
            return s;
        } catch (Exception ex) {
            return "Whoops, cannot be saved.";
        }
    }
}
