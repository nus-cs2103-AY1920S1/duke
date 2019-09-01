package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.Storage;
import duke.exception.DukeException;
import duke.task.ToDo;

public class AddTodoCommand extends Command {

    public AddTodoCommand(String[] description) {
        super(description);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (super.description.length < 2) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }

        String content = description[1];
        ToDo todo = new ToDo(content);

        tasks.addTask(todo);
        storage.save(tasks);

        ui.showTask(todo, tasks, "     Got it. I've added this task: ");
    }
}