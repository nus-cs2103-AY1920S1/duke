package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class DeleteCommand extends Command {
    private int taskNumToDelete;

    public DeleteCommand(int taskNumToDelete){
        super();
        this.taskNumToDelete = taskNumToDelete;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (this.taskNumToDelete <= 0 || this.taskNumToDelete > tasks.getSize()) {
            throw new DukeException("Task Number is out of bounds");
        }
            Task removedTask = tasks.deleteTaskFromList(this.taskNumToDelete - 1);
            ui.messageUser("Noted. I've removed this task:",
                    removedTask.getTaskDescription(),
                    "Now you have " + tasks.getSize()
                            + ((tasks.getSize() <= 1) ? " task" : " tasks") + " in the list.");
    }
}
