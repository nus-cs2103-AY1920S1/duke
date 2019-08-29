package command;

import main.DukeException;
import main.Storage;
import main.TaskList;
import main.Ui;
import task.Task;

public class DeleteCommand extends Command {

    private int taskID;

    public DeleteCommand(int taskID) {
        super();
        this.taskID = taskID;
    }
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (this.taskID > tasks.size()) {
            throw new DukeException("Invalid ask ID. Please Enter a task ID between 1 and " + tasks.size());
        }
        Task removedTask = tasks.deleteTask(taskID);
        ui.dukeEcho("Noted. I've removed this task:", removedTask.toString(),
                "Now you have " + tasks.size() + " tasks in the list.");
        storage.save(tasks);
    }

    @Override
    public String toString(){
        return "Delete " + this.taskID;
    }
}
