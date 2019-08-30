package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Event;
import duke.task.TaskList;
import duke.ui.Ui;

import java.time.LocalDateTime;

public class EventCommand extends AddCommandWithTime {
    public EventCommand(final String description, final LocalDateTime time) {
        super(description, time);
    }

    @Override
    protected void check(final TaskList tasks) throws DukeException {
        super.check(tasks);
        if (this.time == null) {
            throw new DukeException("Event time cannot be empty");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        check(tasks);
        addTask(new Event(this.description, this.time), tasks, ui, storage);
    }
}
