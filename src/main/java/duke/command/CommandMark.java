package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.Storage;
import duke.DukeException;

import duke.task.Task;

public class CommandMark extends Command {
    private int taskNumber;

    public CommandMark(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task taskToMark;

        try {
            taskToMark = tasks.get(taskNumber);
            taskToMark.markDone();
        } catch (Exception e) {
            throw new DukeException("Invalid task number.");
        }

        storage.save(tasks);

        ui.printTaskMarkedDone(taskToMark);
    }
}
