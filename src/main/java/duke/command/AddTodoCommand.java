package duke.command;

import duke.Duke;
import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.ui.Ui;
import duke.task.Todo;

public class AddTodoCommand extends TextBasedCommand {
    public static final String COMMAND = "todo";
    public static final String LINE_DESCRIPTOR = "description";

    /**
     * Create todo command (extended from text based command).
     *
     * @param line line of user input
     * @throws DukeException generic error with message
     */
    public AddTodoCommand(String line) throws DukeException {
        super(line, COMMAND, LINE_DESCRIPTOR);
    }

    @Override
    public void execute(Duke duke, TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.insertNewTask(ui, new Todo(remainingLine));
        storage.saveTaskListToFile(taskList);
    }
}
