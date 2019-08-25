package command;

import exception.DukeException;
import storage.Storage;
import tasks.Task;
import tasks.TaskList;
import ui.Ui;

public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    public boolean isExit(){
        return false;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException{
        if(task == null) throw new DukeException("No task to be added to the list");
        taskList.addTask(task);
        StringBuilder sb = new StringBuilder("Got it. I've added this task:");
        sb.append("\n  " + task);
        sb.append("\nNow you have " + taskList.getSize() + " tasks in the list.");
        ui.print(sb.toString());
    }
}
