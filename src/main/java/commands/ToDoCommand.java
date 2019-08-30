package commands;

import logic.*;
import task.Task;
import task.ToDo;

public class ToDoCommand extends Command {
    private String args;

    public ToDoCommand(String args) {
        this.args = args;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (args.trim().isEmpty()) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty");
        }

        Task task = new ToDo(false, args); //args is the description string
        tasks.addTask(task);
        storage.updateFile(tasks);
    }
}
