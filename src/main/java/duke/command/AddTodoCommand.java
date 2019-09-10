package duke.command;

import duke.dukeexception.DukeException;
import duke.task.Task;
import duke.task.Todo;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.UiText;

public class AddTodoCommand extends Command {

    public AddTodoCommand(String[] msg) {
        super(msg);
    }
    /**
     * Check is the user input format correct.
     * If is correct then create a Todo task and write into the file.
     * @param list  TaskList
     * @param ui    UiText
     * @param storage   Storage
     * @throws DukeException empty description
     */

    @Override
    public String execute(TaskList list, UiText ui, Storage storage) throws DukeException {
        if (super.command.length != 2) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        }
        assert super.command.length == 2 && !super.command[1].trim().equals("");
        Task task = new Todo(super.command[1]);
        list.addToList(task);
        return UiText.addedMsg(task);
    }

}
