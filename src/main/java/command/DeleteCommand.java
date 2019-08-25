package command;

import exception.DukeException;
import storage.Storage;
import tasks.Task;
import tasks.TaskList;
import ui.Ui;

public class DeleteCommand extends Command {
    private int number;

    public DeleteCommand(int number) {
        this.number = number;
    }

    public boolean isExit() {
        return false;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        StringBuilder sb = new StringBuilder("Noted. I've removed this task:");
        if (number > taskList.getSize()) throw new DukeException("It's an invalid task");
        Task deletedTask = taskList.deleteTask(number);
        sb.append("\n" + deletedTask);
        ui.print(sb.toString());
    }
}
