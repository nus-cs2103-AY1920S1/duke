package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;
import duke.Storage;
import duke.exception.DukeException;
import duke.task.ToDo;

import static duke.ui.Message.ADD_TASK_MESSAGE;

public class AddTodoCommand extends Command {

    public AddTodoCommand(String[] description) {
        super(description);
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        assert super.description != null;
        if (super.description.length < 2) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }

        String content = description[1];
        ToDo todo = new ToDo(content);

        tasks.addTask(todo);
        storage.save(tasks);

        return ui.showTask(todo, tasks, ADD_TASK_MESSAGE);
    }
}