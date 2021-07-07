package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;
import duke.exception.EmptyDescriptionException;
import duke.task.Task;
import duke.task.ToDo;
import java.io.IOException;

public class AddTodoCommand extends AddCommand {
    private String description;

    public AddTodoCommand(String description) {
        this.description = description;
    }

    /**
     * Adds a to do task into task list and saves the task list into
     * the external file.
     *
     * @param tasks Current {@link TaskList}
     * @param ui {@link Ui} being used
     * @param storage Current {@link Storage} in use
     * @throws IOException Error with accessing file or {@link java.io.FileWriter}
     * @throws EmptyDescriptionException Error when no description is provided
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage)
            throws IOException, EmptyDescriptionException {
        if (description.equals("")) {
            throw new EmptyDescriptionException(
                    "☹ OOPS!!! The description of a todo cannot be empty.");
        }
        Task task = new ToDo(description);
        tasks.add(task);
        ui.showTaskAddedMessage(task, tasks);
        storage.saveTaskList(tasks);

    }

    /**
     * Tests Exit Command.
     *
     * @return Return true if {@link Command} is {@link ExitCommand}
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
