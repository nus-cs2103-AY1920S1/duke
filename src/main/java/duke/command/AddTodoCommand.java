package duke.command;

import duke.*;
import duke.task.Todo;

public class AddTodoCommand extends TextBasedCommand {
    public static final String COMMAND = "todo";
    public AddTodoCommand(String line) throws DukeException {
        super(line, COMMAND);
    }

    @Override
    public void execute(Duke duke, TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.insertNewTask(ui, new Todo(remainingLine));
        storage.saveTaskListToFile(taskList);
    }
}
