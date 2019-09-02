package command;

import exception.DukeException;
import exception.EmptyToDoDescriptionException;
import run.Storage;
import run.TaskList;
import run.Ui;
import task.ToDo;

public class ToDoCommand extends AddCommand {
    String rawString;

    public ToDoCommand(String rawString) throws DukeException {
        if(rawString.equals("todo") || rawString.equals("todo ")) {
            throw new EmptyToDoDescriptionException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        this.rawString = rawString;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        super.tasks = tasks;
        super.ui = ui;
        super.storage = storage;
        ToDo curr_task = new ToDo(rawString.replaceFirst("todo ", ""));
        tasks.add(curr_task);
        super.addCommandUpdateState();
    }
}