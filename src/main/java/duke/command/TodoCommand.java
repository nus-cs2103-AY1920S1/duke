package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TextUi;
import duke.task.TaskList;
import duke.task.Todo;

/**
 * A TodoCommand contains instructions to create a new Todo task.
 */
public class TodoCommand extends AddCommand {

    /**
     * Constructs a new TodoCommand with the given command details.
     *
     * @param details   Command details
     */
    public TodoCommand(String details) {
        super(details);
    }

    /**
     * Creates a new Todo using the details specified in the current
     * TodoCommand, then adds it to the task list. The superclass method
     * execute(TaskList, TextUi, Storage) is called as part of the process.
     *
     * @param tasks             List of tasks
     * @param ui                User interface
     * @param storage           Hard disk storage
     * @throws DukeException    If the superclass method fails, etc.
     */
    @Override
    public void execute(TaskList tasks, TextUi ui, Storage storage) throws
            DukeException {
        tasks.add(new Todo(details));
        super.execute(tasks, ui, storage);
    }
}
