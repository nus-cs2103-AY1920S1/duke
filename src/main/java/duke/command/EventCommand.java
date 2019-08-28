package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TextUi;
import duke.task.Event;
import duke.task.TaskList;

/**
 * An EventCommand contains instructions to create an event task.
 */
public class EventCommand extends AddCommand {

    /**
     * Constructs a new EventCommand with the given command details.
     *
     * @param details   Command details
     */
    public EventCommand(String details) {
        super(details);
    }

    /**
     * Creates a new Event based on the details contained in the current
     * EventCommand, then adds the event to the task list. The superclass
     * method execute(TaskList, TextUi, Storage) is called as part of the
     * process.
     *
     * @param tasks             List of tasks
     * @param ui                User interface
     * @param storage           Hard disk storage
     * @throws DukeException    If superclass method fails, etc.
     */
    @Override
    public void execute(TaskList tasks, TextUi ui, Storage storage) throws
            DukeException {
        String[] taskDetails = details.split(" /at ");
        tasks.add(new Event(taskDetails[0], taskDetails[1]));
        super.execute(tasks, ui, storage);
    }
}
